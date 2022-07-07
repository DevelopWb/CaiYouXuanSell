package com.juntai.project.sell.mall.beans.sell;

import com.juntai.disabled.basecomponent.base.BaseResult;
import com.sunfusheng.marqueeview.IMarqueeItem;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述  店铺首页基本信息
 * @CreateDate: 2022/6/8 9:24
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/8 9:24
 */
public class ShopHomeInfoBean extends BaseResult {


    /**
     * data : {"shopId":1,"name":"测试小店","headPortrait":"https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png","logo":"https://www.juntaikeji.com","introduction":"店铺简介","categoryList":[{"id":1,"name":"食品","status":0},{"id":2,"name":"服装","status":0},{"id":3,"name":"百货","status":0}],"createTime":"2022-04-24 12:03:01","shopFraction":5,"todayOrderNum":0,"todayMoney":0,"todayVisitor":1,"sysNoticeList":[{"id":1,"title":"新手商家怎样才能谈到比较低的快递价格?"}],"commodityNum":2,"totalOrder":1,"waitOrder":0,"shipmentsOrder":1,"afterOrder":0}
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
         * shopId : 1
         * name : 测试小店
         * headPortrait : https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png
         * logo : https://www.juntaikeji.com
         * introduction : 店铺简介
         * categoryList : [{"id":1,"name":"食品","status":0},{"id":2,"name":"服装","status":0},{"id":3,"name":"百货","status":0}]
         * createTime : 2022-04-24 12:03:01
         * shopFraction : 5.0
         * todayOrderNum : 0
         * todayMoney : 0.0
         * todayVisitor : 1
         * sysNoticeList : [{"id":1,"title":"新手商家怎样才能谈到比较低的快递价格?"}]
         * commodityNum : 2
         * totalOrder : 1
         * waitOrder : 0
         * shipmentsOrder : 1
         * afterOrder : 0
         */

        private int shopId;
        private String name;
        private String headPortrait;
        private String logo;
        private String introduction;
        private String createTime;
        private String shareUrl;
        private double shopFraction;
        private int todayOrderNum;
        private double todayMoney;
        private int todayVisitor;
        private int commodityNum;
        private int totalOrder;
        private int waitOrder;
        private int shipmentsOrder;
        private int afterOrder;
        private List<CategoryListBean> categoryList;
        private List<SysNoticeListBean> sysNoticeList;

        public String getShareUrl() {
            return shareUrl == null ? "" : shareUrl;
        }

        public void setShareUrl(String shareUrl) {
            this.shareUrl = shareUrl == null ? "" : shareUrl;
        }

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHeadPortrait() {
            return headPortrait;
        }

        public void setHeadPortrait(String headPortrait) {
            this.headPortrait = headPortrait;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public double getShopFraction() {
            return shopFraction;
        }

        public void setShopFraction(double shopFraction) {
            this.shopFraction = shopFraction;
        }

        public int getTodayOrderNum() {
            return todayOrderNum;
        }

        public void setTodayOrderNum(int todayOrderNum) {
            this.todayOrderNum = todayOrderNum;
        }

        public double getTodayMoney() {
            return todayMoney;
        }

        public void setTodayMoney(double todayMoney) {
            this.todayMoney = todayMoney;
        }

        public int getTodayVisitor() {
            return todayVisitor;
        }

        public void setTodayVisitor(int todayVisitor) {
            this.todayVisitor = todayVisitor;
        }

        public int getCommodityNum() {
            return commodityNum;
        }

        public void setCommodityNum(int commodityNum) {
            this.commodityNum = commodityNum;
        }

        public int getTotalOrder() {
            return totalOrder;
        }

        public void setTotalOrder(int totalOrder) {
            this.totalOrder = totalOrder;
        }

        public int getWaitOrder() {
            return waitOrder;
        }

        public void setWaitOrder(int waitOrder) {
            this.waitOrder = waitOrder;
        }

        public int getShipmentsOrder() {
            return shipmentsOrder;
        }

        public void setShipmentsOrder(int shipmentsOrder) {
            this.shipmentsOrder = shipmentsOrder;
        }

        public int getAfterOrder() {
            return afterOrder;
        }

        public void setAfterOrder(int afterOrder) {
            this.afterOrder = afterOrder;
        }

        public List<CategoryListBean> getCategoryList() {
            return categoryList;
        }

        public void setCategoryList(List<CategoryListBean> categoryList) {
            this.categoryList = categoryList;
        }

        public List<SysNoticeListBean> getSysNoticeList() {
            return sysNoticeList;
        }

        public void setSysNoticeList(List<SysNoticeListBean> sysNoticeList) {
            this.sysNoticeList = sysNoticeList;
        }

        public static class CategoryListBean {
            /**
             * id : 1
             * name : 食品
             * status : 0
             */

            private int id;
            private String name;
            private int status;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }

        public static class SysNoticeListBean implements IMarqueeItem {
            /**
             * id : 1
             * title : 新手商家怎样才能谈到比较低的快递价格?
             */

            private int id;
            private String title;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            @Override
            public CharSequence marqueeMessage() {
                return title;
            }
        }
    }
}
