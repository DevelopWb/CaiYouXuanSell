package com.juntai.project.sell.mall.home.shop;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.juntai.disabled.basecomponent.base.BaseResult;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseAppActivity;
import com.juntai.project.sell.mall.base.displayPicVideo.PicVideoDisplayActivity;
import com.juntai.project.sell.mall.beans.shop.ShopDetailBean;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.juntai.project.sell.mall.home.QRScanActivity;
import com.juntai.project.sell.mall.home.shop.ijkplayer.PlayerLiveActivity;
import com.juntai.project.sell.mall.share.ShareActivity;
import com.juntai.project.sell.mall.utils.bannerImageLoader.BannerObject;
import com.juntai.project.sell.mall.utils.bannerImageLoader.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @aouther tobato
 * @description 描述  店铺首页
 * @date 2022/5/8 8:50
 */
public class ShopActivity extends BaseAppActivity<ShopPresent> implements HomePageContract.IHomePageView, View.OnClickListener {

    private ImageView mShopBackIv;
    private ImageView mShopCollectIv;
    private ImageView mShopShareIv;
    private ConstraintLayout mTopCl;
    private ImageView mShopOwnerHeadIv;
    /**
     * 店铺名称
     */
    private TextView mShopNameTv;
    /**
     * 开店时间
     */
    private TextView mShopCreatTimeTv, mScoreTv;
    /**
     * 描述
     */
    private TextView mShopDesTv;
    private Banner mShopBanner;
    public int shopId;
    private int collectId = 0;

    private ShopDetailBean.DataBean shopBean;
    private List<BannerObject> bannerPics;
    private GlideImageLoader imageLoader;

    @Override
    protected ShopPresent createPresenter() {
        return new ShopPresent();
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_shop;
    }

    @Override
    public void initView() {
        shopId = getIntent().getIntExtra(BASE_ID, 0);
        initToolbarAndStatusBar(false);
        mShopBackIv = (ImageView) findViewById(R.id.shop_back_iv);
        mShopBackIv.setOnClickListener(this);
        mShopCollectIv = (ImageView) findViewById(R.id.shop_collect_iv);
        mShopCollectIv.setOnClickListener(this);
        findViewById(R.id.scan_iv).setOnClickListener(this);
        findViewById(R.id.search_ll).setOnClickListener(this);
        mShopShareIv = (ImageView) findViewById(R.id.shop_share_iv);
        mShopShareIv.setOnClickListener(this);
        mTopCl = (ConstraintLayout) findViewById(R.id.top_cl);
        mShopOwnerHeadIv = (ImageView) findViewById(R.id.shop_owner_head_iv);
        mShopNameTv = (TextView) findViewById(R.id.shop_name_tv);
        mShopCreatTimeTv = (TextView) findViewById(R.id.shop_creat_time_tv);
        mScoreTv = (TextView) findViewById(R.id.shop_score_tv);
        mShopDesTv = (TextView) findViewById(R.id.shop_des_tv);
        mShopBanner = (Banner) findViewById(R.id.shop_banner);
    }

    private void initBanner(ShopDetailBean.DataBean shopBean) {
        collectId = shopBean.getIsCollect();
        List<BannerObject> bannerObjects = new ArrayList<>();
        bannerPics = new ArrayList<>();

        mShopBanner.isAutoPlay(false);
        mShopBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                // : 2022/5/4 查看图片大图
                BannerObject bannerObject = bannerObjects.get(position);
                switch (bannerObject.getEventKey()) {
                    case BannerObject.BANNER_TYPE_IMAGE:
                    case BannerObject.BANNER_TYPE_VIDEO:
                        // : 2022/5/21 展示图片大图
                        PicVideoDisplayActivity.startPicVideoPlayActivity(mContext, bannerPics, bannerPics.size() == bannerObjects.size() ? position : position - 1);
                        break;
                    case BannerObject.BANNER_TYPE_RTMP:
                        BannerObject.StreamBean streamBean = bannerObject.getStreamBean();
                        PlayerLiveActivity.startPlayerLiveActivity(mContext, streamBean.getCameraNum(), streamBean.getCameraCover(), streamBean.getRtmpUrl());

                        break;
                    default:
                        break;
                }

            }
        });
        imageLoader = new GlideImageLoader().setOnFullScreenCallBack(new GlideImageLoader.OnFullScreenListener() {
            @Override
            public void onFullScreen() {

            }
        });

        mShopBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (0 != i) {
                    // : 2022/5/22 如果视频在播放 释放资源
                    imageLoader.pause();
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        if (!TextUtils.isEmpty(shopBean.getCameraCover()) && !TextUtils.isEmpty(shopBean.getCameraNumber())) {
            bannerObjects.add(new BannerObject(BannerObject.BANNER_TYPE_RTMP, new BannerObject.StreamBean(shopBean.getCameraNumber(), shopBean.getCameraCover(), shopBean.getCameraUrl())));
        }

        String bannerPic = shopBean.getShopImg();
        if (!TextUtils.isEmpty(bannerPic)) {
            if (bannerPic.contains(",")) {
                String[] pics = bannerPic.split(",");
                for (String pic : pics) {
                    bannerObjects.add(new BannerObject(BannerObject.BANNER_TYPE_IMAGE, pic));
                    bannerPics.add(new BannerObject(BannerObject.BANNER_TYPE_IMAGE, pic));
                }
            } else {
                bannerPics.add(new BannerObject(BannerObject.BANNER_TYPE_IMAGE, bannerPic));
                bannerObjects.add(new BannerObject(BannerObject.BANNER_TYPE_IMAGE, bannerPic));

            }
        }
        mShopBanner.setImages(bannerObjects).setImageLoader(imageLoader).start();
    }

    /**
     * 配置基本数据
     *
     * @param shopBean
     */
    public void initOwnerBaseInfo(ShopDetailBean.DataBean shopBean) {
        this.shopBean = shopBean;
        ImageLoadUtil.loadSquareImageHasCorner(mContext, shopBean.getHeadPortrait(), mShopOwnerHeadIv);
        mShopNameTv.setText(shopBean.getName());
        mShopCreatTimeTv.setText("开店时间:" + shopBean.getCreateTime());
        mScoreTv.setText("店铺得分:" + shopBean.getShopFraction());
        mShopDesTv.setText(shopBean.getIntroduction());
        mShopCollectIv.setImageResource(shopBean.getIsCollect() > 0 ? R.mipmap.collected_icon : R.mipmap.un_collect_icon);
        initBanner(shopBean);

    }

    @Override
    public void initData() {

    }


    @Override
    public void onSuccess(String tag, Object o) {
        switch (tag) {
            case HomePageContract.UN_COLLECT_COMMODITY_SHOP:
                collectId = 0;
                mShopCollectIv.setImageResource(R.mipmap.un_collect_icon);

                break;
            case HomePageContract.COLLECT_COMMODITY_SHOP:
                BaseResult baseResult = (BaseResult) o;
                collectId = Integer.parseInt(baseResult.getMessage());
                mShopCollectIv.setImageResource(R.mipmap.collected_icon);

                break;
            default:
                break;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.shop_back_iv:
                finish();
                break;
            case R.id.search_ll:
                // : 2022/5/31 店铺内部搜索商品
                startActivity(new Intent(mContext, SearchShopCommodityActivity.class)
                .putExtra(BASE_ID,shopBean.getId())

                );


                break;
            case R.id.scan_iv:
                startActivity(new Intent(mContext, QRScanActivity.class));
                break;
            case R.id.shop_collect_iv:
                if (collectId > 0) {
                    mPresenter.collectShop(getBaseBuilder()
                            .add("isCollect", "1")
                            .add("id", String.valueOf(collectId))
                            .add("shopId", String.valueOf(shopId)).build(), HomePageContract.UN_COLLECT_COMMODITY_SHOP
                    );

                } else {
                    //收藏
                    mPresenter.collectShop(getBaseBuilder()
                            .add("isCollect", "0")
                            .add("shopId", String.valueOf(shopId)).build(), HomePageContract.COLLECT_COMMODITY_SHOP
                    );
                }
                break;
            case R.id.shop_share_iv:
                // : 2022/5/8 店铺分享
                if (shopBean == null) {
                    ToastUtils.toast(mContext, "无法获取店铺信息 不能分享");
                    return;
                }
                ShareActivity.startShareActivity(mContext, 0, bannerPics.isEmpty() ? "" : (String) bannerPics.get(0).getPicPath(), shopBean.getIntroduction(), shopBean.getShareUrl());

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (imageLoader != null) {
            imageLoader.release();

        }
    }
}
