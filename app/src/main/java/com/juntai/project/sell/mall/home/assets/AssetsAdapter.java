package com.juntai.project.sell.mall.home.assets;

import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.beans.AssetsMenuBean;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @UpdateUser: 更新者
 */
public class AssetsAdapter extends BaseQuickAdapter<AssetsMenuBean, BaseViewHolder> {
    public AssetsAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, AssetsMenuBean item) {
        helper.addOnClickListener(R.id.assets_bt_name_tv);
        helper.setText(R.id.assets_item_name_tv, item.getMenuName());
        helper.setText(R.id.assets_item_amount_tv, item.getAmount());
        if (TextUtils.isEmpty(item.getButtonName())) {
            helper.setVisible(R.id.assets_bt_name_tv,false);
        }else {
            helper.setVisible(R.id.assets_bt_name_tv,true);
            helper.setText(R.id.assets_bt_name_tv, item.getButtonName());
        }

        if (item.isSelected()) {
            helper.setBackgroundRes(R.id.assets_item_cl,R.mipmap.assets_item_bg);
            helper.setTextColor(R.id.assets_item_name_tv, ContextCompat.getColor(mContext,R.color.white));
            helper.setTextColor(R.id.assets_item_amount_tv, ContextCompat.getColor(mContext,R.color.white));
            helper.setTextColor(R.id.assets_bt_name_tv, ContextCompat.getColor(mContext,R.color.white));
            helper.setBackgroundRes(R.id.assets_bt_name_tv,R.drawable.stroke_white_fill_trans_circle_bg);
        }else {
            helper.setBackgroundRes(R.id.assets_item_cl,R.drawable.sp_filled_gray_lighter);
            helper.setTextColor(R.id.assets_item_name_tv, ContextCompat.getColor(mContext,R.color.black));
            helper.setTextColor(R.id.assets_item_amount_tv, ContextCompat.getColor(mContext,R.color.black));
            helper.setTextColor(R.id.assets_bt_name_tv, ContextCompat.getColor(mContext,R.color.black));
            helper.setBackgroundRes(R.id.assets_bt_name_tv,R.drawable.stroke_black_fill_trans_circle_bg);

        }
    }
}
