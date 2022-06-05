package com.juntai.project.sell.mall.mine.address;

import com.juntai.disabled.basecomponent.base.BaseObserver;
import com.juntai.disabled.basecomponent.base.BaseResult;
import com.juntai.disabled.basecomponent.bean.address.AddressListBean;
import com.juntai.disabled.basecomponent.utils.RxScheduler;
import com.juntai.project.sell.mall.AppNetModuleMall;
import com.juntai.project.sell.mall.base.BaseAppMallPresent;
import com.juntai.project.sell.mall.beans.CitysBean;
import com.juntai.project.sell.mall.utils.UserInfoManagerMall;
import com.juntai.disabled.basecomponent.utils.HawkProperty;
import com.orhanobut.hawk.Hawk;

import java.util.List;

import okhttp3.RequestBody;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/5/8 17:29
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/8 17:29
 */
public class AddrPresent extends BaseAppMallPresent {
    /**
     * 获取所有的城市
     *
     * @param keywords
     */
    public void getAllCitys(String keywords, String tag) {
        AppNetModuleMall.createrRetrofit()
                .getAllCitys(keywords, "2", "65862700fcb4f4b4dcbe4e554e5bbe3d")
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<CitysBean>(null) {
                    @Override
                    public void onSuccess(CitysBean o) {
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

    public void getAddrList( RequestBody body,String tag) {
        AppNetModuleMall.createrRetrofit()
                .getAddrList(body)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<AddressListBean>(getView()) {
                    @Override
                    public void onSuccess(AddressListBean o) {
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
    public void addOrEditAddrList(RequestBody body,String tag) {
        AppNetModuleMall.createrRetrofit()
                .addOrEditAddrList(body)
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
    public void setDefaultAdddr(RequestBody body,String tag) {
        AppNetModuleMall.createrRetrofit()
                .setDefaultAdddr(body)
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


    public void deleteAddr(List<Integer> ids, String tag) {
        AppNetModuleMall.createrRetrofit()
                .deleteAddr(UserInfoManagerMall.getAccount(), Hawk.get(HawkProperty.SP_KEY_TOKEN), UserInfoManagerMall.DEVICE_TYPE,ids)
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

}
