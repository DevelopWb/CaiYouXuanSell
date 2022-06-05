package com.juntai.project.sell.mall.base.displayPicVideo;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.bean.BaseMenuBean;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022-02-28 11:50
 * @UpdateUser: 更新者
 * @UpdateDate: 2022-02-28 11:50
 */
public class MoreActionAdapter extends BaseQuickAdapter<BaseMenuBean, BaseViewHolder> {
    public MoreActionAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseMenuBean item) {
        helper.setImageResource(com.juntai.disabled.video.R.id.action_iv,item.getRes());
        helper.setText(com.juntai.disabled.video.R.id.action_tv,item.getName());
    }
}
