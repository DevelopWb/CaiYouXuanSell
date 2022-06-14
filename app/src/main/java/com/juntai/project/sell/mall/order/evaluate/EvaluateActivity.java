package com.juntai.project.sell.mall.order.evaluate;

import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.chat.MainContract;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.project.sell.mall.AppHttpPathMall;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.selectPics.BaseSelectPhotosFragment;
import com.juntai.project.sell.mall.base.selectPics.BaseSelectPicsAndVedioActivity;
import com.juntai.project.sell.mall.base.selectPics.SelectPhotosFragment;
import com.juntai.project.sell.mall.beans.order.OrderDetailBean;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.juntai.project.sell.mall.order.OrderPresent;
import com.juntai.project.sell.mall.order.refund.RefundCommodityAdapter;

import java.util.List;

import okhttp3.FormBody;

/**
 * @Author: tobato
 * @Description: 作用描述  开始评价
 * @CreateDate: 2022/5/15 17:16
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/15 17:16
 */
public class EvaluateActivity extends BaseSelectPicsAndVedioActivity<OrderPresent> implements HomePageContract.IHomePageView {
    private FormBody.Builder builder;

    /**
     * 店铺名称
     */
    private TextView mEvaluateShopNameTv;
    private RatingBar mCommodityEvaluateRatingBar;
    private RatingBar mShopEvaluateRatingBar;
    OrderDetailBean orderDetailBean;

    OrderDetailBean.CommodityListBean commodityBean;
    /**
     * 从多个角度评价宝贝，帮助更多更想买的人吧
     */
    private EditText mEvaluateContentEt;

    @Override
    public int getLayoutView() {
        return R.layout.mall_evaluate_activity;
    }

    @Override
    protected LinearLayoutManager getBaseAdapterManager() {
        return null;
    }

    @Override
    protected void recordVedio() {
        mPresenter.recordVideo(this);
    }

    @Override
    protected BaseSelectPhotosFragment getFragment() {
        return SelectPhotosFragment.newInstance("").setMaxCount(3);
    }

    public void initView() {
        super.initView();
        setTitleName("发表评价");
        orderDetailBean = getIntent().getParcelableExtra(BASE_PARCELABLE);
        commodityBean = orderDetailBean.getCommodityList().get(0);
        getTitleRightTv().setText("发布");
        getTitleRightTv().setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(getTextViewValue(mEvaluateContentEt))) {
                    ToastUtils.toast(mContext, "请输入评价的内容");
                    return;
                }
                builder = getBaseBuilder()
                        .add("orderId", String.valueOf(commodityBean.getOrderId()))
                        .add("shopId", String.valueOf(commodityBean.getShopId()))
                        .add("commodityId", String.valueOf(commodityBean.getCommodityId()))
                        .add("sku", commodityBean.getCartInfo())
                        .add("shopScore", String.valueOf(mShopEvaluateRatingBar.getRating()))
                        .add("commodityScore", String.valueOf(mCommodityEvaluateRatingBar.getRating()))
                        .add("evaluate", getTextViewValue(mEvaluateContentEt));
                //首先上传图片
                //将图片上传
                List<String> icons = selectPhotosFragment.getPhotosPath();
                if (icons.size() > 0) {
                    mPresenter.uploadFile(icons, MainContract.UPLOAD_IMAGES);
                } else {
                    //没有选择图片
                    if (TextUtils.isEmpty(videoPath)) {
                        //没有选择视频
                        // : 2022/5/17 提交评价
                        mPresenter.startEvaluate(builder.build(), AppHttpPathMall.START_EVALUATE
                        );
                    } else {
                        // : 2022/5/17 上传视频封面
                        mPresenter.uploadFile(MainContract.UPLOAD_VIDEO_COVER, videoScreen);


                    }


                }
            }
        });
        mEvaluateShopNameTv = (TextView) findViewById(R.id.evaluate_shop_name_tv);
        mEvaluateShopNameTv.setText(orderDetailBean.getShopName());
        mCommodityEvaluateRatingBar = (RatingBar) findViewById(R.id.commodity_evaluate_ratingBar);
        mShopEvaluateRatingBar = (RatingBar) findViewById(R.id.shop_evaluate_ratingBar);
        mEvaluateContentEt = (EditText) findViewById(R.id.evaluate_content_et);
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
    protected void getRvAdapterData() {
        if (orderDetailBean == null) {
            finish();
        }
        List<OrderDetailBean.CommodityListBean> commodityListBeans = orderDetailBean.getCommodityList();
        baseQuickAdapter.setNewData(commodityListBeans);
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
        return new RefundCommodityAdapter(R.layout.mall_refund_commodity_item);
    }

    @Override
    protected OrderPresent createPresenter() {
        return new OrderPresent();
    }

    @Override
    public void onVedioPicClick(BaseQuickAdapter adapter, int position) {

    }

    @Override
    public void onPicClick(BaseQuickAdapter adapter, int position) {

    }

    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);

        switch (tag) {
            case MainContract.UPLOAD_VIDEO:
                List<String> videoPaths = (List<String>) o;
                if (videoPaths != null && videoPaths.size() > 0) {
                    builder.add("videoUrl", videoPaths.get(0));
                    mPresenter.startEvaluate(builder.build(), AppHttpPathMall.START_EVALUATE);
                }

                break;
            case MainContract.UPLOAD_VIDEO_COVER:
                //上传封面成功之后上传视频文件
                List<String> videoCoverPaths = (List<String>) o;
                if (videoCoverPaths != null && videoCoverPaths.size() > 0) {
                    builder.add("videoCover", videoCoverPaths.get(0));
                }
                mPresenter.uploadFile(MainContract.UPLOAD_VIDEO, videoPath);


                break;
            case MainContract.UPLOAD_IMAGES:
                //发送图片
                List<String> picPaths = (List<String>) o;
                builder.add("imgUrl", TextUtils.join(",",picPaths));
                if (TextUtils.isEmpty(videoPath)) {
                    if (picPaths != null && picPaths.size() > 0) {
                        // 调用评价的接口
                        mPresenter.startEvaluate(builder.build(), AppHttpPathMall.START_EVALUATE);
                    }
                } else {
                    // : 2022/5/17 上传视频
                    mPresenter.uploadFile(MainContract.UPLOAD_VIDEO_COVER, videoScreen);
                }


                break;
            case AppHttpPathMall.START_EVALUATE:
                startToAllOrderActivity(1, 0);
                break;
            default:
                break;
        }
    }
}
