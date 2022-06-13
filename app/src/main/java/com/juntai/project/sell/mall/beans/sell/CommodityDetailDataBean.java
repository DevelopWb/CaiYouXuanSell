package com.juntai.project.sell.mall.beans.sell;

import com.juntai.disabled.basecomponent.base.BaseResult;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/6/12 16:00
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/12 16:00
 */
public class CommodityDetailDataBean  extends BaseResult {


    /**
     * data : {"id":5,"shopId":1,"userId":null,"shopClassifyId":52,"shopClassifyName":"大米","categoryId":2,"categoryName":"水果类","name":"夏季短裤","coverImg":"https://www.juntaikeji.com:21900/2022-04-26/1111.jpg","videoUrl":"https://www.juntaikeji.com:21900/2022-04-26/1650947938399.mp4","synopsis":"商品简介短裤","description":"sadfasf gsrdgsdghse ","price":0.01,"packingCharges":0,"transportCharges":2,"sales":217,"stock":553,"isPostage":1,"browse":0,"isCollect":null,"result":null,"value":null,"images":[{"id":243,"commodityId":5,"attrId":null,"imgUrl":"https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png"},{"id":244,"commodityId":5,"attrId":null,"imgUrl":"https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png"}]}
     */

    private CommodityDetailBean data;

    public CommodityDetailBean getData() {
        return data;
    }

    public void setData(CommodityDetailBean data) {
        this.data = data;
    }
}
