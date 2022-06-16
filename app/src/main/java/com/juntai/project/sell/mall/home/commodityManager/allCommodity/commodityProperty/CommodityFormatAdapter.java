package com.juntai.project.sell.mall.home.commodityManager.allCommodity.commodityProperty;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.utils.eventbus.EventBusObject;
import com.juntai.disabled.basecomponent.utils.eventbus.EventManager;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.beans.CommodityFormatBean;
import com.juntai.project.sell.mall.beans.StringBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @UpdateUser: 更新者
 */
public class CommodityFormatAdapter extends BaseQuickAdapter<CommodityFormatBean.ResultBean, BaseViewHolder> {
    public CommodityFormatAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommodityFormatBean.ResultBean item) {
        TextView addPropertyTv = helper.getView(R.id.add_property_tv);
        EditText formatEt = helper.getView(R.id.commodity_format_name_et);
        helper.setText(R.id.commodity_format_name_et,item.getValue());
        formatEt.setTag(R.id.tag_first,item);
        formatEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                CommodityFormatBean.ResultBean item = (CommodityFormatBean.ResultBean) formatEt.getTag(R.id.tag_first);
                item.setValue(formatEt.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        helper.addOnClickListener(R.id.delete_iv);
        helper.addOnClickListener(R.id.add_property_tv);
        RecyclerView propertyRv = helper.getView(R.id.commodity_property_content_rv);
        CommodityPropertyAdapter propertyAdapter = new CommodityPropertyAdapter(R.layout.commodity_add_property_item);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
        propertyRv.setAdapter(propertyAdapter);
        propertyRv.setLayoutManager(gridLayoutManager);
        addPropertyTv.setTag(propertyAdapter);
        formatEt.setTag(R.id.tag_second,propertyAdapter);
        List<String> values = item.getDetail();
        List<StringBean> datas = new ArrayList<>();
        if (values != null&&values.size()>0) {
            for (String value : values) {
                datas.add(new StringBean(value,helper.getAdapterPosition()));
            }
            propertyAdapter.setNewData(datas);
        }
        propertyAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                StringBean stringBean = (StringBean) adapter.getItem(position);
                switch (view.getId()) {
                    case R.id.delete_property_iv:
                        adapter.remove(position);
                        EventManager.getEventBus().post(new EventBusObject(EventBusObject.REFRESH_COMMODITY_FORMAT_DATA,stringBean.getPresentPosition()));

                        break;
                    default:
                        break;
                }
            }
        });
    }
}
