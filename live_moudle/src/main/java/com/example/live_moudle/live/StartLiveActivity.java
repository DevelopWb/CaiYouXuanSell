package com.example.live_moudle.live;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.FragmentTransaction;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.live_moudle.LivePresent;
import com.example.live_moudle.R;
import com.example.live_moudle.bean.LiveResultBean;
import com.juntai.disabled.basecomponent.base.BaseActivity;
import com.juntai.disabled.basecomponent.base.BaseMvpActivity;
import com.juntai.disabled.basecomponent.base.WarnDialog;
import com.juntai.disabled.basecomponent.mvp.IView;
import com.juntai.disabled.basecomponent.utils.ToastUtils;

import me.lake.librestreaming.core.listener.RESConnectionListener;
import me.lake.librestreaming.ws.StreamAVOption;
import me.lake.librestreaming.ws.StreamLiveCameraView;

/**
 * @aouther tobato
 * @description 描述 开始直播
 *
 * @date 2022/7/2 16:55
 */
public class StartLiveActivity extends BaseMvpActivity<LivePresent> implements IView,
        View.OnClickListener, CommentFragment.OnLineUsersListener {
    LiveResultBean.DataBean liveBean;
    private StreamLiveCameraView mStreamPreviewView;
    private StreamAVOption streamAVOption;
    /**
     * 100人次观看
     */
    private TextView mViewNumberTv;
    private ImageView mLiveCloseBtn;
    private ImageView mCameraQiehuan;
    private CommentFragment commentFragment;

//    private CommentFragment cameraCommentFragment;
//    private CameraLiveDetailBean.DataBean mLiveBean;//详情

    @Override
    protected LivePresent createPresenter() {
        return new LivePresent();
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_open_live;
    }

    @Override
    public void initView() {
        initToolbarAndStatusBar(false);
        mImmersionBar.reset().statusBarColor(R.color.transparent)
                .statusBarDarkFont(true)
                .init();
        liveBean = getIntent().getParcelableExtra(BaseActivity.BASE_PARCELABLE);
        mStreamPreviewView = (StreamLiveCameraView) findViewById(R.id.stream_previewView);
        mViewNumberTv = (TextView) findViewById(R.id.view_number_tv);
        mLiveCloseBtn = (ImageView) findViewById(R.id.live_close_btn);
        mLiveCloseBtn.setOnClickListener(this);
        mCameraQiehuan = (ImageView) findViewById(R.id.camera_qiehuan);
        mCameraQiehuan.setOnClickListener(this);

        commentFragment = CommentFragment.newInstance(liveBean.getLiveNumber()).setCanLike(false)
                .setCanShare(true).setOnLineUsersListener(this).setShareCallBack(true);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.camera_fl, commentFragment);
        fragmentTransaction.commit();

        initLiveConfig();
        if (Build.VERSION.SDK_INT >= 26) {
            startForegroundService(new Intent(this, BackgroundService.class));
        } else {
            // Pre-O behavior.
            startService(new Intent(this, BackgroundService.class));
        }
    }

    @Override
    public void initData() {
//        //获取直播详情
//        mPresenter.getStreamCameraDetail(mPresenter.getPublishMultipartBody()
//                .addFormDataPart("cameraId", String.valueOf(liveId)).build(), ILiveContract.GET_STREAM_CAMERA_DETAIL);
    }


    /**
     * 设置推流参数
     */
    public void initLiveConfig() {
        //参数配置 start
        streamAVOption = new StreamAVOption();
        streamAVOption.streamUrl =liveBean.getRtmpUrl();
        //参数配置 end
        mStreamPreviewView.init(this, streamAVOption);
        mStreamPreviewView.addStreamStateListener(resConnectionListener);
    }

    RESConnectionListener resConnectionListener = new RESConnectionListener() {
        @Override
        public void onOpenConnectionResult(int result) {
            //result 0成功  1 失败
//            ToastUtils.info(mContext, "推流成功");
        }

        @Override
        public void onWriteError(int errno) {
            ToastUtils.info(mContext, "推流出错,请尝试重连");
            mStreamPreviewView.stopStreaming();
            if (!mStreamPreviewView.isStreaming()) {
                mStreamPreviewView.startStreaming(liveBean.getRtmpUrl());
            }
        }

        @Override
        public void onCloseConnectionResult(int result) {
            //result 0成功  1 失败
            ToastUtils.info(mContext, "关闭推流");
        }
    };

    @Override
    public void onSuccess(String tag, Object o) {
        switch (tag) {
//            case ILiveContract.GET_STREAM_CAMERA_DETAIL:
//                CameraLiveDetailBean detailBean = (CameraLiveDetailBean) o;
//                if (detailBean.getData() != null) {
//                    mLiveBean = detailBean.getData();
//                    cameraCommentFragment.setmStreamCameraBean(mLiveBean);
//                }
//                break;
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.live_close_btn) {
            onBackPressed();
        } else if (id == R.id.camera_qiehuan) {
            mStreamPreviewView.swapCamera();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!mStreamPreviewView.isStreaming()) {
            mStreamPreviewView.startStreaming(liveBean.getRtmpUrl());
        }
    }

    @Override
    public void onBackPressed() {
        WarnDialog agreementDialog = new WarnDialog(this).builder();
        agreementDialog.getContentTextView().setMovementMethod(LinkMovementMethod.getInstance());
        agreementDialog.setCanceledOnTouchOutside(false)
                .setTitle("要结束直播么？")
                .setCancelButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .setOkButton("结束", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                        // : 2022/7/2 跳转结束画面 、
//                        startActivity(new Intent(mContext, LiveEndActivity.class));
                    }
                }).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mStreamPreviewView != null) {
            mStreamPreviewView.stopStreaming();
        }
        mStreamPreviewView.destroy();
        stopService(new Intent(this, BackgroundService.class));
    }

    @Override
    protected boolean canCancelLoadingDialog() {
        return false;
    }


    @Override
    public void onUsersCountChange(int userCount) {
        mViewNumberTv.setText(userCount + "人在观看");
    }
}
