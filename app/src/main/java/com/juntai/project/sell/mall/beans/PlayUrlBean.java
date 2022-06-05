package com.juntai.project.sell.mall.beans;


import com.juntai.disabled.basecomponent.base.BaseResult;

/**
 * @Author: tobato
 * @Description: 作用描述  播放地址
 * @CreateDate: 2020/5/30 10:25
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/30 10:25
 */
public class PlayUrlBean extends BaseResult {

    /**
     * error : null
     * returnValue : null
     * msg : null
     * code : null
     * data : {"strsessionid":"37130201561327011001","videourl":"rtmp://www.juntaikeji
     * .net:1935/video/37130201561327011001","keepalivetime":"60","rtmpurl":"rtmp://www.juntaikeji
     * .net:1935/video/37130201561327011001","hlsurl":"http://60.213.43.241:8080/video/37130201561327011001.m3u8",
     * "flvurl":"http://60.213.43.241:8080/video/37130201561327011001.flv","rtcurl":"webrtc://60.213.43
     * .241:32085/video/37130201561327011001"}
     * type : null
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
         * strsessionid : 37130201561327011001
         * videourl : rtmp://www.juntaikeji.net:1935/video/37130201561327011001
         * keepalivetime : 60
         * rtmpurl : rtmp://www.juntaikeji.net:1935/video/37130201561327011001
         * hlsurl : http://60.213.43.241:8080/video/37130201561327011001.m3u8
         * flvurl : http://60.213.43.241:8080/video/37130201561327011001.flv
         * rtcurl : webrtc://60.213.43.241:32085/video/37130201561327011001
         */

        private String strsessionid;
        private String videourl;
        private String keepalivetime;
        private String rtmpurl;
        private String imageurl;
        private String hlsurl;
        private String flvurl;
        private String rtcurl;


        public String getImageurl() {
            return imageurl == null ? "" : imageurl;
        }

        public void setImageurl(String imageurl) {
            this.imageurl = imageurl == null ? "" : imageurl;
        }

        public String getStrsessionid() {
            return strsessionid;
        }

        public void setStrsessionid(String strsessionid) {
            this.strsessionid = strsessionid;
        }

        public String getVideourl() {
            return videourl;
        }

        public void setVideourl(String videourl) {
            this.videourl = videourl;
        }

        public String getKeepalivetime() {
            return keepalivetime;
        }

        public void setKeepalivetime(String keepalivetime) {
            this.keepalivetime = keepalivetime;
        }

        public String getRtmpurl() {
            return rtmpurl;
        }

        public void setRtmpurl(String rtmpurl) {
            this.rtmpurl = rtmpurl;
        }

        public String getHlsurl() {
            return hlsurl;
        }

        public void setHlsurl(String hlsurl) {
            this.hlsurl = hlsurl;
        }

        public String getFlvurl() {
            return flvurl;
        }

        public void setFlvurl(String flvurl) {
            this.flvurl = flvurl;
        }

        public String getRtcurl() {
            return rtcurl;
        }

        public void setRtcurl(String rtcurl) {
            this.rtcurl = rtcurl;
        }
    }
}
