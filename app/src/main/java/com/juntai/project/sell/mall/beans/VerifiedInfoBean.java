package com.juntai.project.sell.mall.beans;

/**
 * @Author: tobato
 * @Description: 作用描述  认证信息
 * @CreateDate: 2020/7/1 10:05
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/7/1 10:05
 */
public class VerifiedInfoBean {

    private String title;//标题
    private int sampleRes;//样品
    private String realPicPath;//真品选择图片的路径

    public VerifiedInfoBean(String title, int sampleRes) {
        this.title = title;
        this.sampleRes = sampleRes;
    }

    public String getRealPicPath() {
        return realPicPath == null ? "" : realPicPath;
    }

    public void setRealPicPath(String realPicPath) {
        this.realPicPath = realPicPath == null ? "" : realPicPath;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title == null ? "" : title;
    }

    public int getSampleRes() {
        return sampleRes;
    }

    public void setSampleRes(int sampleRes) {
        this.sampleRes = sampleRes;
    }
}
