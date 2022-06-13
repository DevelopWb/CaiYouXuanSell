package com.juntai.project.sell.mall.home.commodityManager.allCommodity;

import com.juntai.disabled.basecomponent.mvp.BasePresenter;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseAppActivity;
import com.juntai.project.sell.mall.utils.a3.A3LevelCombo;

public class TestActivity extends BaseAppActivity {

    private A3LevelCombo mTextSpacerNoTitle;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    @Override
    public int getLayoutView() {
        return R.layout.activity_test;
    }

    public void initView() {
        mTextSpacerNoTitle = (A3LevelCombo) findViewById(R.id.textSpacerNoTitle);
        mTextSpacerNoTitle.setData(getTestData());
    }

    @Override
    public void initData() {

    }

    @Override
    public void onSuccess(String tag, Object o) {

    }
}
