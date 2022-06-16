package com.juntai.project.sell.mall.beans;

import com.juntai.disabled.basecomponent.base.BaseResult;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @UpdateUser: 更新者
 */
public class CommodityFormatDataBean extends BaseResult {

    /**
     * data : {"attr":[{"detail":["红色","黑色"],"value":"颜色"},{"detail":["M","L"],"value":"尺码"}],"value":[{"image":"https://www.juntaikeji.com:21900/2022-04-26/1111.jpg","price":10,"unique":"4c75a1e196804b318ca723db94a395c0","detail":{"颜色":"红色","尺码":"M"},"stock":0,"commodityName":"短裤"},{"image":"https://www.juntaikeji.com:21900/2022-04-26/1111.jpg","price":10,"unique":"f07222aff1dc476083c378f5c3ec9ce0","detail":{"颜色":"红色","尺码":"L"},"stock":0,"commodityName":"短裤"},{"image":"https://www.juntaikeji.com:21900/2022-04-26/1111.jpg","price":10,"unique":"f0696e43298543da9a5e16fe9fe50b3e","detail":{"颜色":"黑色","尺码":"M"},"stock":0,"commodityName":"短裤"},{"image":"https://www.juntaikeji.com:21900/2022-04-26/1111.jpg","price":10,"unique":"6fe9ca7106d94e57b9e7717b99f271a5","detail":{"颜色":"黑色","尺码":"L"},"stock":0,"commodityName":"短裤"}]}
     */

    private CommodityFormatBean data;

    public CommodityFormatBean getData() {
        return data;
    }

    public void setData(CommodityFormatBean data) {
        this.data = data;
    }
}
