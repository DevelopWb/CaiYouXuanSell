package com.juntai.project.sell.mall.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/5/9 9:47
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/9 9:47
 */
public class ItemFragmentBean {

    private String key;
    private int mSpanCount = 3;//一行的个数，默认4
    private int mMaxCount = 9;//最大个数，默认9个
    private int minCount = 0;//最少数
    private int type = 0;////0拍照照片，1拍照
    private boolean isShowTag = false;//是否显示底部标记

    private List<String> fragmentPics;//选中的图片库

    public ItemFragmentBean(String key,int mSpanCount, int mMaxCount, int minCount, boolean isShowTag,
                            List<String> fragmentPics) {
        this.key = key;
        this.mSpanCount = mSpanCount;
        this.mMaxCount = mMaxCount;
        this.minCount = minCount;
        this.isShowTag = isShowTag;
        this.fragmentPics = fragmentPics;
    }

    public ItemFragmentBean(int mSpanCount, int mMaxCount, int minCount, int type, boolean isShowTag,
                            List<String> fragmentPics) {
        this.mSpanCount = mSpanCount;
        this.mMaxCount = mMaxCount;
        this.minCount = minCount;
        this.type = type;
        this.isShowTag = isShowTag;
        this.fragmentPics = fragmentPics;
    }

    public int getMinCount() {
        return minCount;
    }

    public int getType() {
        return type;
    }

    public String getKey() {
        return key == null ? "" : key;
    }

    public void setKey(String key) {
        this.key = key == null ? "" : key;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setMinCount(int minCount) {
        this.minCount = minCount;
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

    public int getmSpanCount() {
        return mSpanCount;
    }

    public void setmSpanCount(int mSpanCount) {
        this.mSpanCount = mSpanCount;
    }

    public int getmMaxCount() {
        return mMaxCount;
    }

    public void setmMaxCount(int mMaxCount) {
        this.mMaxCount = mMaxCount;
    }


    public boolean isShowTag() {
        return isShowTag;
    }

    public void setShowTag(boolean showTag) {
        isShowTag = showTag;
    }
}
