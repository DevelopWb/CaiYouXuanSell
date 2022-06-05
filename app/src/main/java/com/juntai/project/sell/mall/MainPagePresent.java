package com.juntai.project.sell.mall;


import com.juntai.disabled.basecomponent.mvp.BasePresenter;
import com.juntai.disabled.basecomponent.mvp.IModel;

/**
 * Describe:首页present
 * Create by zhangzhenlong
 * 2020-8-8
 * email:954101549@qq.com
 */
public class MainPagePresent extends BasePresenter<IModel, MainPageContract.IMainPageView> implements MainPageContract.IMainPagePresent {

    @Override
    protected IModel createModel() {
        return null;
    }


    @Override
    public void uploadHistory(String data, String tag) {
//        AppNetModuleChat.createrRetrofit()
//                .uploadHistory(MyChatApp.getAccount(), MyChatApp.getUserToken(), MyChatApp.getUid(), 1, data)
//                .compose(RxScheduler.ObsIoMain(getView()))
//                .subscribe(new BaseObserver<BaseResult>(null) {
//                    @Override
//                    public void onSuccess(BaseResult o) {
//                        if (getView() != null) {
//                            getView().onSuccess(tag, o.message);
//                        }
//                    }
//                    @Override
//                    public void onError(String msg) {
//                        if (getView() != null) {
//                            //                            getView().onError(tag, msg);
//                        }
//                    }
//                });
    }
}
