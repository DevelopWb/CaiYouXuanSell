package com.juntai.project.sell.mall.share;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.live_moudle.bean.LiveListBean;
import com.juntai.disabled.basecomponent.utils.FileCacheUtils;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.disabled.basecomponent.utils.ScreenUtils;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseRecyclerviewActivity;
import com.juntai.project.sell.mall.beans.PicTextBean;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.juntai.project.sell.mall.home.HomePagePresent;
import com.juntai.project.sell.mall.home.PicTextAdapter;
import com.juntai.project.sell.mall.utils.ToolShare;
import com.king.zxing.util.CodeUtils;

import java.util.ArrayList;
import java.util.List;

import static android.graphics.BitmapFactory.decodeFile;

/**
 * @aouther tobato
 * @description 描述 分享
 * @date 2022/5/21 10:41
 */
public class ShareActivity extends BaseRecyclerviewActivity<HomePagePresent> implements View.OnClickListener, HomePageContract.IHomePageView {

    private ImageView mShareLiveShopIv;
    /**
     * dfadsfa
     */
    private TextView mShopNameTv;
    private TextView mShopDesTv;
    private ConstraintLayout mShareLiveCl;
    private ImageView mShareCoverIv;
    /**
     * 描述
     */
    private TextView mShareDesTv;
    private ImageView mQrcodeIv;
    /**
     * 取消
     */
    private TextView mCancelShareTv;
    private String sharePic;
    private String shareDes;
    private String shareUrl;
    private LinearLayout mRootLl;
    private LiveListBean.DataBean.ListBean bean;

    /**
     * @param mContext
     * @param type     0是店铺 1是商品 2是直播
     * @param pic
     * @param des
     */
    public static void startShareActivity(Context mContext, int type, String pic, String des, String shareUrl) {
        Intent intent = new Intent(mContext, ShareActivity.class);
        intent.putExtra(BASE_ID, type)
                .putExtra(BASE_STRING, pic)
                .putExtra(BASE_STRING3, shareUrl)
                .putExtra(BASE_STRING2, des);
        mContext.startActivity(intent);

    }
    /**
     * @param mContext
     * @param type     0是店铺 1是商品 2是直播
     */
    public static void startShareActivity(Context mContext, int type, LiveListBean.DataBean.ListBean bean) {
        Intent intent = new Intent(mContext, ShareActivity.class);
        intent.putExtra(BASE_ID, type)
                .putExtra(BASE_PARCELABLE, bean);
        mContext.startActivity(intent);

    }

    @Override
    protected HomePagePresent createPresenter() {
        return new HomePagePresent();
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_share;
    }

    @Override
    public void initView() {
        super.initView();
        initToolbarAndStatusBar(false);
        mImmersionBar.reset().statusBarColor(R.color.gray_lighter)
                .statusBarDarkFont(true)
                .init();
        mShareLiveShopIv = (ImageView) findViewById(R.id.share_live_shop_iv);
        mShopNameTv = (TextView) findViewById(R.id.shop_name_tv);
        mShopDesTv = (TextView) findViewById(R.id.shop_des_tv);
        mShopDesTv.setVisibility(View.GONE);
        mShareLiveCl = (ConstraintLayout) findViewById(R.id.share_live_cl);
        mRootLl = (LinearLayout) findViewById(R.id.share_root_ll);
        mShareCoverIv = (ImageView) findViewById(R.id.share_cover_iv);
        mShareDesTv = (TextView) findViewById(R.id.share_des_tv);
        mQrcodeIv = (ImageView) findViewById(R.id.qrcode_iv);
        mCancelShareTv = (TextView) findViewById(R.id.cancel_share_tv);
        mCancelShareTv.setOnClickListener(this);
        baseQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                PicTextBean picTextBean = (PicTextBean) adapter.getItem(position);
                switch (picTextBean.getTextName()) {
                    case HomePageContract.SHARE_WEIXIN:
                        // : 2022/5/21 分享到微信
                        ToolShare.share(mContext, ToolShare.SHARE_WECHAT, shareDes, shareDes, sharePic, shareUrl);
                        break;
                    case HomePageContract.SHARE_WEIXIN_FRIENDS:
                        // : 2022/5/21 分享到微信 朋友圈

                        ToolShare.share(mContext, ToolShare.SHARE_WECHAT_CIRCLE, shareDes, shareDes, sharePic, shareUrl);

                        break;
                    case HomePageContract.SHARE_QQ:
                        // : 2022/5/21 分享到QQ
                        ToolShare.share(mContext, ToolShare.SHARE_QQ, shareDes, shareDes, sharePic, shareUrl);

                        break;
                    case HomePageContract.SHARE_QQ_SPACE:
                        // TODO: 2022/5/21 分享到QQ空间
                        break;
                    case HomePageContract.SHARE_SAVE_PIC:
                        // : 2022/5/21 保存到本地
                        FileCacheUtils.saveBitmapByView(ShareActivity.this, mContext, mRootLl, shareDes + ".png");
                        break;
                    case HomePageContract.SHARE_COPY_LINK:
                        copy(shareUrl);

                        break;
                    default:
                        ToastUtils.toast(mContext, "暂未开放");
                        break;
                }
            }
        });
    }

    @Override
    protected View getAdapterHeadView() {
        return null;
    }

    @Override
    protected View getAdapterFootView() {
        return null;
    }


    @Override
    public void initData() {
        int type = getIntent().getIntExtra(BASE_ID, 0);
        if (2==type) {
            bean = getIntent().getParcelableExtra(BASE_PARCELABLE);
            sharePic = bean.getCoverImg();
            shareDes = bean.getTitle();
            shareUrl = bean.getShareLiveUrl();
            ImageLoadUtil.loadHeadCirclePic(mContext,bean.getHeadPortrait(),mShareLiveShopIv);
            mShopNameTv.setText(bean.getShopName());
        }else {
            sharePic = getIntent().getStringExtra(BASE_STRING);
            shareDes = getIntent().getStringExtra(BASE_STRING2);
            shareUrl = getIntent().getStringExtra(BASE_STRING3);
        }

        ImageLoadUtil.loadImage(mContext, sharePic, mShareCoverIv);
        mQrcodeIv.setImageBitmap(CodeUtils.createQRCode(shareUrl, ScreenUtils.getInstance(mContext).getScreenWidth(), decodeFile(FileCacheUtils.getAppImagePath(true) + getSavedFileName(sharePic))));
        mShareDesTv.setText(shareDes);
        if (2 == type) {
            //分享直播
            mShareLiveCl.setVisibility(View.VISIBLE);
        } else {
            mShareLiveCl.setVisibility(View.GONE);

        }
        baseQuickAdapter.setNewData(getShareMenus());
    }

    @Override
    protected LinearLayoutManager getBaseAdapterManager() {
        return new GridLayoutManager(mContext, 5);
    }

    @Override
    protected void getRvAdapterData() {

    }

    @Override
    protected boolean enableRefresh() {
        return false;
    }

    @Override
    protected boolean enableLoadMore() {
        return false;
    }

    @Override
    protected BaseQuickAdapter getBaseQuickAdapter() {
        return new PicTextAdapter(R.layout.share_item);
    }

    public List<PicTextBean> getShareMenus() {
// : 2022/5/21 这个地方需要添加功能图片
        List<PicTextBean> arrays = new ArrayList<>();
        arrays.add(new PicTextBean(R.mipmap.weixin_icon, HomePageContract.SHARE_WEIXIN));
        arrays.add(new PicTextBean(R.mipmap.weixin_friends, HomePageContract.SHARE_WEIXIN_FRIENDS));
        arrays.add(new PicTextBean(R.mipmap.qq_icon, HomePageContract.SHARE_QQ));
//        arrays.add(new PicTextBean(R.mipmap.share_qq_space, HomePageContract.SHARE_QQ_SPACE));
        arrays.add(new PicTextBean(R.mipmap.share_save_img, HomePageContract.SHARE_SAVE_PIC));
        arrays.add(new PicTextBean(R.mipmap.share_copy_link, HomePageContract.SHARE_COPY_LINK));
        return arrays;
    }

    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.cancel_share_tv:
                finish();
                break;
        }
    }
}
