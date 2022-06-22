package com.juntai.project.sell.mall;


import com.example.chat.MyChatApp;
import com.juntai.disabled.video.ModuleVideo_Init;
import com.mob.MobSDK;

/**
 * @aouther Ma
 * @date 2019/3/12
 */
public class MyApp extends MyChatApp {
    public static MyApp app;
    public boolean isFinish = false;
    public static long lastClickTime;//上次点击按钮时间
    public static int timeLimit = 1000;




    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        MobSDK.init(this);
        //Video模块初始化
        ModuleVideo_Init.init();
        // TODO: 2022/6/21 暂时关闭
//        if (UserInfoManagerMall.isLogin()) {
//            MyWsManager.getInstance()
//                    .init(getApplicationContext())
//                    .setWsUrl(AppHttpPathMall.BASE_SOCKET + UserInfoManagerMall.getUserId())
//                    .startConnect();
//        } else {
//            MyWsManager.getInstance()
//                    .init(getApplicationContext());
//        }
    }



    /**
     * 防止暴力点击
     */
    public synchronized static boolean isFastClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < timeLimit) {
            return true;
        }
        lastClickTime = time;
        return false;
    }


}
