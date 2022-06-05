package com.juntai.project.sell.mall.beans;

import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/5/4 10:17
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/4 10:17
 */
public class CommodityEvaluationBean extends BaseResult {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * userId : 101
         * nickname : 铁人王进喜
         * headPortrait : https://www.juntaikeji.com:17002/head_img/8150129b631a4cc89702083d0ddb54a1.jpeg
         * orderId : 295
         * shopId : 1
         * commodityId : 5
         * shopScore : 5.0
         * commodityScore : 5.0
         * deliverySpeed : 5.0
         * evaluate : 很好
         * videoUrl : null
         * imgUrl : null
         * anonymity : 0
         * createTime : 2022-04-28 15:08:56
         * replyEvaluate : 感谢
         * replyTime : 2022-04-28 15:09:03
         * browseNum : 115
         */

        private int id;
        private int userId;
        private String nickname;
        private String headPortrait;
        private int orderId;
        private int shopId;
        private int commodityId;
        private double shopScore;
        private double commodityScore;
        private double deliverySpeed;
        private String evaluate;
        private String videoUrl;
        private String imgUrl;
        private int anonymity;
        private String createTime;
        private String replyEvaluate;
        private String replyTime;
        private int browseNum;
        private String videoCover;//评价视频封面路径

        public int getId() {
            return id;
        }

        public String getVideoCover() {
            return videoCover == null ? "" : videoCover;
        }

        public void setVideoCover(String videoCover) {
            this.videoCover = videoCover == null ? "" : videoCover;
        }


        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
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

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public double getShopScore() {
            return shopScore;
        }

        public void setShopScore(double shopScore) {
            this.shopScore = shopScore;
        }

        public double getCommodityScore() {
            return commodityScore;
        }

        public void setCommodityScore(double commodityScore) {
            this.commodityScore = commodityScore;
        }

        public double getDeliverySpeed() {
            return deliverySpeed;
        }

        public void setDeliverySpeed(double deliverySpeed) {
            this.deliverySpeed = deliverySpeed;
        }

        public String getEvaluate() {
            return evaluate;
        }

        public void setEvaluate(String evaluate) {
            this.evaluate = evaluate;
        }


        public int getAnonymity() {
            return anonymity;
        }

        public String getVideoUrl() {
            return videoUrl == null ? "" : videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl == null ? "" : videoUrl;
        }

        public String getImgUrl() {
            return imgUrl == null ? "" : imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl == null ? "" : imgUrl;
        }

        public void setAnonymity(int anonymity) {
            this.anonymity = anonymity;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getReplyEvaluate() {
            return replyEvaluate;
        }

        public void setReplyEvaluate(String replyEvaluate) {
            this.replyEvaluate = replyEvaluate;
        }

        public String getReplyTime() {
            return replyTime;
        }

        public void setReplyTime(String replyTime) {
            this.replyTime = replyTime;
        }

        public int getBrowseNum() {
            return browseNum;
        }

        public void setBrowseNum(int browseNum) {
            this.browseNum = browseNum;
        }
    }
}
