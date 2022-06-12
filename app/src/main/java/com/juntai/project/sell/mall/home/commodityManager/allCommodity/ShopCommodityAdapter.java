package com.juntai.project.sell.mall.home.commodityManager.allCommodity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.beans.sell.EditShopCommodityBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/6/12 14:17
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/12 14:17
 */
public class ShopCommodityAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public ShopCommodityAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageLoadUtil.loadSquareImage(mContext, item, helper.getView(R.id.commodity_cover_iv));
        helper.setText(R.id.commodity_name_tv, item);
        helper.setText(R.id.commodity_resove_tv, item);

        RecyclerView recyclerView = helper.getView(R.id.edit_commodity_rv);
        ShopCommodityEditAdapter editAdapter = new ShopCommodityEditAdapter(R.layout.edit_commodity_item);
        GridLayoutManager manager = new GridLayoutManager(mContext, 4);
        recyclerView.setAdapter(editAdapter);
        recyclerView.setLayoutManager(manager);
        editAdapter.setNewData(getEditMenus());
        editAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                EditShopCommodityBean bean = (EditShopCommodityBean) adapter.getItem(position);
                List<EditShopCommodityBean> textBeans = adapter.getData();
                for (int i = 0; i < textBeans.size(); i++) {
                    EditShopCommodityBean textBean = textBeans.get(i);
                    if (i==position) {
                        textBean.setSelect(true);
                    }else {
                        textBean.setSelect(false);
                    }
                }
                adapter.notifyDataSetChanged();

                switch (bean.getTextContent()) {
                    case "修改":

                        break;
                    default:
                        break;
                }

            }
        });
    }

    private List<EditShopCommodityBean> getEditMenus() {
        List<EditShopCommodityBean> arrays = new ArrayList<>();
        arrays.add(new EditShopCommodityBean("修改"));
        arrays.add(new EditShopCommodityBean("删除"));
        arrays.add(new EditShopCommodityBean("下架"));
        arrays.add(new EditShopCommodityBean("上架"));
        return arrays;
    }
}
