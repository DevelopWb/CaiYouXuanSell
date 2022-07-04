package com.example.live_moudle.websocket;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.SecureRandom;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/**
 * Created by dds on 2019/7/26.
 * ddssignsong@163.com
 */
public class SocketManager  {
    private final static String TAG = "dds_SocketManager";
    private MyWebSocket webSocket;


    private SocketManager() {

    }

    private static class Holder {
        private static final SocketManager socketManager = new SocketManager();
    }

    public static SocketManager getInstance() {
        return Holder.socketManager;
    }

    public void connect(String url, String userId, String roomId,String device,IEvent iEvent) {
        if (webSocket == null || !webSocket.isOpen()) {
            URI uri;
            try {
                String urls = String.format("%s/%s/%s/%s",url,userId,roomId,device);
                uri = new URI(urls);
            } catch (URISyntaxException e) {
                e.printStackTrace();
                return;
            }
            webSocket = new MyWebSocket(uri, iEvent);
            // 设置wss
            if (url.startsWith("wss")) {
                try {
                    SSLContext sslContext = SSLContext.getInstance("TLS");
                    if (sslContext != null) {
                        sslContext.init(null, new TrustManager[]{new MyWebSocket.TrustManagerTest()}, new SecureRandom());
                    }

                    SSLSocketFactory factory = null;
                    if (sslContext != null) {
                        factory = sslContext.getSocketFactory();
                    }

                    if (factory != null) {
                        webSocket.setSocket(factory.createSocket());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // 开始connect
            webSocket.connect();
        }else {
            if (webSocket.isClosed()) {
                webSocket.reconnect();
            }

        }


    }

    public  boolean  isConnect(){
        return webSocket!=null&&webSocket.isOpen();
    }

    public void unConnect() {
        if (webSocket != null) {
            webSocket.setConnectFlag(false);
            webSocket.close();
            webSocket = null;
        }

    }

    /**
     * 加入房间
     * @param room
     * @param userId
     */
    public void sendJoin(String room,String userId) {
        if (webSocket != null&&webSocket.isOpen()) {
            webSocket.sendJoin(room, userId);
        }
    }
    /**
     * 发送消息
     * @param room
     * @param userId
     */
    public void sendMsg(String room,String userId,String content) {
        if (webSocket != null&&webSocket.isOpen()) {
            webSocket.sendMsg(room, userId,content);
        }
    }


}
