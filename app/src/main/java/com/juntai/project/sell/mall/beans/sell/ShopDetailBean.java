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
     * data : {"id":1,"userId":101,"userAccount":"18669505929","name":"测试小店","headPortrait":"https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png","introduction":"店铺简介","phoneNumber":"18669505929","backImg":"https://www.juntaikeji.com:21900/2022-04-26/1111.jpg","cameraUrl":"https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png","shopImg":"https://www.juntaikeji.com:21900/2022-04-26/1111.jpg,https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png","shopImgList":["https://www.juntaikeji.com:21900/2022-04-26/1111.jpg","https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png"],"commodityStyle":1,"category":"1,2,3","categoryList":[1,2,3],"gpsAddress":"山东临沂","longitude":"118.365616","latitude":"35.1511631","businessLicense":"https://www.juntaikeji.com:21900/2022-04-26/1111.jpg","idPositive":"https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png","idSide":"https://www.juntaikeji.com:21900/2022-04-26/1111.jpg","shopRealScene":"https://www.juntaikeji.com:21900/2022-04-26/1111.jpg","isAgreement":1,"state":2,"createTime":"2022-04-24 12:03:01"}
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
         * name : 测试小店
         * headPortrait : https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png
         * introduction : 店铺简介
         * phoneNumber : 18669505929
         * backImg : https://www.juntaikeji.com:21900/2022-04-26/1111.jpg
         * cameraUrl : https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png
         * shopImg : https://www.juntaikeji.com:21900/2022-04-26/1111.jpg,https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png
         * shopImgList : ["https://www.juntaikeji.com:21900/2022-04-26/1111.jpg","https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png"]
         * commodityStyle : 1
         * category : 1,2,3
         * categoryList : [1,2,3]
         * gpsAddress : 山东临沂
         * longitude : 118.365616
         * latitude : 35.1511631
         * businessLicense : https://www.juntaikeji.com:21900/2022-04-26/1111.jpg
         * idPositive : https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png
         * idSide : https://www.juntaikeji.com:21900/2022-04-26/1111.jpg
         * shopRealScene : https://www.juntaikeji.com:21900/2022-04-26/1111.jpg
         * isAgreement : 1
         * state : 2
         * createTime : 2022-04-24 12:03:01
         */

        private int id;
        private int userId;
        private String userAccount;
        private String name;
        private String headPortrait;
        private String introduction;
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
            return cameraUrl;
        }

        public void setCameraUrl(String cameraUrl) {
            this.cameraUrl = cameraUrl;
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
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readInt();
            this.userId = in.readInt();
            this.userAccount = in.readString();
            this.name = in.readString();
            this.headPortrait = in.readString();
            this.introduction = in.readString();
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
}
