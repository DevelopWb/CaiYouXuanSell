package com.juntai.disabled.basecomponent.base;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.R;
import com.juntai.disabled.basecomponent.bean.MoreMenuBean;

import java.util.List;

/**
 * Describe:更多菜单
 * Create by zhangzhenlong
 * 2021-1-12
 * email:954101549@qq.com
 */
public class BottomDialogGridAdapter extends BaseQuickAdapter<MoreMenuBean, BaseViewHolder> {

    public BottomDialogGridAdapter(int layoutResId, @Nullable List<MoreMenuBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MoreMenuBean item) {
        helper.setText(R.id.item_text, item.getName());
        helper.setImageResource(R.id.item_image, item.getImageId());
        helper.getView(R.id.item_image).setSelected(item.isChoose());
        if (item.isWorkAble()){
            helper.itemView.setClickable(true);
            helper.itemView.setAlpha(1.0f);
        }else {
            helper.itemView.setClickable(false);
            helper.itemView.setAlpha(0.3f);
        }
    }
}
