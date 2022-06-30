package com.juntai.project.sell.mall.beans;

import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @UpdateUser: 更新者
 */
public class MonthStatisticsBean extends BaseResult {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * jan : 0.0
         * feb : 0.0
         * mar : 0.0
         * apr : 0.0
         * may : 813.0
         * june : 0.0
         * july : 0.0
         * aug : 0.0
         * sept : 0.0
         * oct : 0.0
         * nov : 0.0
         * dec : 0.0
         */

        private String jan;
        private String feb;
        private String mar;
        private String apr;
        private String may;
        private String june;
        private String july;
        private String aug;
        private String sept;
        private String oct;
        private String nov;
        private String dec;

        public String getJan() {
            return jan;
        }

        public void setJan(String jan) {
            this.jan = jan;
        }

        public String getFeb() {
            return feb;
        }

        public void setFeb(String feb) {
            this.feb = feb;
        }

        public String getMar() {
            return mar;
        }

        public void setMar(String mar) {
            this.mar = mar;
        }

        public String getApr() {
            return apr;
        }

        public void setApr(String apr) {
            this.apr = apr;
        }

        public String getMay() {
            return may;
        }

        public void setMay(String may) {
            this.may = may;
        }

        public String getJune() {
            return june;
        }

        public void setJune(String june) {
            this.june = june;
        }

        public String getJuly() {
            return july;
        }

        public void setJuly(String july) {
            this.july = july;
        }

        public String getAug() {
            return aug;
        }

        public void setAug(String aug) {
            this.aug = aug;
        }

        public String getSept() {
            return sept;
        }

        public void setSept(String sept) {
            this.sept = sept;
        }

        public String getOct() {
            return oct;
        }

        public void setOct(String oct) {
            this.oct = oct;
        }

        public String getNov() {
            return nov;
        }

        public void setNov(String nov) {
            this.nov = nov;
        }

        public String getDec() {
            return dec;
        }

        public void setDec(String dec) {
            this.dec = dec;
        }
    }
}
