package com.juntai.project.sell.mall.base;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.base.BaseMoreBottomDialog;
import com.juntai.disabled.basecomponent.base.BaseMvpActivity;
import com.juntai.disabled.basecomponent.bean.MoreMenuBean;
import com.juntai.disabled.basecomponent.mvp.BasePresenter;
import com.juntai.disabled.basecomponent.net.FileRetrofit;
import com.juntai.disabled.basecomponent.utils.FileCacheUtils;
import com.juntai.disabled.basecomponent.utils.LogUtil;
import com.juntai.disabled.basecomponent.utils.ToastUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.favorite.WechatFavorite;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Describe:分享更多
 * Create by zhangzhenlong
 * 2021-3-22
 * email:954101549@qq.com
 */
public abstract class BaseShareActivity<P extends BasePresenter> extends BaseMvpActivity<P> {
    protected int curNewsId;
    protected BaseMoreBottomDialog moreBottomDialog;
    protected BaseMoreBottomDialog.OnItemClick onItemClick;

    public static final String WECHAT = "wechat";
    public static final String WECHAT_MOMENTS = "wechat_moments";
    public static final String WECHAT_FAVORITE = "wechat_favorite";
    public static final String QQ_CHAT = "qq_chat";
    public static final String QZONE = "q_zone";
    public static final String CHAOSJ = "chao_shiju";
    public static final String REPORT = "report";
    public static final String DOWNLOAD = "download";
    public static final String COPY_PATH = "copy_path";
    public static final String CREATE_POSTER = "create_poster";
    public static final int SHARE_CODE = 0x00333;

    /**
     * 初始化dialog
     */
    public void initBottomDialog(int newsId, int typeId, String videoUrl, String title, String shareUrl, String picPath, List<MoreMenuBean> moreMenuBeans) {
        if (newsId == 0) {
            return;
        }
        curNewsId = newsId;
        if (moreBottomDialog == null) {
            moreBottomDialog = new BaseMoreBottomDialog(mContext);
            moreBottomDialog.setData(moreMenuBeans);
        }
        onItemClick = new BaseMoreBottomDialog.OnItemClick() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MoreMenuBean moreMenuBean = (MoreMenuBean) adapter.getItem(position);
                switch (moreMenuBean.getMenu_tag()) {
                    case WECHAT://微信
                        initShare(title, shareUrl, picPath, Wechat.NAME);
                        break;
                    case WECHAT_MOMENTS://朋友圈
                        initShare(title, shareUrl, picPath, WechatMoments.NAME);
                        break;
                    case WECHAT_FAVORITE://微信收藏
                        initShare(title, shareUrl, picPath, WechatFavorite.NAME);
                        break;
                    case QQ_CHAT://qq
                        initShare(title, shareUrl, picPath, QQ.NAME);
                        break;
                    case BaseShareActivity.CHAOSJ://超视距
//                        if (!AppUtils.isAvilible("com.juntai.wisdom.im", mContext)) {
//                            ToastUtils.toast(mContext, "请先下载超视距再来分享吧！");
//                            return;
//                        }
//                        try {
//                            Intent intent = new Intent(Intent.ACTION_MAIN);
//                            ComponentName componentName =
//                                    new ComponentName("com.juntai.wisdom.im", "com.juntai.wisdom.im.entrance.SplashActivity");
//                            intent.setComponent(componentName);
//                            intent.putExtra("title", title);
//                            intent.putExtra("shareUrl", shareUrl);
//                            intent.putExtra("picPath", !StringTools.isStringValueOk(picPath) ? getString(R.string.logo_url) : picPath);
//                            intent.putExtra("content", title);
//                            intent.putExtra("shareType", typeId);
//                            intent.putExtra("shareFromApp", AppUtils.getAppName(mContext));
//                            startActivityForResult(intent, SHARE_CODE);
//                        } catch (ActivityNotFoundException e) {
//                            ToastUtils.toast(mContext, "分享页面不存在");
//                        }
                        break;
                    case DOWNLOAD:
                        if (typeId == 1) {
                            //视频下载
                            downloadFileContent(videoUrl);
                        }
                        break;
                    case COPY_PATH://复制链接
                        copyContentToClipboard(shareUrl, mContext);
                        break;
                }
                moreBottomDialog.dismiss();
            }
        };
        moreBottomDialog.setOnBottomDialogCallBack(onItemClick);
        moreBottomDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK && requestCode == SHARE_CODE) {
            ToastUtils.toast(mContext, "分享成功");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 分享初始化
     */
    public void initShare(String title, String shareUrl, String picPath, String platform) {
//        if (shareUrl != null) {
//            if (!StringTools.isStringValueOk(picPath)) {
//                picPath = getString(R.string.logo_url);
//            }
//            ToolShare.shareForMob(mContext,
//                    title,
//                    shareUrl,
//                    title,
//                    picPath,
//
//                    platform);
//        } else {
//            ToastUtils.warning(mContext, "分享失败！");
//        }
    }


    /**
     * 分享外部回调
     */
    protected PlatformActionListener callback = new PlatformActionListener() {
        @Override
        public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
            //  分享成功后的操作或者提示
            ToastUtils.success(mContext, "分享成功！");
            onShareCallBack();
        }

        @Override
        public void onError(Platform platform, int i, Throwable throwable) {
            //  失败，打印throwable为错误码
            ToastUtils.warning(mContext, "分享失败！");
        }

        @Override
        public void onCancel(Platform platform, int i) {
            //  分享取消操作
            ToastUtils.warning(mContext, "分享已取消！");
        }
    };

    /**
     * 分享回调
     */
    public abstract void onShareCallBack();

    /**
     * 获取保存路径
     *
     * @return
     */
    private String getSavePath(String downloadPath) {
        String path = null;
        if (!TextUtils.isEmpty(downloadPath)) {
            if (downloadPath.contains(".mp4")) {
                path = FileCacheUtils.getAppVideoPath(true) + downloadPath.substring(downloadPath.lastIndexOf("/") + 1,
                        downloadPath.length());
            } else {
                if (downloadPath.contains(".jpeg") || downloadPath.contains(".jpg") || downloadPath.contains(".png") || downloadPath.contains(".svg")) {
                    path = FileCacheUtils.getAppImagePath(true) + downloadPath.substring(downloadPath.lastIndexOf("/") + 1, downloadPath.length());
                } else {
                    //巡检图片  直接从crm读取的"xunjiantubiao" +
                    path = FileCacheUtils.getAppImagePath(true) + downloadPath.substring(downloadPath.lastIndexOf("/") + 1, downloadPath.length()) + ".jpeg";

                }
            }
        }
        return path;
    }

    /**
     * 下载文件
     */
    public void downloadFileContent(String downLoadUrl) {
        String savePath = getSavePath(downLoadUrl);
        if (!TextUtils.isEmpty(savePath)) {
            final File file = new File(savePath);
            ToastUtils.info(mContext, "已保存");
            downFileLogic(downLoadUrl, file);
        } else {
            ToastUtils.info(mContext, "无效的下载地址");
        }
    }

    @SuppressLint("CheckResult")
    private void downFileLogic(String downloadPath, File file) {
        FileRetrofit.getInstance().getFileService()
                .getFile_GET(downloadPath)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        try {
                            //responseBody里的数据只可以读取一次
                            saveFileToLocal(file, responseBody.byteStream());
                        } catch (Exception e) {
                            e.printStackTrace();
                            LogUtil.e(e.toString());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtil.e(throwable.toString());
                    }
                });
    }

    /**
     * 缓存文件到本地
     *
     * @param ins
     */
    public void saveFileToLocal(File file, InputStream ins) {
        try {
            LogUtil.d("----->in");
            if (!file.exists()) {
                file.createNewFile();
            }
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            FileCacheUtils.sendBroadcastToAlbum(mContext, file.getAbsolutePath());
            LogUtil.d("----->ok");
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.d("----->error-" + e.toString());
        }
    }

}
