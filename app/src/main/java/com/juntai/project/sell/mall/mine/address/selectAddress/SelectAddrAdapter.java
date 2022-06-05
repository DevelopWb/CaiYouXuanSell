package com.juntai.project.sell.mall.mine.address.selectAddress;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.beans.CitysBean;

import java.util.List;

/**
 * @aouther tobato
 * @description 描述
 * @date 2022/5/9 16:53
 */
public class SelectAddrAdapter extends BaseQuickAdapter<CitysBean.DistrictsBean, BaseViewHolder> {
    String pinyin = "0";
    int color = -1;
    //MallHomeB
    public SelectAddrAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CitysBean.DistrictsBean item) {
        if (color == -1) {
            color = mContext.getResources().getColor(R.color.colorTheme);
        }

        helper.setText(R.id.item_address_pinyin, item.getPinYin());
        if (pinyin.equals(item.getPinYin())){
            helper.setText(R.id.item_address_pinyin,"");
        }
        helper.setText(R.id.item_address_selector, item.getName());
        pinyin = item.getPinYin();
    }

}