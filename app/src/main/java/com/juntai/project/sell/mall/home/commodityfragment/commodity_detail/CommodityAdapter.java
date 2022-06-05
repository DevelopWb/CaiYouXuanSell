package com.juntai.project.sell.mall.home.commodityfragment.commodity_detail;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.chat.util.MultipleItem;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.displayPicVideo.PicVideoDisplayActivity;
import com.juntai.project.sell.mall.base.web.BaseWebviewFragment;
import com.juntai.project.sell.mall.beans.CommodityDetailBean;
import com.juntai.project.sell.mall.beans.CommodityEvaluationBean;
import com.juntai.project.sell.mall.home.commodityfragment.commodity_detail.evaluation.EvaluationAdapter;
import com.juntai.project.sell.mall.utils.bannerImageLoader.BannerObject;
import com.juntai.project.sell.mall.utils.bannerImageLoader.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述  商品的适配器  包含基本信息  评价  详情
 * @CreateDate: 2022/5/3 16:30
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/3 16:30
 */
public class CommodityAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {

    private FragmentManager fragmentManager;
    private GlideImageLoader imageLoader;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public CommodityAdapter(FragmentManager fragmentManager,List<MultipleItem> data) {
        super(data);
        addItemType(MultipleItem.ITEM_COMMODITY_BASE_INFO, R.layout.shop_commodity_base_info);
        addItemType(MultipleItem.ITEM_COMMODITY_EVALUTA, R.layout.shop_commodity_evaluta_item);
        addItemType(MultipleItem.ITEM_COMMODITY_DETAIL, R.layout.shop_commodity_detail_item);
        this.fragmentManager = fragmentManager;
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        switch (item.getItemType()) {
            case MultipleItem.ITEM_COMMODITY_BASE_INFO:
                CommodityDetailBean.DataBean dataBean = (CommodityDetailBean.DataBean) item.getObject();
                List<BannerObject> bannerObjects = new ArrayList<>();
                Banner banner = helper.getView(R.id.commodity_banner);
                banner.isAutoPlay(false);
                banner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        // : 2022/5/4 查看图片大图

                        PicVideoDisplayActivity.startPicVideoPlayActivity(mContext,bannerObjects,position);
                    }
                });
                banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
                imageLoader = new GlideImageLoader().setOnFullScreenCallBack(new GlideImageLoader.OnFullScreenListener() {
                    @Override
                    public void onFullScreen() {

                    }
                });
                if (!TextUtils.isEmpty(dataBean.getVideoUrl())) {
                    bannerObjects.add(new BannerObject(BannerObject.BANNER_TYPE_VIDEO, new BannerObject.VideoBean(dataBean.getVideoUrl())));
                }
                if (!TextUtils.isEmpty(dataBean.getCoverImg())) {
                    bannerObjects.add(new BannerObject(BannerObject.BANNER_TYPE_IMAGE, dataBean.getCoverImg()));
                }

                List<CommodityDetailBean.DataBean.ImagesBean> imagesBeans = dataBean.getImages();
                if (imagesBeans != null && imagesBeans.size() > 0) {
                    for (CommodityDetailBean.DataBean.ImagesBean imagesBean : imagesBeans) {
                        bannerObjects.add(new BannerObject(BannerObject.BANNER_TYPE_IMAGE, imagesBean.getImgUrl()));
                    }
                }
                banner.setImages(bannerObjects).setImageLoader(imageLoader).start();
                helper.setText(R.id.commodity_des_tv, dataBean.getName());
                helper.setText(R.id.commodity_price_tv, String.format("￥%s", dataBean.getPrice()));
                helper.setText(R.id.commodity_sales_tv, String.format("销量:%s", dataBean.getSales()));


                break;
            case MultipleItem.ITEM_COMMODITY_EVALUTA:
                helper.addOnClickListener(R.id.all_evaluation_tv);
                List<CommodityEvaluationBean.DataBean> arrays = (List<CommodityEvaluationBean.DataBean>) item.getObject();
                helper.setText(R.id.evaluation_title_tv, String.format("宝贝评价(%s)", arrays.size()));
                RecyclerView evaluationRv = helper.getView(R.id.evaluation_rv);
                LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                evaluationRv.setLayoutManager(manager);
                EvaluationAdapter evaluationAdapter = new EvaluationAdapter(R.layout.shop_commodity_evaluta_child_item);
                evaluationRv.setAdapter(evaluationAdapter);
                if (arrays.size() > 2) {
                    // TODO: 2022/5/4 暂定只显示2行评价
                    arrays = arrays.subList(0, 2);
                }
                evaluationAdapter.setNewData(arrays);
                break;

            case MultipleItem.ITEM_COMMODITY_DETAIL:
                // : 2022/5/22 商品详情
                String url = (String) item.getObject();
                BaseWebviewFragment baseWebviewFragment = (BaseWebviewFragment) fragmentManager.findFragmentById(R.id.base_webview_fg);
                baseWebviewFragment.setWebData(url);


                break;

            default:
                break;
        }
    }

    public void releaseVideo() {
        if (imageLoader != null) {
            imageLoader.release();
        }
    }
}
