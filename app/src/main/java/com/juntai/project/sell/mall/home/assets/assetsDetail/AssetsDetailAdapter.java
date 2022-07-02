package com.juntai.project.sell.mall.home.assets.assetsDetail;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.disabled.basecomponent.utils.UrlFormatUtil;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.beans.BillListBean;

/**
 * @Author: tobato
 * @Description: 作用描述  收入明细
 * @UpdateUser: 更新者
 */
public class AssetsDetailAdapter extends BaseQuickAdapter<BillListBean.DataBean.ListBean, BaseViewHolder> {
    public AssetsDetailAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, BillListBean.DataBean.ListBean item) {

        helper.setText(R.id.buyer_name_tv, item.getNickname());
        helper.setText(R.id.buy_amount_tv, String.valueOf(item.getPayPrice()));
        ImageLoadUtil.loadHeadSquareImageHasCorner(mContext, UrlFormatUtil.getImageThumUrl(item.getHeadPortrait()), (ImageView) helper.getView(R.id.buyer_head_iv));
        helper.setText(R.id.buy_date_tv, String.valueOf(item.getPaymentTime()));

    }
}
