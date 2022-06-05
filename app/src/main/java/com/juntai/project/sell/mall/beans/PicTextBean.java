package com.juntai.project.sell.mall.beans;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/5/3 16:32
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/3 16:32
 */
public class PicTextBean {


    public final static String  PAY_TYPE_WEIXIN = "微信支付";
    public final static String  PAY_TYPE_ZHIFUBAO = "支付宝支付";
    public final static String  PAY_TYPE_PUB_ACCOUNT = "公户";

    private  int picRes;
    private String  textName;
    private  int unReadAmount;
    private boolean  isSelect;

    public int getUnReadAmount() {
        return unReadAmount;
    }

    public void setUnReadAmount(int unReadAmount) {
        this.unReadAmount = unReadAmount;
    }

    public PicTextBean(int picRes, String textName) {
        this.picRes = picRes;
        this.textName = textName;
    }

    public PicTextBean(int picRes, String textName, boolean isSelect) {
        this.picRes = picRes;
        this.textName = textName;
        this.isSelect = isSelect;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public int getPicRes() {
        return picRes;
    }

    public void setPicRes(int picRes) {
        this.picRes = picRes;
    }

    public String getTextName() {
        return textName == null ? "" : textName;
    }

    public void setTextName(String textName) {
        this.textName = textName == null ? "" : textName;
    }
}
