package com.juntai.project.sell.mall.beans;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @UpdateUser: 更新者
 */
public class StringBean {
    private String content;
    private int presentPosition;

    public int getPresentPosition() {
        return presentPosition;
    }

    public void setPresentPosition(int presentPosition) {
        this.presentPosition = presentPosition;
    }

    public StringBean(String content, int presentPosition) {
        this.content = content;
        this.presentPosition = presentPosition;
    }

    public String getContent() {
        return content == null ? "" : content;
    }

    public void setContent(String content) {
        this.content = content == null ? "" : content;
    }
}
