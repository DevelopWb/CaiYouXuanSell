package com.example.live_moudle;


import com.example.live_moudle.bean.LiveResultBean;
import com.example.live_moudle.bean.LiveTypeListBean;
import com.example.live_moudle.net.AppNetModuleLive;
import com.example.live_moudle.util.UserInfoManagerLive;
import com.juntai.disabled.basecomponent.base.BaseObserver;
import com.juntai.disabled.basecomponent.bean.UploadFileBean;
import com.juntai.disabled.basecomponent.mvp.BasePresenter;
import com.juntai.disabled.basecomponent.mvp.IModel;
import com.juntai.disabled.basecomponent.mvp.IView;
import com.juntai.disabled.basecomponent.utils.RxScheduler;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @UpdateUser: 更新者
 */
public class LivePresent extends BasePresenter<IModel, IView> {

    /**
     * 获取builder
     *
     * @return
     */
    public FormBody.Builder getBaseBuilder() {
        FormBody.Builder builder = new FormBody.Builder()
                .add("account", UserInfoManagerLive.getAccount())
                .add("token",UserInfoManagerLive.getUserToken())
                .add("typeEnd", "app_seller")
                .add("userId", String.valueOf(UserInfoManagerLive.getUserId()));
        if (UserInfoManagerLive.getShopId() > 0) {
            builder.add("shopId", String.valueOf(UserInfoManagerLive.getShopId()));
        }
        return builder;
    }

    public void getLiveType(final String tag) {
        AppNetModuleLive.createrRetrofit()
                .getLiveType()
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<LiveTypeListBean>(getView()) {
                    @Override
                    public void onSuccess(LiveTypeListBean o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }


    public void startLive(RequestBody requestBody, String tag) {
        AppNetModuleLive.createrRetrofit()
                .startLive(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<LiveResultBean>(getView()) {
                    @Override
                    public void onSuccess(LiveResultBean o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }

    /**
     * 上传文件
     *
     * @param
     * @param body
     */
    private void uploadFiles(RequestBody body, String tag) {
        AppNetModuleLive.createrRetrofit()
                .uploadFiles(body)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<UploadFileBean>(getView()) {
                    @Override
                    public void onSuccess(UploadFileBean o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o.getFilePaths());
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }


    /**
     * 上传文件
     *
     * @param filePaths
     */
    public void uploadFile(String tag, String... filePaths) {
        if (filePaths.length > 0) {
            MultipartBody.Builder builder = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM);
            for (String filePath : filePaths) {
                try {
                    builder.addFormDataPart("file", URLEncoder.encode(filePath, "utf-8"), RequestBody.create(MediaType.parse("file"), new File(filePath)));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            uploadFiles(builder.build(), tag);
        }

    }

    /**
     * 上传文件
     *
     * @param filePaths
     */
    public void uploadFile(List<String> filePaths, String tag) {
        if (filePaths.size() > 0) {
            MultipartBody.Builder builder = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM);
            for (String filePath : filePaths) {
                try {
                    builder.addFormDataPart("file", URLEncoder.encode(filePath, "utf-8"), RequestBody.create(MediaType.parse("file"), new File(filePath)));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            uploadFiles(builder.build(), tag);
        }

    }


    @Override
    protected IModel createModel() {
        return null;
    }
}
