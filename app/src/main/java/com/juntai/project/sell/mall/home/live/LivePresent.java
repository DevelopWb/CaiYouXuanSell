package com.juntai.project.sell.mall.home.live;

import com.juntai.disabled.basecomponent.base.BaseObserver;
import com.juntai.disabled.basecomponent.utils.RxScheduler;
import com.juntai.project.sell.mall.AppNetModuleMall;
import com.juntai.project.sell.mall.base.BaseAppMallPresent;
import com.juntai.project.sell.mall.beans.LiveResultBean;
import com.juntai.project.sell.mall.beans.LiveTypeListBean;

import okhttp3.RequestBody;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @UpdateUser: 更新者
 */
public class LivePresent extends BaseAppMallPresent {


    public void getLiveType( String tag) {
        AppNetModuleMall.createrRetrofit()
                .getLiveType()
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<LiveTypeListBean>(getView()) {
                    @Override
                    public void onSuccess(LiveTypeListBean o) {
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


    public void startLive(RequestBody requestBody, String tag) {
        AppNetModuleMall.createrRetrofit()
                .startLive(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<LiveResultBean>(getView()) {
                    @Override
                    public void onSuccess(LiveResultBean o) {
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
