package com.juntai.project.sell.mall.mine;


import com.example.chat.util.MultipleItem;
import com.juntai.disabled.basecomponent.base.BaseObserver;
import com.juntai.disabled.basecomponent.base.BaseResult;
import com.juntai.disabled.basecomponent.bean.MyMenuBean;
import com.juntai.disabled.basecomponent.mvp.BasePresenter;
import com.juntai.disabled.basecomponent.mvp.IModel;
import com.juntai.disabled.basecomponent.mvp.IView;
import com.juntai.disabled.basecomponent.utils.RxScheduler;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.beans.IdNameBean;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;

/**
 * Describe:
 * Create by zhangzhenlong
 * 2020/3/7
 * email:954101549@qq.com
 */
public class MyCenterPresent extends BasePresenter<IModel, MyCenterContract.ICenterView> implements MyCenterContract.ICenterPresent {

    private IView iView;

    @Override
    protected IModel createModel() {
        return null;
    }


    /**
     * 注销
     */
    public void logout(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
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
    public void getMyMsgs(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .getMyMSG(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<MyMsgBean>(getView()) {
                    @Override
                    public void onSuccess(MyMsgBean o) {
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


    public void getMyMsgUnread(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .getMyMsgUnread(requestBody)
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
        AppNetModule
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

    public void addInfo(RequestBody requestBody, String tag) {
        AppNetModule
                .createrRetrofit()
                .addInfo(requestBody)
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

    public void searchAccountNature(RequestBody requestBody, String tag) {
        AppNetModule
                .createrRetrofit()
                .searchAccountNature(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<UnitsBean>(getView()) {
                    @Override
                    public void onSuccess(UnitsBean o) {
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
    public void getNextDepartment(RequestBody requestBody, String tag) {
        AppNetModule
                .createrRetrofit()
                .getNextDepartment(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<IdNameBean>(getView()) {
                    @Override
                    public void onSuccess(IdNameBean o) {
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
    public void updateHeadPic(RequestBody requestBody, String tag) {
        AppNetModule
                .createrRetrofit()
                .updateHeadPic(requestBody)
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
        menuBeans.add(new MultipleItem(MultipleItem.ITEM_MENUS, new MyMenuBean("我的消息", 0, R.mipmap.my_message, MyCenterContract.MENU_NEWS, true)));
        menuBeans.add(new MultipleItem(MultipleItem.ITEM_MENUS, new MyMenuBean("修改密码", 0, R.mipmap.modify_pwd, MyCenterContract.MODIFY_PWD, true)));
        menuBeans.add(new MultipleItem(MultipleItem.ITEM_MENUS, new MyMenuBean("清理内存", 0, R.mipmap.clear_cache, MyCenterContract.SET_CLEAR_TAG, true)));
        menuBeans.add(new MultipleItem(MultipleItem.ITEM_MENUS, new MyMenuBean("检测更新", 0, R.mipmap.check_update, MyCenterContract.SET_UPDATE_TAG, true)));
        menuBeans.add(new MultipleItem(MultipleItem.ITEM_MENUS, new MyMenuBean("关于我们", 0, R.mipmap.about_us, MyCenterContract.SET_ABOUT_TAG, true)));

        return menuBeans;
    }

}
