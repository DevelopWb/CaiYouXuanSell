package com.juntai.project.sell.mall.utils.bannerImageLoader;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/5/21 16:52
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/21 16:52
 */
public class BannerObject implements Parcelable {
    /**
     * 图片
     */
    public final static String BANNER_TYPE_IMAGE = "BANNER_TYPE_IMAGE";

    /**
     * 视频
     */
    public final static String BANNER_TYPE_VIDEO = "BANNER_TYPE_VIDEO";
    /**
     * 流媒体
     */
    public final static String BANNER_TYPE_RTMP = "BANNER_TYPE_RTMP";


    private String eventKey;
    /**
     * 图片
     */
    private String picPath;
    /**
     * 视频
     */
    private VideoBean  videoBean;
    /**
     * 流
     */
    private StreamBean  streamBean;

    public BannerObject(String eventKey, String content) {
        this.eventKey = eventKey;
        this.picPath = content;
    }

    public BannerObject(String eventKey, VideoBean videoBean) {
        this.eventKey = eventKey;
        this.videoBean = videoBean;
    }

    public BannerObject(String eventKey, StreamBean streamBean) {
        this.eventKey = eventKey;
        this.streamBean = streamBean;
    }

    public VideoBean getVideoBean() {
        return videoBean;
    }

    public void setVideoBean(VideoBean videoBean) {
        this.videoBean = videoBean;
    }

    public StreamBean getStreamBean() {
        return streamBean;
    }

    public void setStreamBean(StreamBean streamBean) {
        this.streamBean = streamBean;
    }

    public String getEventKey() {
        return eventKey == null ? "" : eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey == null ? "" : eventKey;
    }

    public String getPicPath() {
        return picPath == null ? "" : picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath == null ? "" : picPath;
    }

    public static class VideoBean implements Parcelable {

        private String videoPath;
        private String videoCover;

        public VideoBean(String videoPath) {
            this.videoPath = videoPath;
        }

        public VideoBean(String videoPath, String videoCover) {
            this.videoPath = videoPath;
            this.videoCover = videoCover;
        }

        public String getVideoPath() {
            return videoPath == null ? "" : videoPath;
        }

        public void setVideoPath(String videoPath) {
            this.videoPath = videoPath == null ? "" : videoPath;
        }

        public String getVideoCover() {
            return videoCover == null ? "" : videoCover;
        }

        public void setVideoCover(String videoCover) {
            this.videoCover = videoCover == null ? "" : videoCover;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.videoPath);
            dest.writeString(this.videoCover);
        }

        protected VideoBean(Parcel in) {
            this.videoPath = in.readString();
            this.videoCover = in.readString();
        }

        public static final Parcelable.Creator<VideoBean> CREATOR = new Parcelable.Creator<VideoBean>() {
            @Override
            public VideoBean createFromParcel(Parcel source) {
                return new VideoBean(source);
            }

            @Override
            public VideoBean[] newArray(int size) {
                return new VideoBean[size];
            }
        };
    }

    public static class StreamBean implements Parcelable {

        private String cameraNum;
        private String cameraCover;
        private String rtmpUrl;

        public StreamBean(String cameraNum, String cameraCover, String rtmpUrl) {
            this.cameraNum = cameraNum;
            this.cameraCover = cameraCover;
            this.rtmpUrl = rtmpUrl;
        }

        public String getCameraNum() {
            return cameraNum == null ? "" : cameraNum;
        }

        public void setCameraNum(String cameraNum) {
            this.cameraNum = cameraNum == null ? "" : cameraNum;
        }

        public String getCameraCover() {
            return cameraCover == null ? "" : cameraCover;
        }

        public void setCameraCover(String cameraCover) {
            this.cameraCover = cameraCover == null ? "" : cameraCover;
        }

        public String getRtmpUrl() {
            return rtmpUrl == null ? "" : rtmpUrl;
        }

        public void setRtmpUrl(String rtmpUrl) {
            this.rtmpUrl = rtmpUrl == null ? "" : rtmpUrl;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.cameraNum);
            dest.writeString(this.cameraCover);
            dest.writeString(this.rtmpUrl);
        }

        protected StreamBean(Parcel in) {
            this.cameraNum = in.readString();
            this.cameraCover = in.readString();
            this.rtmpUrl = in.readString();
        }

        public static final Parcelable.Creator<StreamBean> CREATOR = new Parcelable.Creator<StreamBean>() {
            @Override
            public StreamBean createFromParcel(Parcel source) {
                return new StreamBean(source);
            }

            @Override
            public StreamBean[] newArray(int size) {
                return new StreamBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.eventKey);
        dest.writeString(this.picPath);
        dest.writeParcelable(this.videoBean, flags);
        dest.writeParcelable(this.streamBean, flags);
    }

    protected BannerObject(Parcel in) {
        this.eventKey = in.readString();
        this.picPath = in.readString();
        this.videoBean = in.readParcelable(VideoBean.class.getClassLoader());
        this.streamBean = in.readParcelable(StreamBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<BannerObject> CREATOR = new Parcelable.Creator<BannerObject>() {
        @Override
        public BannerObject createFromParcel(Parcel source) {
            return new BannerObject(source);
        }

        @Override
        public BannerObject[] newArray(int size) {
            return new BannerObject[size];
        }
    };
}
