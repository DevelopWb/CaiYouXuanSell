package com.juntai.project.sell.mall.order.orderDetail;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.OrderDetailItemBean;
import com.juntai.project.sell.mall.base.selectPics.SelectPhotosFragment;

import java.util.List;


/**
 * @aouther tobato
 * @description 描述  我的信息
 * @date 2021/6/1 16:48
 */
public class OrderBaseInfoAdapter extends BaseQuickAdapter<OrderDetailItemBean, BaseViewHolder> {
    private FragmentManager fragmentManager;

    public OrderBaseInfoAdapter(int layoutResId, FragmentManager fragmentManager) {
        super(layoutResId);
        this.fragmentManager = fragmentManager;
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderDetailItemBean orderDetailBean) {

        helper.setText(R.id.order_info_title_tv, orderDetailBean.getTitle());

        RecyclerView  recyclerView = helper.getView(R.id.order_detail_info_rv);
        OrderBaseInfoChildAdapter childAdapter = new OrderBaseInfoChildAdapter(R.layout.mall_order_baseinfo_child_item);
        recyclerView.setAdapter(childAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        childAdapter.setNewData(orderDetailBean.getArrays());

        List<String> pics = orderDetailBean.getImages();
        if (pics != null&&pics.size()>0) {
            helper.setGone(R.id.order_pics_fl,true);
            SelectPhotosFragment selectPhotosFragment = SelectPhotosFragment.newInstance(String.valueOf(orderDetailBean.getTitle()));
            FragmentTransaction transaction =  fragmentManager.beginTransaction();
            transaction.replace(R.id.order_pics_fl,selectPhotosFragment);
            transaction.commit();
            selectPhotosFragment.setMaxCount(pics.size());
            selectPhotosFragment
                    .setPhotoDelateable(false).setIcons(pics);

        }else {
            helper.setGone(R.id.order_pics_fl,false);

        }

    }

}