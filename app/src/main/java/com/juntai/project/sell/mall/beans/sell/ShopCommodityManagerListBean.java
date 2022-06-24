package com.juntai.project.sell.mall.beans.sell;

import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/5/8 14:04
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/8 14:04
 */
public class ShopCommodityManagerListBean extends BaseResult {


    /**
     * data : {"totalCount":2,"pageSize":5,"totalPage":1,"currPage":1,"list":[{"id":5,"name":"短裤","coverImg":"https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png","shopClassifyId":52,"shopClassifyName":"上衣","price":10,"sales":200,"stock":500,"putAwayStatus":0,"createTime":"2022-05-10 10:00"},{"id":6,"name":"半截袖","coverImg":"https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png","shopClassifyId":52,"shopClassifyName":"上衣","price":19.99,"sales":360,"stock":1000,"putAwayStatus":0,"createTime":"2022-05-10 10:00"}]}
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
         * totalCount : 2
         * pageSize : 5
         * totalPage : 1
         * currPage : 1
         * list : [{"id":5,"name":"短裤","coverImg":"https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png","shopClassifyId":52,"shopClassifyName":"上衣","price":10,"sales":200,"stock":500,"putAwayStatus":0,"createTime":"2022-05-10 10:00"},{"id":6,"name":"半截袖","coverImg":"https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png","shopClassifyId":52,"shopClassifyName":"上衣","price":19.99,"sales":360,"stock":1000,"putAwayStatus":0,"createTime":"2022-05-10 10:00"}]
         */

        private int totalCount;
        private int pageSize;
        private int totalPage;
        private int currPage;
        private List<ListBean> list;

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getCurrPage() {
            return currPage;
        }

        public void setCurrPage(int currPage) {
            this.currPage = currPage;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 5
             * name : 短裤
             * coverImg : https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png
             * shopClassifyId : 52
             * shopClassifyName : 上衣
             * price : 10.0
             * sales : 200
             * stock : 500
             * putAwayStatus : 0
             * createTime : 2022-05-10 10:00
             */

            private int id;
            private String name;
            private String coverImg;
            private int shopClassifyId;
            private String shopClassifyName;
            private double price;
            private int sales;
            private int stock;
            private int putAwayStatus;
            private String createTime;

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

            public String getCoverImg() {
                return coverImg;
            }

            public void setCoverImg(String coverImg) {
                this.coverImg = coverImg;
            }

            public int getShopClassifyId() {
                return shopClassifyId;
            }

            public void setShopClassifyId(int shopClassifyId) {
                this.shopClassifyId = shopClassifyId;
            }

            public String getShopClassifyName() {
                return shopClassifyName;
            }

            public void setShopClassifyName(String shopClassifyName) {
                this.shopClassifyName = shopClassifyName;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public int getSales() {
                return sales;
            }

            public void setSales(int sales) {
                this.sales = sales;
            }

            public int getStock() {
                return stock;
            }

            public void setStock(int stock) {
                this.stock = stock;
            }

            public int getPutAwayStatus() {
                return putAwayStatus;
            }

            public void setPutAwayStatus(int putAwayStatus) {
                this.putAwayStatus = putAwayStatus;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }
        }
    }
}
