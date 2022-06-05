package com.juntai.project.sell.mall.beans.order;

import android.os.Parcel;
import android.os.Parcelable;

import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述  创建订单
 * @CreateDate: 2022/5/11 9:52
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/11 9:52
 */
public class CreatOrderBean extends BaseResult {


    /**
     * data : {"addressId":1,"name":"顾启杭","phone":"18669505929","detailedAddress":"山东省临沂市河东区九曲街道阳光水岸","totalPrice":42,"totalCommodityNum":4,"shopList":[{"shopId":1,"shopName":"测试小店","transportCharges":2,"commodities":[{"commodityId":5,"commodityName":"短裤","sku":"L,红色","unique":"0b03454b9d0246d99e8ffceaa5fcd103","price":10,"image":"https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png","commodityNum":1},{"commodityId":5,"commodityName":"短裤","sku":"M,黑色","unique":"be028e5ef938464bb6a6f2e975f12f83","price":10,"image":"https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png","commodityNum":2}]},{"shopId":2,"shopName":"测试小店22","transportCharges":0,"commodities":[{"commodityId":6,"commodityName":"短裤","sku":"L,黑色","unique":"c7410bf89e394828b27c86df34de9f28","price":10,"image":"https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png","commodityNum":1}]}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        /**
         * addressId : 1
         * name : 顾启杭
         * phone : 18669505929
         * detailedAddress : 山东省临沂市河东区九曲街道阳光水岸
         * totalPrice : 42.0
         * totalCommodityNum : 4
         * shopList : [{"shopId":1,"shopName":"测试小店","transportCharges":2,"commodities":[{"commodityId":5,"commodityName":"短裤","sku":"L,红色","unique":"0b03454b9d0246d99e8ffceaa5fcd103","price":10,"image":"https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png","commodityNum":1},{"commodityId":5,"commodityName":"短裤","sku":"M,黑色","unique":"be028e5ef938464bb6a6f2e975f12f83","price":10,"image":"https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png","commodityNum":2}]},{"shopId":2,"shopName":"测试小店22","transportCharges":0,"commodities":[{"commodityId":6,"commodityName":"短裤","sku":"L,黑色","unique":"c7410bf89e394828b27c86df34de9f28","price":10,"image":"https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png","commodityNum":1}]}]
         */

        private int addressId;
        private String name;
        private String phone;
        private String detailedAddress;
        private double totalPrice;
        private int totalCommodityNum;
        private List<ShopListBean> shopList;

        public int getAddressId() {
            return addressId;
        }

        public void setAddressId(int addressId) {
            this.addressId = addressId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getDetailedAddress() {
            return detailedAddress;
        }

        public void setDetailedAddress(String detailedAddress) {
            this.detailedAddress = detailedAddress;
        }

        public double getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
        }

        public int getTotalCommodityNum() {
            return totalCommodityNum;
        }

        public void setTotalCommodityNum(int totalCommodityNum) {
            this.totalCommodityNum = totalCommodityNum;
        }

        public List<ShopListBean> getShopList() {
            return shopList;
        }

        public void setShopList(List<ShopListBean> shopList) {
            this.shopList = shopList;
        }

        public static class ShopListBean implements Parcelable {
            /**
             * shopId : 1
             * shopName : 测试小店
             * transportCharges : 2.0
             * commodities : [{"commodityId":5,"commodityName":"短裤","sku":"L,红色","unique":"0b03454b9d0246d99e8ffceaa5fcd103","price":10,"image":"https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png","commodityNum":1},{"commodityId":5,"commodityName":"短裤","sku":"M,黑色","unique":"be028e5ef938464bb6a6f2e975f12f83","price":10,"image":"https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png","commodityNum":2}]
             */

            private int shopId;
            private int id;
            private String shopName;
            private double transportCharges;
            private List<CommoditiesBean> commodities;

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

            public String getShopName() {
                return shopName;
            }

            public void setShopName(String shopName) {
                this.shopName = shopName;
            }

            public double getTransportCharges() {
                return transportCharges;
            }

            public void setTransportCharges(double transportCharges) {
                this.transportCharges = transportCharges;
            }

            public List<CommoditiesBean> getCommodities() {
                return commodities;
            }

            public void setCommodities(List<CommoditiesBean> commodities) {
                this.commodities = commodities;
            }

            public static class CommoditiesBean implements Parcelable {
                /**
                 * commodityId : 5
                 * commodityName : 短裤
                 * sku : L,红色
                 * unique : 0b03454b9d0246d99e8ffceaa5fcd103
                 * price : 10.0
                 * image : https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png
                 * commodityNum : 1
                 */

                private int commodityId;
                private String commodityName;
                private String sku;
                private String unique;
                private double price;
                private String image;
                private int commodityNum;
                private int id;

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

                public String getSku() {
                    return sku;
                }

                public void setSku(String sku) {
                    this.sku = sku;
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

                public CommoditiesBean() {
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeInt(this.commodityId);
                    dest.writeString(this.commodityName);
                    dest.writeString(this.sku);
                    dest.writeString(this.unique);
                    dest.writeDouble(this.price);
                    dest.writeString(this.image);
                    dest.writeInt(this.commodityNum);
                    dest.writeInt(this.id);
                }

                protected CommoditiesBean(Parcel in) {
                    this.commodityId = in.readInt();
                    this.commodityName = in.readString();
                    this.sku = in.readString();
                    this.unique = in.readString();
                    this.price = in.readDouble();
                    this.image = in.readString();
                    this.commodityNum = in.readInt();
                    this.id = in.readInt();
                }

                public static final Creator<CommoditiesBean> CREATOR = new Creator<CommoditiesBean>() {
                    @Override
                    public CommoditiesBean createFromParcel(Parcel source) {
                        return new CommoditiesBean(source);
                    }

                    @Override
                    public CommoditiesBean[] newArray(int size) {
                        return new CommoditiesBean[size];
                    }
                };
            }

            public ShopListBean() {
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.shopId);
                dest.writeInt(this.id);
                dest.writeString(this.shopName);
                dest.writeDouble(this.transportCharges);
                dest.writeTypedList(this.commodities);
            }

            protected ShopListBean(Parcel in) {
                this.shopId = in.readInt();
                this.id = in.readInt();
                this.shopName = in.readString();
                this.transportCharges = in.readDouble();
                this.commodities = in.createTypedArrayList(CommoditiesBean.CREATOR);
            }

            public static final Creator<ShopListBean> CREATOR = new Creator<ShopListBean>() {
                @Override
                public ShopListBean createFromParcel(Parcel source) {
                    return new ShopListBean(source);
                }

                @Override
                public ShopListBean[] newArray(int size) {
                    return new ShopListBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.addressId);
            dest.writeString(this.name);
            dest.writeString(this.phone);
            dest.writeString(this.detailedAddress);
            dest.writeDouble(this.totalPrice);
            dest.writeInt(this.totalCommodityNum);
            dest.writeList(this.shopList);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.addressId = in.readInt();
            this.name = in.readString();
            this.phone = in.readString();
            this.detailedAddress = in.readString();
            this.totalPrice = in.readDouble();
            this.totalCommodityNum = in.readInt();
            this.shopList = new ArrayList<ShopListBean>();
            in.readList(this.shopList, ShopListBean.class.getClassLoader());
        }

        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }
}
