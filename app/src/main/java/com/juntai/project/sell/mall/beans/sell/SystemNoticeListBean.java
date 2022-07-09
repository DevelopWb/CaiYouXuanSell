package com.juntai.project.sell.mall.beans.sell;

import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/6/8 15:04
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/8 15:04
 */
public class SystemNoticeListBean extends BaseResult {


    /**
     * data : {"totalCount":1,"pageSize":100,"totalPage":1,"currPage":1,"list":[{"id":362,"type":1,"title":"测试公告","content":null,"contentId":null,"coverPhoto":null,"isRead":0}]}
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
         * totalCount : 1
         * pageSize : 100
         * totalPage : 1
         * currPage : 1
         * list : [{"id":362,"type":1,"title":"测试公告","content":null,"contentId":null,"coverPhoto":null,"isRead":0}]
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
             * id : 362
             * type : 1
             * title : 测试公告
             * content : null
             * contentId : null
             * coverPhoto : null
             * isRead : 0
             */

            private int id;
            private int type;
            private String title;
            private String content;
            private String createTime;
            private int contentId;
            private String coverPhoto;
            private int isRead;

            public String getCreateTime() {
                return createTime == null ? "" : createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime == null ? "" : createTime;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content == null ? "" : content;
            }

            public void setContent(String content) {
                this.content = content == null ? "" : content;
            }

            public int getContentId() {
                return contentId;
            }

            public void setContentId(int contentId) {
                this.contentId = contentId;
            }

            public String getCoverPhoto() {
                return coverPhoto == null ? "" : coverPhoto;
            }

            public void setCoverPhoto(String coverPhoto) {
                this.coverPhoto = coverPhoto == null ? "" : coverPhoto;
            }

            public int getIsRead() {
                return isRead;
            }

            public void setIsRead(int isRead) {
                this.isRead = isRead;
            }
        }
    }
}
