package com.juntai.project.sell.mall.home.assets.withdraw;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.juntai.disabled.basecomponent.utils.MultipleItem;
import com.juntai.disabled.basecomponent.bean.TextKeyValueBean;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.project.sell.mall.AppHttpPathMall;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.sendcode.SmsCheckCodeActivity;
import com.juntai.project.sell.mall.beans.BaseAdapterDataBean;
import com.juntai.project.sell.mall.beans.BillBaseInfoBean;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.juntai.project.sell.mall.home.shop.BaseShopAdapter;
import com.juntai.project.sell.mall.utils.UserInfoManagerMall;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import okhttp3.FormBody;

/**
 * @aouther tobato
 * @description 描述 绑定银行卡
 * @date 2022/6/20 11:16
 */
public class BindBankCardActivity extends SmsCheckCodeActivity implements HomePageContract.IHomePageView {


    private RecyclerView mRecyclerview;
    private SmartRefreshLayout mSmartrefreshlayout;
    private BaseShopAdapter baseQuickAdapter;
    private TextView mSendCodeTv;
    private BillBaseInfoBean.DataBean dataBean;
    private  int  assentType ;

    @Override
    public int getLayoutView() {
        return R.layout.recycleview_layout;
    }

    @Override
    public void initView() {
        setTitleName("绑定银行卡");
        dataBean = getIntent().getParcelableExtra(BASE_PARCELABLE);
        assentType = getIntent().getIntExtra(BASE_ID,1);
        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        mSmartrefreshlayout = (SmartRefreshLayout) findViewById(R.id.smartrefreshlayout);
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mRecyclerview.setLayoutManager(manager);
        baseQuickAdapter = new BaseShopAdapter(null, false, getSupportFragmentManager(), null);
        baseQuickAdapter.addFooterView(getAdapterFootView());
        mRecyclerview.setAdapter(baseQuickAdapter);
    }

    @Override
    public void initData() {
        baseQuickAdapter.setNewData(mPresenter.bindBackCard());
    }

    protected View getAdapterFootView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.bind_bank_card_footview, null);
        TextView commitTv = view.findViewById(R.id.commit_tv);
        mSendCodeTv = view.findViewById(R.id.send_check_code_tv);
        mSendCodeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNum = getItemBean().getPhoneNumber();
                if (TextUtils.isEmpty(phoneNum)) {
                    ToastUtils.toast(mContext, "请输入手机号");
                    return;
                }
                sendCheckCode(phoneNum);
            }
        });
        EditText mCodeEt = view.findViewById(R.id.sms_code_et);
        commitTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseAdapterDataBean baseAdapterDataBean = getBaseOfAdapterData();
                if (baseAdapterDataBean == null) {
                    return;
                }
                if (TextUtils.isEmpty(getTextViewValue(mCodeEt))) {
                    ToastUtils.toast(mContext, "请输入验证码");
                    return;
                }
                FormBody.Builder builder = baseAdapterDataBean.getBuilder();
                builder.add("code", String.valueOf(getTextViewValue(mCodeEt)));
                mPresenter.bindBankCard(builder.build(), AppHttpPathMall.BIND_BANK_CARD);
            }
        });
        return view;
    }

    /**
     * 获取条目里面的值
     *
     * @return
     */
    private BillBaseInfoBean.DataBean getItemBean() {
        List<MultipleItem> arrays = baseQuickAdapter.getData();
        for (MultipleItem array : arrays) {
            switch (array.getItemType()) {
                case MultipleItem.ITEM_EDIT:
                    TextKeyValueBean textValueEditBean = (TextKeyValueBean) array
                            .getObject();
                    String textValue = textValueEditBean.getValue();
                    switch (textValueEditBean.getKey()) {
                        case HomePageContract.ASSETS_WITHDRAW_PHONE:
                          dataBean.setPhoneNumber(textValue);
                            break;
                        case HomePageContract.ASSETS_WITHDRAW_BANK_CARD:
                            dataBean.setBankCode(textValue);
                            break;
                        case HomePageContract.ASSETS_WITHDRAW_BANK_NAME:
                            dataBean.setBankName(textValue);
                            break;
                        case HomePageContract.ASSETS_WITHDRAW_BANK:
                            dataBean.setBankAddress(textValue);
                            break;
                        case HomePageContract.ASSETS_WITHDRAW_REAL_NAME:
                            dataBean.setRealName(textValue);
                            break;
                        case HomePageContract.ASSETS_WITHDRAW_IDCARD:
                          dataBean.setIdCode(textValue);
                            break;
                    }
                    break;


            }
        }
        return dataBean;
    }

    /**
     * 获取adapter中的数据
     *
     * @return
     */
    protected BaseAdapterDataBean getBaseOfAdapterData() {
        BaseAdapterDataBean baseAdapterDataBean = new BaseAdapterDataBean();
        FormBody.Builder builder = getBaseBuilder();
        builder.add("userAccount", UserInfoManagerMall.getAccount());
        List<MultipleItem> arrays = baseQuickAdapter.getData();
        for (MultipleItem array : arrays) {
            switch (array.getItemType()) {
                case MultipleItem.ITEM_EDIT:
                    TextKeyValueBean textValueEditBean = (TextKeyValueBean) array
                            .getObject();
                    String textValue = textValueEditBean.getValue();
                    if (textValueEditBean.isImportant() && TextUtils.isEmpty(textValue)) {
                        String key = textValueEditBean.getKey();
                        ToastUtils.toast(mContext, "请输入" + key);
                        return null;
                    }
                    switch (textValueEditBean.getKey()) {
                        case HomePageContract.ASSETS_WITHDRAW_PHONE:
                            builder.add("phoneNumber", textValue);
                            break;
                        case HomePageContract.ASSETS_WITHDRAW_BANK_CARD:
                            builder.add("bankCode", textValue);
                            break;
                        case HomePageContract.ASSETS_WITHDRAW_BANK_NAME:
                            builder.add("bankName", textValue);
                            break;
                        case HomePageContract.ASSETS_WITHDRAW_BANK:
                            builder.add("bankAddress", textValue);
                            break;
                        case HomePageContract.ASSETS_WITHDRAW_REAL_NAME:
                            builder.add("realName", textValue);
                            break;
                        case HomePageContract.ASSETS_WITHDRAW_IDCARD:
                            builder.add("idCode", textValue);
                            break;
                    }
                    break;


            }
        }
        baseAdapterDataBean.setBuilder(builder);
        return baseAdapterDataBean;
    }

    @Override
    protected TextView getSendCodeTv() {
        return mSendCodeTv;
    }

    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);
        switch (tag) {
            case AppHttpPathMall.BIND_BANK_CARD:
                startActivity(new Intent(mContext, AssetsWithDrawActivity.class)
                        .putExtra(BASE_ID,assentType)
                        .putExtra(BASE_PARCELABLE, getItemBean()));
                finish();
                break;
            default:
                break;
        }
    }

}
