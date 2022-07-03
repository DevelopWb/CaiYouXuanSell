package com.example.live_moudle.websocket;

/**
 * Created by dds on 2019/7/26.
 * ddssingsong@163.com
 */
public interface IEvent {


    void onOpen();



    void onPeers(String myId, String userList, int roomSize);

    void onNewPeer(String myId);


    void onLeave(String userId);



    void onDisConnect(String userId);

    void reConnect();

}
