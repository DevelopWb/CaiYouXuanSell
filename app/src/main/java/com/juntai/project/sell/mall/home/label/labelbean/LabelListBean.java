package com.juntai.project.sell.mall.home.label.labelbean;

import com.google.gson.annotations.SerializedName;
import com.juntai.disabled.basecomponent.base.BaseResult;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Describe:资讯标签列表
 * Create by zhangzhenlong
 * 2021-2-20
 * email:954101549@qq.com
 */
public class LabelListBean extends BaseResult {

    /**
     * error : null
     * data : [{"labelId":1,"name":"娱乐","type":1},{"labelId":2,"name":"科技","type":1},{"labelId":3,"name":"财经","type":0},{"labelId":4,"name":"社会","type":0},{"labelId":5,"name":"生活","type":0},{"labelId":6,"name":"国际","type":1},{"labelId":7,"name":"旅游","type":1},{"labelId":8,"name":"娱乐","type":0}]
     */

    private List<LabelBean> data;

    public List<LabelBean> getData() {
        return data == null? new ArrayList<>() : data;
    }

    public void setData(List<LabelBean> data) {
        this.data = data;
    }

    public static class LabelBean implements Serializable {
        /**
         * labelId : 1
         * name : 娱乐
         * type : 1
         */

        private String pictureUrl;
        private int labelId;
        private String name;
        @SerializedName("type")
        private int typeX;////1已选择；0未选择
        //常驻的
        public boolean isResident = false;
        private boolean isChoosed = false;//是否已选中，发布时选择标签

        public LabelBean(int labelId, String name, int typeX) {
            this.labelId = labelId;
            this.name = name;
            this.typeX = typeX;
        }

        public LabelBean(int labelId, String name, int typeX, boolean isResident) {
            this.labelId = labelId;
            this.name = name;
            this.typeX = typeX;
            this.isResident = isResident;
        }

        public String getPictureUrl() {
            return pictureUrl == null? "" : pictureUrl;
        }

        public void setPictureUrl(String pictureUrl) {
            this.pictureUrl = pictureUrl;
        }

        public boolean isChoosed() {
            return isChoosed;
        }

        public void setChoosed(boolean choosed) {
            isChoosed = choosed;
        }

        public boolean isResident() {
            return isResident;
        }

        public void setResident(boolean resident) {
            isResident = resident;
        }

        public int getLabelId() {
            return labelId;
        }

        public void setLabelId(int labelId) {
            this.labelId = labelId;
        }

        public String getName() {
            return name == null? "" : name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getTypeX() {
            return typeX;
        }

        public void setTypeX(int typeX) {
            this.typeX = typeX;
        }
    }
}
