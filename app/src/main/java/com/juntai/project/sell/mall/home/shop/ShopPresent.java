package com.juntai.project.sell.mall.home.shop;

import com.juntai.disabled.basecomponent.base.BaseObserver;
import com.juntai.disabled.basecomponent.utils.RxScheduler;
import com.juntai.project.sell.mall.AppNetModuleMall;
import com.juntai.project.sell.mall.base.BaseAppMallPresent;
import com.juntai.project.sell.mall.beans.shop.ShopCommodityListBean;

import okhttp3.RequestBody;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/5/8 8:50
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/8 8:50
 */
public class ShopPresent extends BaseAppMallPresent {


    public void getShopCommodityList(RequestBody requestBody, String tag) {
        AppNetModuleMall.createrRetrofit()
                .getShopCommodityList(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<ShopCommodityListBean>(getView()) {
                    @Override
                    public void onSuccess(ShopCommodityListBean o) {
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
