package com.juntai.project.sell.mall.mine;


import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.utils.MultipleItem;
import com.juntai.disabled.basecomponent.bean.MyMenuBean;
import com.juntai.project.sell.mall.R;

import java.util.List;

/**
 * Describe:
 * Create by zhangzhenlong
 * 2020/3/7
 * email:954101549@qq.com
 */
public class MyMenuAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MyMenuAdapter(List<MultipleItem> data) {
        super(data);
        addItemType(MultipleItem.ITEM_DIVIDER, R.layout.memu_divider);
        addItemType(MultipleItem.ITEM_MENUS,R.layout.my_center_menu_item);
    }


    @Override
    protected void convert(BaseViewHolder helper, MultipleItem multipleItem) {
        switch (multipleItem.getItemType()) {
            case MultipleItem.ITEM_DIVIDER:
                break;
            case MultipleItem.ITEM_MENUS:
                MyMenuBean item = (MyMenuBean) multipleItem.getObject();
                helper.setText(R.id.item_name, item.getName());
                helper.setImageResource(R.id.item_iv, item.getImageId());
                if (item.getNumber() > 0) {
                    helper.setVisible(R.id.item_number, true);
                    helper.setText(R.id.item_number, item.getNumber() > 99 ? "99+" : String.valueOf(item.getNumber()));
                } else {
                    helper.setVisible(R.id.item_number, false);
                }
                if (item.isHasEndLine()) {
                    helper.setGone(R.id.menu_item_divider_v,true);
                }else {
                    helper.setGone(R.id.menu_item_divider_v,false);
                }
                break;
            default:
                break;
        }


    }

}
