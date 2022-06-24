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
    private ShopCommodityManagerListBean.DataBean.ListBean listBean;
    private boolean isSelect;

    public EditShopCommodityBean(String textContent, ShopCommodityManagerListBean.DataBean.ListBean listBean) {
        this.textContent = textContent;
        this.listBean = listBean;
    }

    public String getTextContent() {
        return textContent == null ? "" : textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent == null ? "" : textContent;
    }

    public ShopCommodityManagerListBean.DataBean.ListBean getListBean() {
        return listBean;
    }

    public void setListBean(ShopCommodityManagerListBean.DataBean.ListBean listBean) {
        this.listBean = listBean;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
