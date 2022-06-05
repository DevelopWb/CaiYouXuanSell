package com.juntai.project.sell.mall.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/5/5 16:03
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/5 16:03
 */
public class CommodityPropertyListBean {

    private String propertyName;
    private List<PropertyContentBean> propertyContent;

    public CommodityPropertyListBean(String propertyName, List<PropertyContentBean> propertyContent) {
        this.propertyName = propertyName;
        this.propertyContent = propertyContent;
    }


    public String getPropertyName() {
        return propertyName == null ? "" : propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName == null ? "" : propertyName;
    }

    public List<PropertyContentBean> getPropertyContent() {
        if (propertyContent == null) {
            return new ArrayList<>();
        }
        return propertyContent;
    }

    public void setPropertyContent(List<PropertyContentBean> propertyContent) {
        this.propertyContent = propertyContent;
    }



    public static class  PropertyContentBean{

        private String presentName;
        private  String  content;
        private  String  pic;
        private boolean selected;

        public PropertyContentBean(String presentName, String content) {
            this.presentName = presentName;
            this.content = content;
        }


        public String getContent() {
            return content == null ? "" : content;
        }

        public void setContent(String content) {
            this.content = content == null ? "" : content;
        }

        public String getPic() {
            return pic == null ? "" : pic;
        }

        public String getPresentName() {
            return presentName == null ? "" : presentName;
        }

        public void setPresentName(String presentName) {
            this.presentName = presentName == null ? "" : presentName;
        }

        public void setPic(String pic) {
            this.pic = pic == null ? "" : pic;
        }

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }
    }
}
