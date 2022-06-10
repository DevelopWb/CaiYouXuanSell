package com.juntai.project.sell.mall.home.shop;

import com.example.chat.util.MultipleItem;
import com.juntai.disabled.basecomponent.base.BaseObserver;
import com.juntai.disabled.basecomponent.bean.TextKeyValueBean;
import com.juntai.disabled.basecomponent.utils.RxScheduler;
import com.juntai.project.sell.mall.AppNetModuleMall;
import com.juntai.project.sell.mall.base.BaseAppMallPresent;
import com.juntai.project.sell.mall.beans.sell.adapterbean.ImportantTagBean;
import com.juntai.project.sell.mall.beans.sell.adapterbean.LocationBean;
import com.juntai.project.sell.mall.beans.sell.adapterbean.PicBean;
import com.juntai.project.sell.mall.beans.shop.ShopCommodityListBean;
import com.juntai.project.sell.mall.beans.shop.ShopDetailBean;
import com.juntai.project.sell.mall.home.HomePageContract;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/5/8 8:50
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/8 8:50
 */
public class ShopPresent extends BaseAppMallPresent {


    /**
     * 店铺管理
     *
     * @return
     */
    public List<MultipleItem> getShopManagerData(ShopDetailBean.DataBean bean, boolean isDetail) {
        List<MultipleItem> arrays = new ArrayList<>();
        arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_SMALL, new ImportantTagBean
                (HomePageContract.SHOP_PIC, true)));
        arrays.add(new MultipleItem(MultipleItem.ITEM_HEAD_PIC,
                new PicBean(HomePageContract.SHOP_PIC, -1,
                        bean == null ? "" : bean.getHeadPortrait())));
        initTextType(arrays, MultipleItem.ITEM_EDIT, HomePageContract.SHOP_NAME, bean == null ? "" :
                        bean.getName()
                , true, 0,isDetail);
        initTextType(arrays, MultipleItem.ITEM_EDIT, HomePageContract.SHOP_INTRODUCTION, bean == null ? "" :
                bean.getIntroduction(), true, 1,isDetail);

        arrays.add(new MultipleItem(MultipleItem.ITEM_LOCATION, new LocationBean(bean == null ? null :
                bean.getGpsAddress()
                , bean == null ? null : bean.getLatitude(), bean == null ? null : bean.getLongitude())));
        initTextType(arrays, MultipleItem.ITEM_EDIT, HomePageContract.SHOP_TEL, bean == null ? "" :
                        bean.getName()
                , true, 0,isDetail);
        // TODO: 2022/6/9 这个地方需要调整
        initTextType(arrays, MultipleItem.ITEM_SELECT, HomePageContract.SHOP_CATEGORY, bean == null ? "" : "主营类目", true, 0,isDetail);
        arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_BIG, "店铺证件"));
        arrays.add(new MultipleItem(MultipleItem.ITEM_PIC,
                new PicBean(HomePageContract.SHOP_LICENSE, 1, bean == null ? "" :
                        bean.getBusinessLicense())));
        arrays.add(new MultipleItem(MultipleItem.ITEM_PIC,
                new PicBean(HomePageContract.ID_CARD_FRONT, 2, bean == null ? "" :
                        bean.getIdPositive())));
        arrays.add(new MultipleItem(MultipleItem.ITEM_PIC,
                new PicBean(HomePageContract.ID_CARD_BACK, 3, bean == null ? "" :
                        bean.getIdSide())));
        if (bean != null) {
            List<String> pics =bean.getShopImgList();
            arrays.add(new MultipleItem(MultipleItem.ITEM_FRAGMENT,
                    new PicBean(HomePageContract.SHOP_BANNER_PICS, 4,pics)));
        }else {
            arrays.add(new MultipleItem(MultipleItem.ITEM_FRAGMENT,
                    new PicBean(HomePageContract.SHOP_BANNER_PICS, 4, new ArrayList<>())));
        }
        return arrays;
    }


    /**
     * initTextType
     *
     * @param arrays
     * @param typeName
     * @param editHeightType 0代表高度固定 1代表不固定
     */
    private void initTextType(List<MultipleItem> arrays, int layoutType, String typeName, String value,
                              boolean isImportant, int editHeightType,boolean isDetail) {
        switch (layoutType) {
            case MultipleItem.ITEM_SELECT:
                arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_SMALL, new ImportantTagBean
                        (typeName, isImportant)));
                arrays.add(new MultipleItem(MultipleItem.ITEM_SELECT,
                        new TextKeyValueBean(typeName, value, String.format("%s%s", "请选择",
                                typeName), 0, isImportant,isDetail)));
                break;
            case MultipleItem.ITEM_EDIT:
                arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_SMALL, new ImportantTagBean(typeName,
                        isImportant)));
                arrays.add(new MultipleItem(MultipleItem.ITEM_EDIT,
                        new TextKeyValueBean(typeName, value,
                                String.format("%s%s", "请输入", typeName), editHeightType, isImportant,isDetail)));

                break;
            case MultipleItem.ITEM_EDIT2:
                arrays.add(new MultipleItem(MultipleItem.ITEM_EDIT2,
                        new TextKeyValueBean(typeName, value,
                                String.format("%s%s", "请输入", typeName), editHeightType, isImportant,isDetail)));
                break;
            default:
                break;
        }

    }

    public void getShopCommodityList(RequestBody requestBody, String tag) {
        AppNetModuleMall.createrRetrofit()
                .getShopCommodityList(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<ShopCommodityListBean>(getView()) {
                    @Override
                    public void onSuccess(ShopCommodityListBean o) {
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
