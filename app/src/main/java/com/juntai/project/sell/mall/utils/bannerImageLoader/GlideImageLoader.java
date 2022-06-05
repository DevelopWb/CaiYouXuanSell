package com.juntai.project.sell.mall.utils.bannerImageLoader;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.project.sell.mall.R;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.player.PlayerFactory;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.youth.banner.loader.ImageLoaderInterface;

import tv.danmaku.ijk.media.exo2.Exo2PlayerManager;


public class GlideImageLoader implements ImageLoaderInterface<View> {
    StandardGSYVideoPlayer videoPlayer;
    long seek = 0;
    private OnFullScreenListener onFullScreenListener;
    private BannerObject.VideoBean videoBean;

    public GlideImageLoader setOnFullScreenCallBack(OnFullScreenListener onFullScreenListener) {
        this.onFullScreenListener = onFullScreenListener;
        return this;
    }

    @Override
    public void displayImage(Context context, Object path, View view) {
        /**
         注意：
         1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
         2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
         传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
         切记不要胡乱强转！
         */
        //eg：

        BannerObject bannerObject = (BannerObject) path;
        String eventKey = bannerObject.getEventKey();

        if (BannerObject.BANNER_TYPE_IMAGE.equals(eventKey)) {
            ImageView imageView = (ImageView) view;
            //Glide 加载图片简单用法
            ImageLoadUtil.loadSquareImage(context,(String) bannerObject.getPicPath(), imageView);
        } else if (BannerObject.BANNER_TYPE_VIDEO.equals(eventKey)) {
            videoBean = bannerObject.getVideoBean();
            startVideo(context);
        } else if (BannerObject.BANNER_TYPE_RTMP.equals(eventKey)) {
            ImageView imageView = (ImageView) view.findViewById(R.id.rtmp_iv);
            ImageView imageView1 = (ImageView) view.findViewById(R.id.item_top_iv);
            BannerObject.StreamBean streamBean = (BannerObject.StreamBean) bannerObject.getStreamBean();
            ImageLoadUtil.loadImageCache(context, streamBean.getCameraCover(), imageView);
            Glide.with(context).load(R.mipmap.live_going).into(imageView1);

        }


    }


    @Override
    public View createImageView(Context context, Object path) {
        //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
        BannerObject bannerObject = (BannerObject) path;
        String eventKey = bannerObject.getEventKey();
        if (BannerObject.BANNER_TYPE_IMAGE.equals(eventKey)) {
            ImageView simpleDraweeView = new ImageView(context);
            return simpleDraweeView;
        } else if (BannerObject.BANNER_TYPE_VIDEO.equals(eventKey)) {
            videoPlayer = new StandardGSYVideoPlayer(context);
            return videoPlayer;
        } else if (BannerObject.BANNER_TYPE_RTMP.equals(eventKey)) {
            // 流的封面图
            return getRtmpView(context);
        }

        return null;

    }

    /**
     *
     * @param context
     * @return
     */
    private View getRtmpView(Context context) {
        return LayoutInflater.from(context).inflate(R.layout.rtmp_view, null);
    }

    private ImageView startVideo(Context mContext) {
        //RTMP播放需切换至exo播放
        PlayerFactory.setPlayManager(Exo2PlayerManager.class);
        videoPlayer.setUp(videoBean.getVideoPath(), false, "");
        //增加封面
        ImageView imageView = new ImageView(mContext);
        Glide.with(mContext).asBitmap().load(TextUtils.isEmpty(videoBean.getVideoCover())?videoBean.getVideoPath():videoBean.getVideoCover()).into(imageView);
        videoPlayer.setThumbImageView(imageView);
        //增加title
        videoPlayer.getTitleTextView().setVisibility(View.GONE);
        //设置返回键
        videoPlayer.getBackButton().setVisibility(View.GONE);
        videoPlayer.seekTo(seek);
        //全屏键
        videoPlayer.getFullscreenButton().setVisibility(View.VISIBLE);
        videoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onFullScreenListener != null) {
                    onFullScreenListener.onFullScreen();
                }
            }
        });
        //是否可以滑动调整
        videoPlayer.setIsTouchWiget(false);
        //设置返回按键功能
        videoPlayer.getBackButton().setVisibility(View.GONE);
        videoPlayer.getLayoutId();
        return imageView;
    }

    public void pause() {
        if (videoPlayer != null) {
            GSYVideoManager.onPause();
        }
    }

    //释放所有
    public void release() {
        GSYVideoManager.releaseAllVideos();
    }

    /**
     * 全屏得点击事件
     */
    public interface OnFullScreenListener {
        void onFullScreen();
    }
}
