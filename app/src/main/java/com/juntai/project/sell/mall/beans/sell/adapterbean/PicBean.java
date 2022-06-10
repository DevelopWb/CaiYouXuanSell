package com.juntai.project.sell.mall.beans.sell.adapterbean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/1/19 11:33
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/1/19 11:33
 */
public class PicBean {
    private String picName;
    private int picNameIndex;//图片的索引  -1 代表没有序号
    private String picPath;
    private List<String> fragmentPics;//多选图片时 图片库

    public PicBean(String picName, int picNameIndex, String picPath) {
        this.picName = picName;
        this.picNameIndex = picNameIndex;
        this.picPath = picPath;
    }

    public PicBean(String picName, int picNameIndex, List<String> fragmentPics) {
        this.picName = picName;
        this.picNameIndex = picNameIndex;
        this.fragmentPics = fragmentPics;
    }

    public List<String> getFragmentPics() {
        if (fragmentPics == null) {
            return new ArrayList<>();
        }
        return fragmentPics;
    }

    public void setFragmentPics(List<String> fragmentPics) {
        this.fragmentPics = fragmentPics;
    }

    public int getPicNameIndex() {
        return picNameIndex;
    }

    public void setPicNameIndex(int picNameIndex) {
        this.picNameIndex = picNameIndex;
    }

    public String getPicName() {
        return picName == null ? "" : picName;
    }

    public void setPicName(String picName) {
        this.picName = picName == null ? "" : picName;
    }

    public String getPicPath() {
        return picPath == null ? "" : picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath == null ? "" : picPath;
    }
}
