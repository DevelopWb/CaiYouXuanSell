package com.juntai.project.sell.mall.base;

import com.juntai.disabled.basecomponent.base.BaseObserver;
import com.juntai.disabled.basecomponent.base.BaseResult;
import com.juntai.disabled.basecomponent.mvp.IModel;
import com.juntai.disabled.basecomponent.utils.RxScheduler;
import com.juntai.project.sell.mall.AppNetModuleMall;
import com.juntai.project.sell.mall.home.HomePageContract;

import okhttp3.RequestBody;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/5/8 8:52
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/8 8:52
 */
public abstract class BaseAppMallPresent extends BaseAppPresent<IModel, HomePageContract.IHomePageView> {

    @Override
    protected IModel createModel() {
        return null;
    }


    public void handlerRefundRequest(RequestBody requestBody, String tag) {
        AppNetModuleMall.createrRetrofit()
                .handlerRefundRequest(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<BaseResult>(null) {
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
