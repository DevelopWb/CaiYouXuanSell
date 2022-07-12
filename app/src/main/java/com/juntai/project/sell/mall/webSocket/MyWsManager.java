package com.juntai.project.sell.mall.webSocket;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021-10-31 10:34
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-10-31 10:34
 */

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.example.chat.util.UserInfoManagerChat;
import com.juntai.disabled.basecomponent.base.BaseActivity;
import com.juntai.disabled.basecomponent.bean.ContactBean;
import com.juntai.disabled.basecomponent.bean.objectboxbean.MessageBodyBean;
import com.juntai.disabled.basecomponent.utils.GsonTools;
import com.juntai.disabled.basecomponent.utils.HawkProperty;
import com.juntai.disabled.basecomponent.utils.LogUtil;
import com.juntai.disabled.basecomponent.utils.NotificationTool;
import com.juntai.disabled.basecomponent.utils.eventbus.EventBusObject;
import com.juntai.disabled.basecomponent.utils.eventbus.EventManager;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.news.ChatActivity;
import com.rabtman.wsmanager.WsManager;
import com.rabtman.wsmanager.listener.WsStatusListener;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Response;
import okio.ByteString;

/**
 * MyWsManager管理类
 */
public class MyWsManager {

    private final String TAG = this.getClass().getSimpleName();

    private static MyWsManager wsManager;
    private static WsManager myWsManager;
    private WsManager.Builder builder;

    private Context mContext;

    public MyWsManager setWsUrl(String wsUrl) {
        if (builder != null) {
            LogUtil.e( wsUrl);
            builder.wsUrl(wsUrl);
        }
        return this;
    }

    //单例
    public static MyWsManager getInstance() {
        if (wsManager == null) {
            synchronized (MyWsManager.class) {
                if (wsManager == null) {
                    wsManager = new MyWsManager();
                }
            }
        }
        return wsManager;
    }

    public MyWsManager init(Context context) {
        this.mContext = context;
        builder = new WsManager.Builder(context)
                .client(new OkHttpClient().newBuilder()
                        .pingInterval(5, TimeUnit.SECONDS)
                        .retryOnConnectionFailure(true).build())
                //.needReconnect(true)                  //是否需要重连
                //.setHeaders(null)                     //设置请求头
                //.setReconnnectIMaxTime(30*1000)       //设置重连最大时长
                .needReconnect(true);
        return this;
    }

    public void startConnect() {
        try {
            if (myWsManager == null) {
                myWsManager = builder.build();
                myWsManager.setWsStatusListener(wsStatusListener);
            }
            if (!myWsManager.isWsConnected()) {
                myWsManager.startConnect();
            }

        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e( "myWsManager-----Exception");
        }
    }

    private WsStatusListener wsStatusListener = new WsStatusListener() {
        @Override
        public void onOpen(Response response) {
            LogUtil.e( "myWsManager-----onOpen");
        }

        @Override
        public void onMessage(String text) {
            LogUtil.e( text + "\n\n");
            //在这里接收和处理收到的ws数据吧
            if (!TextUtils.isEmpty(text)) {
                MessageBodyBean messageBody = GsonTools.changeGsonToBean(text, MessageBodyBean.class);
                //未读
                messageBody.setRead(false);
                HawkProperty.setRedPoint(mContext, 1);
                EventManager.getEventBus().post(new EventBusObject(EventBusObject.HANDLER_MESSAGE, messageBody));

                if (NotificationTool.SHOW_NOTIFICATION) {
                    showNotification(messageBody);
                }
            }
        }


        @Override
        public void onMessage(ByteString bytes) {
            LogUtil.e( "MyWsManager-----onMessage");
        }

        @Override
        public void onReconnect() {
            LogUtil.e( "MyWsManager-----onReconnect");
        }

        @Override
        public void onClosing(int code, String reason) {
            LogUtil.e( "MyWsManager-----onClosing");

            //这一步我个人认为是比较骚的操作,上面提及了设备会出现断开后无法连接的情况,那这种无法连接的情
            //况我发现有可能会卡在这个关闭过程中,因为如果是确实断开后会确实的启动重连机制,至于还有别的坑
            //我后面会补充;这里主要的目的就死让他跳出这个关闭中的状态,确实的关闭了ws先
            if (myWsManager != null) {
                myWsManager.stopConnect();
                myWsManager.startConnect();
            }

        }

        @Override
        public void onClosed(int code, String reason) {
            LogUtil.e( "MyWsManager-----onClosed");
        }

        @Override
        public void onFailure(Throwable t, Response response) {
            LogUtil.e( "MyWsManager-----onFailure");
        }
    };

    public void showNotification(MessageBodyBean messageBody) {
        LogUtil.e( "MyWsManager-----onMessage---发送通知");
//有时候会收到后台转发的自己发送的消息。。
        if (UserInfoManagerChat.getUserId() == messageBody.getFromUserId()) {
            return;
        }

        ContactBean contactBean = new ContactBean();
        String content = messageBody.getContent();
        Intent intent = new Intent();
        contactBean.setMessageBodyBean(messageBody);
        contactBean.setUserId(messageBody.getFromUserId());
        contactBean.setNickname(messageBody.getFromNickname());
        contactBean.setNickname(messageBody.getFromNickname());
        contactBean.setAccount(messageBody.getFromAccount());
        contactBean.setHeadPortrait(messageBody.getFromHead());
        String title = UserInfoManagerChat.getContactRemarkName(messageBody);

        intent.setClass(mContext, ChatActivity.class);
        intent.putExtra(BaseActivity.BASE_PARCELABLE, contactBean);

        // : 2021-12-08   这个地方需要获取到发送方在本地的备注名
        NotificationTool.sendNotifMessage(messageBody.getMsgType(), mContext, messageBody.getFromUserId(), title, content, R.mipmap.app_icon, false, intent);
    }

    //发送ws数据
    public void sendDataD(String content) {

        if (myWsManager != null && myWsManager.isWsConnected()) {
            boolean isSend = myWsManager.sendMessage(content);
            if (isSend) {
                LogUtil.e( "发送成功");
            } else {
                LogUtil.e( "发送失败");
            }
        } else {
            if (myWsManager != null) {
                myWsManager.stopConnect();
                myWsManager.startConnect();
            }
        }

    }

    //断开ws
    public void disconnect() {
        if (myWsManager != null)
            myWsManager.stopConnect();
        myWsManager = null;
    }


}