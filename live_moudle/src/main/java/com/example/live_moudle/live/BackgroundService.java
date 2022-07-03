package com.example.live_moudle.live;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.view.WindowManager;

import com.juntai.disabled.basecomponent.utils.NotificationTool;

import me.lake.librestreaming.ws.StreamLiveCameraView;

public class BackgroundService extends Service {
    private static final int NOTIFICATION_ID = 2;

    /**
     * 表示后台是否正在渲染
     */
    private StreamLiveCameraView mLiveCameraView;
    private WindowManager mWindowManager;

    // Binder given to clients
    private final IBinder mBinder = new LocalBinder();
    private static final String TAG = BackgroundService.class.getSimpleName()+"push_background";

//    /**
//     * 开始后台推流
//     */
//    public void startPush() {
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (Settings.canDrawOverlays(this)) {
//                WindowManager.LayoutParams param = new WindowManager.LayoutParams();
//                param.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
//
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                    param.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
//                }
//
//                param.format = PixelFormat.TRANSLUCENT;
//                param.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
//                param.alpha = 1.0f;
//                param.gravity = Gravity.LEFT | Gravity.TOP;
//                param.width = 1;
//                param.height = 1;
//
//                mWindowManager.addView(mLiveCameraView, param);
//            }
//        } else {
//            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(1, 1,
//                    WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
//                    WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
//                    PixelFormat.TRANSLUCENT);
//            layoutParams.gravity = Gravity.LEFT | Gravity.TOP;
//            mWindowManager.addView(mLiveCameraView, layoutParams);
//        }
//        mLiveCameraView = new StreamLiveCameraView(this);
//        //参数配置 start
//        StreamAVOption streamAVOption = new StreamAVOption();
//        streamAVOption.streamUrl = Hawk.get(QiNiuVideoActivity.URL,"");
//        //参数配置 end
//
//        mLiveCameraView.init(this, streamAVOption);
//        mLiveCameraView.addStreamStateListener(resConnectionListener);
//        mLiveCameraView.startStreaming(Hawk.get(QiNiuVideoActivity.URL,""));
//    }

    private void backGroundNotificate() {
        startForeground(NOTIFICATION_ID, NotificationTool.getNotification(this));
    }

//    public void inActivePreview() {
//        if (mLiveCameraView != null) {
//            if (mLiveCameraView.getParent() != null) {
//                mWindowManager.removeView(mLiveCameraView);
//            }
//        }
//
//        stopForeground(true);
//    }

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinder extends Binder {
        public BackgroundService getService() {
            return BackgroundService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // Create new SurfaceView, set its size to 1x1, move it to the top left
        // corner and set this service as a callback
        mWindowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        backGroundNotificate();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) {
            return START_STICKY ;
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        stopForeground(true);

//        if (mLiveCameraView != null) {
//            if (mLiveCameraView.getParent() != null) {
//                mWindowManager.removeView(mLiveCameraView);
//            }
//        }

        super.onDestroy();
    }

}
