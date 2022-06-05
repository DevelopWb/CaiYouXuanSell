package com.juntai.project.sell.mall.home.commodityfragment.commodity_detail.selectCommodityProperty;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.beans.CommodityPropertyListBean;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述  商品属性
 * @CreateDate: 2022/5/3 16:30
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/3 16:30
 */
public class CommodityPropertyAdapter extends BaseQuickAdapter<CommodityPropertyListBean, BaseViewHolder> {
    public CommodityPropertyAdapter(int layoutResId) {
        super(layoutResId);
    }

    private OnAdapterItemCallBack onAdapterItemCallBack;

    public OnAdapterItemCallBack getOnAdapterItemCallBack() {
        return onAdapterItemCallBack;
    }

    public void setOnAdapterItemCallBack(OnAdapterItemCallBack onAdapterItemCallBack) {
        this.onAdapterItemCallBack = onAdapterItemCallBack;
    }

    @Override
    protected void convert(BaseViewHolder helper, CommodityPropertyListBean item) {
        helper.setText(R.id.commodity_property_name_tv, item.getPropertyName());
        RecyclerView recyclerView = helper.getView(R.id.commodity_property_content_rv);
        CommodityPropertyContentAdapter contentAdapter = new CommodityPropertyContentAdapter(R.layout.commodity_property_content_item);
        GridLayoutManager manager = new GridLayoutManager(mContext, 3);
        recyclerView.setAdapter(contentAdapter);
        recyclerView.setLayoutManager(manager);
        contentAdapter.setNewData(item.getPropertyContent());
        contentAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                // : 2022/5/5 选择属性值
                List<CommodityPropertyListBean.PropertyContentBean> arrays = adapter.getData();

                for (int i = 0; i < arrays.size(); i++) {
                    CommodityPropertyListBean.PropertyContentBean propertyContentBean = arrays.get(i);
                    if (i == position) {
                        propertyContentBean.setSelected(true);
                    } else {
                        propertyContentBean.setSelected(false);
                    }
                }
                adapter.notifyDataSetChanged();
                // TODO: 2022/5/5   图片和金额需要动态改变
                CommodityPropertyListBean.PropertyContentBean propertyContentBean = (CommodityPropertyListBean.PropertyContentBean) adapter.getItem(position);
                if (onAdapterItemCallBack != null) {
                    onAdapterItemCallBack.propertySelected(propertyContentBean);
                }
            }
        });

    }


    public interface OnAdapterItemCallBack {
        /**
         * 选择属性
         *
         */
        void propertySelected(CommodityPropertyListBean.PropertyContentBean propertyContentBean);
    }
}
