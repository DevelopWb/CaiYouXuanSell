package com.juntai.project.sell.mall.home.commodityManager.allCommodity.commodityProperty;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.utils.eventbus.EventBusObject;
import com.juntai.disabled.basecomponent.utils.eventbus.EventManager;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.beans.StringBean;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @UpdateUser: 更新者
 */
public class CommodityPropertyAdapter extends BaseQuickAdapter<StringBean, BaseViewHolder> {
    public CommodityPropertyAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, StringBean item) {
        EditText mPropertyEt = helper.getView(R.id.commodity_property_name_et);
        helper.setText(R.id.commodity_property_name_et,item.getContent());
        mPropertyEt.setTag(item);
        mPropertyEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                StringBean item = (StringBean) mPropertyEt.getTag();
                item.setContent(s.toString());
                // : 2022/6/15 通知父适配器更新数据
                EventManager.getEventBus().post(new EventBusObject(EventBusObject.REFRESH_COMMODITY_FORMAT_DATA,item.getPresentPosition()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        helper.addOnClickListener(R.id.delete_property_iv);

    }
}
