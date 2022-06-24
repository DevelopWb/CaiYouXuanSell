package com.juntai.project.sell.mall.home.commodityManager.allCommodity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.beans.sell.EditShopCommodityBean;
import com.juntai.project.sell.mall.beans.sell.ShopCommodityManagerListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/6/12 14:17
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/12 14:17
 */
public class ShopCommodityAdapter extends BaseQuickAdapter<ShopCommodityManagerListBean.DataBean.ListBean, BaseViewHolder> {

  private  OnChildClickCallBack childClickCallBack;

    public ShopCommodityAdapter(int layoutResId,OnChildClickCallBack childClickCallBack) {
        super(layoutResId);
        this.childClickCallBack = childClickCallBack;
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopCommodityManagerListBean.DataBean.ListBean item) {
        ImageLoadUtil.loadSquareImage(mContext, item.getCoverImg(), helper.getView(R.id.commodity_cover_iv));
        helper.setText(R.id.commodity_name_tv, item.getName());
        helper.setText(R.id.commodity_resove_tv, String.format("库存量 %s", item.getStock()));

        RecyclerView recyclerView = helper.getView(R.id.edit_commodity_rv);
        ShopCommodityEditAdapter editAdapter = new ShopCommodityEditAdapter(R.layout.edit_commodity_item);
        GridLayoutManager manager = new GridLayoutManager(mContext, 4);
        recyclerView.setAdapter(editAdapter);
        recyclerView.setLayoutManager(manager);
        editAdapter.setNewData(getEditMenus(item));
        editAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                EditShopCommodityBean bean = (EditShopCommodityBean) adapter.getItem(position);
                List<EditShopCommodityBean> textBeans = adapter.getData();
                for (int i = 0; i < textBeans.size(); i++) {
                    EditShopCommodityBean textBean = textBeans.get(i);
                    if (i == position) {
                        textBean.setSelect(true);
                    } else {
                        textBean.setSelect(false);
                    }
                }
                adapter.notifyDataSetChanged();

                switch (bean.getTextContent()) {
                    case "修改":
                        if (childClickCallBack != null) {
                            childClickCallBack.onChildClick(0,bean.getListBean());
                        }
                        break;
                    case "删除":
                        if (childClickCallBack != null) {
                            childClickCallBack.onChildClick(1,bean.getListBean());
                        }
                        break;
                    case "规格":
                        if (childClickCallBack != null) {
                            childClickCallBack.onChildClick(2,bean.getListBean());
                        }
                        break;
                    case "上架":
                        if (childClickCallBack != null) {
                            childClickCallBack.onChildClick(3,bean.getListBean());
                        }
                        break;
                    case "下架":
                        if (childClickCallBack != null) {
                            childClickCallBack.onChildClick(4,bean.getListBean());
                        }
                        break;
                    default:
                        break;
                }

            }
        });
    }

    private List<EditShopCommodityBean> getEditMenus(ShopCommodityManagerListBean.DataBean.ListBean item) {
        List<EditShopCommodityBean> arrays = new ArrayList<>();
        arrays.add(new EditShopCommodityBean("修改", item));
        arrays.add(new EditShopCommodityBean("删除", item));
        arrays.add(new EditShopCommodityBean("规格", item));
        if (item.getPutAwayStatus() == 0) {
            arrays.add(new EditShopCommodityBean("下架", item));
        } else {
            arrays.add(new EditShopCommodityBean("上架", item));
        }

        return arrays;
    }

    public interface OnChildClickCallBack{
        /**
         *
         * @param editType  0 修改 1 删除 2规格 3上架 4下架
         * @param item
         */
        void onChildClick(int editType, ShopCommodityManagerListBean.DataBean.ListBean item);
    }

}
