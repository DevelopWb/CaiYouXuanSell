package com.juntai.project.sell.mall.beans;

import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/5/20 9:56
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/20 9:56
 */
public class NewsListBean extends BaseResult {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * fromUserId : 101
         * fromNickname : 铁人王进喜
         * fromHead : https://www.juntaikeji.com:17002/head_img/8150129b631a4cc89702083d0ddb54a1.jpeg
         * toUserId : 97
         * msgType : 0
         * content : hello
         * createTime : 1652680217082
         */

        private int fromUserId;
        private String fromNickname;
        private String fromHead;
        private int toUserId;
        private int msgType;
        private int unread;
        private String content;
        private long createTime;

        public int getFromUserId() {
            return fromUserId;
        }

        public void setFromUserId(int fromUserId) {
            this.fromUserId = fromUserId;
        }

        public String getFromNickname() {
            return fromNickname;
        }

        public int getUnread() {
            return unread;
        }

        public void setUnread(int unread) {
            this.unread = unread;
        }

        public void setFromNickname(String fromNickname) {
            this.fromNickname = fromNickname;
        }

        public String getFromHead() {
            return fromHead;
        }

        public void setFromHead(String fromHead) {
            this.fromHead = fromHead;
        }

        public int getToUserId() {
            return toUserId;
        }

        public void setToUserId(int toUserId) {
            this.toUserId = toUserId;
        }

        public int getMsgType() {
            return msgType;
        }

        public void setMsgType(int msgType) {
            this.msgType = msgType;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }
    }
}
