package com.juntai.project.sell.mall.home.commodityManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.beans.PicTextBean;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/6/7 15:35
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/7 15:35
 */
public class CommodityManagerAdapter extends BaseQuickAdapter<PicTextBean, BaseViewHolder> {
    public CommodityManagerAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, PicTextBean item) {
        helper.setBackgroundRes(R.id.commodity_bg_cl, item.getPicbg());
        helper.setImageResource(R.id.commodity_manager_iv,item.getPicRes());
        helper.setText(R.id.commodity_manager_name_tv,item.getTextName());
        helper.setText(R.id.commodity_manager_en_name_tv,item.getTextNameEn());

    }
}
