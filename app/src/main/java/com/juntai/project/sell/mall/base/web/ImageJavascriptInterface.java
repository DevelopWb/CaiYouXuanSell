package com.juntai.project.sell.mall.base.web;

import android.content.Context;
import android.webkit.JavascriptInterface;

import com.juntai.disabled.video.img.DisPlayPicsActivity;
import com.king.zxing.util.LogUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021-09-19 11:10
 * @UpdateUser: 更新者  decode
 * @UpdateDate: 2021-09-19 11:10
 */
public class ImageJavascriptInterface {
    private Context mContext;
    private String[] imageUrls;

    public ImageJavascriptInterface(Context context) {
        this.mContext = context;
    }

    public void setImageUrls(String[] imageUrls) {
        this.imageUrls = imageUrls;
    }

    //java回调js代码，不要忘了@JavascriptInterface这个注解，不然点击事件不起作用
    @JavascriptInterface
    public void openImage(String img,int pos) {
        LogUtils.d("webview 点击事件");
        ArrayList<String> images = new ArrayList<>();
        if (imageUrls != null) {
            for (String imageUrl : imageUrls) {
                try {
                    String url = URLDecoder.decode(imageUrl,"utf-8");
                    if (url.contains("url=")) {
                        url = url.substring(url.indexOf("url")+4,url.indexOf("&amp"));
                    }
                    images.add(url);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        DisPlayPicsActivity.startDisplayPics(mContext,images,pos);
    }
}
