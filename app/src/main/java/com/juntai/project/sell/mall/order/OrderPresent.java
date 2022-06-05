package com.juntai.project.sell.mall.order;

import android.Manifest;
import android.annotation.SuppressLint;
import android.support.v4.app.FragmentActivity;

import com.juntai.disabled.basecomponent.base.BaseObserver;
import com.juntai.disabled.basecomponent.base.BaseResult;
import com.juntai.disabled.basecomponent.utils.RxScheduler;
import com.juntai.disabled.video.record.VideoPreviewActivity;
import com.juntai.project.sell.mall.AppNetModuleMall;
import com.juntai.project.sell.mall.base.BaseAppMallPresent;
import com.juntai.project.sell.mall.beans.order.ConfirmOrderBean;
import com.juntai.project.sell.mall.beans.order.OrderDetailDataBean;
import com.juntai.project.sell.mall.beans.order.OrderListBean;
import com.juntai.project.sell.mall.beans.order.OrderPayWxBean;
import com.juntai.project.sell.mall.beans.order.OrderPayZfbBean;
import com.juntai.project.sell.mall.beans.order.RefundReasonBean;
import com.mabeijianxi.smallvideorecord2.MediaRecorderActivity;
import com.mabeijianxi.smallvideorecord2.model.MediaRecorderConfig;
import com.tbruyelle.rxpermissions2.RxPermissions;

import es.dmoral.toasty.Toasty;
import io.reactivex.functions.Consumer;
import okhttp3.RequestBody;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/5/10 17:31
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/10 17:31
 */
public class OrderPresent extends BaseAppMallPresent {

    /**
     * 录制视频
     *
     * @param activity
     */
    @SuppressLint("CheckResult")
    public void recordVideo(FragmentActivity activity) {
        new RxPermissions(activity)
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            // 录制
                            MediaRecorderConfig config = new MediaRecorderConfig.Buidler()
                                    .fullScreen(false)
                                    .smallVideoWidth(500)
                                    .smallVideoHeight(480)
                                    .recordTimeMax(10000)
                                    .recordTimeMin(2000)
                                    .videoBitrate(960 * 640)
                                    .captureThumbnailsTime(1)
                                    .build();
                            MediaRecorderActivity.goSmallVideoRecorder(activity, VideoPreviewActivity.class.getName()
                                    , config);
                        } else {
                            Toasty.info(activity, "请给与相应权限").show();
                        }
                    }
                });
    }

    public void commitOrder(RequestBody requestBody, String tag) {
        AppNetModuleMall.createrRetrofit()
                .commitOrder(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<ConfirmOrderBean>(getView()) {
                    @Override
                    public void onSuccess(ConfirmOrderBean o) {
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

    public void cancelOrder(RequestBody requestBody, String tag) {
        AppNetModuleMall.createrRetrofit()
                .cancelOrder(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<BaseResult>(getView()) {
                    @Override
                    public void onSuccess(BaseResult o) {
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

    public void noticeSend(RequestBody requestBody, String tag) {
        AppNetModuleMall.createrRetrofit()
                .noticeSend(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<BaseResult>(getView()) {
                    @Override
                    public void onSuccess(BaseResult o) {
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

    public void deleteCancelOrder(RequestBody requestBody, String tag) {
        AppNetModuleMall.createrRetrofit()
                .deleteCancelOrder(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<BaseResult>(getView()) {
                    @Override
                    public void onSuccess(BaseResult o) {
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

    public void requestRefund(RequestBody requestBody, String tag) {
        AppNetModuleMall.createrRetrofit()
                .requestRefund(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<BaseResult>(getView()) {
                    @Override
                    public void onSuccess(BaseResult o) {
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

    public void startEvaluate(RequestBody requestBody, String tag) {
        AppNetModuleMall.createrRetrofit()
                .startEvaluate(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<BaseResult>(getView()) {
                    @Override
                    public void onSuccess(BaseResult o) {
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


    public void confirmReceived(RequestBody requestBody, String tag) {
        AppNetModuleMall.createrRetrofit()
                .confirmReceived(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<BaseResult>(getView()) {
                    @Override
                    public void onSuccess(BaseResult o) {
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

    public void getOrderStatus(RequestBody requestBody, String tag) {
        AppNetModuleMall.createrRetrofit()
                .getOrderStatus(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<BaseResult>(getView()) {
                    @Override
                    public void onSuccess(BaseResult o) {
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

    public void getOrderList(RequestBody requestBody, String tag) {
        AppNetModuleMall.createrRetrofit()
                .getOrderList(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<OrderListBean>(getView()) {
                    @Override
                    public void onSuccess(OrderListBean o) {
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

    public void getOrderDetail(RequestBody requestBody, String tag) {
        AppNetModuleMall.createrRetrofit()
                .getOrderDetail(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<OrderDetailDataBean>(getView()) {
                    @Override
                    public void onSuccess(OrderDetailDataBean o) {
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

    public void getRefundReasons(RequestBody requestBody, String tag) {
        AppNetModuleMall.createrRetrofit()
                .getRefundReasons(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<RefundReasonBean>(getView()) {
                    @Override
                    public void onSuccess(RefundReasonBean o) {
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


    public void payByWeixin(RequestBody requestBody, String tag) {
        AppNetModuleMall.createrRetrofit()
                .payByWeixin(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<OrderPayWxBean>(getView()) {
                    @Override
                    public void onSuccess(OrderPayWxBean o) {
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

    public void payByZhifubao(RequestBody requestBody,String tag) {
        AppNetModuleMall.createrRetrofit()
                .payByZhifubao(requestBody)

                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<OrderPayZfbBean>(getView()) {
                    @Override
                    public void onSuccess(OrderPayZfbBean o) {
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

    public void payByPubAccount(RequestBody requestBody, String tag) {
        AppNetModuleMall.createrRetrofit()
                .payByPubAccount(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<BaseResult>(getView()) {
                    @Override
                    public void onSuccess(BaseResult o) {
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

}
