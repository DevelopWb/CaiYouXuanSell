package com.juntai.project.sell.mall.beans.shop;

import com.juntai.disabled.basecomponent.base.BaseResult;
import com.juntai.project.sell.mall.beans.CommodityBean;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/5/8 14:04
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/8 14:04
 */
public class ShopCommodityListBean extends BaseResult {


    private List<CommodityBean> data;

    public List<CommodityBean> getData() {
        return data;
    }

    public void setData(List<CommodityBean> data) {
        this.data = data;
    }

}
