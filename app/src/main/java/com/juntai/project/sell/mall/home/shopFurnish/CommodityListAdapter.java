package com.juntai.project.sell.mall.home.shopFurnish;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.beans.CommodityBean;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/5/3 14:07
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/3 14:07
 */
public class CommodityListAdapter extends BaseQuickAdapter<CommodityBean, BaseViewHolder> {
    //是否是线性布局
    private  boolean  isLinearLayoutManager;


    public void setLinearLayoutManager(boolean linearLayoutManager) {
        isLinearLayoutManager = linearLayoutManager;
        notifyDataSetChanged();
    }

    public CommodityListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommodityBean item) {
        if (isLinearLayoutManager) {
            helper.setGone(R.id.un_linearlayout_cl,false);
            helper.setGone(R.id.linearlayout_cl,true);
            ImageLoadUtil.loadSquareImage(mContext,item.getCoverImg(),helper.getView(R.id.linearlayout_commodity_cover_iv));
            helper.setText(R.id.linearlayout_commodity_des_tv, item.getName());
            helper.setText(R.id.linearlayout_commodity_price_tv,String.format("￥%s",item.getPrice()));
            helper.setText(R.id.linearlayout_commodity_sales_tv,String.format("销量:%s",item.getSales()));
        }else {
            helper.setGone(R.id.un_linearlayout_cl,true);
            helper.setGone(R.id.linearlayout_cl,false);
            ImageLoadUtil.loadSquareImage(mContext,item.getCoverImg(),helper.getView(R.id.commodity_cover_iv));
            helper.setText(R.id.commodity_des_tv, item.getName());
            helper.setText(R.id.commodity_price_tv,String.format("￥%s",item.getPrice()));
            helper.setText(R.id.commodity_sales_tv,String.format("销量:%s",item.getSales()));
        }

    }

}
