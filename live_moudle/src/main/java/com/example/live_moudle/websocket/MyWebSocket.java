package com.example.live_moudle.websocket;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.live_moudle.bean.LiveMsgBean;
import com.juntai.disabled.basecomponent.utils.GsonTools;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;

import java.net.URI;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.X509TrustManager;

/**
 * Created by dds on 2019/7/26.
 * android_shuai@163.com
 */
public class MyWebSocket extends WebSocketClient {
    private final static String TAG = "dds_WebSocket";
    private final IEvent iEvent;
    private boolean connectFlag = false;


    public MyWebSocket(URI serverUri, IEvent event) {
        super(serverUri);
        this.iEvent = event;
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        Log.e("dds_error", "onClose:" + reason + "remote:" + remote);
        if (connectFlag) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.iEvent.reConnect();
        }

    }

    @Override
    public void onError(Exception ex) {
        Log.e("dds_error", "onError:" + ex.toString());
        connectFlag = false;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        Log.e("dds_info", "onOpen");
        this.iEvent.onOpen();
        connectFlag = true;
    }

    @Override
    public void onMessage(String message) {
        Log.d(TAG, message);
        handleMessage(message);
    }


    public void setConnectFlag(boolean flag) {
        connectFlag = flag;
    }

    // ---------------------------------------处理接收消息-------------------------------------

    private void handleMessage(String message) {
        LiveMsgBean eventBean = GsonTools.changeGsonToBean(message, LiveMsgBean.class);
        String eventName =eventBean.getEventName();
        if (eventName == null) return;
        // 别人加入自己创建的房间
        if (eventName.equals("__new_peer")) {
            handleNewPeer(eventBean);
            return;
        }
        // 离开房间
        if (eventName.equals("__leave")) {
            handleLeave(eventBean);
        }
        // 意外断开
        if (eventName.equals("__disconnect")) {
            handleDisConnect(eventBean);
        }
        // 接收到别人的发言
        if (eventName.equals("__new_talk")) {
            handleNewTalk(eventBean);
        }
        // 接收到直播结束
        if (eventName.equals("__live_close")) {
            handleLiveFinish(eventBean);
        }


    }

    private void handleDisConnect(LiveMsgBean eventBean) {
        this.iEvent.onDisConnect(eventBean);
    }

    private void handleNewTalk(LiveMsgBean eventBean) {
        this.iEvent.onNewTalk(eventBean);
    }


    private void handleNewPeer(LiveMsgBean eventBean) {
        this.iEvent.onNewPeer(eventBean);
    }

    private void handleLeave(LiveMsgBean eventBean) {
        this.iEvent.onLeave(eventBean);
    }
    private void handleLiveFinish(LiveMsgBean eventBean) {
        this.iEvent.onLiveFinished(eventBean);
    }


    //加入房间
    public void sendJoin(String room, String userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("eventName", "__join");
        Map<String, String> childMap = new HashMap<>();
        childMap.put("liveNumber", room);
        childMap.put("userId", userId);
        map.put("data", childMap);
        JSONObject object = new JSONObject(map);
        final String jsonString = object.toString();
        Log.d(TAG, "send-->" + jsonString);
        send(jsonString);
    }
    //发送消息
    public void sendMsg(String room, String userId,String content) {
        Map<String, Object> map = new HashMap<>();
        map.put("eventName", "__talk");
        Map<String, String> childMap = new HashMap<>();
        childMap.put("liveNumber", room);
        childMap.put("userId", userId);
        childMap.put("content", content);
        map.put("data", childMap);
        JSONObject object = new JSONObject(map);
        final String jsonString = object.toString();
        Log.d(TAG, "send-->" + jsonString);
        send(jsonString);
    }

    // 离开房间
    public void sendLeave(String myId, String room, String userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("eventName", "__leave");

        Map<String, Object> childMap = new HashMap<>();
        childMap.put("room", room);
        childMap.put("fromID", myId);
        childMap.put("userID", userId);

        map.put("data", childMap);
        JSONObject object = new JSONObject(map);
        final String jsonString = object.toString();
        Log.d(TAG, "send-->" + jsonString);
        if (isOpen()) {
            send(jsonString);
        }
    }


    // 忽略证书
    public static class TrustManagerTest implements X509TrustManager {

        @SuppressLint("TrustAllX509TrustManager")
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @SuppressLint("TrustAllX509TrustManager")
        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

}
