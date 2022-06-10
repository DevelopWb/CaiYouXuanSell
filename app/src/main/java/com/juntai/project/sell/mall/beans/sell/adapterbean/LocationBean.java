package com.juntai.project.sell.mall.beans.sell.adapterbean;

/**
 * Describe:本地位置缓存
 * Create by zhangzhenlong
 * 2020-10-24
 * email:954101549@qq.com
 */
public class LocationBean {
    private Long id;
    private String address;//地址
    private String posType;//卫星定位-GPS, 网络NET
    private String latitude;
    private String longitude;
    private String gmtCreate;

    public LocationBean(String address, String latitude, String longitude) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address == null ? "" : address;
    }

    public void setAddress(String address) {
        this.address = address == null ? "" : address;
    }

    public String getPosType() {
        return posType == null ? "" : posType;
    }

    public void setPosType(String posType) {
        this.posType = posType == null ? "" : posType;
    }

    public String getLatitude() {
        return latitude == null ? "" : latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? "" : latitude;
    }

    public String getLongitude() {
        return longitude == null ? "" : longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? "" : longitude;
    }

    public String getGmtCreate() {
        return gmtCreate == null ? "" : gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate == null ? "" : gmtCreate;
    }
}
