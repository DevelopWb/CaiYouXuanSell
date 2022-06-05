package com.juntai.project.sell.mall.base.displayPicVideo;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.chat.MainContract;
import com.example.chat.chatmodule.ChatPresent;
import com.juntai.disabled.basecomponent.bean.BaseMenuBean;
import com.juntai.disabled.basecomponent.utils.FileCacheUtils;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.basecomponent.widght.BaseBottomDialog;
import com.juntai.disabled.video.CustomStandardGSYVideoPlayer;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseAppFragment;
import com.juntai.project.sell.mall.utils.ToolShare;
import com.juntai.project.sell.mall.utils.bannerImageLoader.BannerObject;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.shuyu.gsyvideoplayer.player.PlayerFactory;

import java.io.File;
import java.util.Objects;

import tv.danmaku.ijk.media.exo2.Exo2PlayerManager;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022-02-27 11:48
 * @UpdateUser: 更新者
 * @UpdateDate: 2022-02-27 11:48
 */
public class DisplayVideoFragment extends BaseAppFragment<ChatPresent> implements MainContract.IBaseView {


    private CustomStandardGSYVideoPlayer videoPlayer;
    boolean isPlay, isPause;
    private String videoCover,videoPath;

    @Override
    protected ChatPresent createPresenter() {
        return new ChatPresent();
    }


    public static DisplayVideoFragment getInstance(BannerObject bannerObject) {
        DisplayVideoFragment fragment = new DisplayVideoFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(BASE_PARCELABLE, bannerObject);
        fragment.setArguments(bundle);
        return fragment;

    }

    @Override
    protected boolean canCancelLoadingDialog() {
        return false;
    }

    @Override
    protected void lazyLoad() {
        BannerObject bannerObject = getArguments().getParcelable(BASE_PARCELABLE);
        BannerObject.VideoBean videoBean = bannerObject.getVideoBean();
        videoPath = videoBean.getVideoPath();
        videoCover = videoBean.getVideoCover();
        videoPlayer = (CustomStandardGSYVideoPlayer) getView(R.id.video_player);
        //RTMP播放需切换至exo播放
        PlayerFactory.setPlayManager(Exo2PlayerManager.class);
        /**如果是本人发送的视频 文件地址路径是
         * 如果是非本人发送的适配 文件地址是getAppVideoPath()+getSavedFileName(messageBodyBean)/文件
         */
        loadNetVideoFile(videoPath);


        //增加封面
        ImageView imageView = new ImageView(getActivity());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        if (TextUtils.isEmpty(videoBean.getVideoCover())) {
            imageView.setImageResource(R.drawable.empty_drawable);
        } else {
            ImageLoadUtil.loadImage(mContext, videoBean.getVideoCover(), imageView);
        }
        videoPlayer.setThumbImageView(imageView);
        //增加title
        videoPlayer.getTitleTextView().setVisibility(View.VISIBLE);
        //设置返回键
        videoPlayer.getBackButton().setVisibility(View.VISIBLE);
        //设置旋转
        //是否可以滑动调整
        videoPlayer.setIsTouchWiget(true);
        //设置返回按键功能
        videoPlayer.startPlayLogic();
//        videoPlayer.getStartButton().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (videoPlayer.getCurrentState()== GSYVideoView.CURRENT_STATE_NORMAL ||videoPlayer.getCurrentState()== GSYVideoView.CURRENT_STATE_ERROR) {
//                    videoPlayer.startPlayLogic();
//                }else{
//                    videoPlayer.onVideoResume();
//                }
//
//            }
//        });
        videoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Objects.requireNonNull(getActivity()).onBackPressed();
            }
        });

        videoPlayer.setVideoAllCallBack(new GSYSampleCallBack() {
            @Override
            public void onPrepared(String url, Object... objects) {
                super.onPrepared(url, objects);
                isPlay = true;
            }

            @Override
            public void onAutoComplete(String url, Object... objects) {
            }
            // : 2022-02-27 所有的播放回调
        });

        videoPlayer.getDownloadActionIv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // : 2022-02-27 将缓存到本地的文件复制到外部存储器上
                downloadVideoFile();

            }
        });
        videoPlayer.getMoreActionIv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoreActionAdapter actionAdapter = new MoreActionAdapter(R.layout.more_action);

                ((PicVideoDisplayActivity) getActivity()).initBottomDialog(getBaseActivity().getBaseBottomDialogMenus(
                        new BaseMenuBean("分享", R.mipmap.share_pic_video_icon)
                        , new BaseMenuBean("保存到本地", R.mipmap.save_icon)
                        ), actionAdapter
                        , new GridLayoutManager(mContext, 5), new BaseBottomDialog.OnItemClick() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                switch (position) {
                                    case 1:
                                        // TODO: 2022-02-28 分享
                                        ToolShare.share(mContext, ToolShare.SHARE_WECHAT, "视频分享", "视频分享", videoCover, videoPath);

                                        break;
                                    case 3:
                                        // : 2022-02-28 下载视频
                                        downloadVideoFile();

                                        break;
                                    default:
                                        break;
                                }
                            }
                        });
            }
        });

    }

    private void downloadVideoFile() {
        String oldFilePath = null;
        oldFilePath = FileCacheUtils.isVideoFileExistsInDir(getSavedFileNameWithoutSuffix(videoPath), true);

        if (!TextUtils.isEmpty(oldFilePath)) {
            File file = new File(oldFilePath);
            FileCacheUtils.copyFile((PicVideoDisplayActivity) getActivity(), oldFilePath, FileCacheUtils.getAppVideoPath(false) + getSavedFileNameWithoutSuffix(videoPath) + ".mp4", false);
        } else {
            ToastUtils.toast(mContext, "视频缓存完成之后才可保存到本地");
        }
    }

    private void loadNetVideoFile(String videoPath) {
        if (!TextUtils.isEmpty(FileCacheUtils.isVideoFileExistsInDir(getSavedFileNameWithoutSuffix(videoPath), true))) {
//            ToastUtils.toast(getBaseActivity(), "对方发送的   本地已缓存");
            //视频存在
            videoPath = FileCacheUtils.getVideoFileInDir(getSavedFileNameWithoutSuffix(videoPath), true);
            videoPlayer.setUp(videoPath, false, "");
        } else {
//            ToastUtils.toast(getBaseActivity(), "对方发送的   本地没有缓存");
            videoPlayer.setUp(videoPath, true, new File(FileCacheUtils.getAppVideoPath(true) + getSavedFileNameWithoutSuffix(videoPath)), "");
        }
    }


    @Override
    protected void lazyloadGone() {
        super.lazyloadGone();
        if (videoPlayer != null) {
            videoPlayer.onVideoPause();
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.display_video_layout;
    }


    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onSuccess(String tag, Object o) {
    }

    @Override
    public void onPause() {
        if (videoPlayer != null) {
            videoPlayer.onVideoPause();

        }
        super.onPause();
        isPause = true;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isPlay) {
            videoPlayer.getCurrentPlayer().release();
            videoPlayer.release();
        }
        videoPlayer = null;
    }


}
