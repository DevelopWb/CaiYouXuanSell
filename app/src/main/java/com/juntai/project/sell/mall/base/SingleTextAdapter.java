package com.juntai.project.sell.mall.base;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.project.sell.mall.R;

/**
 * 单个text的适配器
 *
 * @date 2019/3/17
 */
public class SingleTextAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public SingleTextAdapter(int layoutResId) {
        super(layoutResId);
    }


    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.single_text_tv, item);
        if (helper.getAdapterPosition()==getData().size()-1) {
            helper.setBackgroundRes(R.id.single_text_tv,0);
        }else {
            helper.setBackgroundRes(R.id.single_text_tv,R.drawable.bg_gray_only_bottom_black_shape_1px);

        }
    }
}