package com.juntai.project.sell.mall.mine;


import com.juntai.disabled.basecomponent.utils.MultipleItem;
import com.juntai.disabled.basecomponent.base.BaseObserver;
import com.juntai.disabled.basecomponent.base.BaseResult;
import com.juntai.disabled.basecomponent.bean.MyMenuBean;
import com.juntai.disabled.basecomponent.mvp.IModel;
import com.juntai.disabled.basecomponent.mvp.IView;
import com.juntai.disabled.basecomponent.utils.RxScheduler;
import com.juntai.project.sell.mall.AppNetModuleMall;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseAppPresent;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;

/**
 * Describe:
 * Create by zhangzhenlong
 * 2020/3/7
 * email:954101549@qq.com
 */
public class MyCenterPresent extends BaseAppPresent<IModel, MyCenterContract.ICenterView> implements MyCenterContract.ICenterPresent {

    private IView iView;

    @Override
    protected IModel createModel() {
        return null;
    }

    public void logout(RequestBody requestBody, String tag) {
        AppNetModuleMall.createrRetrofit()
                .logout(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<BaseResult>(getView()) {
                    @Override
                    public void onSuccess(BaseResult o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }

                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }
    public void modifyUserInfo(RequestBody requestBody, String tag) {
        AppNetModuleMall.createrRetrofit()
                .modifyUserInfo(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<BaseResult>(getView()) {
                    @Override
                    public void onSuccess(BaseResult o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }

                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }


    public void getUserInfo(RequestBody requestBody, String tag) {
        AppNetModuleMall
                .createrRetrofit()
                .getUserInfo(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<BaseResult>(getView()) {
                    @Override
                    public void onSuccess(BaseResult o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }

                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }


    @Override
    public List<MultipleItem> getMenuBeans() {
        List<MultipleItem> menuBeans = new ArrayList<>();
        menuBeans.add(new MultipleItem(MultipleItem.ITEM_DIVIDER, ""));
        menuBeans.add(new MultipleItem(MultipleItem.ITEM_MENUS, new MyMenuBean(MyCenterContract.MENU_MODIFY_PHONE, 0, R.mipmap.mycenter_modify_phone, true)));
        menuBeans.add(new MultipleItem(MultipleItem.ITEM_MENUS, new MyMenuBean(MyCenterContract.MENU_MODIFY_PWD, 0, R.mipmap.mycenter_modify_pwd, true)));
        menuBeans.add(new MultipleItem(MultipleItem.ITEM_MENUS, new MyMenuBean(MyCenterContract.MENU_MODIFY_AUTH, 0, R.mipmap.mycenter_auth, true)));
//        menuBeans.add(new MultipleItem(MultipleItem.ITEM_MENUS, new MyMenuBean(MyCenterContract.MENU_MODIFY_SUGGESTION, 0, R.mipmap.mycenter_suggestion, true)));
//        menuBeans.add(new MultipleItem(MultipleItem.ITEM_MENUS, new MyMenuBean(MyCenterContract.MENU_MODIFY_BIND, 0, R.mipmap.mycenter_bind_third, true)));
        menuBeans.add(new MultipleItem(MultipleItem.ITEM_MENUS, new MyMenuBean(MyCenterContract.MENU_MODIFY_GUIDE, 0, R.mipmap.mycenter_newer_guide, true)));

        return menuBeans;
    }

}
