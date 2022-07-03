package com.juntai.project.sell.mall.home.assets.withdraw;

import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.beans.WithDrawListBean;

/**
 * @Author: tobato
 * @Description: 作用描述  提现记录
 * @UpdateUser: 更新者
 */
public class AssetsWithDrawAdapter extends BaseQuickAdapter<WithDrawListBean.DataBean, BaseViewHolder> {
    public AssetsWithDrawAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, WithDrawListBean.DataBean item) {

        helper.setText(R.id.paytype_tv,1==item.getType()?"商城":"公户");
        helper.setText(R.id.buyer_name_tv, item.getBankName());
        helper.setText(R.id.buy_amount_tv, String.valueOf(item.getPrice()));
        helper.setText(R.id.buy_date_tv, String.valueOf(item.getCreateTime()));
        if (1!=item.getType()) {
            //公户
            helper.setTextColor(R.id.paytype_tv, ContextCompat.getColor(mContext,R.color.colorAccent));
            helper.setBackgroundRes(R.id.paytype_tv,R.drawable.stroke_accent_3dp);
        }else {
            helper.setTextColor(R.id.paytype_tv, ContextCompat.getColor(mContext,R.color.red));
            helper.setBackgroundRes(R.id.paytype_tv,R.drawable.stroke_red_square_bg);
        }

    }
}
