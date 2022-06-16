package com.juntai.project.sell.mall.beans;

import com.google.gson.JsonObject;
import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @UpdateUser: 更新者
 */
public class CommodityFormatListBean extends BaseResult {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * commodityName : 短裤
         * unique : 5b874f185f8343bda79e67bf890b3947
         * price : 10.0
         * stock : 500
         * image : https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png
         * detail : {"颜色":"红色","尺码":"M"}
         */

        private String commodityName;
        private String unique;
        private double price;
        private int stock;
        private String image;
        private JsonObject detail;

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
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

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public JsonObject getDetail() {
            return detail;
        }

        public void setDetail(JsonObject detail) {
            this.detail = detail;
        }

    }
}
