package com.juntai.project.sell.mall.home;

import com.juntai.disabled.basecomponent.base.BaseObserver;
import com.juntai.disabled.basecomponent.bean.weather.CityBean;
import com.juntai.disabled.basecomponent.bean.weather.ResponseForcastWeather;
import com.juntai.disabled.basecomponent.bean.weather.ResponseRealTimeWeather;
import com.juntai.disabled.basecomponent.mvp.IModel;
import com.juntai.disabled.basecomponent.utils.RxScheduler;
import com.juntai.project.sell.mall.AppNetModuleMall;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseAppPresent;
import com.juntai.project.sell.mall.beans.AroundShopBean;
import com.juntai.project.sell.mall.beans.BillBaseInfoBean;
import com.juntai.project.sell.mall.beans.BillListBean;
import com.juntai.project.sell.mall.beans.MonthStatisticsBean;
import com.juntai.project.sell.mall.beans.PicTextBean;
import com.juntai.project.sell.mall.beans.WithDrawListBean;
import com.juntai.project.sell.mall.beans.sell.ShopHomeInfoBean;
import com.juntai.project.sell.mall.beans.sell.SystemNoticeBean;
import com.juntai.project.sell.mall.beans.sell.SystemNoticeListBean;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;

/**
 * @aouther Ma
 * @date 2019/3/14
 */
public class HomePagePresent extends BaseAppPresent<IModel, HomePageContract.IHomePageView> implements HomePageContract.IHomePagePresent {
    @Override
    protected IModel createModel() {
        return null;
    }


    /**
     * 获取菜单
     *
     * @return
     */
    public List<PicTextBean> getMenus() {

        List<PicTextBean> arrays = new ArrayList<>();
        arrays.add(new PicTextBean(R.drawable.home_menu_map, HomePageContract.MENUE_MAP_TYPE));
        arrays.add(new PicTextBean(R.drawable.home_menu_weather, HomePageContract.MENUE_WEATHER));
        arrays.add(new PicTextBean(R.drawable.home_menu_shop, HomePageContract.SHOP, true));
        return arrays;
    }


    public void getWeatherRealTime(String tag, String lng, String lat) {
        AppNetModuleMall.createrRetrofit()
                .getWeatherRealtime(lng, lat)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<ResponseRealTimeWeather>(getView()) {
                    @Override
                    public void onSuccess(ResponseRealTimeWeather o) {
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

    public void getForcastWeather(String tag, String lng, String lat) {
        AppNetModuleMall.createrRetrofit().getForcast(lng, lat).compose(RxScheduler.ObsIoMain(getView())).subscribe(new BaseObserver<ResponseForcastWeather>(getView()) {
            @Override
            public void onSuccess(ResponseForcastWeather o) {
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


    public void getPrivince(String tag) {
        AppNetModuleMall.createrRetrofit().getProvince().compose(RxScheduler.ObsIoMain(getView())).subscribe(new BaseObserver<CityBean>(getView()) {
            @Override
            public void onSuccess(CityBean o) {
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

    public void getCitys(String tag, int privinceNum) {
        AppNetModuleMall.createrRetrofit().getCity(privinceNum).compose(RxScheduler.ObsIoMain(getView())).subscribe(new BaseObserver<CityBean>(getView()) {
            @Override
            public void onSuccess(CityBean o) {
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

    public void getTowns(String tag, int cityNum) {
        AppNetModuleMall.createrRetrofit().getArea(cityNum).compose(RxScheduler.ObsIoMain(getView())).subscribe(new BaseObserver<CityBean>(getView()) {
            @Override
            public void onSuccess(CityBean o) {
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

    public void getStreets(String tag, int townNum) {
        AppNetModuleMall.createrRetrofit()
                .getStreet(townNum)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<CityBean>(getView()) {
                    @Override
                    public void onSuccess(CityBean o) {
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

    public void getAroundShopes(RequestBody requestBody, String tag) {
        AppNetModuleMall.createrRetrofit()
                .getAroundShopes(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<AroundShopBean>(getView()) {
                    @Override
                    public void onSuccess(AroundShopBean o) {
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

    public void getShopHomeInfo(RequestBody requestBody, String tag) {
        AppNetModuleMall.createrRetrofit()
                .getShopHomeInfo(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<ShopHomeInfoBean>(null) {
                    @Override
                    public void onSuccess(ShopHomeInfoBean o) {
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
    public void getSystemNotice(RequestBody requestBody, String tag) {
        AppNetModuleMall.createrRetrofit()
                .getSystemNotice(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<SystemNoticeListBean>(getView()) {
                    @Override
                    public void onSuccess(SystemNoticeListBean o) {
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
    public void getSystemNoticeDetail(RequestBody requestBody, String tag) {
        AppNetModuleMall.createrRetrofit()
                .getSystemNoticeDetail(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<SystemNoticeBean>(getView()) {
                    @Override
                    public void onSuccess(SystemNoticeBean o) {
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
    public void getBillList(RequestBody requestBody, String tag) {
        AppNetModuleMall.createrRetrofit()
                .getBillList(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<BillListBean>(getView()) {
                    @Override
                    public void onSuccess(BillListBean o) {
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
    public void getBillBaseInfo(RequestBody requestBody, String tag) {
        AppNetModuleMall.createrRetrofit()
                .getBillBaseInfo(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<BillBaseInfoBean>(getView()) {
                    @Override
                    public void onSuccess(BillBaseInfoBean o) {
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

    public void getBillWithDrawList(RequestBody requestBody, String tag) {
        AppNetModuleMall.createrRetrofit()
                .getBillWithDrawList(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<WithDrawListBean>(getView()) {
                    @Override
                    public void onSuccess(WithDrawListBean o) {
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
    public void getMonthStatistics(RequestBody requestBody, String tag) {
        AppNetModuleMall.createrRetrofit()
                .getMonthStatistics(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<MonthStatisticsBean>(getView()) {
                    @Override
                    public void onSuccess(MonthStatisticsBean o) {
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
