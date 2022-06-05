package com.juntai.project.sell.mall.order.refund;

import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.beans.order.OrderDetailBean;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/5/15 9:18
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/15 9:18
 */
public class RefundCommodityAdapter extends BaseQuickAdapter<OrderDetailBean.CommodityListBean, BaseViewHolder> {
   public RefundCommodityAdapter(int layoutResId) {
      super(layoutResId);
   }

   @Override
   protected void convert(BaseViewHolder helper, OrderDetailBean.CommodityListBean item) {
      ImageLoadUtil.loadSquareImageHasCorner(mContext, item.getCoverImg(), helper.getView(R.id.commodity_pic_iv));
      helper.setText(R.id.commodity_name_tv, item.getCommodityName());
      helper.addOnClickListener(R.id.commodity_property_tv);
      helper.addOnClickListener(R.id.commodity_selected_iv);
      if (TextUtils.isEmpty(item.getCartInfo())) {
         helper.setText(R.id.commodity_property_tv, "宝贝已失效");
         helper.setTextColor(R.id.commodity_property_tv, ContextCompat.getColor(mContext,R.color.red));
      }else {
         helper.setText(R.id.commodity_property_tv, item.getCartInfo());
         helper.setTextColor(R.id.commodity_property_tv, ContextCompat.getColor(mContext,R.color.gray_deeper));
      }
      helper.setText(R.id.all_price_tv, String.valueOf(item.getPrices()));
   }
}
