package com.juntai.project.sell.mall.beans.sell;

import android.os.Parcel;

import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/5/8 14:03
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/8 14:03
 */
public class ShopDetailBean extends BaseResult {


    /**
     * data : {"id":1,"userId":101,"userAccount":"18669505929","name":"临沂市河东区粮油食品有限公司","headPortrait":"https://www.juntaikeji.com:21900/2022-05-23/1653289817732.png","introduction":"店铺简介","shopFraction":0,"phoneNumber":"18669505929","backImg":"https://www.juntaikeji.com:21900/2022-04-26/1111.jpg","cameraUrl":null,"shopImg":"https://www.juntaikeji.com:21900/2022-04-26/1111.jpg,https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png","shopImgList":["https://www.juntaikeji.com:21900/2022-04-26/1111.jpg","https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png"],"commodityStyle":1,"category":"蔬菜类,水果类,粮食类","categoryList":[1,2,3],"classifyList":[{"id":52,"shopId":1,"shopClassifyName":"大米","status":0,"createTime":"2022-05-10 09:29:21","addTime":null},{"id":53,"shopId":1,"shopClassifyName":"面","status":0,"createTime":"2022-05-10 09:29:23","addTime":null},{"id":54,"shopId":1,"shopClassifyName":"油","status":0,"createTime":"2022-05-10 09:29:26","addTime":null},{"id":61,"shopId":1,"shopClassifyName":"佐料","status":0,"createTime":"2022-05-10 10:20:20","addTime":null}],"gpsAddress":"山东省临沂市兰山区南坊北京路","longitude":"118.378125","latitude":"35.101104","businessLicense":"https://www.juntaikeji.com:21900/2022-05-23/1653289817739.png","idPositive":"https://www.juntaikeji.com:21900/2022-05-23/1653289817747.png","idSide":"https://www.juntaikeji.com:21900/2022-05-23/1653289817752.png","shopRealScene":"https://www.juntaikeji.com:21900/2022-05-23/1653289817761.png","isAgreement":1,"state":1,"createTime":"2022-04-24 12:03:01"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements android.os.Parcelable {
        /**
         * id : 1
         * userId : 101
         * userAccount : 18669505929
         * name : 临沂市河东区粮油食品有限公司
         * headPortrait : https://www.juntaikeji.com:21900/2022-05-23/1653289817732.png
         * introduction : 店铺简介
         * shopFraction : 0.0
         * phoneNumber : 18669505929
         * backImg : https://www.juntaikeji.com:21900/2022-04-26/1111.jpg
         * cameraUrl : null
         * shopImg : https://www.juntaikeji.com:21900/2022-04-26/1111.jpg,https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png
         * shopImgList : ["https://www.juntaikeji.com:21900/2022-04-26/1111.jpg","https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png"]
         * commodityStyle : 1
         * category : 蔬菜类,水果类,粮食类
         * categoryList : [1,2,3]
         * classifyList : [{"id":52,"shopId":1,"shopClassifyName":"大米","status":0,"createTime":"2022-05-10 09:29:21","addTime":null},{"id":53,"shopId":1,"shopClassifyName":"面","status":0,"createTime":"2022-05-10 09:29:23","addTime":null},{"id":54,"shopId":1,"shopClassifyName":"油","status":0,"createTime":"2022-05-10 09:29:26","addTime":null},{"id":61,"shopId":1,"shopClassifyName":"佐料","status":0,"createTime":"2022-05-10 10:20:20","addTime":null}]
         * gpsAddress : 山东省临沂市兰山区南坊北京路
         * longitude : 118.378125
         * latitude : 35.101104
         * businessLicense : https://www.juntaikeji.com:21900/2022-05-23/1653289817739.png
         * idPositive : https://www.juntaikeji.com:21900/2022-05-23/1653289817747.png
         * idSide : https://www.juntaikeji.com:21900/2022-05-23/1653289817752.png
         * shopRealScene : https://www.juntaikeji.com:21900/2022-05-23/1653289817761.png
         * isAgreement : 1
         * state : 1
         * createTime : 2022-04-24 12:03:01
         */

        private int id;
        private int userId;
        private String userAccount;
        private String name;
        private String headPortrait;
        private String introduction;
        private double shopFraction;
        private String phoneNumber;
        private String backImg;
        private String cameraUrl;
        private String shopImg;
        private int commodityStyle;
        private String category;
        private String gpsAddress;
        private String longitude;
        private String latitude;
        private String businessLicense;
        private String idPositive;
        private String idSide;
        private String shopRealScene;
        private int isAgreement;
        private int state;
        private String createTime;
        private List<String> shopImgList;
        private List<Integer> categoryList;
        private List<ClassifyListBean> classifyList;

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

        public String getUserAccount() {
            return userAccount;
        }

        public void setUserAccount(String userAccount) {
            this.userAccount = userAccount;
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

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public double getShopFraction() {
            return shopFraction;
        }

        public void setShopFraction(double shopFraction) {
            this.shopFraction = shopFraction;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getBackImg() {
            return backImg;
        }

        public void setBackImg(String backImg) {
            this.backImg = backImg;
        }

        public String getCameraUrl() {
            return cameraUrl == null ? "" : cameraUrl;
        }

        public void setCameraUrl(String cameraUrl) {
            this.cameraUrl = cameraUrl == null ? "" : cameraUrl;
        }

        public String getShopImg() {
            return shopImg;
        }

        public void setShopImg(String shopImg) {
            this.shopImg = shopImg;
        }

        public int getCommodityStyle() {
            return commodityStyle;
        }

        public void setCommodityStyle(int commodityStyle) {
            this.commodityStyle = commodityStyle;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getGpsAddress() {
            return gpsAddress;
        }

        public void setGpsAddress(String gpsAddress) {
            this.gpsAddress = gpsAddress;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getBusinessLicense() {
            return businessLicense;
        }

        public void setBusinessLicense(String businessLicense) {
            this.businessLicense = businessLicense;
        }

        public String getIdPositive() {
            return idPositive;
        }

        public void setIdPositive(String idPositive) {
            this.idPositive = idPositive;
        }

        public String getIdSide() {
            return idSide;
        }

        public void setIdSide(String idSide) {
            this.idSide = idSide;
        }

        public String getShopRealScene() {
            return shopRealScene;
        }

        public void setShopRealScene(String shopRealScene) {
            this.shopRealScene = shopRealScene;
        }

        public int getIsAgreement() {
            return isAgreement;
        }

        public void setIsAgreement(int isAgreement) {
            this.isAgreement = isAgreement;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public List<String> getShopImgList() {
            return shopImgList;
        }

        public void setShopImgList(List<String> shopImgList) {
            this.shopImgList = shopImgList;
        }

        public List<Integer> getCategoryList() {
            return categoryList;
        }

        public void setCategoryList(List<Integer> categoryList) {
            this.categoryList = categoryList;
        }

        public List<ClassifyListBean> getClassifyList() {
            return classifyList;
        }

        public void setClassifyList(List<ClassifyListBean> classifyList) {
            this.classifyList = classifyList;
        }

        public static class ClassifyListBean implements android.os.Parcelable {
            /**
             * id : 52
             * shopId : 1
             * shopClassifyName : 大米
             * status : 0
             * createTime : 2022-05-10 09:29:21
             * addTime : null
             */

            private int id;
            private int shopId;
            private String shopClassifyName;
            private int status;
            private String createTime;
            private String addTime;

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

            public String getShopClassifyName() {
                return shopClassifyName;
            }

            public void setShopClassifyName(String shopClassifyName) {
                this.shopClassifyName = shopClassifyName;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getAddTime() {
                return addTime == null ? "" : addTime;
            }

            public void setAddTime(String addTime) {
                this.addTime = addTime == null ? "" : addTime;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.id);
                dest.writeInt(this.shopId);
                dest.writeString(this.shopClassifyName);
                dest.writeInt(this.status);
                dest.writeString(this.createTime);
                dest.writeString(this.addTime);
            }

            public ClassifyListBean() {
            }

            protected ClassifyListBean(Parcel in) {
                this.id = in.readInt();
                this.shopId = in.readInt();
                this.shopClassifyName = in.readString();
                this.status = in.readInt();
                this.createTime = in.readString();
                this.addTime = in.readString();
            }

            public static final Creator<ClassifyListBean> CREATOR = new Creator<ClassifyListBean>() {
                @Override
                public ClassifyListBean createFromParcel(Parcel source) {
                    return new ClassifyListBean(source);
                }

                @Override
                public ClassifyListBean[] newArray(int size) {
                    return new ClassifyListBean[size];
                }
            };
        }

        public DataBean() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeInt(this.userId);
            dest.writeString(this.userAccount);
            dest.writeString(this.name);
            dest.writeString(this.headPortrait);
            dest.writeString(this.introduction);
            dest.writeDouble(this.shopFraction);
            dest.writeString(this.phoneNumber);
            dest.writeString(this.backImg);
            dest.writeString(this.cameraUrl);
            dest.writeString(this.shopImg);
            dest.writeInt(this.commodityStyle);
            dest.writeString(this.category);
            dest.writeString(this.gpsAddress);
            dest.writeString(this.longitude);
            dest.writeString(this.latitude);
            dest.writeString(this.businessLicense);
            dest.writeString(this.idPositive);
            dest.writeString(this.idSide);
            dest.writeString(this.shopRealScene);
            dest.writeInt(this.isAgreement);
            dest.writeInt(this.state);
            dest.writeString(this.createTime);
            dest.writeStringList(this.shopImgList);
            dest.writeList(this.categoryList);
            dest.writeList(this.classifyList);
        }

        protected DataBean(Parcel in) {
            this.id = in.readInt();
            this.userId = in.readInt();
            this.userAccount = in.readString();
            this.name = in.readString();
            this.headPortrait = in.readString();
            this.introduction = in.readString();
            this.shopFraction = in.readDouble();
            this.phoneNumber = in.readString();
            this.backImg = in.readString();
            this.cameraUrl = in.readString();
            this.shopImg = in.readString();
            this.commodityStyle = in.readInt();
            this.category = in.readString();
            this.gpsAddress = in.readString();
            this.longitude = in.readString();
            this.latitude = in.readString();
            this.businessLicense = in.readString();
            this.idPositive = in.readString();
            this.idSide = in.readString();
            this.shopRealScene = in.readString();
            this.isAgreement = in.readInt();
            this.state = in.readInt();
            this.createTime = in.readString();
            this.shopImgList = in.createStringArrayList();
            this.categoryList = new ArrayList<Integer>();
            in.readList(this.categoryList, Integer.class.getClassLoader());
            this.classifyList = new ArrayList<ClassifyListBean>();
            in.readList(this.classifyList, ClassifyListBean.class.getClassLoader());
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(this.data, flags);
    }

    public ShopDetailBean() {
    }

    protected ShopDetailBean(Parcel in) {
        super(in);
        this.data = in.readParcelable(DataBean.class.getClassLoader());
    }

    public static final Creator<ShopDetailBean> CREATOR = new Creator<ShopDetailBean>() {
        @Override
        public ShopDetailBean createFromParcel(Parcel source) {
            return new ShopDetailBean(source);
        }

        @Override
        public ShopDetailBean[] newArray(int size) {
            return new ShopDetailBean[size];
        }
    };
}
