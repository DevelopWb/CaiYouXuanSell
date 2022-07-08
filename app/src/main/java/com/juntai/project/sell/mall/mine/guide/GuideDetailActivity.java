package com.juntai.project.sell.mall.mine.guide;

import com.juntai.disabled.basecomponent.mvp.BasePresenter;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.disabled.video.img.PhotoView;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseAppActivity;
import com.juntai.project.sell.mall.home.HomePageContract;

/**
 * @aouther tobato
 * @description 描述  新手指导详情
 * @date 2022/6/22 15:53
 */
public class GuideDetailActivity extends BaseAppActivity {

    private PhotoView mGuidePicPv;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_guide_detail;
    }

    @Override
    public void initView() {
        String guideName = getIntent().getStringExtra(BASE_STRING);
        setTitleName(guideName);
        mGuidePicPv = (PhotoView) findViewById(R.id.guide_pic_pv);
        mGuidePicPv.enable();
        switch (guideName) {
            case HomePageContract.GUIDE_MENU_ADD_COMMODITY:
                // : 2022/6/22 商品添加
                ImageLoadUtil.loadImageCache(mContext, R.mipmap.guide_add_commodity_icon, mGuidePicPv);
                break;
            case HomePageContract.GUIDE_MENU_SHOP_FURNISH:
                // : 2022/6/22 店铺装修
                ImageLoadUtil.loadImageCache(mContext, R.mipmap.guide_shop_zhuagnxiu, mGuidePicPv);

                break;
            case HomePageContract.GUIDE_MENU_FINANCE_MANAGER:
                // : 2022/6/22 财务管理
                ImageLoadUtil.loadImageCache(mContext, R.mipmap.guide_assets_manager, mGuidePicPv);

                break;
            default:
                break;
        }
    }

    @Override
    public void initData() {

    }


    @Override
    public void onSuccess(String tag, Object o) {

    }

}
