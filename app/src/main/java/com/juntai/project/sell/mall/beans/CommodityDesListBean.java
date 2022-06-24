package com.juntai.project.sell.mall.beans;

import android.annotation.SuppressLint;

import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/5/3 13:55
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/3 13:55
 */
@SuppressLint("ParcelCreator")
public class CommodityDesListBean extends BaseResult {


    /**
     * data : {"totalCount":2,"pageSize":1,"totalPage":2,"currPage":1,"list":[{"id":5,"shopId":1,"name":"短裤","coverImg":"https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png","price":10,"sales":200,"isPostage":1,"browse":0}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * totalCount : 2
         * pageSize : 1
         * totalPage : 2
         * currPage : 1
         * list : [{"id":5,"shopId":1,"name":"短裤","coverImg":"https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png","price":10,"sales":200,"isPostage":1,"browse":0}]
         */

        private int totalCount;
        private int pageSize;
        private int totalPage;
        private int currPage;
        private List<CommodityBean> list;

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getCurrPage() {
            return currPage;
        }

        public void setCurrPage(int currPage) {
            this.currPage = currPage;
        }

        public List<CommodityBean> getList() {
            return list;
        }

        public void setList(List<CommodityBean> list) {
            this.list = list;
        }

    }
}
