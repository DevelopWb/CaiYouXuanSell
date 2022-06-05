package com.juntai.project.sell.mall.order.refund;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.beans.order.RefundReasonBean;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/5/15 14:34
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/15 14:34
 */
class SelectRefundReasonAdapter extends BaseQuickAdapter<RefundReasonBean.DataDTO, BaseViewHolder> {
    public SelectRefundReasonAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, RefundReasonBean.DataDTO item) {
        helper.setText(R.id.text_tv, item.getCauseName());
        if (item.isSelect()) {
            helper.setImageResource(R.id.text_selected_iv,R.mipmap.select_icon);
        }else {
            helper.setImageResource(R.id.text_selected_iv,R.mipmap.unselect_icon);

        }
    }
}
