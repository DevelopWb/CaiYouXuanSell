package com.juntai.project.sell.mall.utils;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import com.juntai.disabled.basecomponent.utils.BaseAppUtils;

import java.io.File;

/**
 * @aouther Ma
 * @date 2019/4/2
 */
public class MyFileProvider extends FileProvider {


    public static  Uri getUriFromFile(Context context, File file) {
        Uri  uri = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {   //如果在Android7.0以上,使用FileProvider获取Uri
            try {
                uri = FileProvider.getUriForFile(context, BaseAppUtils.getFileprovider(),
                        file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {    //否则使用Uri.fromFile(file)方法获取Uri
            uri = Uri.fromFile(file);
        }
        return uri;
    }
}
