package com.juntai.disabled.basecomponent.bean;

/**
 * Describe:更多菜单（分享等）
 * Create by zhangzhenlong
 * 2021-1-12
 * email:954101549@qq.com
 */
public class MoreMenuBean {
    private String name;
    private String imageUrl;
    private int imageId;//R.mipmap.more
    private boolean workAble = true;//是否可用
    private boolean isChoose = false;//是否已选中
    private String menu_tag;//标签


    public MoreMenuBean(String name, int imageId, String menu_tag) {
        this.name = name;
        this.imageId = imageId;
        this.menu_tag = menu_tag;
    }

    public MoreMenuBean(String name, int imageId, boolean workAble, String menu_tag) {
        this.name = name;
        this.imageId = imageId;
        this.workAble = workAble;
        this.menu_tag = menu_tag;
    }

    public String getMenu_tag() {
        return menu_tag == null? "" : menu_tag;
    }

    public void setMenu_tag(String menu_tag) {
        this.menu_tag = menu_tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public boolean isWorkAble() {
        return workAble;
    }

    public void setWorkAble(boolean workAble) {
        this.workAble = workAble;
    }

    public boolean isChoose() {
        return isChoose;
    }

    public void setChoose(boolean choose) {
        isChoose = choose;
    }
}
