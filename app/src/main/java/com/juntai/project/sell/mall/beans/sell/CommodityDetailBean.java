package com.juntai.project.sell.mall.beans.sell;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/6/12 15:54
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/12 15:54
 */
public class CommodityDetailBean {

    private int id;
    private int shopId;
    private int userId;
    private int shopClassifyId;
    private String shopClassifyName;
    private int categoryId;
    private String categoryName;
    private String name;
    private String coverImg;
    private String videoUrl;
    private String synopsis;
    private String description;
    private double price;
    private int packingCharges;
    private double transportCharges;
    private int sales;
    private int stock;
    private int isPostage;
    private int browse;
    private String isCollect;
    private String result;
    private String value;
    private List<ImagesBean> images;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getShopClassifyId() {
        return shopClassifyId;
    }

    public void setShopClassifyId(int shopClassifyId) {
        this.shopClassifyId = shopClassifyId;
    }

    public String getShopClassifyName() {
        return shopClassifyName == null ? "" : shopClassifyName;
    }

    public void setShopClassifyName(String shopClassifyName) {
        this.shopClassifyName = shopClassifyName == null ? "" : shopClassifyName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName == null ? "" : categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? "" : categoryName;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name == null ? "" : name;
    }

    public String getCoverImg() {
        return coverImg == null ? "" : coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg == null ? "" : coverImg;
    }

    public String getVideoUrl() {
        return videoUrl == null ? "" : videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl == null ? "" : videoUrl;
    }

    public String getSynopsis() {
        return synopsis == null ? "" : synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis == null ? "" : synopsis;
    }

    public String getDescription() {
        return description == null ? "" : description;
    }

    public void setDescription(String description) {
        this.description = description == null ? "" : description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPackingCharges() {
        return packingCharges;
    }

    public void setPackingCharges(int packingCharges) {
        this.packingCharges = packingCharges;
    }

    public double getTransportCharges() {
        return transportCharges;
    }

    public void setTransportCharges(double transportCharges) {
        this.transportCharges = transportCharges;
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

    public int getIsPostage() {
        return isPostage;
    }

    public void setIsPostage(int isPostage) {
        this.isPostage = isPostage;
    }

    public int getBrowse() {
        return browse;
    }

    public void setBrowse(int browse) {
        this.browse = browse;
    }

    public String getIsCollect() {
        return isCollect == null ? "" : isCollect;
    }

    public void setIsCollect(String isCollect) {
        this.isCollect = isCollect == null ? "" : isCollect;
    }

    public String getResult() {
        return result == null ? "" : result;
    }

    public void setResult(String result) {
        this.result = result == null ? "" : result;
    }

    public String getValue() {
        return value == null ? "" : value;
    }

    public void setValue(String value) {
        this.value = value == null ? "" : value;
    }

    public List<ImagesBean> getImages() {
        if (images == null) {
            return new ArrayList<>();
        }
        return images;
    }

    public void setImages(List<ImagesBean> images) {
        this.images = images;
    }

    public static class ImagesBean {
        /**
         * id : 243
         * commodityId : 5
         * attrId : null
         * imgUrl : https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png
         */

        private int id;
        private int commodityId;
        private int attrId;
        private String imgUrl;

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

        public Object getAttrId() {
            return attrId;
        }

        public void setAttrId(int attrId) {
            this.attrId = attrId;
        }

        public String getImgUrl() {
            return imgUrl == null ? "" : imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }
    }
}
