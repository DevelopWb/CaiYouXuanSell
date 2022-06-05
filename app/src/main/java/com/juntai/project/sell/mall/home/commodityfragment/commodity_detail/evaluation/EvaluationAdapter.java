package com.juntai.project.sell.mall.home.commodityfragment.commodity_detail.evaluation;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.displayPicVideo.PicVideoDisplayActivity;
import com.juntai.project.sell.mall.beans.CommodityEvaluationBean;
import com.juntai.project.sell.mall.utils.bannerImageLoader.BannerObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述  商品评价的适配器
 * @CreateDate: 2022/5/3 16:30
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/3 16:30
 */
public class EvaluationAdapter extends BaseQuickAdapter<CommodityEvaluationBean.DataBean, BaseViewHolder> {

    public EvaluationAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommodityEvaluationBean.DataBean item) {
        ImageLoadUtil.loadHeadCirclePic(mContext, item.getHeadPortrait(), helper.getView(R.id.evaluator_iv));
        helper.setText(R.id.evaluator_name_tv, item.getNickname());
        helper.setText(R.id.evaluate_time_tv, item.getCreateTime());
        helper.setText(R.id.evaluate_content_tv, item.getEvaluate());
        List<BannerObject> picVideos = new ArrayList<>();
        if (!TextUtils.isEmpty(item.getVideoUrl())) {
            picVideos.add(new BannerObject(BannerObject.BANNER_TYPE_VIDEO,new BannerObject.VideoBean(item.getVideoUrl(),item.getVideoCover())));
        }
        String imageUrls = item.getImgUrl();
        if (!TextUtils.isEmpty(imageUrls)) {
            if (imageUrls.contains(",")) {
                String[] urls = imageUrls.split(",");
                for (String url : urls) {
                    picVideos.add(new BannerObject(BannerObject.BANNER_TYPE_IMAGE,url));
                }
            } else {
                picVideos.add(new BannerObject(BannerObject.BANNER_TYPE_IMAGE,imageUrls));
            }
        }

        RecyclerView picVideoRv = helper.getView(R.id.evaluate_pic_video_rv);
        GridLayoutManager manager = new GridLayoutManager(mContext, 4);
        EvaluatePicVideoAdapter picVideoAdapter = new EvaluatePicVideoAdapter(R.layout.show_selected_pic_video_item);
        picVideoRv.setAdapter(picVideoAdapter);
        picVideoRv.setLayoutManager(manager);
        picVideoAdapter.setNewData(picVideos);

        picVideoAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<BannerObject> arrays = adapter.getData();
                PicVideoDisplayActivity.startPicVideoPlayActivity(mContext,arrays,position);


            }
        });

    }

}
