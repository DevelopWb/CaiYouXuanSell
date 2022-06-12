package com.juntai.project.sell.mall.beans.sell;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/6/12 15:40
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/12 15:40
 */
public class EditShopCommodityBean {

    private String textContent ;
    private int commodityId;
    private boolean isSelect;

    public EditShopCommodityBean(String textContent, int commodityId) {
        this.textContent = textContent;
        this.commodityId = commodityId;
    }

    public String getTextContent() {
        return textContent == null ? "" : textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent == null ? "" : textContent;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
