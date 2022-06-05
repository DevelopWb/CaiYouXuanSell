package com.juntai.project.sell.mall.beans.order;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述  购物车结算的时候 上传你的结构体
 * @CreateDate: 2022/5/11 13:54
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/11 13:54
 */
public class ToCommitSelectedCommoditiesBean {

    private List<TrolleyBean> trolley;

    public List<TrolleyBean> getTrolley() {
        return trolley;
    }

    public void setTrolley(List<TrolleyBean> trolley) {
        this.trolley = trolley;
    }

    public static class TrolleyBean {
        public TrolleyBean(int id, List<Integer> commodityList) {
            this.id = id;
            this.commodityList = commodityList;
        }

        /**
         * id : 10
         * commodityList : [13]
         */

        private int id;
        private List<Integer> commodityList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<Integer> getCommodityList() {
            return commodityList;
        }

        public void setCommodityList(List<Integer> commodityList) {
            this.commodityList = commodityList;
        }
    }
}
