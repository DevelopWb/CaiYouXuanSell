package com.juntai.project.sell.mall.beans;

import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @UpdateUser: 更新者
 */
public class WithDrawListBean extends BaseResult {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * type : 1
         * price : 20.0
         * createTime : 2022-05-07 10:20:31
         */

        private int type;
        private double price;
        private String createTime;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}
