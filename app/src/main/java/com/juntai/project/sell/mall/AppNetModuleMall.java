package com.juntai.project.sell.mall;


import com.juntai.disabled.basecomponent.net.ApiRetrofit;

/**
 * 网络请求
 */
public class AppNetModuleMall {
    static AppServerMall appServer ;
    public static AppServerMall createrRetrofit() {
        if (appServer == null){
            appServer = ApiRetrofit.getInstance().getApiService(AppServerMall.class);
        }
        return appServer;
    }
}
