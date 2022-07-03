package com.juntai.disabled.basecomponent.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.juntai.disabled.basecomponent.bean.objectboxbean.MessageBodyBean;
import com.mcxtzhang.indexlib.IndexBar.bean.BaseIndexPinyinBean;


/**
 * @aouther tobato
 * @description 描述  联系人
 * @date 2021-09-24 16:40
 */

public class ContactBean extends BaseIndexPinyinBean implements Parcelable {
    private String token;//秘钥

    /**
     * userId : 97
     * account : 15735046365
     * phoneNumber : 15735046365
     * nickname : 铁人王进喜
     * headPortrait : https://www.juntaikeji.com:17002/head_img/b66f7c92700d41fc83b23a07d8b37537.jpeg
     * schoolName : 临沂一中
     * paymentType : 0
     */

    private int userId;
    /**
     * 实名认证状态（0未提交；1提交审核中；2审核通过；3审核失败）
     */
    private int realNameStatus;
    private int shopId;
    private int shopState;
    private String account;
    private String phoneNumber;
    private String nickname;
    private String headPortrait;
    /**
     * 开店申请不通过的原因
     */
    private String stateContent;
    private String schoolName;
    private int paymentType;

    private MessageBodyBean messageBodyBean;

    public MessageBodyBean getMessageBodyBean() {
        return messageBodyBean;
    }

    public void setMessageBodyBean(MessageBodyBean messageBodyBean) {
        this.messageBodyBean = messageBodyBean;
    }

    public int getRealNameStatus() {
        return realNameStatus;
    }

    public int getShopId() {
        return shopId;
    }

    public String getStateContent() {
        return stateContent == null ? "" : stateContent;
    }

    public void setStateContent(String stateContent) {
        this.stateContent = stateContent == null ? "" : stateContent;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getShopState() {
        return shopState;
    }

    public void setShopState(int shopState) {
        this.shopState = shopState;
    }

    public void setRealNameStatus(int realNameStatus) {
        this.realNameStatus = realNameStatus;
    }

    public String getToken() {
        return token == null ? "" : token;
    }

    public void setToken(String token) {
        this.token = token == null ? "" : token;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    @Override
    public String getTarget() {
        return getNickname();
    }

    public ContactBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.token);
        dest.writeInt(this.userId);
        dest.writeInt(this.realNameStatus);
        dest.writeInt(this.shopId);
        dest.writeInt(this.shopState);
        dest.writeString(this.account);
        dest.writeString(this.phoneNumber);
        dest.writeString(this.nickname);
        dest.writeString(this.headPortrait);
        dest.writeString(this.stateContent);
        dest.writeString(this.schoolName);
        dest.writeInt(this.paymentType);
        dest.writeParcelable(this.messageBodyBean, flags);
    }

    protected ContactBean(Parcel in) {
        this.token = in.readString();
        this.userId = in.readInt();
        this.realNameStatus = in.readInt();
        this.shopId = in.readInt();
        this.shopState = in.readInt();
        this.account = in.readString();
        this.phoneNumber = in.readString();
        this.nickname = in.readString();
        this.headPortrait = in.readString();
        this.stateContent = in.readString();
        this.schoolName = in.readString();
        this.paymentType = in.readInt();
        this.messageBodyBean = in.readParcelable(MessageBodyBean.class.getClassLoader());
    }

    public static final Creator<ContactBean> CREATOR = new Creator<ContactBean>() {
        @Override
        public ContactBean createFromParcel(Parcel source) {
            return new ContactBean(source);
        }

        @Override
        public ContactBean[] newArray(int size) {
            return new ContactBean[size];
        }
    };
}
