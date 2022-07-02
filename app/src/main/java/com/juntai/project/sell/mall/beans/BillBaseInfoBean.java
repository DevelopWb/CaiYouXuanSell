package com.juntai.project.sell.mall.beans;

import android.os.Parcel;

import com.juntai.disabled.basecomponent.base.BaseResult;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @UpdateUser: 更新者
 */
public class BillBaseInfoBean extends BaseResult {

    /**
     * data : {"turnover":0,"settled":0,"withdrawalCash":0,"bankCode":"123456789","phoneNumber":"18669505929","realName":"张三","idCode":"1122334455667788","bankAddress":"山东临沂"}
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
         * turnover : 0.0
         * settled : 0.0
         * withdrawalCash : 0.0
         * bankCode : 123456789
         * phoneNumber : 18669505929
         * realName : 张三
         * idCode : 1122334455667788
         * bankAddress : 山东临沂
         */

        private double turnover;
        private double settled;
        private double withdrawalCash;
        private String bankCode;
        private String phoneNumber;
        private String realName;
        private String idCode;
        private String bankName;
        private String bankAddress;

        public String getBankName() {
            return bankName == null ? "" : bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName == null ? "" : bankName;
        }

        public double getTurnover() {
            return turnover;
        }

        public void setTurnover(double turnover) {
            this.turnover = turnover;
        }

        public double getSettled() {
            return settled;
        }

        public void setSettled(double settled) {
            this.settled = settled;
        }

        public double getWithdrawalCash() {
            return withdrawalCash;
        }

        public void setWithdrawalCash(double withdrawalCash) {
            this.withdrawalCash = withdrawalCash;
        }

        public String getBankCode() {
            return bankCode;
        }

        public void setBankCode(String bankCode) {
            this.bankCode = bankCode;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getIdCode() {
            return idCode;
        }

        public void setIdCode(String idCode) {
            this.idCode = idCode;
        }

        public String getBankAddress() {
            return bankAddress;
        }

        public void setBankAddress(String bankAddress) {
            this.bankAddress = bankAddress;
        }

        public DataBean() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeDouble(this.turnover);
            dest.writeDouble(this.settled);
            dest.writeDouble(this.withdrawalCash);
            dest.writeString(this.bankCode);
            dest.writeString(this.phoneNumber);
            dest.writeString(this.realName);
            dest.writeString(this.idCode);
            dest.writeString(this.bankName);
            dest.writeString(this.bankAddress);
        }

        protected DataBean(Parcel in) {
            this.turnover = in.readDouble();
            this.settled = in.readDouble();
            this.withdrawalCash = in.readDouble();
            this.bankCode = in.readString();
            this.phoneNumber = in.readString();
            this.realName = in.readString();
            this.idCode = in.readString();
            this.bankName = in.readString();
            this.bankAddress = in.readString();
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
