package com.juntai.project.sell.mall.home.assets.withdraw;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.beans.WithDrawListBean;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @UpdateUser: 更新者
 */
public class AssetsWithDrawAdapter extends BaseQuickAdapter<WithDrawListBean.DataBean, BaseViewHolder> {
    public AssetsWithDrawAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, WithDrawListBean.DataBean item) {

//        helper.setText(R.id.buyer_name_tv, item.getNickname());
        helper.setText(R.id.buy_amount_tv, String.valueOf(item.getPrice()));
//        ImageLoadUtil.loadHeadSquareImageHasCorner(mContext, UrlFormatUtil.getImageThumUrl(item.getHeadPortrait()), (ImageView) helper.getView(R.id.buyer_head_iv));
        helper.setText(R.id.buy_date_tv, String.valueOf(item.getCreateTime()));

    }
}
