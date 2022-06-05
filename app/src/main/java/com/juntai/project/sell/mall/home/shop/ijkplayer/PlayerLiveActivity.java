package com.juntai.project.sell.mall.home.shop.ijkplayer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Group;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dou361.ijkplayer.listener.OnShowThumbnailListener;
import com.dou361.ijkplayer.utils.MediaUtils;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.juntai.disabled.basecomponent.base.BaseDownLoadActivity;
import com.juntai.disabled.basecomponent.utils.DisplayUtil;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.disabled.basecomponent.utils.PubUtil;
import com.juntai.disabled.basecomponent.utils.SoundPlayer;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.project.sell.mall.MainActivity;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.beans.PlayUrlBean;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import okhttp3.FormBody;

/**
 * @aouther tobato
 * @description 描述   播放视频流
 * @date 2020/7/25 16:44
 */
public class PlayerLiveActivity extends BaseDownLoadActivity<PlayPresent> implements PlayContract.IPlayView,
        View.OnClickListener, BaseDownLoadActivity.OnFileDownloaded {

    private PlayerView player;
    //    private String url = "rtmp://juntaikeji.net:1935/video/37130201561327011011";
    private PowerManager.WakeLock wakeLock;
    private Intent intent;
    public static String STREAM_CAMERA_ID = "stream";
    public static String ENTER_TYPE = "entry_type";//进入的入口
    private int enterType = 0;//1代表从硬盘录像机进入
    public static String STREAM_CAMERA_NUM = "stream_num";
    public static String STREAM_CAMERA_THUM_URL = "stream_thum_url";//缩略图路径
    private int mCameraId;//
    public String mCameraNum;//
    private String playUrl;
    private String COMMENT = "COMMENT";//评价
    private String YUN_CONTROL = "YUN_CONTROL";//云控制
    private String VIDEO_RECORD = "VIDEO_RECORD";
    private ImageView mVideoIv, mShareCamera;
    private ImageView mYuntaiIv;
    private ImageView mCalendarIv;
    private String mThumUrl;
    private boolean isPlay = false;
    private String videoStrsessionid = null;
    /**
     * 1121321
     */
    public static boolean isVerScreen = true;//是否是竖屏
    private LinearLayout mVideoViewLl;
    private DrawerLayout mDrawerlayout;
    private Group mOperateRightIvsG, mHorSuspensionG, mVerSuspensionG;


    private ImageView mControlUpIv;
    private ImageView mControlLeftIv;
    private ImageView mControlDownIv;
    private ImageView mControlRightIv;
    private TextView mControlStopTv;
    private ImageView mZoomOutIv;
    private ImageView mZoomInIv;
    private ImageView mCollectIv;//收藏预置位
    private ImageView mCutPicIv;
    private ImageView mRecordIv;
    private LinearLayout mFullScreenRightLl;
    private ImageView mYuntaiFloatIv;//悬浮窗上的云台控制
    private ConstraintLayout mFullScreenRightMoreCl;
    private LinearLayout mFullScreenRightControlLl;
    private ImageView mFullScreenShareIv;
    private ImageView mFullScreenSetIv;
    private TextView mFullScreenVisitAmountTv;
    private TextView mFullScreenOnlineAmountTv;
    private ImageView mZoomShrinkIv;
    private ImageView mVerCaptureIv;//竖屏模式下的截屏
    private boolean isMyDev = true;//默认是我的设备
    private boolean devHasYunTai = false;//设备是否有云台
    private TextView mFullScreenSetTv;
    private boolean hideAllScreen = false;//是否隐藏所有按钮
    private SoundPlayer soundPlayer;

    /**
     * 获取摄像头num
     *
     * @return
     */
    public String getStreamCameraNum() {
        return mCameraNum;
    }



    public static void startPlayerLiveActivity(Context mContext, String mCameraNum, String mThumUrl, String playUrl) {
        Intent intent = new Intent(mContext, PlayerLiveActivity.class);
        intent.putExtra(BASE_STRING, mCameraNum)
                .putExtra(BASE_STRING2, mThumUrl)
                .putExtra(BASE_STRING3, playUrl);
        mContext.startActivity(intent);
    }


    @Override
    protected PlayPresent createPresenter() {
        return new PlayPresent();
    }

    @Override
    public int getLayoutView() {
        return R.layout.simple_player_view_player;
    }

    @Override
    public void initView() {
        if (getIntent() != null) {
            mCameraNum = getIntent().getStringExtra(BASE_STRING);
            mThumUrl = getIntent().getStringExtra(BASE_STRING2);
            playUrl = getIntent().getStringExtra(BASE_STRING3);
        }
        hideBottomVirtureBar();
        setFileDownLoadCallBack(this);
        setTitleName("摄像头");
        mVideoIv = (ImageView) findViewById(R.id.video_iv);
        mVideoIv.setOnClickListener(this);
        mShareCamera = (ImageView) findViewById(R.id.share_camera_iv);
        mShareCamera.setOnClickListener(this);
        mYuntaiIv = (ImageView) findViewById(R.id.yuntai_iv);
        mYuntaiIv.setOnClickListener(this);
        mCalendarIv = (ImageView) findViewById(R.id.calendar_iv);
        mCalendarIv.setOnClickListener(this);
        mVideoViewLl = (LinearLayout) findViewById(R.id.video_view_ll);
        initDrawerlayout();
        mControlUpIv = (ImageView) findViewById(R.id.control_up_iv);
        mControlUpIv.setOnClickListener(this);
        mControlLeftIv = (ImageView) findViewById(R.id.control_left_iv);
        mControlLeftIv.setOnClickListener(this);
        mControlDownIv = (ImageView) findViewById(R.id.control_down_iv);
        mControlDownIv.setOnClickListener(this);
        mControlRightIv = (ImageView) findViewById(R.id.control_right_iv);
        mControlRightIv.setOnClickListener(this);
        mControlStopTv = (TextView) findViewById(R.id.control_stop_tv);
        mControlStopTv.setOnClickListener(this);
        mZoomOutIv = (ImageView) findViewById(R.id.zoom_out_iv);
        mZoomOutIv.setOnClickListener(this);
        mZoomShrinkIv = (ImageView) findViewById(R.id.zoom_shrink_iv);
        mZoomShrinkIv.setOnClickListener(this);
        mZoomInIv = (ImageView) findViewById(R.id.zoom_in_iv);
        mZoomInIv.setOnClickListener(this);
        mCollectIv = (ImageView) findViewById(R.id.collect_iv);
        mCollectIv.setOnClickListener(this);
        mCutPicIv = (ImageView) findViewById(R.id.cut_pic_iv);
        mCutPicIv.setOnClickListener(this);
        mRecordIv = (ImageView) findViewById(R.id.record_iv);
        mRecordIv.setOnClickListener(this);
        mFullScreenRightLl = (LinearLayout) findViewById(R.id.full_screen_right_control_cl);
        mFullScreenRightControlLl = (LinearLayout) findViewById(R.id.yun_control_Ll);
        mFullScreenRightMoreCl = (ConstraintLayout) findViewById(R.id.full_screen_right_more_cl);
        mYuntaiFloatIv = (ImageView) findViewById(R.id.top_yuntai_iv);
        mYuntaiFloatIv.setOnClickListener(this);
        mFullScreenShareIv = (ImageView) findViewById(R.id.full_screen_share_iv);
        mFullScreenSetIv = (ImageView) findViewById(R.id.full_screen_set_iv);
        mFullScreenSetTv = (TextView) findViewById(R.id.full_screen_set_tv);
        mFullScreenVisitAmountTv = (TextView) findViewById(R.id.full_screen_visit_amount_tv);
        mFullScreenOnlineAmountTv = (TextView) findViewById(R.id.full_screen_online_amount_tv);
        mFullScreenShareIv.setOnClickListener(this);
        mFullScreenSetIv.setOnClickListener(this);
        mVerCaptureIv = (ImageView) findViewById(R.id.app_video_capture);
        mVerCaptureIv.setOnClickListener(this);
    }

    /**
     * 初始化抽屉布局
     */
    private void initDrawerlayout() {
        mDrawerlayout = findViewById(R.id.drawerlayout);
        mOperateRightIvsG = findViewById(R.id.operate_right_ivs_g);
        mHorSuspensionG = findViewById(R.id.horizontal_suspension_g);
        mVerSuspensionG = findViewById(R.id.vertical_suspension_g);
        mDrawerlayout.setScrimColor(Color.TRANSPARENT);
        mDrawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        mDrawerlayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {

            }

            @Override
            public void onDrawerOpened(@NonNull View view) {
                mOperateRightIvsG.setVisibility(View.GONE);
            }

            @Override
            public void onDrawerClosed(@NonNull View view) {
                mOperateRightIvsG.setVisibility(View.VISIBLE);
            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });
    }


    @Override
    public void initData() {
        /**
         * 注意！！！注意！！！注意！！！注意！！！注意！！！
         *
         * 播放摄像头流数据的时候 只有流地址是不行的  需要开流  并且还要保活 要不一分钟之后流就断了
         *
         */
        //打开流数据
        mPresenter.openStream(getBaseBuilder().add("chanpubid",
                mCameraNum)
                .add("transport", "udp").add("videourltype", "rtmp").build(), PlayContract.GET_URL_PATH);
        /**
         * 保活
         */
        intent = new Intent(this, KeepAliveService.class);
        intent.putExtra("sessionId", mCameraNum);
        startService(intent);
    }
    /**
     * 获取builder
     *
     * @return
     */
    public FormBody.Builder getBaseBuilder() {
        FormBody.Builder builder = new FormBody.Builder();
        return builder;
    }

    @SuppressLint("InvalidWakeLockTag")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE); //去除这个Activity的标题栏
        super.onCreate(savedInstanceState);
        /**常亮*/
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "liveTAG");
        wakeLock.acquire();
        soundPlayer = new SoundPlayer(mContext);

        initPlayer();


    }

    private void initPlayer() {
        player = new PlayerView(this, mBaseRootCol)
                //隐藏顶部布局
                .hideHideTopBar(false)
                //分辨率
                .hideSteam(true)
                //旋转
                .hideRotation(true)
                //隐藏全屏按钮，true隐藏，false为显示
                .hideFullscreen(false)
                .hideCenterPlayer(false)
                .forbidTouch(false)
                .setOnlyFullScreen(true)
                .setForbidDoulbeUp(true)
                .setThumPic(mThumUrl)
                .setScaleType(PlayStateParams.fitxy).showThumbnail(new OnShowThumbnailListener() {
                    @Override
                    public void onShowThumbnail(ImageView ivThumbnail) {
                        ImageLoadUtil.loadImageCache(mContext,
                                mThumUrl, ivThumbnail);
                    }
                });
        player.getFullScreenView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(playUrl)) {
                    player.setOnlyFullScreen(true);
                } else {
                    ToastUtils.toast(mContext, "无法获取流地址");
                }
            }
        });

        player.getBackView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //切换到竖屏模式
              finish();
            }
        });
    }

    @Override
    protected String getDownloadTitleRightName() {
        return null;
    }


    @Override
    protected String getDownLoadPath() {
        return null;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.cut_pic_iv:
                mVerCaptureIv.performClick();
                break;
            case R.id.zoom_shrink_iv:
                //切换到竖屏模式
               finish();
                break;
            case R.id.record_iv:
                ToastUtils.toast(mContext, "暂未开放");
                break;
            case R.id.top_yuntai_iv:
                mDrawerlayout.openDrawer(mFullScreenRightLl);
                mFullScreenRightControlLl.setVisibility(View.VISIBLE);
                mFullScreenRightMoreCl.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }



    @Override
    protected void onPause() {
        super.onPause();
        MediaUtils.muteAudioFocus(mContext, true);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (player != null) {
            player.onResume();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (player != null) {
            player.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MediaUtils.muteAudioFocus(mContext, false);
        if (wakeLock != null) {
            wakeLock.acquire();
        }
    }

    @Override
    public void onBackPressed() {

        if (wakeLock != null) {
            wakeLock.release();
        }
        if (player != null && player.onBackPressed()) {
            return;
        }
        if (0 == enterType) {
            startActivity(new Intent(mContext, MainActivity.class));
        }

        super.onBackPressed();

    }

    @Override
    public void onError(String tag, Object o) {
        super.onError(tag, o);
        switch (tag) {
            case PlayContract.GET_URL_PATH:
                player.isOffLine(true);
            default:
                break;
        }
    }
    @Override
    public void onSuccess(String tag, Object o) {


        switch (tag) {
            case PlayContract.GET_URL_PATH:
                PlayUrlBean.DataBean bean = (PlayUrlBean.DataBean) o;
                player.isOffLine(false);
                playUrl = bean.getRtmpurl();
                String strsessionid = bean.getStrsessionid();
                player.setPlaySource(playUrl).startPlay();
                /**
                 * 保活
                 */
                intent = new Intent(this, KeepAliveService.class);
                intent.putExtra("sessionId", strsessionid);
                startService(intent);

                break;
            case PlayContract.UPLOAD_CAMERA_CAPTURE:
                //上传预置位的封面图
                ToastUtils.toast(mContext, "收藏成功");
                break;
            case PlayContract.OPERATE_RECORD_VIDEO:
                player.isLive = false;
                player.onPlayPause();

                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isVerScreen = true;
        soundPlayer.release();
        if (player != null) {
            player.onDestroy();
        }
        if (intent != null) {
            stopService(intent);
        }
        setFileDownLoadCallBack(null);
    }

    @Override
    protected boolean canCancelLoadingDialog() {
        return false;
    }


    @Override
    public void onFileDownloaded(String filePath) {

    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //横屏

            isVerScreen = false;
            //显示横屏的布局
            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) mVideoViewLl.getLayoutParams();
            params.height = ConstraintLayout.LayoutParams.MATCH_PARENT;
            params.width = ConstraintLayout.LayoutParams.MATCH_PARENT;
            mVideoViewLl.setLayoutParams(params);
            mPresenter.setMarginOfConstraintLayout(mVideoViewLl, mContext, 0, 0, 0, 0);
            getToolbar().setVisibility(View.GONE);
            mVideoViewLl.postDelayed(new Runnable() {
                @Override
                public void run() {
                    player.setFatherW_H(mVideoViewLl.getTop(), mVideoViewLl.getBottom());
                }
            }, 100);
            player.updateRender();
            initLayoutByOritation();
        } else {
            //竖屏
            //            showBottomVirtureBar();
            isVerScreen = true;
            //显示竖屏的布局
            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) mVideoViewLl.getLayoutParams();
            params.height = DisplayUtil.dp2px(mContext, 190);
            params.width = ConstraintLayout.LayoutParams.MATCH_PARENT;
            mVideoViewLl.setLayoutParams(params);
            getToolbar().setVisibility(View.VISIBLE);
            mPresenter.setMarginOfConstraintLayout(mVideoViewLl, mContext, 10, 10, 10, 10);
            getToolbar().setVisibility(View.VISIBLE);
            player.updateRender();
            initLayoutByOritation();


        }
        if (player != null) {
            player.onConfigurationChanged(newConfig);
        }
    }

    /**
     * 初始化布局
     */
    private void initLayoutByOritation() {

        if (isVerScreen) {
            //隐藏横屏悬浮布局 显示竖屏 悬浮布局

            mHorSuspensionG.setVisibility(View.GONE);
            mOperateRightIvsG.setVisibility(View.GONE);
            if (hideAllScreen) {
                mVerSuspensionG.setVisibility(View.GONE);
            } else {
                mVerSuspensionG.setVisibility(View.VISIBLE);
            }
        } else {
            //隐藏竖屏悬浮布局 显示横屏 悬浮布局
            mVerSuspensionG.setVisibility(View.GONE);
            if (hideAllScreen) {
                mHorSuspensionG.setVisibility(View.GONE);
                mOperateRightIvsG.setVisibility(View.GONE);
            } else {
                mHorSuspensionG.setVisibility(View.VISIBLE);
                mOperateRightIvsG.setVisibility(View.VISIBLE);
            }

        }



    }


    /**
     * 隐藏pad底部虚拟键
     */
    protected void hideBottomVirtureBar() {
        Window window;
        window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE;
        window.setAttributes(params);
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onOneClick(String msg) {
        switch (msg) {
            case PubUtil.ONE_CLICK_EVENT:
                hideAllScreen = !hideAllScreen;
                if (hideAllScreen) {
                    player.getBarPlayerView().setVisibility(View.GONE);
                    player.getBarSoundView().setVisibility(View.GONE);
                } else {
                    player.getBarPlayerView().setVisibility(View.VISIBLE);
                    player.getBarSoundView().setVisibility(View.VISIBLE);
                }
                initLayoutByOritation();

                break;
            default:
                break;
        }
    }


}
