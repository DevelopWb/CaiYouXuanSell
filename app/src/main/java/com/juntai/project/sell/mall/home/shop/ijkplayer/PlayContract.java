package com.juntai.project.sell.mall.home.shop.ijkplayer;



import com.juntai.disabled.basecomponent.mvp.IView;

import java.util.Map;

import okhttp3.RequestBody;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2020/5/30 9:50
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/30 9:50
 */
public interface PlayContract {
    //权限名称
    String PERMISSION_PLAY = "视频预览";
    String PERMISSION_RECORD = "录像回放";
    String PERMISSION_PUSHING = "告警推送";
    String PERMISSION_CONTROL = "云台控制";
    String STOP_VEDIO_STREAM = "/vss/stream/stop?";
    String GET_URL_PATH = "get_url_path";//获取流地址
    String GET_STREAM_CAMERA_DETAIL = "get_url_path_detail";//获取摄像头详情
    String GET_STREAM_CAMERA_THUMBNAIL_UPLOAD = "get_url_path_capture";//缩略图
    String GET_STREAM_CAPTURE = "get_url_path_capture_save";//截图  保存本地
    String UPLOAD_CAMERA_CAPTURE = "upload_path_capture";//上传截图
    String SEARCH_MONTH_OF_VIDEOABLE = "searchVideos";// 查看一个月中 哪些天有录像
    String SEARCH_DAY_OF_VIDEOABLE = "searchVideosDay";// 查看一天中 哪些时间段有录像
    String GET_VIDEO_RTMP_URL = "hrygeturl";//获取录像的流
    String OPERATE_YUNTAI = "operate";//操控云台
    String OPERATE_YUNTAI_COLLECT = "operate_colect";//操控云台 停止
    String OPERATE_YUNTAI_UP = "up";//操控云台  向上
    String OPERATE_YUNTAI_DOWN = "down";//操控云台  向上
    String OPERATE_YUNTAI_LEFT = "left";//操控云台  向上
    String OPERATE_YUNTAI_RIGHT = "right";//操控云台  向上
    String OPERATE_YUNTAI_ZOOM_IN = "zoomin";//操控云台  拉近
    String OPERATE_YUNTAI_ZOOM_OUT = "zoomout";//操控云台  拉远
    String OPERATE_YUNTAI_SAVE_POS = "setpos";//操控云台  保存位置
    String OPERATE_YUNTAI_CALL_POS = "callpos";//操控云台  调整位置
    String ONLINE = "online";//
    String OPERATE_YUNTAI_STOP = "stop";//操控云台  停止
    int OPERATE_YUNTAI_SPEED = 50;//操控云台 调控速度
    String OPERATE_RECORD_VIDEO = "oprate_video";//操控录像
    /**
     * 添加预置位
     */
    String ADD_PRE_POSITION = "/addVideoPTZCollect.shtml";
    /**
     * 添加预置位
     */
    String CALL_PRE_POSITION = "/callideoPTZCollect.shtml";


    /**
     * 删除预置位
     */
    String DEL_PRE_POSITION = "/deleteVideoPTZCollect.shtml";


    /**
     * 查询预置位
     */
    String GET_PRE_POSITIONS = "/getVideoPTZCollect.shtml";


    interface IPlayView extends IView {

    }

    interface IPlayPresent {
        /**
         * 打开视频流
         */
        void openStream(RequestBody requestBody, String tag);

        /**
         * 截图
         *
         * @param channelid
         * @param type
         * @param tag
         */
        void capturePic(String channelid, String type, String tag);

        /**
         * 摄像头详情
         *
         * @param tag
         */
        void getStreamCameraDetail(RequestBody requestBody, String tag);


        /**
         * 上传封面图
         *
         * @param tag
         */
        void uploadStreamCameraThumbPic(RequestBody requestBody, String tag);

        /**
         * 录像查询
         *
         * @param begintime
         * @param endtime
         * @param channelid
         * @param tag
         */
        void searchVideos(String begintime, String endtime, String channelid, String tag);

        /**
         * 云台操控
         *
         * @param ptztype
         * @param ptzparam
         * @param channelid
         * @param tag
         */
        void operateYunTai(String ptztype, int ptzparam, String channelid, String tag);

        /**
         * 录像点播 获取rtmp流
         *
         * @return
         */
        void playHisVideo(Map<String, String> queryMap, String tag);

        /**
         * 录像控制
         *
         * @return
         */
        void operateRecordVideo(String sessionid, String vodctrltype, String vodctrlparam, String tag);

        /**
         * 添加预置位
         *
         * @param tag
         */
        void addPrePosition(RequestBody requestBody, String tag);

        /**
         * 删除预置位
         *
         * @param tag
         */
        void delPrePosition(RequestBody requestBody, String tag);

        /**
         * 查询预置位
         *
         * @param tag
         */
        void getPrePositions(RequestBody requestBody, String tag);

        /**
         * 获取在线数
         *
         * @param tag
         */
        void getOnlineAmount(String parameter, String tag);

        /**
         * 停止流
         *
         * @param tag
         */
        void stopStream(String sessionid, String tag);

    }
}
