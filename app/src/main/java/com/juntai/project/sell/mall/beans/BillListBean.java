package com.juntai.project.sell.mall.beans;

import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @UpdateUser: 更新者
 */
public class BillListBean extends BaseResult {


    /**
     * data : {"revenue":36.97,"list":[{"userId":99,"headPortrait":"https://www.juntaikeji.com:17003/head_img/568c81a20742458aaf68390977b16cbe.jpeg","nickname":"王彬","payPrice":23.98,"payType":0,"paymentTime":"05月14 11:53"},{"userId":99,"headPortrait":"https://www.juntaikeji.com:17003/head_img/568c81a20742458aaf68390977b16cbe.jpeg","nickname":"王彬","payPrice":12.99,"payType":4,"paymentTime":"05月14 11:55"}]}
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
         * revenue : 36.97
         * list : [{"userId":99,"headPortrait":"https://www.juntaikeji.com:17003/head_img/568c81a20742458aaf68390977b16cbe.jpeg","nickname":"王彬","payPrice":23.98,"payType":0,"paymentTime":"05月14 11:53"},{"userId":99,"headPortrait":"https://www.juntaikeji.com:17003/head_img/568c81a20742458aaf68390977b16cbe.jpeg","nickname":"王彬","payPrice":12.99,"payType":4,"paymentTime":"05月14 11:55"}]
         */

        private double revenue;
        private List<ListBean> list;

        public double getRevenue() {
            return revenue;
        }

        public void setRevenue(double revenue) {
            this.revenue = revenue;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * userId : 99
             * headPortrait : https://www.juntaikeji.com:17003/head_img/568c81a20742458aaf68390977b16cbe.jpeg
             * nickname : 王彬
             * payPrice : 23.98
             * payType : 0
             * paymentTime : 05月14 11:53
             */

            private int userId;
            private String headPortrait;
            private String nickname;
            private double payPrice;
            private int payType;
            private String paymentTime;

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getHeadPortrait() {
                return headPortrait;
            }

            public void setHeadPortrait(String headPortrait) {
                this.headPortrait = headPortrait;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public double getPayPrice() {
                return payPrice;
            }

            public void setPayPrice(double payPrice) {
                this.payPrice = payPrice;
            }

            public int getPayType() {
                return payType;
            }

            public void setPayType(int payType) {
                this.payType = payType;
            }

            public String getPaymentTime() {
                return paymentTime;
            }

            public void setPaymentTime(String paymentTime) {
                this.paymentTime = paymentTime;
            }
        }
    }
}
