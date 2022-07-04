package com.example.live_moudle.bean;

public class LiveMsgBean {


    /**
     * eventName : __new_talk
     * data : {"userId":"101","nickname":"铁人王进喜","headPortrait":"https://www.juntaikeji.com:17002/head_img/8150129b631a4cc89702083d0ddb54a1.jpeg","account":"18669505929","content":"大家好"}
     */

    private String eventName;
    private String message;
    private DataBean data;

    public String getMessage() {
        return message == null ? "" : message;
    }

    public void setMessage(String message) {
        this.message = message == null ? "" : message;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * userId : 101
         * nickname : 铁人王进喜
         * headPortrait : https://www.juntaikeji.com:17002/head_img/8150129b631a4cc89702083d0ddb54a1.jpeg
         * account : 18669505929
         * content : 大家好
         */

        private String userId;
        private String nickname;
        private String liveNumber;
        private String headPortrait;
        private String account;
        private String content;
        private int online;

        public DataBean(String nickname, String content) {
            this.nickname = nickname;
            this.content = content;
        }

        public int getOnline() {
            return online;
        }

        public void setOnline(int online) {
            this.online = online;
        }

        public String getLiveNumber() {
            return liveNumber == null ? "" : liveNumber;
        }

        public void setLiveNumber(String liveNumber) {
            this.liveNumber = liveNumber == null ? "" : liveNumber;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getHeadPortrait() {
            return headPortrait;
        }

        public void setHeadPortrait(String headPortrait) {
            this.headPortrait = headPortrait;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
