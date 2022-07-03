package com.juntai.project.sell.mall.base;

import com.juntai.disabled.basecomponent.bean.UploadFileBean;
import com.juntai.disabled.basecomponent.utils.MultipleItem;
import com.juntai.disabled.basecomponent.base.BaseObserver;
import com.juntai.disabled.basecomponent.base.BaseResult;
import com.juntai.disabled.basecomponent.bean.TextKeyValueBean;
import com.juntai.disabled.basecomponent.mvp.BasePresenter;
import com.juntai.disabled.basecomponent.mvp.IModel;
import com.juntai.disabled.basecomponent.mvp.IView;
import com.juntai.disabled.basecomponent.utils.RxScheduler;
import com.juntai.project.sell.mall.AppNetModuleMall;
import com.juntai.project.sell.mall.beans.IdNameBean;
import com.juntai.project.sell.mall.beans.ShopListDataBean;
import com.juntai.project.sell.mall.beans.UserBeanMall;
import com.juntai.project.sell.mall.beans.order.CreatOrderBean;
import com.juntai.project.sell.mall.beans.sell.ShopDetailBean;
import com.juntai.project.sell.mall.beans.sell.adapterbean.ImportantTagBean;
import com.juntai.project.sell.mall.home.HomePageContract;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2020/6/3 8:38
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/3 8:38
 */
public abstract class BaseAppPresent<M extends IModel, V extends IView> extends BasePresenter<M, V> {
    /**
     * initTextType
     *
     * @param arrays
     * @param typeName
     * @param editHeightType 0代表高度固定 1代表不固定
     */
    public void initTextType(List<MultipleItem> arrays, int layoutType, String typeName, String value,
                              boolean isImportant, int editHeightType, boolean isDetail) {
        switch (layoutType) {
            case MultipleItem.ITEM_SELECT:
                arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_SMALL, new ImportantTagBean
                        (typeName, isImportant)));
                arrays.add(new MultipleItem(MultipleItem.ITEM_SELECT,
                        new TextKeyValueBean(typeName, value, String.format("%s%s", "请选择",
                                typeName), 0, isImportant, isDetail)));
                break;
            case MultipleItem.ITEM_EDIT:
                arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_SMALL, new ImportantTagBean(typeName,
                        isImportant)));
                arrays.add(new MultipleItem(MultipleItem.ITEM_EDIT,
                        new TextKeyValueBean(typeName, value,
                                String.format("%s%s", "请输入", typeName), editHeightType, isImportant, isDetail)));

                break;
            case MultipleItem.ITEM_EDIT2:
                arrays.add(new MultipleItem(MultipleItem.ITEM_EDIT2,
                        new TextKeyValueBean(typeName, value,
                                String.format("%s%s", "请输入", typeName), editHeightType, isImportant, isDetail)));
                break;
            default:
                break;
        }

    }
    /**
     *绑定银行卡
     * @return
     */
    public List<MultipleItem> bindBackCard( ) {
        List<MultipleItem> arrays = new ArrayList<>();
        arrays.add(new MultipleItem(MultipleItem.ITEM_NOTICE, "提现之前,请先绑定本人银行卡"));
        initTextType(arrays, MultipleItem.ITEM_EDIT, HomePageContract.ASSETS_WITHDRAW_REAL_NAME, ""
                , true, 0, false);
        initTextType(arrays, MultipleItem.ITEM_EDIT, HomePageContract.ASSETS_WITHDRAW_IDCARD, "", true, 0, false);
        initTextType(arrays, MultipleItem.ITEM_EDIT, HomePageContract.ASSETS_WITHDRAW_PHONE, "", true, 0, false);
        initTextType(arrays, MultipleItem.ITEM_EDIT, HomePageContract.ASSETS_WITHDRAW_BANK_NAME, "", true, 0, false);
        initTextType(arrays, MultipleItem.ITEM_EDIT, HomePageContract.ASSETS_WITHDRAW_BANK, "", true, 0, false);
        initTextType(arrays, MultipleItem.ITEM_EDIT, HomePageContract.ASSETS_WITHDRAW_BANK_CARD, "", true, 0, false);

        return arrays;
    }



    public void withDraw(RequestBody requestBody, String tag) {
        AppNetModuleMall.createrRetrofit()
                .withDraw(requestBody)
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
    public void bindBankCard(RequestBody requestBody, String tag) {
        AppNetModuleMall.createrRetrofit()
                .bindBankCard(requestBody)
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
    public void userAuth(String tag, RequestBody requestBody) {
        AppNetModuleMall.createrRetrofit()
                .userAuth(requestBody)
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
        /*====================================================    商城   ==============================================================*/
        public void creatOrderCart(RequestBody requestBody, String tag) {
            AppNetModuleMall.createrRetrofit()
                    .creatOrderCart(requestBody)
                    .compose(RxScheduler.ObsIoMain(getView()))
                    .subscribe(new BaseObserver<CreatOrderBean>(getView()) {
                        @Override
                        public void onSuccess(CreatOrderBean o) {
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


        public void creatOrderBuy(RequestBody requestBody, String tag) {
            AppNetModuleMall.createrRetrofit()
                    .creatOrderBuy(requestBody)
                    .compose(RxScheduler.ObsIoMain(getView()))
                    .subscribe(new BaseObserver<CreatOrderBean>(getView()) {
                        @Override
                        public void onSuccess(CreatOrderBean o) {
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

    public void login(RequestBody requestBody, String tag) {
        AppNetModuleMall.createrRetrofit()
                .login(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<UserBeanMall>(getView()) {
                    @Override
                    public void onSuccess(UserBeanMall o) {
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
    public void regist(String tag, RequestBody body) {
        AppNetModuleMall.createrRetrofit()
                .regist(body)
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
    public void modifyPwd( RequestBody body,String tag) {
        AppNetModuleMall.createrRetrofit()
                .modifyPwd(body)
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
    public void modifyPhone( RequestBody body,String tag) {
        AppNetModuleMall.createrRetrofit()
                .modifyPhone(body)
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

    /**
     * 上传文件
     *
     * @param
     * @param body
     */
    private void uploadFiles(RequestBody body, String tag) {
        AppNetModuleMall.createrRetrofit()
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

    /**
     * 获取用户信息
     *
     * @param tag
     * @param body
     */
    public void getUserInfo(RequestBody body, String tag) {
        AppNetModuleMall.createrRetrofit()
                .getUserInfo(body)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<UserBeanMall>(null) {
                    @Override
                    public void onSuccess(UserBeanMall o) {
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


    public void getShopDetail(RequestBody requestBody, String tag) {
        AppNetModuleMall.createrRetrofit()
                .getShopDetail(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<ShopDetailBean>(getView()) {
                    @Override
                    public void onSuccess(ShopDetailBean o) {
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

    public void shopApply(RequestBody requestBody, String tag) {
        AppNetModuleMall.createrRetrofit()
                .shopApply(requestBody)
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
    public void eidtShopApply(RequestBody requestBody, String tag) {
        AppNetModuleMall.createrRetrofit()
                .eidtShopApply(requestBody)
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
    public void getAllCategory(RequestBody requestBody, String tag) {
        AppNetModuleMall.createrRetrofit()
                .getAllCategory(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<IdNameBean>(getView()) {
                    @Override
                    public void onSuccess(IdNameBean o) {
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

    public void collectShop(RequestBody requestBody, String tag) {
        AppNetModuleMall.createrRetrofit()
                .collectShop(requestBody)
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

    public void collectCommodity(RequestBody requestBody, String tag) {
        AppNetModuleMall.createrRetrofit()
                .collectCommodity(requestBody)
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
    public void startSearchShop(RequestBody body, String tag) {
        AppNetModuleMall.createrRetrofit()
                .startSearchShop(body)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<ShopListDataBean>(null) {
                    @Override
                    public void onSuccess(ShopListDataBean o) {
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
