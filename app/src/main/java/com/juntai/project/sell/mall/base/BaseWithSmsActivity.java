package com.juntai.project.sell.mall.base;

import android.content.Intent;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.juntai.disabled.basecomponent.utils.RuleTools;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.project.sell.mall.AppHttpPathMall;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.sendcode.SmsCheckCodeActivity;
import com.juntai.project.sell.mall.mine.myinfo.HeadCropActivity;
import com.juntai.project.sell.mall.utils.UserInfoManagerMall;

import java.util.List;

/**
 * @aouther tobato
 * @description 描述
 * @date 2021-10-13 14:49
 */
public abstract class BaseWithSmsActivity extends SmsCheckCodeActivity implements View.OnClickListener {
    public static String REGIST_PHONE = "regist_phone";

    /**
     * 注册
     */
    public TextView mRegistTv;
    /**
     * 注册手机号
     */
    public EditText mRegistPhoneEt;
    /**
     * 短信验证码
     */
    public EditText mRegistCheckCodeEt;
    /**
     * 获取验证码
     */
    public TextView mRegistSendCheckCodeTv;
    /**
     * 密码
     */
    public EditText mPasswordEt;
    public ImageView mHideShowIv;
    public boolean isHide = true;//默认隐藏
    protected String headPicPath;

    @Override
    public int getLayoutView() {
        return R.layout.activity_modify_pwd;
    }

    @Override
    public void initView() {
        setTitleName(getTitleName());
        mRegistTv = (TextView) findViewById(R.id.regist_tv);
        mRegistTv.setText(getCommitTextName());
        mRegistTv.setOnClickListener(this);
        mRegistPhoneEt = (EditText) findViewById(R.id.regist_phone_et);
        mRegistCheckCodeEt = (EditText) findViewById(R.id.regist_check_code_et);
        mRegistSendCheckCodeTv = (TextView) findViewById(R.id.regist_send_check_code_tv);
        mRegistSendCheckCodeTv.setOnClickListener(this);
        mPasswordEt = (EditText) findViewById(R.id.password_et);
        mPasswordEt.setHint(getPwdHint());
        mRegistPhoneEt.setHint(TextUtils.isEmpty(getPhoneHint())?"请输入你的手机号":getPhoneHint());
        mHideShowIv = (ImageView) findViewById(R.id.hide_show_iv);
        mHideShowIv.setOnClickListener(this);
    }

    protected abstract String getPhoneHint();

    protected abstract String getPwdHint();

    protected abstract String getCommitTextName();

    protected abstract String getTitleName();

    @Override
    public void initData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.regist_tv:

                if (checkMobileNo()) return;

                if (TextUtils.isEmpty(getTextViewValue(mRegistCheckCodeEt))) {
                    ToastUtils.warning(mContext, "请输入验证码");
                    return;
                }
                if (TextUtils.isEmpty(getTextViewValue(mPasswordEt))) {
                    ToastUtils.warning(mContext, "请输入密码");
                    return;
                }
                commit();

                break;
            case R.id.regist_send_check_code_tv:
                // 注册界面发送验证码
                if (checkMobileNo()) return;
                sendCheckCode(getTextViewValue(mRegistPhoneEt));
                break;
            case R.id.hide_show_iv:
                if (isHide) {
                    isHide = false;
                    //设置EditText的密码为可见的
                    mHideShowIv.setImageResource(R.mipmap.show_icon);
                    mPasswordEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    isHide = true;
                    //设置EditText的密码为隐藏
                    mHideShowIv.setImageResource(R.mipmap.hide_icon);
                    mPasswordEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                mPasswordEt.setSelection(mPasswordEt.getText().length());
                break;
        }
    }

    /**
     *
     * @return
     */
    private boolean checkMobileNo() {
        if (!RuleTools.isMobileNO(getTextViewValue(mRegistPhoneEt))) {
            ToastUtils.warning(mContext, "手机号码格式不正确");
            return true;
        }
        if ("修改密码".equals(getTitleName())) {
            if (!UserInfoManagerMall.getPhoneNumber().equals(getTextViewValue(mRegistPhoneEt))) {
                ToastUtils.toast(mContext, "手机号码和注册号码不一致");
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onPicsAndEmpressed(List<String> icons) {
        super.onPicsAndEmpressed(icons);
        if (icons.size() > 0) {
            String path = icons.get(0);
            //跳转到裁剪头像的界面
            startActivityForResult(new Intent(this, HeadCropActivity.class).putExtra(HeadCropActivity.HEAD_PIC,
                    path), BASE_REQUEST_RESULT);

        }
    }

    protected abstract void commit();

    @Override
    protected TextView getSendCodeTv() {
        return mRegistSendCheckCodeTv;
    }

    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag,o);
        switch (tag) {
            case AppHttpPathMall.MODIFY_PWD:
            case AppHttpPathMall.MODIFY_PHONE:
                ToastUtils.toast(mContext,"修改成功");
                reLogin(getTextViewValue(mRegistPhoneEt));
                break;
            default:
                break;
        }

    }



}
