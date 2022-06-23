package com.juntai.project.sell.mall.mine.guide;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.beans.GuideMenuBean;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/4/18 15:39
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/18 15:39
 */
public class GuideMenuAdapter extends BaseQuickAdapter<GuideMenuBean, BaseViewHolder> {
    public GuideMenuAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, GuideMenuBean item) {

        helper.setText(R.id.homepage_menu_title_tv,item.getMenuName());
        helper.setText(R.id.homepage_menu_title_en_tv,item.getMenuEnName());
        ImageLoadUtil.loadImage(mContext,item.getMenuPicId(),helper.getView(R.id.menu_icon_iv));
        helper.setBackgroundRes(R.id.guide_menu_ll,item.getMenuPicBgId());
    }
}
