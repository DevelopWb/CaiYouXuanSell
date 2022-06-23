package com.juntai.project.sell.mall.mine.modifyPwd;

import com.juntai.disabled.basecomponent.utils.MD5;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.project.sell.mall.AppHttpPathMall;
import com.juntai.project.sell.mall.base.BaseWithSmsActivity;
import com.juntai.project.sell.mall.utils.UserInfoManagerMall;

/**
 * @aouther tobato
 * @description 描述  修改密码
 * @date 2021-10-13 15:09
 */
public class ModifyPwdActivity extends BaseWithSmsActivity {

    @Override
    protected String getPhoneHint() {
        return null;
    }

    @Override
    protected String getPwdHint() {
        return "请输入新密码";
    }

    @Override
    protected String getCommitTextName() {
        return "提交";
    }

    @Override
    protected String getTitleName() {
        return "修改密码";
    }

    @Override
    protected void commit() {
        if (!UserInfoManagerMall.getPhoneNumber().equals(getTextViewValue(mRegistPhoneEt))) {
            ToastUtils.toast(mContext, "请输入注册的手机号");
            return;
        }
// : 2022/5/10 调用修改密码的接口
        mPresenter.modifyPwd(
                getBaseBuilder().add("phone", getTextViewValue(mRegistPhoneEt))
                        .add("code", getTextViewValue(mRegistCheckCodeEt))
                        .add("newPwd", MD5.md5(String.format("%s#%s", getTextViewValue(mRegistPhoneEt), getTextViewValue(mPasswordEt)))).build(), AppHttpPathMall.MODIFY_PWD);
    }

}
