package com.juntai.project.sell.mall.home.shop;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.bean.TextKeyValueBean;
import com.juntai.project.sell.mall.R;

/**
 * @Author: tobato
 * @Description: 作用描述  店铺流量
 * @CreateDate: 2022/6/7 10:04
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/7 10:04
 */
public class ShopFlowAdapter extends BaseQuickAdapter<TextKeyValueBean, BaseViewHolder> {
    public ShopFlowAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, TextKeyValueBean item) {
        helper.setText(R.id.text_key_tv, item.getKey());
        TextView valueTv  = helper.getView(R.id.text_value_tv);
        valueTv.setTextSize(20);
        helper.setText(R.id.text_value_tv, item.getValue());
    }
}
