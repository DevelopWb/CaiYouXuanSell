package com.juntai.project.sell.mall.home.assets.withdraw;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.project.sell.mall.AppHttpPathMall;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseAppActivity;
import com.juntai.project.sell.mall.beans.BillBaseInfoBean;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.juntai.project.sell.mall.home.HomePagePresent;

import okhttp3.FormBody;

/**
 * @aouther tobato
 * @description 描述 提现
 * @date 2022/6/20 11:16
 */
public class AssetsWithDrawActivity extends BaseAppActivity<HomePagePresent> implements HomePageContract.IHomePageView, View.OnClickListener {

    private BillBaseInfoBean.DataBean dataBean;
    /**
     * 银行（daf）
     */
    private TextView mBankBaseInfoTv;
    /**
     * 123
     */
    private EditText mWithDrawEt;
    /**
     * 确定
     */
    private TextView mCommitTv;

    private int assetType;

    @Override
    public int getLayoutView() {
        return R.layout.assets_withdraw_activity;
    }

    @Override
    public void initView() {
        setTitleName("提现");
        dataBean = getIntent().getParcelableExtra(BASE_PARCELABLE);
        assetType = getIntent().getIntExtra(BASE_ID,1);
        mBankBaseInfoTv = (TextView) findViewById(R.id.bank_base_info_tv);
        mWithDrawEt = (EditText) findViewById(R.id.withDraw_et);
        mWithDrawEt.setText(String.valueOf(dataBean.getWithdrawalCash()));
        mCommitTv = (TextView) findViewById(R.id.commit_tv);
        mCommitTv.setOnClickListener(this);
        String bankCard = dataBean.getBankCode();
        mBankBaseInfoTv.setText(String.format("%s(%s)",dataBean.getBankName(),bankCard.substring(bankCard.length()-4,bankCard.length())));
    }

    @Override
    public void initData() {
    }


    @Override
    public void onSuccess(String tag, Object o) {
        switch (tag) {
            case AppHttpPathMall.WITHDRAW:
                ToastUtils.toast(mContext, "已提交,请等待处理");
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    protected HomePagePresent createPresenter() {
        return new HomePagePresent();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.commit_tv:
                if (TextUtils.isEmpty(getTextViewValue(mWithDrawEt)) || Double.parseDouble(getTextViewValue(mWithDrawEt)) == 0) {
                    ToastUtils.toast(mContext, "请输入非0的提现金额");
                    return;
                }
                if (Double.parseDouble(getTextViewValue(mWithDrawEt)) > dataBean.getWithdrawalCash()) {
                    ToastUtils.toast(mContext, "超出最大提现金额");
                    return;
                }
                FormBody.Builder builder = getBaseBuilder();
                builder
                        .add("type", String.valueOf(assetType))
                        .add("bankCode", dataBean.getBankCode())
                        .add("phoneNumber", dataBean.getPhoneNumber())
                        .add("realName", dataBean.getRealName())
                        .add("idCode", dataBean.getIdCode())
                        .add("bankName", dataBean.getBankName())
                        .add("bankAddress", dataBean.getBankAddress())
                        .add("price", getTextViewValue(mWithDrawEt));
                mPresenter.withDraw(builder.build(), AppHttpPathMall.WITHDRAW);
                break;
        }
    }
}
