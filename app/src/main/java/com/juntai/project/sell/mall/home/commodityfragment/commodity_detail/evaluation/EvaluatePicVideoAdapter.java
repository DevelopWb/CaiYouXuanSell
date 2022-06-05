package com.juntai.project.sell.mall.home.commodityfragment.commodity_detail.evaluation;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.utils.bannerImageLoader.BannerObject;

/**
 * @Author: tobato
 * @Description: 作用描述  评价的视频和图片
 * @CreateDate: 2022/5/17 14:22
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/17 14:22
 */
public class EvaluatePicVideoAdapter extends BaseQuickAdapter<BannerObject, BaseViewHolder> {
    public EvaluatePicVideoAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, BannerObject item) {
        switch (item.getEventKey()) {
            case BannerObject.BANNER_TYPE_VIDEO:
                BannerObject.VideoBean videoBean = item.getVideoBean();
                helper.setGone(R.id.item_video_tag, true);
                ImageLoadUtil.loadSquareImageHasCorner(mContext,videoBean.getVideoCover(), helper.getView(R.id.select_pic_icon_iv));

                break;
            case BannerObject.BANNER_TYPE_IMAGE:
                ImageLoadUtil.loadSquareImageHasCorner(mContext,item.getPicPath(), helper.getView(R.id.select_pic_icon_iv));

                helper.setGone(R.id.item_video_tag, false);
                break;
            default:
                break;
        }

    }
}
