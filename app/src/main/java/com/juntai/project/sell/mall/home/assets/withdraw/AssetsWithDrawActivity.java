package com.juntai.project.sell.mall.home.assets.withdraw;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.beans.BaseAdapterDataBean;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.juntai.project.sell.mall.home.shop.BaseShopActivity;

import okhttp3.FormBody;

/**
 * @aouther tobato
 * @description 描述 提现
 * @date 2022/6/20 11:16
 */
public class AssetsWithDrawActivity extends BaseShopActivity implements HomePageContract.IHomePageView {



    @Override
    public void initData() {
        super.initData();
        baseQuickAdapter.setNewData(mPresenter.assetsWithDraw());
    }

    @Override
    protected String getTitleName() {
        return "提现";
    }

    @Override
    protected boolean isDetail() {
        return false;
    }

    @Override
    protected View getAdapterHeadView() {
        return null;
    }

    @Override
    protected View getAdapterFootView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.assets_withdraw_footview, null);
        TextView commitTv = view.findViewById(R.id.commit_tv);
        commitTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseAdapterDataBean baseAdapterDataBean = getBaseOfAdapterData();
                if (baseAdapterDataBean == null) {
                    return;
                }
                FormBody.Builder builder = baseAdapterDataBean.getBuilder();


            }
        });
        return view;
    }

    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);
        switch (tag) {
            default:
                break;
        }
    }
}
