package com.juntai.project.sell.mall.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;

import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.project.sell.mall.R;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 * 分享
 * Created by Ma
 * on 2019/7/4
 */
public class ToolShare {


    public final static int SHARE_WECHAT = 1000;
    public final static int SHARE_WECHAT_CIRCLE = 1001;
    public final static int SHARE_QQ = 1002;

    private static final String TAG = "ToolShare";

    /**
     * 分享文字
     *
     * @param context
     * @param string
     */
    public static void shareText(Context context, String string) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, string);
        intent.setType("text/plain");
        context.startActivity(Intent.createChooser(intent, "分享到"));
    }

    public static void shareImage(Context context, String path) {
        //由文件得到uri
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, path);
        shareIntent.setType("image/*");
        context.startActivity(Intent.createChooser(shareIntent, "分享到"));
    }

    /**
     * 第三方分享  规则
     *
     * @param context
     */
    public static void shareForMob(Context context, String title, String url, String content, String imagepath) {
//        Log.e("ffff",title+" 1 " + url + " ");
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle(title);
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl(url);
        // text是分享文本，所有平台都需要这个字段
        oks.setText(content);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        oks.setImageUrl(imagepath);
        // url在微信、微博，Facebook等平台中使用
        oks.setUrl(url);
        // comment是我对这条分享的评论，仅在人人网使用
//        oks.setComment("我是测试评论文本");
        // 启动分享GUI
        oks.show(context);
    }

    /**
     * 第三方分享
     *
     * @param context
     */
    public static void shareForMob(Context context, String title, String url, String content, String imagepath, PlatformActionListener callback) {
//        Log.e("ffff",title+" 1 " + url + " ");
        OnekeyShare oks = new OnekeyShare();
        oks.setCallback(callback);
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle(title);
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl(url);
        // text是分享文本，所有平台都需要这个字段
        oks.setText(content);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        oks.setImageUrl(imagepath);
        // url在微信、微博，Facebook等平台中使用
        oks.setUrl(url);
        // comment是我对这条分享的评论，仅在人人网使用
//        oks.setComment("我是测试评论文本");
        // 启动分享GUI
        oks.show(context.getApplicationContext());
    }


    /**
     * @param shareType     朋友圈/微信/QQ
     * @param shareTitle    不能为null
     * @param shareText     不能为null
     * @param shareImageUrl
     * @param shareUrl
     */
    public static void share(Context mContext, int shareType, String shareTitle, String shareText, String shareImageUrl, String shareUrl) {
        Log.e(TAG, "share: " + shareTitle + "  shareDes  " + shareText + " shareImg  " + shareImageUrl + "   shareUrl " + shareUrl);
        MyShareListener listener = new MyShareListener();
        Platform.ShareParams sharePlatform = new Platform.ShareParams();
        Bitmap logo = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.app_icon);
        Platform platform;
        if (TextUtils.isEmpty(shareText)) {
            ToastUtils.toast(mContext, "分享内容不能为空");
            return;
        }
        if (TextUtils.isEmpty(shareUrl)) {
            // : 2022/5/31  如果分享链接为null 直接显示百度首页
            shareUrl = "https://www.baidu.com/";
        }
        if (shareType == SHARE_WECHAT) {
            setSharePlatform(sharePlatform, shareTitle, shareText, shareImageUrl, logo, shareUrl);
            sharePlatform.setTitleUrl(shareUrl);
            platform = ShareSDK.getPlatform(Wechat.NAME);
            if (platform != null) {
            if (!platform.isClientValid()) {
                ToastUtils.toast(mContext, "未安装微信");
                return;
            }
            platform.setPlatformActionListener(listener);
            platform.share(sharePlatform);}
        } else if (shareType == SHARE_WECHAT_CIRCLE) {
            platform = ShareSDK.getPlatform(WechatMoments.NAME);
            if (platform != null) {
            if (!platform.isClientValid()) {
                ToastUtils.toast(mContext, "未安装微信");

                return;
            }
            sharePlatform.setShareType(Platform.SHARE_WEBPAGE);
            setSharePlatform(sharePlatform, shareTitle, null, shareImageUrl, logo, shareUrl);
            if (shareUrl != null && !shareUrl.equalsIgnoreCase("")) {
                sharePlatform.setTitleUrl(shareUrl);
            }
            platform.setPlatformActionListener(listener);
            platform.share(sharePlatform);}
        }
        /**
         * 如果是QQ分享
         */
        else if (shareType == SHARE_QQ) {
            setSharePlatform(sharePlatform, shareTitle, shareText, shareImageUrl, logo,shareUrl);
            if (shareUrl != null && !shareUrl.equalsIgnoreCase("")) {
                sharePlatform.setTitleUrl(shareUrl);
            }
            sharePlatform.setShareType(Platform.SHARE_WEBPAGE);
            platform = ShareSDK.getPlatform(QQ.NAME);
            if (platform != null) {
            if (!platform.isClientValid()) {
                ToastUtils.toast(mContext,"未安装QQ");
                return;
            }
            platform.setPlatformActionListener(listener);
            platform.share(sharePlatform);}
        }

//        else if (shareType == SHARE_QQ_ZONE) {
//
//            setSharePlatform(sharePlatform, shareTitle, shareText, shareImageUrl, logo,shareUrl);
//            if (shareUrl != null && !shareUrl.equalsIgnoreCase("")) {
//                sharePlatform.setTitleUrl(shareUrl);
//            }
//            sharePlatform.setShareType(Platform.SHARE_WEBPAGE);
//            platform = ShareSDK.getPlatform(QZone.NAME);
//            if (!platform.isClientValid()) {
//                GDApplication.getInstance().showToast("未安装QQ");
//                return;
//            }
//            platform.setPlatformActionListener(listener);
//            platform.share(sharePlatform);
//        } else if (shareType == SHARE_SINA_WEIBO) {
//
//            setSharePlatform(sharePlatform, shareTitle, shareText, shareImageUrl, logo,shareUrl );
//            if (shareUrl != null && !shareUrl.equalsIgnoreCase("")) {
//                sharePlatform.setTitleUrl(shareUrl);
//            }
//            sharePlatform.setShareType(Platform.SHARE_WEBPAGE);
//            platform = ShareSDK.getPlatform(SinaWeibo.NAME);
//            if (!platform.isClientValid()) {
//                GDApplication.getInstance().showToast("未安装微博");
//                return;
//            }
//            platform.setPlatformActionListener(listener);
//            platform.share(sharePlatform);
//        }
    }

    private static void setSharePlatform(Platform.ShareParams sharePlatform, String shareTitle, String shareText, String shareImageUrl, Bitmap logo, String url) {
        sharePlatform.setShareType(Platform.SHARE_WEBPAGE);
        sharePlatform.setTitle(shareTitle);

        if (shareText != null && !shareText.equalsIgnoreCase("")) {
            sharePlatform.setText(shareText);
        }
        if (shareImageUrl != null && !shareImageUrl.equalsIgnoreCase("")) {
            sharePlatform.setImageUrl(shareImageUrl);
        } else {
            sharePlatform.setImageData(logo);
        }
        sharePlatform.setUrl(url);
    }


    static class MyShareListener implements PlatformActionListener {

        @Override
        public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
            Log.e(TAG, "onComplete: ");
        }

        @Override
        public void onError(Platform platform, int i, Throwable throwable) {
            Log.e(TAG, "onError: " + i + "   throwable   " + throwable.getMessage());
        }

        @Override
        public void onCancel(Platform platform, int i) {
            Log.e(TAG, "onCancel: ");
        }
    }
}
