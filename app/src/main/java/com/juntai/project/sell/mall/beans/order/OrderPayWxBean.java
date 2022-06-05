package com.juntai.project.sell.mall.beans.order;

import com.juntai.disabled.basecomponent.base.BaseResult;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/5/19 9:19
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/19 9:19
 */
public class OrderPayWxBean extends BaseResult {

    /**
     * data : {"nonce_str":"efd7e9ed0e5e694ba6df444d84dfa37d","packageVal":"Sign=WXPay","appid":"wx5fd6d26f7806a119","sign":"9970332EC988B38C91A03A38BD175BF7","prepayId":"wx22133941916742d1745bbdaaf5ae9a0000","mch_id":"1573354791","timestamp":"1653197980"}
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
         * nonce_str : efd7e9ed0e5e694ba6df444d84dfa37d
         * packageVal : Sign=WXPay
         * appid : wx5fd6d26f7806a119
         * sign : 9970332EC988B38C91A03A38BD175BF7
         * prepayId : wx22133941916742d1745bbdaaf5ae9a0000
         * mch_id : 1573354791
         * timestamp : 1653197980
         */

        private String nonce_str;
        private String packageVal;
        private String appid;
        private String sign;
        private String prepayId;
        private String mch_id;
        private String timestamp;

        public String getNonce_str() {
            return nonce_str;
        }

        public void setNonce_str(String nonce_str) {
            this.nonce_str = nonce_str;
        }

        public String getPackageVal() {
            return packageVal;
        }

        public void setPackageVal(String packageVal) {
            this.packageVal = packageVal;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getPrepayId() {
            return prepayId;
        }

        public void setPrepayId(String prepayId) {
            this.prepayId = prepayId;
        }

        public String getMch_id() {
            return mch_id;
        }

        public void setMch_id(String mch_id) {
            this.mch_id = mch_id;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }
    }
}
