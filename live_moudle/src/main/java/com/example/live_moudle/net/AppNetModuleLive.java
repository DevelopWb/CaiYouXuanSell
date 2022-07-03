package com.example.live_moudle.net;


import com.juntai.disabled.basecomponent.net.ApiRetrofit;

/**
 * 网络请求
 */
public class AppNetModuleLive {
    static AppServerLive appServer ;
    public static AppServerLive createrRetrofit() {
        if (appServer == null){
            appServer = ApiRetrofit.getInstance().getApiService(AppServerLive.class);
        }
        return appServer;
    }
}
