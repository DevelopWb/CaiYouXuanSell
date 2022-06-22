package com.juntai.project.sell.mall.order.orderDetail;


import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.bean.TextKeyValueBean;
import com.juntai.disabled.basecomponent.utils.DisplayUtil;
import com.juntai.project.sell.mall.R;


/**
 * @aouther tobato
 * @description 描述  我的信息
 * @date 2021/6/1 16:48
 */
public class OrderBaseInfoChildAdapter extends BaseQuickAdapter<TextKeyValueBean, BaseViewHolder> {

    public OrderBaseInfoChildAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, TextKeyValueBean item) {
        helper.setText(R.id.item_myinfo_name, item.getKey());
        TextView  valueTv = helper.getView(R.id.item_myinfo_value);
        valueTv.setText(item.getValue());
        valueTv.setGravity(Gravity.RIGHT|Gravity.CENTER_VERTICAL);

    }

    /**
     * 设置左边图标
     * @param textView
     * @param drawableId
     */
    public void initViewLeftDrawable(TextView textView, int drawableId, int width, int height) {
        Drawable drawable = mContext.getResources().getDrawable(drawableId);
        drawable.setBounds(0, 0, DisplayUtil.dp2px(mContext, width), DisplayUtil.dp2px(mContext, height));//第一个 0 是距左边距离，第二个 0 是距上边距离，40 分别是长宽
        textView.setCompoundDrawables(null, null, drawable, null);//放左边
    }

}