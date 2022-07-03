package com.juntai.project.sell.mall.entrance;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.juntai.disabled.basecomponent.utils.HawkProperty;
import com.juntai.project.sell.mall.AppHttpPathMall;
import com.juntai.project.sell.mall.MainActivity;
import com.juntai.project.sell.mall.base.BaseAppActivity;
import com.juntai.project.sell.mall.beans.UserBeanMall;
import com.juntai.project.sell.mall.beans.sell.ShopDetailBean;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.juntai.project.sell.mall.home.HomePagePresent;
import com.juntai.project.sell.mall.utils.UserInfoManagerMall;
import com.orhanobut.hawk.Hawk;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;

/**
 * @aouther Ma
 * @date 2019/3/13
 */
public class SplashActivity extends BaseAppActivity<HomePagePresent> implements HomePageContract.IHomePageView {
    String[] permissions = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION};

    @Override
    protected HomePagePresent createPresenter() {
        return new HomePagePresent();
    }

    @Override
    public int getLayoutView() {
        return 0;
    }

    @Override
    public void initView() {
        initToolbarAndStatusBar(false);
    }

    @Override
    public void initData() {

    }

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new RxPermissions(this)
                .request(permissions)
                .delay(1, TimeUnit.SECONDS)
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            //所有权限通过
                        } else {
                            //有一个权限没通过
                        }
                        if (UserInfoManagerMall.isLogin()) {
                            if (UserInfoManagerMall.getShopStatus() == 2) {
                                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                                finish();
                            } else {
                                // : 2022/6/8 获取用户详情
                                if (!TextUtils.isEmpty(UserInfoManagerMall.getAccount())) {
                                    mPresenter.getUserInfo(getBaseBuilder().build(), AppHttpPathMall.GET_USER_INFO);
                                }else {
                                    UserInfoManagerMall.clearUserData();
                                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                                    finish();
                                }
                            }

                        } else {
                            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                            finish();
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    }
                });
    }

    @Override
    public void onSuccess(String tag, Object o) {

        switch (tag) {
            case AppHttpPathMall.GET_USER_INFO:
                UserBeanMall loginBean = (UserBeanMall) o;
                if (loginBean != null) {
                    Hawk.put(HawkProperty.SP_KEY_USER, loginBean.getData());
                    switch (UserInfoManagerMall.getShopStatus()) {
                        case 0:
                            // : 2022/6/8 跳转到店铺提交页面
                            startToShopAuthActivity(null);
                            finish();
                            break;
                        case 1:
                        case 2:
                            // : 2022/6/8 审核通过
                            startActivity(new Intent(SplashActivity.this, MainActivity.class));
                            finish();
                            break;
                        case 3:
                            // : 2022/6/8 审核失败  是否需要加上原因
                            showAlertDialog(String.format("%s,请重新提交", UserInfoManagerMall.getUser().getStateContent()), "去认证", "", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // : 2022/6/10 获取店铺信息 跳转到店铺管理页面
                                    mPresenter.getShopDetail(getBaseBuilder().add("shopId", String.valueOf(UserInfoManagerMall.getShopId())).build(), AppHttpPathMall.SHOP_DETAIL);
                                }
                            });

                            break;
                        default:
                            break;
                    }

                }
                break;

            case AppHttpPathMall.SHOP_DETAIL:
                ShopDetailBean shopDetailBean = (ShopDetailBean) o;
                if (shopDetailBean != null) {
                    ShopDetailBean.DataBean dataBean = shopDetailBean.getData();
                    if (dataBean != null) {
                       startToShopAuthActivity(dataBean);
                       finish();
                    }
                }
                break;
            default:
                break;
        }

    }
}
