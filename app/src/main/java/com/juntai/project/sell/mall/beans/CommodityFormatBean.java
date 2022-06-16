package com.juntai.project.sell.mall.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @UpdateUser: 更新者
 */
public class CommodityFormatBean {

    private List<ResultBean> attr;
    private List<CommodityFormatListBean.DataBean> value;

    public List<ResultBean> getAttr() {
        if (attr == null) {
            return new ArrayList<>();
        }
        return attr;
    }

    public void setAttr(List<ResultBean> attr) {
        this.attr = attr;
    }

    public List<CommodityFormatListBean.DataBean> getValue() {
        if (value == null) {
            return new ArrayList<>();
        }
        return value;
    }

    public void setValue(List<CommodityFormatListBean.DataBean> value) {
        this.value = value;
    }

    public static class ResultBean {

        /**
         * detail : ["红色","黑色"]
         * value : 颜色
         */

        private String value;
        private List<String> detail;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public List<String> getDetail() {
            return detail;
        }

        public void setDetail(List<String> detail) {
            this.detail = detail;
        }
    }
}
