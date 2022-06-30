package com.juntai.project.sell.mall.home.assets.withdraw;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.project.sell.mall.AppHttpPathMall;
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


    private String assetsWithDraw;
    private int assetsType;

    @Override
    public void initData() {
        super.initData();
        baseQuickAdapter.setNewData(mPresenter.assetsWithDraw());
        assetsType = getIntent().getIntExtra(BASE_ID, 0);
        assetsWithDraw = getIntent().getStringExtra(BASE_STRING);
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
        EditText withDrawEt = view.findViewById(R.id.withDraw_et);
        withDrawEt.setText(assetsWithDraw);
        commitTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseAdapterDataBean baseAdapterDataBean = getBaseOfAdapterData();
                if (baseAdapterDataBean == null) {
                    return;
                }
                if (TextUtils.isEmpty(getTextViewValue(withDrawEt))||Double.parseDouble(getTextViewValue(withDrawEt))==0) {
                    ToastUtils.toast(mContext, "请输入非0的提现金额");
                    return;
                }
                if (Double.parseDouble(getTextViewValue(withDrawEt))>Double.parseDouble(assetsWithDraw)) {
                    ToastUtils.toast(mContext, "超出最大提现金额");
                    return;
                }
                FormBody.Builder builder = baseAdapterDataBean.getBuilder();
                builder.add("type", String.valueOf(assetsType))
                        .add("price", getTextViewValue(withDrawEt));
                mPresenter.withDraw(builder.build(), AppHttpPathMall.WITHDRAW);
            }
        });
        return view;
    }

    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);
        switch (tag) {
            case AppHttpPathMall.WITHDRAW:
                ToastUtils.toast(mContext, "已提交,请等待");
                break;
            default:
                break;
        }
    }
}
