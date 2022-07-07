package com.example.live_moudle.bean;

import android.os.Parcel;

import com.juntai.disabled.basecomponent.base.BaseResult;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @UpdateUser: 更新者
 */
public class LiveResultBean extends BaseResult {


    /**
     * data : {"liveNumber":"958332","rtmpUrl":"rtmp://www.juntaikeji.com:21950/live/958332","flvUrl":"http://www.juntaikeji.com:21955/live/958332.live.flv?st=1656290831189"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements android.os.Parcelable {
        /**
         * liveNumber : 958332
         * rtmpUrl : rtmp://www.juntaikeji.com:21950/live/958332
         * flvUrl : http://www.juntaikeji.com:21955/live/958332.live.flv?st=1656290831189
         */

        private String liveNumber;
        private String rtmpUrl;
        private String coverImg;
        private String shareLiveUrl;
        private String title;
        private String flvUrl;

        public DataBean(String liveNumber, String rtmpUrl) {
            this.liveNumber = liveNumber;
            this.rtmpUrl = rtmpUrl;
        }

        public String getCoverImg() {
            return coverImg == null ? "" : coverImg;
        }

        public void setCoverImg(String coverImg) {
            this.coverImg = coverImg == null ? "" : coverImg;
        }

        public String getShareLiveUrl() {
            return shareLiveUrl == null ? "" : shareLiveUrl;
        }

        public void setShareLiveUrl(String shareLiveUrl) {
            this.shareLiveUrl = shareLiveUrl == null ? "" : shareLiveUrl;
        }

        public String getTitle() {
            return title == null ? "" : title;
        }

        public void setTitle(String title) {
            this.title = title == null ? "" : title;
        }

        public String getLiveNumber() {
            return liveNumber;
        }

        public void setLiveNumber(String liveNumber) {
            this.liveNumber = liveNumber;
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

        public DataBean() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.liveNumber);
            dest.writeString(this.rtmpUrl);
            dest.writeString(this.coverImg);
            dest.writeString(this.shareLiveUrl);
            dest.writeString(this.title);
            dest.writeString(this.flvUrl);
        }

        protected DataBean(Parcel in) {
            this.liveNumber = in.readString();
            this.rtmpUrl = in.readString();
            this.coverImg = in.readString();
            this.shareLiveUrl = in.readString();
            this.title = in.readString();
            this.flvUrl = in.readString();
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }
}
