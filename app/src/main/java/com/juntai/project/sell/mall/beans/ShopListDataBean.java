package com.juntai.project.sell.mall.beans;

import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/5/20 16:41
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/20 16:41
 */
public class ShopListDataBean extends BaseResult {


    /**
     * data : {"totalCount":2,"pageSize":10,"totalPage":1,"currPage":1,"list":[{"id":1,"userId":101,"name":"测试小店","headPortrait":"https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png","introduction":"店铺简介","gpsAddress":"山东临沂"},{"id":3,"userId":100,"name":"测试小店33","headPortrait":"https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png","introduction":"店铺简介","gpsAddress":"山东临沂"}]}
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
         * pageSize : 10
         * totalPage : 1
         * currPage : 1
         * list : [{"id":1,"userId":101,"name":"测试小店","headPortrait":"https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png","introduction":"店铺简介","gpsAddress":"山东临沂"},{"id":3,"userId":100,"name":"测试小店33","headPortrait":"https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png","introduction":"店铺简介","gpsAddress":"山东临沂"}]
         */

        private int totalCount;
        private int pageSize;
        private int totalPage;
        private int currPage;
        private List<ListBean> list;

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

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 1
             * userId : 101
             * name : 测试小店
             * headPortrait : https://www.juntaikeji.com:21900/2022-04-26/1650947938399.png
             * introduction : 店铺简介
             * gpsAddress : 山东临沂
             */

            private int id;
            private int userId;
            private String name;
            private String headPortrait;
            private String introduction;
            private String gpsAddress;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getHeadPortrait() {
                return headPortrait;
            }

            public void setHeadPortrait(String headPortrait) {
                this.headPortrait = headPortrait;
            }

            public String getIntroduction() {
                return introduction;
            }

            public void setIntroduction(String introduction) {
                this.introduction = introduction;
            }

            public String getGpsAddress() {
                return gpsAddress;
            }

            public void setGpsAddress(String gpsAddress) {
                this.gpsAddress = gpsAddress;
            }
        }
    }
}
