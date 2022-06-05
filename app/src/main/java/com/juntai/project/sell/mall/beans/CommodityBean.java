package com.juntai.project.sell.mall.beans;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/5/8 15:40
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/8 15:40
 */
public class CommodityBean {
    /**
     * id : 5
     * shopId : 1
     * name : 短裤
     * coverImg : https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png
     * price : 10.0
     * sales : 200
     * isPostage : 1
     * browse : 0
     */

    private int id;
    private int shopId;
    private String name;
    private String coverImg;
    private double price;
    private int sales;
    private int isPostage;
    private int browse;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public int getIsPostage() {
        return isPostage;
    }

    public void setIsPostage(int isPostage) {
        this.isPostage = isPostage;
    }

    public int getBrowse() {
        return browse;
    }

    public void setBrowse(int browse) {
        this.browse = browse;
    }
}
