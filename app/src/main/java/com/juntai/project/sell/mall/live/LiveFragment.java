package com.juntai.project.sell.mall.live;


import android.view.View;
import android.widget.TextView;

import com.juntai.disabled.basecomponent.base.BaseMvpFragment;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.juntai.project.sell.mall.home.commodityfragment.CommodityPresent;

/**
 * @aouther tobato
 * @description 描述  直播
 * @date
 */
public class LiveFragment extends BaseMvpFragment<CommodityPresent> implements HomePageContract.IHomePageView,
        View.OnClickListener {

    private View view;
    private TextView mNoneTv;

    @Override
    protected int getLayoutRes() {
        return R.layout.live_fg;
    }

    @Override
    protected void initView() {


        mNoneTv = (TextView)getView(R.id.none_tv);
        mNoneTv.setText("敬请期待~_~");
    }

    @Override
    protected void initData() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    protected void lazyLoad() {
    }

    @Override
    protected void lazyloadGone() {

    }

    @Override
    protected CommodityPresent createPresenter() {
        return new CommodityPresent();
    }

    @Override
    protected boolean canCancelLoadingDialog() {
        return false;
    }


    @Override
    public void onSuccess(String tag, Object o) {
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
        }
    }

}
