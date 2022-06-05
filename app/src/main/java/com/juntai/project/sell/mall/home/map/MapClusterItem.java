package com.juntai.project.sell.mall.home.map;

import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.model.LatLng;
import com.juntai.disabled.bdmap.utils.clusterutil.clustering.ClusterItem;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.beans.AroundShopBean;

/**
 * author:wong
 * Date: 2019/3/22
 * Description:
 */
public class MapClusterItem implements ClusterItem {
    private BitmapDescriptor Bd;
    private LatLng latLng;
    private String type;
    public static final String AROUND_SHOP = "aroundshop";
    public AroundShopBean.DataBean shop;
//    public boolean isClicked;

    public MapClusterItem(LatLng latLng, AroundShopBean.DataBean shope) {
        Bd = BitmapDescriptorFactory.fromResource(R.mipmap.shop_map_icon);
        this.latLng = latLng;
        this.type = AROUND_SHOP;
        this.shop = shope;
//        this.isClicked = false;
    }





    public BitmapDescriptor getBd() {
        return Bd;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public String getType() {
        return type;
    }

    public void setBd(int res) {
        Bd = BitmapDescriptorFactory.fromResource(res);;
    }

    @Override
    public LatLng getPosition() {
        return latLng;
    }

    @Override
    public BitmapDescriptor getBitmapDescriptor() {
        return Bd;
    }

}