package com.juntai.project.sell.mall.beans.order;

import android.os.Parcel;
import android.os.Parcelable;

import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/5/11 15:25
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/11 15:25
 */
public class OrderDetailBean extends BaseResult implements Parcelable {


    /**
     * id : 366
     * orderFormNumber : 1829061657879947
     * userId : 99
     * name : 王景
     * phone : 18763312667
     * address : 甘肃省　金昌市　永昌县　焦家庄镇　5555
     * shopId : 1
     * shopName : 测试小店
     * totalPrices : 123.0
     * transportCharges : 10.0
     * sumPackingCharges : 0.0
     * payPrice : 133.0
     * payPostage : 10.0
     * payPackingCharges : 0.0
     * payType : 4
     * establishTime : 2022-05-16 11:56:47
     * paymentTime : 2022-05-16 11:56:49
     * shipmentsTime : null
     * confirmTime : null
     * refundTime : 2022-05-16 11:57:05
     * cancelTime : null
     * expireTime : 2022-05-16 12:11:47
     * remark : null
     * state : 4
     * logisticsName : null
     * logisticsNumber : null
     * logisticsLink : null
     * commodityList : [{"id":87,"orderFormNumber":"1829061657879947","commodityId":15,"commodityName":"运动鞋","coverImg":"https://www.juntaikeji.com:21900/2022-05-13/1652406679479.png","prices":123,"commodityNum":1,"cartInfo":"红色;m","unique":"184fb43ae77d437f9c63d310b96b1aed"}]
     * returnOrderFormInfo : {"id":7,"userId":99,"orderId":366,"salesFormNumber":"7825945911249092","orderFormNumber":null,"payType":4,"cargoStatus":1,"causeId":1,"causeName":"快递一直未到","returnPrice":123,"remark":"ty","logisticsName":null,"logisticsNumber":null,"pictureOne":"https://www.juntaikeji.com:21900/2022-05-16/1652673424400.png","pictureTwo":null,"pictureThree":null,"refundTime":"2022-05-16 11:57:05","merchantAgreeStatus":2,"merchantConfirmTime":null,"merchantRefuseReason":null,"ultimatelyStatus":null,"ultimatelyTime":null}
     */

    private int id;
    private String orderFormNumber;
    /**
     * 订单支付的时候需要传入  总订单号
     */
    private String totalOrderFormNumber;
    private int userId;
    private String name;
    private String nickname;
    private String phone;
    private String address;
    private int shopId;
    private String shopName;
    private double totalPrices;
    private double transportCharges;
    private double sumPackingCharges;
    private double payPrice;
    private double payPostage;
    private double payPackingCharges;
    private int payType;
    private String establishTime;
    private String paymentTime;
    private String shipmentsTime;
    private String confirmTime;
    private String refundTime;
    private String cancelTime;
    private String expireTime;
    private String remark;
    /**
     * 4是等待商家处理  7是商家同意退款 8是商家不同意退款
     */
    private int state;
    private String logisticsName;
    private String logisticsNumber;
    private String logisticsLink;
    private ReturnOrderFormInfoBean returnOrderFormInfo;
    private CommodityEvaluateBean commodityEvaluateVo;
    private List<CommodityListBean> commodityList;

    public CommodityEvaluateBean getCommodityEvaluateVo() {
        return commodityEvaluateVo;
    }

    public void setCommodityEvaluateVo(CommodityEvaluateBean commodityEvaluateVo) {
        this.commodityEvaluateVo = commodityEvaluateVo;
    }

    public int getId() {
        return id;
    }

    public String getTotalOrderFormNumber() {
        return totalOrderFormNumber == null ? "" : totalOrderFormNumber;
    }

    public void setTotalOrderFormNumber(String totalOrderFormNumber) {
        this.totalOrderFormNumber = totalOrderFormNumber == null ? "" : totalOrderFormNumber;
    }

    public String getNickname() {
        return nickname == null ? "" : nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? "" : nickname;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderFormNumber() {
        return orderFormNumber;
    }

    public void setOrderFormNumber(String orderFormNumber) {
        this.orderFormNumber = orderFormNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public double getTotalPrices() {
        return totalPrices;
    }

    public void setTotalPrices(double totalPrices) {
        this.totalPrices = totalPrices;
    }

    public double getTransportCharges() {
        return transportCharges;
    }

    public void setTransportCharges(double transportCharges) {
        this.transportCharges = transportCharges;
    }

    public double getSumPackingCharges() {
        return sumPackingCharges;
    }

    public void setSumPackingCharges(double sumPackingCharges) {
        this.sumPackingCharges = sumPackingCharges;
    }

    public double getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(double payPrice) {
        this.payPrice = payPrice;
    }

    public double getPayPostage() {
        return payPostage;
    }

    public void setPayPostage(double payPostage) {
        this.payPostage = payPostage;
    }

    public double getPayPackingCharges() {
        return payPackingCharges;
    }

    public void setPayPackingCharges(double payPackingCharges) {
        this.payPackingCharges = payPackingCharges;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public String getEstablishTime() {
        return establishTime;
    }

    public void setEstablishTime(String establishTime) {
        this.establishTime = establishTime;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }


    public String getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(String refundTime) {
        this.refundTime = refundTime;
    }

    public String getShipmentsTime() {
        return shipmentsTime == null ? "" : shipmentsTime;
    }

    public void setShipmentsTime(String shipmentsTime) {
        this.shipmentsTime = shipmentsTime == null ? "" : shipmentsTime;
    }

    public String getConfirmTime() {
        return confirmTime == null ? "" : confirmTime;
    }

    public void setConfirmTime(String confirmTime) {
        this.confirmTime = confirmTime == null ? "" : confirmTime;
    }

    public String getCancelTime() {
        return cancelTime == null ? "" : cancelTime;
    }

    public void setCancelTime(String cancelTime) {
        this.cancelTime = cancelTime == null ? "" : cancelTime;
    }

    public String getRemark() {
        return remark == null ? "" : remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? "" : remark;
    }

    public String getLogisticsName() {
        return logisticsName == null ? "" : logisticsName;
    }

    public void setLogisticsName(String logisticsName) {
        this.logisticsName = logisticsName == null ? "" : logisticsName;
    }

    public String getLogisticsNumber() {
        return logisticsNumber == null ? "" : logisticsNumber;
    }

    public void setLogisticsNumber(String logisticsNumber) {
        this.logisticsNumber = logisticsNumber == null ? "" : logisticsNumber;
    }

    public String getLogisticsLink() {
        return logisticsLink == null ? "" : logisticsLink;
    }

    public void setLogisticsLink(String logisticsLink) {
        this.logisticsLink = logisticsLink == null ? "" : logisticsLink;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }



    public ReturnOrderFormInfoBean getReturnOrderFormInfo() {
        return returnOrderFormInfo;
    }

    public void setReturnOrderFormInfo(ReturnOrderFormInfoBean returnOrderFormInfo) {
        this.returnOrderFormInfo = returnOrderFormInfo;
    }

    public List<CommodityListBean> getCommodityList() {
        return commodityList;
    }

    public void setCommodityList(List<CommodityListBean> commodityList) {
        this.commodityList = commodityList;
    }

    public static class ReturnOrderFormInfoBean implements Parcelable {
        /**
         * id : 7
         * userId : 99
         * orderId : 366
         * salesFormNumber : 7825945911249092
         * orderFormNumber : null
         * payType : 4
         * cargoStatus : 1
         * causeId : 1
         * causeName : 快递一直未到
         * returnPrice : 123.0
         * remark : ty
         * logisticsName : null
         * logisticsNumber : null
         * pictureOne : https://www.juntaikeji.com:21900/2022-05-16/1652673424400.png
         * pictureTwo : null
         * pictureThree : null
         * refundTime : 2022-05-16 11:57:05
         * merchantAgreeStatus : 2
         * merchantConfirmTime : null
         * merchantRefuseReason : null
         * ultimatelyStatus : null
         * ultimatelyTime : null
         */

        private int id;
        private int userId;
        private int orderId;
        private String salesFormNumber;
        private String orderFormNumber;
        private int payType;
        private int cargoStatus;
        private int causeId;
        private String causeName;
        private double returnPrice;
        private String remark;
        private String logisticsName;
        private String logisticsNumber;
        private String pictureOne;
        private String pictureTwo;
        private String pictureThree;
        private String refundTime;
        private int merchantAgreeStatus;
        private String merchantConfirmTime;
        private String merchantRefuseReason;
        private int ultimatelyStatus;
        private String ultimatelyTime;

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

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public String getOrderFormNumber() {
            return orderFormNumber == null ? "" : orderFormNumber;
        }

        public void setOrderFormNumber(String orderFormNumber) {
            this.orderFormNumber = orderFormNumber == null ? "" : orderFormNumber;
        }

        public int getPayType() {
            return payType;
        }

        public void setPayType(int payType) {
            this.payType = payType;
        }

        public int getCargoStatus() {
            return cargoStatus;
        }

        public void setCargoStatus(int cargoStatus) {
            this.cargoStatus = cargoStatus;
        }

        public int getCauseId() {
            return causeId;
        }

        public void setCauseId(int causeId) {
            this.causeId = causeId;
        }

        public String getCauseName() {
            return causeName == null ? "" : causeName;
        }

        public void setCauseName(String causeName) {
            this.causeName = causeName == null ? "" : causeName;
        }

        public double getReturnPrice() {
            return returnPrice;
        }

        public void setReturnPrice(double returnPrice) {
            this.returnPrice = returnPrice;
        }

        public String getRemark() {
            return remark == null ? "" : remark;
        }

        public void setRemark(String remark) {
            this.remark = remark == null ? "" : remark;
        }

        public String getLogisticsName() {
            return logisticsName == null ? "" : logisticsName;
        }

        public void setLogisticsName(String logisticsName) {
            this.logisticsName = logisticsName == null ? "" : logisticsName;
        }

        public String getLogisticsNumber() {
            return logisticsNumber == null ? "" : logisticsNumber;
        }

        public void setLogisticsNumber(String logisticsNumber) {
            this.logisticsNumber = logisticsNumber == null ? "" : logisticsNumber;
        }

        public String getPictureOne() {
            return pictureOne == null ? "" : pictureOne;
        }

        public void setPictureOne(String pictureOne) {
            this.pictureOne = pictureOne == null ? "" : pictureOne;
        }

        public String getPictureTwo() {
            return pictureTwo == null ? "" : pictureTwo;
        }

        public void setPictureTwo(String pictureTwo) {
            this.pictureTwo = pictureTwo == null ? "" : pictureTwo;
        }

        public String getPictureThree() {
            return pictureThree == null ? "" : pictureThree;
        }

        public void setPictureThree(String pictureThree) {
            this.pictureThree = pictureThree == null ? "" : pictureThree;
        }

        public String getRefundTime() {
            return refundTime == null ? "" : refundTime;
        }

        public void setRefundTime(String refundTime) {
            this.refundTime = refundTime == null ? "" : refundTime;
        }

        public int getMerchantAgreeStatus() {
            return merchantAgreeStatus;
        }

        public void setMerchantAgreeStatus(int merchantAgreeStatus) {
            this.merchantAgreeStatus = merchantAgreeStatus;
        }

        public String getMerchantConfirmTime() {
            return merchantConfirmTime == null ? "" : merchantConfirmTime;
        }

        public void setMerchantConfirmTime(String merchantConfirmTime) {
            this.merchantConfirmTime = merchantConfirmTime == null ? "" : merchantConfirmTime;
        }

        public String getMerchantRefuseReason() {
            return merchantRefuseReason == null ? "" : merchantRefuseReason;
        }

        public void setMerchantRefuseReason(String merchantRefuseReason) {
            this.merchantRefuseReason = merchantRefuseReason == null ? "" : merchantRefuseReason;
        }

        public int getUltimatelyStatus() {
            return ultimatelyStatus;
        }

        public void setUltimatelyStatus(int ultimatelyStatus) {
            this.ultimatelyStatus = ultimatelyStatus;
        }

        public String getUltimatelyTime() {
            return ultimatelyTime == null ? "" : ultimatelyTime;
        }

        public void setUltimatelyTime(String ultimatelyTime) {
            this.ultimatelyTime = ultimatelyTime == null ? "" : ultimatelyTime;
        }

        public String getSalesFormNumber() {
            return salesFormNumber;
        }

        public void setSalesFormNumber(String salesFormNumber) {
            this.salesFormNumber = salesFormNumber;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeInt(this.userId);
            dest.writeInt(this.orderId);
            dest.writeString(this.salesFormNumber);
            dest.writeString(this.orderFormNumber);
            dest.writeInt(this.payType);
            dest.writeInt(this.cargoStatus);
            dest.writeInt(this.causeId);
            dest.writeString(this.causeName);
            dest.writeDouble(this.returnPrice);
            dest.writeString(this.remark);
            dest.writeString(this.logisticsName);
            dest.writeString(this.logisticsNumber);
            dest.writeString(this.pictureOne);
            dest.writeString(this.pictureTwo);
            dest.writeString(this.pictureThree);
            dest.writeString(this.refundTime);
            dest.writeInt(this.merchantAgreeStatus);
            dest.writeString(this.merchantConfirmTime);
            dest.writeString(this.merchantRefuseReason);
            dest.writeInt(this.ultimatelyStatus);
            dest.writeString(this.ultimatelyTime);
        }

        public ReturnOrderFormInfoBean() {
        }

        protected ReturnOrderFormInfoBean(Parcel in) {
            this.id = in.readInt();
            this.userId = in.readInt();
            this.orderId = in.readInt();
            this.salesFormNumber = in.readString();
            this.orderFormNumber = in.readString();
            this.payType = in.readInt();
            this.cargoStatus = in.readInt();
            this.causeId = in.readInt();
            this.causeName = in.readString();
            this.returnPrice = in.readDouble();
            this.remark = in.readString();
            this.logisticsName = in.readString();
            this.logisticsNumber = in.readString();
            this.pictureOne = in.readString();
            this.pictureTwo = in.readString();
            this.pictureThree = in.readString();
            this.refundTime = in.readString();
            this.merchantAgreeStatus = in.readInt();
            this.merchantConfirmTime = in.readString();
            this.merchantRefuseReason = in.readString();
            this.ultimatelyStatus = in.readInt();
            this.ultimatelyTime = in.readString();
        }

        public static final Parcelable.Creator<ReturnOrderFormInfoBean> CREATOR = new Parcelable.Creator<ReturnOrderFormInfoBean>() {
            @Override
            public ReturnOrderFormInfoBean createFromParcel(Parcel source) {
                return new ReturnOrderFormInfoBean(source);
            }

            @Override
            public ReturnOrderFormInfoBean[] newArray(int size) {
                return new ReturnOrderFormInfoBean[size];
            }
        };
    }

    public static class CommodityListBean implements Parcelable {
        /**
         * id : 87
         * orderFormNumber : 1829061657879947
         * commodityId : 15
         * commodityName : 运动鞋
         * coverImg : https://www.juntaikeji.com:21900/2022-05-13/1652406679479.png
         * prices : 123.0
         * commodityNum : 1
         * cartInfo : 红色;m
         * unique : 184fb43ae77d437f9c63d310b96b1aed
         */

        private int id;
        private String orderFormNumber;
        private int commodityId;
        private int orderId;
        private int shopId;
        private String commodityName;
        private String coverImg;
        private String shopName;
        private double prices;
        private int commodityNum;
        private String cartInfo;
        private String unique;

        public String getShopName() {
            return shopName == null ? "" : shopName;
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

        public void setShopName(String shopName) {
            this.shopName = shopName == null ? "" : shopName;
        }

        private int orderStatus;

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOrderFormNumber() {
            return orderFormNumber;
        }

        public void setOrderFormNumber(String orderFormNumber) {
            this.orderFormNumber = orderFormNumber;
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

        public String getCoverImg() {
            return coverImg;
        }

        public void setCoverImg(String coverImg) {
            this.coverImg = coverImg;
        }

        public double getPrices() {
            return prices;
        }

        public void setPrices(double prices) {
            this.prices = prices;
        }

        public int getCommodityNum() {
            return commodityNum;
        }

        public void setCommodityNum(int commodityNum) {
            this.commodityNum = commodityNum;
        }

        public String getCartInfo() {
            return cartInfo;
        }

        public void setCartInfo(String cartInfo) {
            this.cartInfo = cartInfo;
        }

        public String getUnique() {
            return unique;
        }

        public void setUnique(String unique) {
            this.unique = unique;
        }

        public CommodityListBean() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.orderFormNumber);
            dest.writeInt(this.commodityId);
            dest.writeInt(this.orderId);
            dest.writeInt(this.shopId);
            dest.writeString(this.commodityName);
            dest.writeString(this.coverImg);
            dest.writeString(this.shopName);
            dest.writeDouble(this.prices);
            dest.writeInt(this.commodityNum);
            dest.writeString(this.cartInfo);
            dest.writeString(this.unique);
            dest.writeInt(this.orderStatus);
        }

        protected CommodityListBean(Parcel in) {
            this.id = in.readInt();
            this.orderFormNumber = in.readString();
            this.commodityId = in.readInt();
            this.orderId = in.readInt();
            this.shopId = in.readInt();
            this.commodityName = in.readString();
            this.coverImg = in.readString();
            this.shopName = in.readString();
            this.prices = in.readDouble();
            this.commodityNum = in.readInt();
            this.cartInfo = in.readString();
            this.unique = in.readString();
            this.orderStatus = in.readInt();
        }

        public static final Creator<CommodityListBean> CREATOR = new Creator<CommodityListBean>() {
            @Override
            public CommodityListBean createFromParcel(Parcel source) {
                return new CommodityListBean(source);
            }

            @Override
            public CommodityListBean[] newArray(int size) {
                return new CommodityListBean[size];
            }
        };
    }

    public OrderDetailBean() {
    }


    public static class CommodityEvaluateBean implements Parcelable {
        /**
         * evaluate : 很好
         * createTime : 2022-06-20 14:30:55
         * imgUrl : https://www.juntaikeji.com:21900/2022-06-20/1655706641700572.jpeg
         * videoCover : https://www.juntaikeji.com:21900/2022-06-20/1655706643609.jpg
         * videoUrl : https://www.juntaikeji.com:21900/2022-06-20/1655706643609.mp4
         */

        private String evaluate;
        private String createTime;
        private String imgUrl;
        private String videoCover;
        private String videoUrl;

        public CommodityEvaluateBean() {
        }

        public String getEvaluate() {
            return evaluate;
        }

        public void setEvaluate(String evaluate) {
            this.evaluate = evaluate;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getVideoCover() {
            return videoCover;
        }

        public void setVideoCover(String videoCover) {
            this.videoCover = videoCover;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.evaluate);
            dest.writeString(this.createTime);
            dest.writeString(this.imgUrl);
            dest.writeString(this.videoCover);
            dest.writeString(this.videoUrl);
        }

        protected CommodityEvaluateBean(Parcel in) {
            this.evaluate = in.readString();
            this.createTime = in.readString();
            this.imgUrl = in.readString();
            this.videoCover = in.readString();
            this.videoUrl = in.readString();
        }

        public static final Creator<CommodityEvaluateBean> CREATOR = new Creator<CommodityEvaluateBean>() {
            @Override
            public CommodityEvaluateBean createFromParcel(Parcel source) {
                return new CommodityEvaluateBean(source);
            }

            @Override
            public CommodityEvaluateBean[] newArray(int size) {
                return new CommodityEvaluateBean[size];
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
        dest.writeInt(this.id);
        dest.writeString(this.orderFormNumber);
        dest.writeString(this.totalOrderFormNumber);
        dest.writeInt(this.userId);
        dest.writeString(this.name);
        dest.writeString(this.nickname);
        dest.writeString(this.phone);
        dest.writeString(this.address);
        dest.writeInt(this.shopId);
        dest.writeString(this.shopName);
        dest.writeDouble(this.totalPrices);
        dest.writeDouble(this.transportCharges);
        dest.writeDouble(this.sumPackingCharges);
        dest.writeDouble(this.payPrice);
        dest.writeDouble(this.payPostage);
        dest.writeDouble(this.payPackingCharges);
        dest.writeInt(this.payType);
        dest.writeString(this.establishTime);
        dest.writeString(this.paymentTime);
        dest.writeString(this.shipmentsTime);
        dest.writeString(this.confirmTime);
        dest.writeString(this.refundTime);
        dest.writeString(this.cancelTime);
        dest.writeString(this.expireTime);
        dest.writeString(this.remark);
        dest.writeInt(this.state);
        dest.writeString(this.logisticsName);
        dest.writeString(this.logisticsNumber);
        dest.writeString(this.logisticsLink);
        dest.writeParcelable(this.returnOrderFormInfo, flags);
        dest.writeParcelable(this.commodityEvaluateVo, flags);
        dest.writeTypedList(this.commodityList);
    }

    protected OrderDetailBean(Parcel in) {
        super(in);
        this.id = in.readInt();
        this.orderFormNumber = in.readString();
        this.totalOrderFormNumber = in.readString();
        this.userId = in.readInt();
        this.name = in.readString();
        this.nickname = in.readString();
        this.phone = in.readString();
        this.address = in.readString();
        this.shopId = in.readInt();
        this.shopName = in.readString();
        this.totalPrices = in.readDouble();
        this.transportCharges = in.readDouble();
        this.sumPackingCharges = in.readDouble();
        this.payPrice = in.readDouble();
        this.payPostage = in.readDouble();
        this.payPackingCharges = in.readDouble();
        this.payType = in.readInt();
        this.establishTime = in.readString();
        this.paymentTime = in.readString();
        this.shipmentsTime = in.readString();
        this.confirmTime = in.readString();
        this.refundTime = in.readString();
        this.cancelTime = in.readString();
        this.expireTime = in.readString();
        this.remark = in.readString();
        this.state = in.readInt();
        this.logisticsName = in.readString();
        this.logisticsNumber = in.readString();
        this.logisticsLink = in.readString();
        this.returnOrderFormInfo = in.readParcelable(ReturnOrderFormInfoBean.class.getClassLoader());
        this.commodityEvaluateVo = in.readParcelable(CommodityEvaluateBean.class.getClassLoader());
        this.commodityList = in.createTypedArrayList(CommodityListBean.CREATOR);
    }

    public static final Creator<OrderDetailBean> CREATOR = new Creator<OrderDetailBean>() {
        @Override
        public OrderDetailBean createFromParcel(Parcel source) {
            return new OrderDetailBean(source);
        }

        @Override
        public OrderDetailBean[] newArray(int size) {
            return new OrderDetailBean[size];
        }
    };
}
