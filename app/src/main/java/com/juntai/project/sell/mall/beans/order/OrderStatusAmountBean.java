package com.juntai.project.sell.mall.beans.order;

import com.juntai.disabled.basecomponent.base.BaseResult;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/5/12 17:29
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/12 17:29
 */
public class OrderStatusAmountBean extends BaseResult {


    /**
     * data : {"waitOrder":0,"shipmentsOrder":0,"receivingOrder":1,"evaluateOrder":1,"afterOrder":0}
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
         * waitOrder : 0
         * shipmentsOrder : 0
         * receivingOrder : 1
         * evaluateOrder : 1
         * afterOrder : 0
         */

        private int waitOrder;
        private int shipmentsOrder;
        private int receivingOrder;
        private int evaluateOrder;
        private int afterOrder;

        public int getWaitOrder() {
            return waitOrder;
        }

        public void setWaitOrder(int waitOrder) {
            this.waitOrder = waitOrder;
        }

        public int getShipmentsOrder() {
            return shipmentsOrder;
        }

        public void setShipmentsOrder(int shipmentsOrder) {
            this.shipmentsOrder = shipmentsOrder;
        }

        public int getReceivingOrder() {
            return receivingOrder;
        }

        public void setReceivingOrder(int receivingOrder) {
            this.receivingOrder = receivingOrder;
        }

        public int getEvaluateOrder() {
            return evaluateOrder;
        }

        public void setEvaluateOrder(int evaluateOrder) {
            this.evaluateOrder = evaluateOrder;
        }

        public int getAfterOrder() {
            return afterOrder;
        }

        public void setAfterOrder(int afterOrder) {
            this.afterOrder = afterOrder;
        }
    }
}
