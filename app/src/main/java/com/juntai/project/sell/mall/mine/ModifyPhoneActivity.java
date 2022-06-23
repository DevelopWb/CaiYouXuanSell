package com.juntai.project.sell.mall.mine;

import android.os.Bundle;

import com.juntai.disabled.basecomponent.utils.MD5;
import com.juntai.project.sell.mall.AppHttpPathMall;
import com.juntai.project.sell.mall.base.BaseWithSmsActivity;

/**
 * @aouther tobato
 * @description 描述 修改手机号
 * @date 2022/6/23 11:14
 */
public class ModifyPhoneActivity extends BaseWithSmsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getPhoneHint() {
        return "输入新手机号码";
    }

    @Override
    protected String getPwdHint() {
        return "输入登录密码";
    }

    @Override
    protected String getCommitTextName() {
        return "提交";
    }

    @Override
    protected String getTitleName() {
        return "修改手机号";
    }

    @Override
    protected void commit() {
        mPresenter.modifyPhone(
                getBaseBuilder()
                        .add("newPhoneNumber", getTextViewValue(mRegistPhoneEt))
                        .add("code", getTextViewValue(mRegistCheckCodeEt))
                        .add("password", MD5.md5(String.format("%s#%s", getTextViewValue(mRegistPhoneEt), getTextViewValue(mPasswordEt))))
                        .build(), AppHttpPathMall.MODIFY_PHONE);
    }
}
