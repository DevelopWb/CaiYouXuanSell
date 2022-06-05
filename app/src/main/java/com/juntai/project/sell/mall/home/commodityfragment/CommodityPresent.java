package com.juntai.project.sell.mall.home.commodityfragment;

import com.juntai.disabled.basecomponent.base.BaseObserver;
import com.juntai.disabled.basecomponent.base.BaseResult;
import com.juntai.disabled.basecomponent.mvp.IModel;
import com.juntai.disabled.basecomponent.utils.HawkProperty;
import com.juntai.disabled.basecomponent.utils.RxScheduler;
import com.juntai.project.sell.mall.AppNetModuleMall;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseAppPresent;
import com.juntai.project.sell.mall.beans.CartListBean;
import com.juntai.project.sell.mall.beans.CommodityDesListBean;
import com.juntai.project.sell.mall.beans.CommodityDetailBean;
import com.juntai.project.sell.mall.beans.CommodityEvaluationBean;
import com.juntai.project.sell.mall.beans.IdNameBean;
import com.juntai.project.sell.mall.beans.PicTextBean;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.juntai.project.sell.mall.utils.UserInfoManagerMall;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/5/3 10:49
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/3 10:49
 */
public class CommodityPresent extends BaseAppPresent<IModel, HomePageContract.IHomePageView> {
    @Override
    protected IModel createModel() {
        return null;
    }


    public void getCommodityLaBels(String tag) {
        AppNetModuleMall.createrRetrofit()
                .getCommodityLaBels()
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<IdNameBean>(getView()) {
                    @Override
                    public void onSuccess(IdNameBean o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o.getData());
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

    public void getCommodityRecommendList(RequestBody requestBody, String tag) {
        AppNetModuleMall.createrRetrofit()
                .getCommodityRecommendList(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<CommodityDesListBean>(getView()) {
                    @Override
                    public void onSuccess(CommodityDesListBean o) {
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

    public void getCommodityDetail(RequestBody requestBody, String tag) {
        AppNetModuleMall.createrRetrofit()
                .getCommodityDetail(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<CommodityDetailBean>(getView()) {
                    @Override
                    public void onSuccess(CommodityDetailBean o) {
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

    public void getCommodityEvaluation(RequestBody requestBody, String tag) {
        AppNetModuleMall.createrRetrofit()
                .getCommodityEvaluation(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<CommodityEvaluationBean>(getView()) {
                    @Override
                    public void onSuccess(CommodityEvaluationBean o) {
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

    public void editCart(RequestBody requestBody, String tag) {
        AppNetModuleMall.createrRetrofit()
                .editCart(requestBody)
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

    public void deleteCartCommodity(List<Integer> ids, String tag) {
        AppNetModuleMall.createrRetrofit()
                .deleteCartCommodity(UserInfoManagerMall.getAccount(), Hawk.get(HawkProperty.SP_KEY_TOKEN), UserInfoManagerMall.DEVICE_TYPE,ids)
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

    public void getCartList(RequestBody requestBody, String tag) {
        AppNetModuleMall.createrRetrofit()
                .getCartList(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<CartListBean>(null) {
                    @Override
                    public void onSuccess(CartListBean o) {
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

    public List<PicTextBean> getCommodityBottomMenus(boolean isCollect) {
// : 2022/5/3 这个地方需要更换图片资源
        List<PicTextBean> arrays = new ArrayList<>();
        arrays.add(new PicTextBean(R.mipmap.shop_icon, HomePageContract.SHOP));
        arrays.add(new PicTextBean(R.mipmap.contact_shop_icon, HomePageContract.CUSTOMER));
        arrays.add(new PicTextBean(isCollect?R.mipmap.collected_icon:R.mipmap.un_collect_icon, HomePageContract.COLLECT));
        return arrays;
    }
}
