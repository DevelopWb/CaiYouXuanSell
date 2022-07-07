package com.example.live_moudle.bean;

import android.os.Parcel;

import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @UpdateUser: 更新者
 */
public class LiveListBean extends BaseResult {


    /**
     * data : {"totalCount":1,"pageSize":10,"totalPage":1,"currPage":1,"list":[{"id":3,"shopId":1,"userId":101,"liveNumber":"958332","shopName":"临沂市河东区粮油食品有限公司","title":"测试直播","headPortrait":"https://www.juntaikeji.com:21900/2022-05-23/1653289817732.png","coverImg":"https://www.juntaikeji.com:21900/2022-05-08/1651970794230.jpg","viewNumber":0,"rtmpUrl":"rtmp://www.juntaikeji.com:21950/live/958332","flvUrl":"http://www.juntaikeji.com:21955/live/958332.live.flv?st=1656230047926"}]}
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
         * pageSize : 10
         * totalPage : 1
         * currPage : 1
         * list : [{"id":3,"shopId":1,"userId":101,"liveNumber":"958332","shopName":"临沂市河东区粮油食品有限公司","title":"测试直播","headPortrait":"https://www.juntaikeji.com:21900/2022-05-23/1653289817732.png","coverImg":"https://www.juntaikeji.com:21900/2022-05-08/1651970794230.jpg","viewNumber":0,"rtmpUrl":"rtmp://www.juntaikeji.com:21950/live/958332","flvUrl":"http://www.juntaikeji.com:21955/live/958332.live.flv?st=1656230047926"}]
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

        public static class ListBean implements android.os.Parcelable {
            /**
             * id : 3
             * shopId : 1
             * userId : 101
             * liveNumber : 958332
             * shopName : 临沂市河东区粮油食品有限公司
             * title : 测试直播
             * headPortrait : https://www.juntaikeji.com:21900/2022-05-23/1653289817732.png
             * coverImg : https://www.juntaikeji.com:21900/2022-05-08/1651970794230.jpg
             * viewNumber : 0
             * rtmpUrl : rtmp://www.juntaikeji.com:21950/live/958332
             * flvUrl : http://www.juntaikeji.com:21955/live/958332.live.flv?st=1656230047926
             */

            private int id;
            private int shopId;
            private int userId;
            //-1未收藏；id已收藏）
            private int isCollect;
            private String liveNumber;
            private String shopName;
            private String title;
            private String headPortrait;
            private String coverImg;
            private int viewNumber;
            private String rtmpUrl;
            private String flvUrl;
            private String shareLiveUrl;

            public ListBean(String liveNumber, String title, String coverImg, String rtmpUrl, String shareLiveUrl) {
                this.liveNumber = liveNumber;
                this.title = title;
                this.coverImg = coverImg;
                this.rtmpUrl = rtmpUrl;
                this.shareLiveUrl = shareLiveUrl;
            }

            public String getShareLiveUrl() {
                return shareLiveUrl == null ? "" : shareLiveUrl;
            }

            public void setShareLiveUrl(String shareLiveUrl) {
                this.shareLiveUrl = shareLiveUrl == null ? "" : shareLiveUrl;
            }

            public int getIsCollect() {
                return isCollect;
            }

            public void setIsCollect(int isCollect) {
                this.isCollect = isCollect;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getShopId() {
                return shopId;
            }

            public void setShopId(int shopId) {
                this.shopId = shopId;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getLiveNumber() {
                return liveNumber;
            }

            public void setLiveNumber(String liveNumber) {
                this.liveNumber = liveNumber;
            }

            public String getShopName() {
                return shopName;
            }

            public void setShopName(String shopName) {
                this.shopName = shopName;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getHeadPortrait() {
                return headPortrait;
            }

            public void setHeadPortrait(String headPortrait) {
                this.headPortrait = headPortrait;
            }

            public String getCoverImg() {
                return coverImg;
            }

            public void setCoverImg(String coverImg) {
                this.coverImg = coverImg;
            }

            public int getViewNumber() {
                return viewNumber;
            }

            public void setViewNumber(int viewNumber) {
                this.viewNumber = viewNumber;
            }

            public String getRtmpUrl() {
                return rtmpUrl;
            }

            public void setRtmpUrl(String rtmpUrl) {
                this.rtmpUrl = rtmpUrl;
            }

            public String getFlvUrl() {
                return flvUrl;
            }

            public void setFlvUrl(String flvUrl) {
                this.flvUrl = flvUrl;
            }


            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.id);
                dest.writeInt(this.shopId);
                dest.writeInt(this.userId);
                dest.writeInt(this.isCollect);
                dest.writeString(this.liveNumber);
                dest.writeString(this.shopName);
                dest.writeString(this.title);
                dest.writeString(this.headPortrait);
                dest.writeString(this.coverImg);
                dest.writeInt(this.viewNumber);
                dest.writeString(this.rtmpUrl);
                dest.writeString(this.flvUrl);
                dest.writeString(this.shareLiveUrl);
            }

            protected ListBean(Parcel in) {
                this.id = in.readInt();
                this.shopId = in.readInt();
                this.userId = in.readInt();
                this.isCollect = in.readInt();
                this.liveNumber = in.readString();
                this.shopName = in.readString();
                this.title = in.readString();
                this.headPortrait = in.readString();
                this.coverImg = in.readString();
                this.viewNumber = in.readInt();
                this.rtmpUrl = in.readString();
                this.flvUrl = in.readString();
                this.shareLiveUrl = in.readString();
            }

            public static final Creator<ListBean> CREATOR = new Creator<ListBean>() {
                @Override
                public ListBean createFromParcel(Parcel source) {
                    return new ListBean(source);
                }

                @Override
                public ListBean[] newArray(int size) {
                    return new ListBean[size];
                }
            };
        }
    }
}
