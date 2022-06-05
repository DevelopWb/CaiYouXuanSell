package com.juntai.project.sell.mall.beans.order;

import com.juntai.disabled.basecomponent.base.BaseResult;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/5/11 15:21
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/11 15:21
 */
public class OrderDetailDataBean extends BaseResult {


    /**
     * data : {"id":305,"orderFormNumber":"b40170d7c1b840a189883e97e0045a51","userId":101,"name":"顾启杭","phone":"18669505929","address":"山东省临沂市河东区九曲街道阳光水岸","shopId":1,"shopName":"测试小店","totalPrices":30,"transportCharges":2,"sumPackingCharges":0,"payPrice":32,"payPostage":2,"payPackingCharges":0,"payType":1,"establishTime":"2022-05-04 14:57:34","paymentTime":"2022-05-05 15:40:10","shipmentsTime":"2022-05-05 15:40:13","confirmTime":"2022-05-05 10:51:14","refundTime":"2022-05-05 14:29:02","cancelTime":"2022-05-05 10:44:59","expireTime":null,"remark":"订单备注","state":7,"logisticsName":"顺丰","logisticsNumber":"123456","logisticsLink":"http://www.baidu.com","commodityList":[{"id":15,"orderFormNumber":"b40170d7c1b840a189883e97e0045a51","commodityId":5,"commodityName":"短裤","prices":20,"commodityNum":2,"cartInfo":"L,红色","unique":"0b03454b9d0246d99e8ffceaa5fcd103"},{"id":16,"orderFormNumber":"b40170d7c1b840a189883e97e0045a51","commodityId":5,"commodityName":"短裤","prices":10,"commodityNum":1,"cartInfo":"M,黑色","unique":"be028e5ef938464bb6a6f2e975f12f83"}]}
     */

    private OrderDetailBean data;

    public OrderDetailBean getData() {
        return data;
    }

    public void setData(OrderDetailBean data) {
        this.data = data;
    }

}
