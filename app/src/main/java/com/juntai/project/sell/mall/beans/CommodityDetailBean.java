package com.juntai.project.sell.mall.beans;

import com.juntai.disabled.basecomponent.base.BaseResult;
import com.juntai.disabled.basecomponent.bean.objectboxbean.CommodityPropertyBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/5/3 13:56
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/3 13:56
 */
public class CommodityDetailBean extends BaseResult {


    /**
     * data : {"id":5,"shopId":1,"shopClassifyId":52,"categoryId":2,"name":"短裤","coverImg":"https://www.juntaikeji.com:21900/2022-04-26/1111.jpg","videoUrl":"https://www.juntaikeji.com:21900/2022-04-26/1650947938399.mp4","synopsis":"商品简介短裤","description":"sadfasf gsrdgsdghse ","price":10,"packingCharges":0,"transportCharges":2,"sales":200,"stock":2000,"isPostage":1,"browse":0,"result":[{"detail":["红色","黑色"],"value":"颜色"},{"detail":["M","L"],"value":"尺码"}],"value":[{"id":11,"commodityName":"短裤","sku":"M,红色","stock":500,"sales":0,"unique":"5b874f185f8343bda79e67bf890b3947","price":10,"image":"https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png"},{"id":12,"commodityName":"短裤","sku":"L,红色","stock":500,"sales":0,"unique":"0b03454b9d0246d99e8ffceaa5fcd103","price":10,"image":"https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png"},{"id":13,"commodityName":"短裤","sku":"M,黑色","stock":500,"sales":0,"unique":"be028e5ef938464bb6a6f2e975f12f83","price":10,"image":"https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png"},{"id":14,"commodityName":"短裤","sku":"L,黑色","stock":500,"sales":0,"unique":"c7410bf89e394828b27c86df34de9f28","price":10,"image":"https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png"}],"images":[{"id":243,"commodityId":5,"attrId":null,"imgUrl":"https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png"},{"id":244,"commodityId":5,"attrId":null,"imgUrl":"https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png"}]}
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
         * id : 5
         * shopId : 1
         * shopClassifyId : 52
         * categoryId : 2
         * name : 短裤
         * coverImg : https://www.juntaikeji.com:21900/2022-04-26/1111.jpg
         * videoUrl : https://www.juntaikeji.com:21900/2022-04-26/1650947938399.mp4
         * synopsis : 商品简介短裤
         * description : sadfasf gsrdgsdghse
         * price : 10.0
         * packingCharges : 0
         * transportCharges : 2.0
         * sales : 200
         * stock : 2000
         * isPostage : 1
         * browse : 0
         * result : [{"detail":["红色","黑色"],"value":"颜色"},{"detail":["M","L"],"value":"尺码"}]
         * value : [{"id":11,"commodityName":"短裤","sku":"M,红色","stock":500,"sales":0,"unique":"5b874f185f8343bda79e67bf890b3947","price":10,"image":"https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png"},{"id":12,"commodityName":"短裤","sku":"L,红色","stock":500,"sales":0,"unique":"0b03454b9d0246d99e8ffceaa5fcd103","price":10,"image":"https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png"},{"id":13,"commodityName":"短裤","sku":"M,黑色","stock":500,"sales":0,"unique":"be028e5ef938464bb6a6f2e975f12f83","price":10,"image":"https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png"},{"id":14,"commodityName":"短裤","sku":"L,黑色","stock":500,"sales":0,"unique":"c7410bf89e394828b27c86df34de9f28","price":10,"image":"https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png"}]
         * images : [{"id":243,"commodityId":5,"attrId":null,"imgUrl":"https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png"},{"id":244,"commodityId":5,"attrId":null,"imgUrl":"https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png"}]
         */

        private int id;
        private int shopId;
        /**
         * 店铺的userid
         */
        private int userId;
        private int shopClassifyId;
        private int categoryId;
        private String name;
        private String shareUrl;//分享地址

        private String coverImg;
        private String videoUrl;
        private String synopsis;
        private String description;
        private double price;
        private int packingCharges;
        private double transportCharges;
        private int sales;
        private int stock;
        private int isCollect;
        private int isPostage;
        private int browse;
        private List<ResultBean> result;
        private List<CommodityPropertyBean> value;
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

        public String getShareUrl() {
            return shareUrl == null ? "" : shareUrl;
        }

        public void setShareUrl(String shareUrl) {
            this.shareUrl = shareUrl == null ? "" : shareUrl;
        }

        public int getShopClassifyId() {
            return shopClassifyId;
        }

        public void setShopClassifyId(int shopClassifyId) {
            this.shopClassifyId = shopClassifyId;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
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

        public int getIsCollect() {
            return isCollect;
        }

        public void setIsCollect(int isCollect) {
            this.isCollect = isCollect;
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

        public List<ResultBean> getResult() {
            if (result == null) {
                return new ArrayList<>();
            }
            return result;
        }

        public void setResult(List<ResultBean> result) {
            this.result = result;
        }

        public List<CommodityPropertyBean> getValue() {
            if (value == null) {
                return new ArrayList<>();
            }
            return value;
        }

        public void setValue(List<CommodityPropertyBean> value) {
            this.value = value;
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

        public static class ResultBean {
            /**
             * detail : ["红色","黑色"]
             * value : 颜色
             */

            private String value;
            private List<String> detail;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public List<String> getDetail() {
                return detail;
            }

            public void setDetail(List<String> detail) {
                this.detail = detail;
            }
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
            private Object attrId;
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

            public void setAttrId(Object attrId) {
                this.attrId = attrId;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }
        }
    }
}
