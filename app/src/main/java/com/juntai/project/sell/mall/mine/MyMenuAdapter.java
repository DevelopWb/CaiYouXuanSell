package com.juntai.project.sell.mall.mine;

import android.support.v7.widget.GridLayoutManager;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.bean.MyMenuBean;
import com.juntai.project.sell.mall.R;

import java.util.List;

/**
 * Describe:
 * Create by zhangzhenlong
 * 2020/3/7
 * email:954101549@qq.com
 */
public class MyMenuAdapter extends BaseQuickAdapter<MyMenuBean, BaseViewHolder> {
    GridLayoutManager gridLayoutManager;

    public MyMenuAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyMenuBean item) {
        helper.setText(R.id.item_name, item.getName());
        helper.setImageResource(R.id.item_iv, item.getImageId());
        if (item.getNumber() > 0) {
            helper.setVisible(R.id.item_number, true);
            helper.setText(R.id.item_number, item.getNumber() > 99 ? "99+" : String.valueOf(item.getNumber()));
        } else {
            helper.setVisible(R.id.item_number, false);
        }

        //设置宽高等比
        ViewGroup.LayoutParams parm = helper.itemView.getLayoutParams();
        parm.height =
                gridLayoutManager.getWidth() / gridLayoutManager.getSpanCount()
                        - 2 * helper.itemView.getPaddingLeft() - 2 * ((ViewGroup.MarginLayoutParams) parm).leftMargin;

    }

    public GridLayoutManager getGridLayoutManager() {
        return gridLayoutManager;
    }

    public void setGridLayoutManager(GridLayoutManager gridLayoutManager) {
        this.gridLayoutManager = gridLayoutManager;
    }
}
