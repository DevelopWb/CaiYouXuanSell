package com.juntai.project.sell.mall.beans;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @UpdateUser: 更新者
 */
public class AssetsMenuBean {

    private String menuName;
    private String amount;
    private String buttonName;
    private boolean selected;


    public AssetsMenuBean(String menuName, String amount, String buttonName, boolean selected) {
        this.menuName = menuName;
        this.amount = amount;
        this.buttonName = buttonName;
        this.selected = selected;
    }

    public String getMenuName() {
        return menuName == null ? "" : menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? "" : menuName;
    }

    public String getAmount() {
        return amount == null ? "" : amount;
    }

    public void setAmount(String amount) {
        this.amount = amount == null ? "" : amount;
    }

    public String getButtonName() {
        return buttonName == null ? "" : buttonName;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName == null ? "" : buttonName;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
