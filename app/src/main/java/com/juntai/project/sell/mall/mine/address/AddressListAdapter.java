package com.juntai.project.sell.mall.mine.address;


import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.bean.address.AddressListBean;
import com.juntai.project.sell.mall.R;

/**
 * @aouther tobato
 * @description 描述  收货地址列表
 * @date 2022/5/9 16:29
 */
public class AddressListAdapter extends BaseQuickAdapter<AddressListBean.DataBean, BaseViewHolder> {

    public AddressListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, AddressListBean.DataBean item) {
        CheckBox defaultAddrCb = helper.getView(R.id.default_addr_cb);
        if (item.getDefaultAddress()==1) {
            //默认地址
            defaultAddrCb.setChecked(true);
        }else {
            defaultAddrCb.setChecked(false);
        }
        helper.addOnClickListener(R.id.item_address_edit);
        helper.addOnClickListener(R.id.default_addr_cb);
        helper.addOnClickListener(R.id.delete_addr_tv);
        helper.setText(R.id.item_address_name,item.getName());
        helper.setText(R.id.item_address_one,item.getName().substring(0,1));
        helper.setText(R.id.item_address_phone,item.getPhone());
        String addrDes = item.getCityName();
        helper.setText(R.id.item_address_adress,
                addrDes+"\u3000"+item.getDetailedAddress());
    }
}