package com.juntai.project.sell.mall.base;

import com.juntai.disabled.basecomponent.bean.TextKeyValueBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述  订单详情条目
 * @UpdateUser: 更新者
 */
public class OrderDetailItemBean {
    private String title;
    private List<TextKeyValueBean>  arrays;
    private List<String>  images;

    public List<String> getImages() {
        if (images == null) {
            return new ArrayList<>();
        }
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public OrderDetailItemBean(String title, List<TextKeyValueBean> arrays) {
        this.title = title;
        this.arrays = arrays;
    }

    public List<TextKeyValueBean> getArrays() {
        if (arrays == null) {
            return new ArrayList<>();
        }
        return arrays;
    }

    public void setArrays(List<TextKeyValueBean> arrays) {
        this.arrays = arrays;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title == null ? "" : title;
    }
}
