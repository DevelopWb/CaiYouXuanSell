package com.juntai.project.sell.mall.entrance;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chat.MyChatApp;
import com.example.chat.util.UserInfoManagerChat;
import com.juntai.disabled.basecomponent.utils.HawkProperty;
import com.juntai.disabled.basecomponent.utils.LogUtil;
import com.juntai.disabled.basecomponent.utils.MD5;
import com.juntai.disabled.basecomponent.utils.RomUtil;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.project.sell.mall.AppHttpPathMall;
import com.juntai.project.sell.mall.MainActivity;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.sendcode.SmsCheckCodeActivity;
import com.juntai.project.sell.mall.beans.UserBeanMall;
import com.juntai.project.sell.mall.mine.modifyPwd.BackPwdActivity;
import com.juntai.project.sell.mall.utils.UserInfoManagerMall;
import com.juntai.project.sell.mall.webSocket.MyWsManager;
import com.orhanobut.hawk.Hawk;

import java.lang.ref.WeakReference;
import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.friends.Wechat;
import okhttp3.FormBody;

/**
 * @aouther tobato
 * @description 描述  登录
 * @date 2020/3/6 9:12
 */
public class LoginActivity extends SmsCheckCodeActivity implements
        View.OnClickListener {
    public String otherHeadIcon = "";
    /**
     * 登录
     */
    private TextView mLoginTv,mLoginTypeTv;
    /**
     * 密码
     */
    private EditText mPasswordEt;
    String account, password;
    /**
     * 找回密码
     */
    private TextView mRebackPwdTv;
    private ImageView mLoginByWchatIv;
    private ImageView mLoginByZfbIv;

    private MyHandler myHandler = new MyHandler(this);
    /**
     * 注册
     */
    private TextView mRegistTv;
    /**
     * 输入手机号
     */
    private EditText mRegistPhoneEt;
    /**
     * 获取验证码
     */
    private TextView mGetCodeTv;
    /**
     * 密码登录
     */
    private TextView mSwitchLoginTypeTv;
    //默认是密码登录
    private int loginType = 0;

    static class MyHandler extends Handler {
        private WeakReference<Activity> mActivity;//弱引用

        MyHandler(Activity activity) {
            mActivity = new WeakReference<Activity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            LoginActivity theActivity = (LoginActivity) mActivity.get();
            switch (msg.what) {
                //此处可以根据what的值处理多条信息
                case 1:
                    theActivity.otherLogin();
                    break;
            }
        }
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_login;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    public void initData() {

    }


    @Override
    protected TextView getSendCodeTv() {
        return mGetCodeTv;
    }

    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);
        switch (tag) {
            //登录成功
            case AppHttpPathMall.LOGIN:
                UserBeanMall loginBean = (UserBeanMall) o;
                if (loginBean != null) {
                    ToastUtils.success(mContext, "登录成功");
                    MyChatApp.isReLoadWarn = true;
                    Hawk.put(HawkProperty.SP_KEY_USER, loginBean.getData());
                    Hawk.put(HawkProperty.SP_KEY_TOKEN, loginBean.getData().getToken());
                    MyWsManager.getInstance() .setWsUrl(String.format("%s%s/%s",AppHttpPathMall.BASE_SOCKET,UserInfoManagerMall.getUserId(),UserInfoManagerMall.DEVICE_TYPE));
                    startActivity(new Intent(mContext, MainActivity.class));
                    finish();
                }
                break;
            case EntranceContract.OTHER_REGIST:
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.login_tv:
                account = mRegistPhoneEt.getText().toString();
                password = mPasswordEt.getText().toString();

                if (!mPresenter.checkMobile(account)) {
                    return;
                }
                if (password.isEmpty()) {
                    ToastUtils.toast(mContext, loginType % 2 == 0 ? "登录密码不能为空" : "验证码不能为空");
                    return;
                }
                // : 2022/4/28 调用登录的接口
                if (0 == loginType % 2) {
                    mPresenter.login(new FormBody.Builder().add("phoneNumber", account)
                            .add("typeEnd", UserInfoManagerMall.DEVICE_TYPE)
                            .add("mobileName", RomUtil.getName())
                            .add("regId", HawkProperty.getRegid())
                            .add("password", MD5.md5(String.format("%s#%s", account, password)))
                            .build(), AppHttpPathMall.LOGIN);
                } else {
                    mPresenter.login(new FormBody.Builder().add("phoneNumber", account)
                            .add("typeEnd", UserInfoManagerMall.DEVICE_TYPE)
                            .add("mobileName", RomUtil.getName())
                            .add("regId", HawkProperty.getRegid())
                            .add("code", password)
                            .build(), AppHttpPathMall.LOGIN);
                }
                break;
            case R.id.reback_pwd_tv:
                // : 2022/4/28 跳转到找回密码的界面
                startActivity(new Intent(this, BackPwdActivity.class));
                break;
            case R.id.login_by_wchat_iv:
                // TODO: 2022/4/28 微信登录
                loginForQQWeChat(Wechat.NAME);
                break;
            case R.id.login_by_zfb_iv:
                // TODO: 2022/4/28 支付宝登录
//                loginForQQWeChat(QQ.NAME);
                break;
            case R.id.regist_tv:
                // : 2022/4/28 跳转到注册的界面
                startActivity(new Intent(this, RegistActivity.class));
                break;
            case R.id.get_code_tv:
                sendCheckCode(getTextViewValue(mRegistPhoneEt));
                break;
            case R.id.switch_login_type_tv:
                //切换登录方式
                loginType++;
                switchLoginType();
                if (0 == loginType % 2) {
                    mLoginTypeTv.setText("手机号登录");
                }else {
                    mLoginTypeTv.setText("验证码登录");

                }

                break;
        }
    }

    /**
     * 切换登录方式
     */
    private void switchLoginType() {

        switch (loginType % 2) {
            case 0:
                //密码登录
                setViewVisibleOrGone(false, mGetCodeTv);
                mSwitchLoginTypeTv.setText("短信验证码登录");
                mPasswordEt.setHint("请输入密码");
                break;
            case 1:
                setViewVisibleOrGone(true, mGetCodeTv);
                mSwitchLoginTypeTv.setText("密码登录");
                mPasswordEt.setHint("请输入验证码");

                break;
            default:
                break;
        }
    }


    @Override
    public void initView() {

        initToolbarAndStatusBar(false);
        mImmersionBar.statusBarColor(R.color.transparent).statusBarDarkFont(true).init();
        mLoginTv = (TextView) findViewById(R.id.login_tv);
        mLoginTypeTv = (TextView) findViewById(R.id.login_type_tv);
        mLoginTv.setOnClickListener(this);
        mRebackPwdTv = (TextView) findViewById(R.id.reback_pwd_tv);
        mRebackPwdTv.setOnClickListener(this);
        mLoginByWchatIv = (ImageView) findViewById(R.id.login_by_wchat_iv);
        mLoginByWchatIv.setOnClickListener(this);
        mLoginByZfbIv = (ImageView) findViewById(R.id.login_by_zfb_iv);
        mLoginByZfbIv.setOnClickListener(this);
        mRegistTv = (TextView) findViewById(R.id.regist_tv);
        mRegistTv.setOnClickListener(this);
        mRegistPhoneEt = (EditText) findViewById(R.id.regist_phone_et);
        mPasswordEt = (EditText) findViewById(R.id.password_et);
        mGetCodeTv = (TextView) findViewById(R.id.get_code_tv);
        mGetCodeTv.setOnClickListener(this);
        mSwitchLoginTypeTv = (TextView) findViewById(R.id.switch_login_type_tv);
        mSwitchLoginTypeTv.setOnClickListener(this);
        switchLoginType();
        String phoneNum = getIntent().getStringExtra(BASE_STRING);
        if (!TextUtils.isEmpty(phoneNum)) {
            mRegistPhoneEt.setText(phoneNum);
        }
    }

    PlatformDb platDB;

    /**
     * 第三方数据
     *
     * @param name
     */
    public void loginForQQWeChat(String name) {
        UserInfoManagerChat.QQ_ID = null;
        UserInfoManagerChat.WECHAT_ID = null;
        otherHeadIcon = null;

        Platform plat = ShareSDK.getPlatform(name);
        if (!plat.isClientValid()) {
            //判断是否存在授权凭条的客户端，true是有客户端，false是无
//            if (name.equals(QQ.NAME)) {
//                ToastUtils.warning(mContext, "未安装QQ");
//            } else {
//                ToastUtils.warning(mContext, "未安装微信");
//            }
        }

        plat.removeAccount(true); //移除授权状态和本地缓存，下次授权会重新授权
        plat.SSOSetting(false); //SSO授权，传false默认是客户端授权，没有客户端授权或者不支持客户端授权会跳web授权
        //        ShareSDK.setActivity(this);//抖音登录适配安卓9.0
        plat.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                //通过打印res数据看看有哪些数据是你想要的
                if (i == Platform.ACTION_USER_INFOR) {
                    platDB = platform.getDb();//获取数平台数据DB
                    //通过DB获取各种数据
                    LogUtil.e("id=" + platDB.getUserId());
                    UserInfoManagerChat.OTHER_NICK_NAME = platDB.getUserName();
                    otherHeadIcon = platDB.getUserIcon();
//                    if (platform.getName().equals(QQ.NAME)) {
//                        String params = "access_token=" + platform.getDb().getToken() + "&unionid=1&fmt=json";
//                        HttpUtil.sendGet("https://graph.qq.com/oauth2.0/me", params, new HttpUtil.NetCallBack() {
//                            @Override
//                            public void onSuccess(String str) {
//                                if (!TextUtils.isEmpty(str)) {
//                                    UnionidBean unionidBean = GsonTools.changeGsonToBean(str, UnionidBean.class);
//                                    UserInfoManagerChat.QQ_ID = unionidBean.getUnionid();
//                                    myHandler.sendEmptyMessage(1);
//                                }
//                            }
//
//                            @Override
//                            public void onError(String str) {
//                            }
//                        });
//
//                    } else {
//                        UserInfoManagerChat.WECHAT_ID = platform.getDb().get("unionid");
//                        myHandler.sendEmptyMessage(1);
//                    }

                }
            }


            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                LogUtil.e(throwable.toString());
                //                plat.removeAccount(true); //移除授权状态和本地缓存，下次授权会重新授权
            }

            @Override
            public void onCancel(Platform platform, int i) {

            }
        });
        plat.showUser(null);    //要数据不要功能，主要体现在不会重复出现授权界面
    }

    /**
     * 第三方登录
     */
    public void otherLogin() {
//        mPresenter.login(null, null, UserInfoManagerChat.WECHAT_ID, UserInfoManagerChat.QQ_ID, EntranceContract.LOGIN_TAG);
    }

    @Override
    protected void onDestroy() {
        myHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    @Override
    protected boolean canCancelLoadingDialog() {
        return false;
    }
}
