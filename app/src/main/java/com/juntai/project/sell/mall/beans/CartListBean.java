package com.juntai.project.sell.mall.beans;

import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述  购物车清单
 * @CreateDate: 2022/5/5 9:08
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/5 9:08
 */
public class CartListBean  extends BaseResult {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 7
         * userId : 101
         * shopId : 1
         * shopName : 测试小店
         * commodityList : [{"id":4,"commodityId":5,"commodityName":"短裤","sku":"L,红色","stock":500,"sales":0,"unique":"0b03454b9d0246d99e8ffceaa5fcd103","price":10,"image":"https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png","commodityNum":1,"putAwayStatus":0,"createTime":"2022-05-03 17:18:07"},{"id":5,"commodityId":6,"commodityName":"半截袖","sku":null,"stock":0,"sales":null,"unique":null,"price":null,"image":"https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png","commodityNum":2,"putAwayStatus":0,"createTime":"2022-05-03 17:18:26"},{"id":7,"commodityId":6,"commodityName":"半截袖","sku":null,"stock":0,"sales":null,"unique":null,"price":null,"image":"https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png","commodityNum":1,"putAwayStatus":0,"createTime":"2022-05-03 17:22:41"},{"id":8,"commodityId":55,"commodityName":null,"sku":null,"stock":0,"sales":null,"unique":null,"price":null,"image":null,"commodityNum":1,"putAwayStatus":null,"createTime":"2022-05-03 17:30:23"}]
         */

        private int id;
        private int userId;
        private int shopId;
        private  boolean isShopSelect;
        private String shopName;
        private List<CommodityListBean> commodityList;

        public int getId() {
            return id;
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

        public boolean isShopSelect() {
            return isShopSelect;
        }

        public void setShopSelect(boolean shopSelect) {
            isShopSelect = shopSelect;
        }

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public List<CommodityListBean> getCommodityList() {
            return commodityList;
        }

        public void setCommodityList(List<CommodityListBean> commodityList) {
            this.commodityList = commodityList;
        }

        public static class CommodityListBean {
            /**
             * id : 4
             * commodityId : 5
             * commodityName : 短裤
             * sku : L,红色
             * stock : 500
             * sales : 0
             * unique : 0b03454b9d0246d99e8ffceaa5fcd103
             * price : 10.0
             * image : https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png
             * commodityNum : 1
             * putAwayStatus : 0
             * createTime : 2022-05-03 17:18:07
             */

            private int id;
            private int commodityId;
            private int shopId;
            private String commodityName;
            private String sku;
            private int stock;
            private int sales;
            private String unique;
            private double price;
            private String image;
            private int commodityNum;
            /**
             * 0上架；1下架
             */
            private Integer putAwayStatus;
            private String createTime;
            private boolean selected;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getCommodityId() {
                return commodityId;
            }

            public void setCommodityId(int commodityId) {
                this.commodityId = commodityId;
            }

            public String getCommodityName() {
                return commodityName;
            }

            public void setCommodityName(String commodityName) {
                this.commodityName = commodityName;
            }

            public int getShopId() {
                return shopId;
            }

            public void setShopId(int shopId) {
                this.shopId = shopId;
            }

            public String getSku() {
                return sku;
            }

            public void setSku(String sku) {
                this.sku = sku;
            }

            public int getStock() {
                return stock;
            }

            public void setStock(int stock) {
                this.stock = stock;
            }

            public int getSales() {
                return sales;
            }

            public void setSales(int sales) {
                this.sales = sales;
            }

            public String getUnique() {
                return unique;
            }

            public void setUnique(String unique) {
                this.unique = unique;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public int getCommodityNum() {
                return commodityNum;
            }

            public void setCommodityNum(int commodityNum) {
                this.commodityNum = commodityNum;
            }

            public Integer getPutAwayStatus() {
                return putAwayStatus;
            }

            public void setPutAwayStatus(Integer putAwayStatus) {
                this.putAwayStatus = putAwayStatus;
            }

            public boolean isSelected() {
                return selected;
            }

            public void setSelected(boolean selected) {
                this.selected = selected;
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
