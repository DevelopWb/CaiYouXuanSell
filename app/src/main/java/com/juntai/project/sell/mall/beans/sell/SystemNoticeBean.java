package com.juntai.project.sell.mall.beans.sell;

import com.juntai.disabled.basecomponent.base.BaseResult;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/6/8 17:11
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/8 17:11
 */
public class SystemNoticeBean extends BaseResult {


    /**
     * data : {"id":1,"type":1,"userId":null,"title":"新手商家怎样才能谈到比较低的快递价格?","content":"新手商家怎样才能谈到比较低的快递价格?\r新手商家怎样才能谈到比较低的快递价格？\r","contentId":null,"coverPhoto":null,"isRead":0,"createTime":"2022-05-10 10:04:49"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * type : 1
         * userId : null
         * title : 新手商家怎样才能谈到比较低的快递价格?
         * content : 新手商家怎样才能谈到比较低的快递价格?新手商家怎样才能谈到比较低的快递价格？
         * contentId : null
         * coverPhoto : null
         * isRead : 0
         * createTime : 2022-05-10 10:04:49
         */

        private int id;
        private int type;
        private int userId;
        private String title;
        private String content;
        private int contentId;
        private String coverPhoto;
        private int isRead;
        private String createTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getContentId() {
            return contentId;
        }

        public void setContentId(int contentId) {
            this.contentId = contentId;
        }

        public String getCoverPhoto() {
            return coverPhoto == null ? "" : coverPhoto;
        }

        public void setCoverPhoto(String coverPhoto) {
            this.coverPhoto = coverPhoto == null ? "" : coverPhoto;
        }

        public void setType(int type) {
            this.type = type;
        }


        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }


        public int getIsRead() {
            return isRead;
        }

        public void setIsRead(int isRead) {
            this.isRead = isRead;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}
