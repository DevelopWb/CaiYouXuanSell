package com.juntai.project.sell.mall.home;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.juntai.disabled.basecomponent.utils.eventbus.EventBusObject;
import com.juntai.disabled.basecomponent.utils.eventbus.EventManager;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseAppFragment;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/5/9 17:26
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/9 17:26
 */
public class HomeCommodityFragment extends BaseAppFragment<HomePagePresent> implements HomePageContract.IHomePageView, View.OnClickListener {
    private LinearLayout mSearchLl;
    private ImageView mSwitchModeIv;

    @Override
    protected HomePagePresent createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.home_commodity_fragment;
    }

    @Override
    protected void initView() {
        mSearchLl = (LinearLayout) getView(R.id.search_ll);
        mSwitchModeIv = (ImageView) getView(R.id.switch_mode_iv);
        mSwitchModeIv.setOnClickListener(this);
        mSearchLl.setOnClickListener(this);
        getView(R.id.scan_iv).setOnClickListener(this);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onSuccess(String tag, Object o) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.switch_mode_iv:
                EventManager.getEventBus().post(new EventBusObject(EventBusObject.HOME_PAGE_DISPLAY_MODE, 1));
                break;
            case R.id.search_ll:
                getBaseAppActivity().startToSearchActivity();
                break;
            case R.id.scan_iv:
                // : 2022/5/31 扫码
                startActivity(new Intent(mContext, QRScanActivity.class));

                break;
            default:
                break;
        }
    }
}
