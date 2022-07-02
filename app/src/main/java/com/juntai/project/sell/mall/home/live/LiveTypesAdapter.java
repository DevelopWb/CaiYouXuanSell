package com.juntai.project.sell.mall.home.live;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.beans.LiveTypeListBean;

import java.util.List;

/**
 * Describe:弹窗  直播类型列表适配器
 * Create by zhangzhenlong
 * 2021-4-8
 * email:954101549@qq.com
 */
public class LiveTypesAdapter extends BaseQuickAdapter<LiveTypeListBean.DataBean, BaseViewHolder> {
    private LiveTypeListBean.DataBean choosedItem;

    public LiveTypesAdapter(int layoutResId, @Nullable List<LiveTypeListBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LiveTypeListBean.DataBean item) {
        helper.setText(R.id.item_name, item.getName());
        if (choosedItem != null && choosedItem.getId() == item.getId()){
            helper.setTextColor(R.id.item_name, mContext.getResources().getColor(R.color.white));
            helper.setBackgroundRes(R.id.item_name, R.drawable.sp_filled_accent);
        }else {
            helper.setTextColor(R.id.item_name, mContext.getResources().getColor(R.color.black));
            helper.setBackgroundRes(R.id.item_name, R.drawable.sp_filled_gray_lighter);
        }
    }

    public LiveTypeListBean.DataBean getChoosedItem() {
        return choosedItem;
    }

    public void setChoosedItem(LiveTypeListBean.DataBean choosedItem) {
        this.choosedItem = choosedItem;
    }
}
