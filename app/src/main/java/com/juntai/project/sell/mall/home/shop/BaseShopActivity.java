package com.juntai.project.sell.mall.home.shop;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.base.BaseObserver;
import com.juntai.disabled.basecomponent.bean.TextKeyValueBean;
import com.juntai.disabled.basecomponent.bean.UploadFileBean;
import com.juntai.disabled.basecomponent.utils.MultipleItem;
import com.juntai.disabled.basecomponent.utils.PickerManager;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.bdmap.act.LocateSelectionActivity;
import com.juntai.project.sell.mall.AppHttpPathMall;
import com.juntai.project.sell.mall.AppNetModuleMall;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseRecyclerviewActivity;
import com.juntai.project.sell.mall.base.displayPicVideo.PicVideoDisplayActivity;
import com.juntai.project.sell.mall.base.selectPics.SelectPhotosFragment;
import com.juntai.project.sell.mall.beans.BaseAdapterDataBean;
import com.juntai.project.sell.mall.beans.ItemFragmentBean;
import com.juntai.project.sell.mall.beans.RadioBean;
import com.juntai.project.sell.mall.beans.sell.CommodityDetailBean;
import com.juntai.project.sell.mall.beans.sell.ShopCommodityCategoryListBean;
import com.juntai.project.sell.mall.beans.sell.adapterbean.LocationBean;
import com.juntai.project.sell.mall.beans.sell.adapterbean.PicBean;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.juntai.project.sell.mall.home.shop.shopCategory.ChoseCategoryActivity;
import com.juntai.project.sell.mall.mine.myinfo.HeadCropActivity;
import com.juntai.project.sell.mall.utils.StringTools;
import com.juntai.project.sell.mall.utils.UserInfoManagerMall;
import com.juntai.project.sell.mall.utils.bannerImageLoader.BannerObject;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/6/9 11:08
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/9 11:08
 */
public abstract class BaseShopActivity extends BaseRecyclerviewActivity<ShopPresent> implements HomePageContract.IHomePageView, SelectPhotosFragment.OnPhotoItemClick, View.OnClickListener, BaseShopAdapter.OnPicVideoLoadSuccessCallBack {

    private int currentPosition;
    private PicBean picBean;
    private String address;
    private TextView mSelectTv;
    private TextKeyValueBean selectBean;

    @Override
    protected LinearLayoutManager getBaseAdapterManager() {
        return null;
    }

    @Override
    protected void getRvAdapterData() {

    }

    @Override
    public boolean requestLocation() {
        return false;
    }

    @Override
    protected boolean enableRefresh() {
        return false;
    }

    @Override
    protected boolean enableLoadMore() {
        return false;
    }

    @Override
    protected BaseQuickAdapter getBaseQuickAdapter() {
        return new BaseShopAdapter(null, isDetail(), getSupportFragmentManager(), this);
    }


    @Override
    public void uploadPicVideo(ItemFragmentBean itemFragmentBean, List<String> icons) {
        if (icons.size() > 0) {
            List<String> pics = new ArrayList<>();
            for (String icon : icons) {
                if (!icon.contains("www.juntaikeji")) {
                    pics.add(icon);
                }
            }
            if (pics.size() > 0) {

                MultipartBody.Builder builder = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM);

                for (String filePath : pics) {

                    try {
                        builder.addFormDataPart("file", URLEncoder.encode(filePath, "utf-8"), RequestBody.create(MediaType.parse("file"), new File(filePath)));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                AppNetModuleMall.createrRetrofit()
                        .uploadFiles(builder.build())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseObserver<UploadFileBean>(this) {
                            @Override
                            public void onSuccess(UploadFileBean o) {
                                itemFragmentBean.setFragmentPics(o.getFilePaths());
                            }

                            @Override
                            public void onError(String msg) {
                                ToastUtils.toast(mContext, msg);
                            }
                        });
            } else {
                // : 2022/6/10 删没了
                itemFragmentBean.setFragmentPics(null);
            }
        }
    }

    @Override
    public void initData() {
        super.initData();
        setTitleName(getTitleName());
        initAdapterClick();

    }

    private void initAdapterClick() {

        baseQuickAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {


            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                currentPosition = position;
                MultipleItem multipleItem = (MultipleItem) adapter.getData().get(position);

                switch (view.getId()) {
                    case R.id.form_pic_src_iv:
                    case R.id.form_head_pic_iv:
                        choseImage(0, BaseShopActivity.this, 1);
                        break;
                    case R.id.action_img:
                        choseImage(0, BaseShopActivity.this, 9);
                        break;
                    case R.id.location_ll:
                        //跳转到选择位置类
                        startActivityForResult(new Intent(mContext, LocateSelectionActivity.class),
                                LocateSelectionActivity.SELECT_ADDR);
                        break;
                    case R.id.select_value_tv:
                        mSelectTv = (TextView) adapter.getViewByPosition(mRecyclerview, position,
                                R.id.select_value_tv);
                        selectBean = (TextKeyValueBean) multipleItem.getObject();
                        switch (selectBean.getKey()) {
                            case HomePageContract.SHOP_CATEGORY:
                                startActivityForResult(new Intent(mContext, ChoseCategoryActivity.class)
                                        .putExtra(BASE_ID, 1), ChoseCategoryActivity.ACTIVITY_RESULT);
                                break;
                            case HomePageContract.COMMODITY_CATEGORY_NAME:
                                // : 2022/6/10 选择店铺主营类目
                                startActivityForResult(new Intent(mContext, ChoseCategoryActivity.class)
                                        .putExtra(BASE_ID, 0), ChoseCategoryActivity.ACTIVITY_RESULT);
                                break;
                            case HomePageContract.COMMODITY_SORT:
                                // : 2022/6/10 选择商品类目
                                mPresenter.getCommodityCategorys(getBaseBuilder().build(), AppHttpPathMall.ALL_COMMODITY_CATEGORY);
                                break;
                        }
                        break;


                    default:
                        break;
                }
            }
        });


    }

    @Override
    protected void onPicsAndEmpressed(List<String> icons) {
        if (icons.size() > 0) {
            String path = icons.get(0);
            MultipleItem multipleItem = (MultipleItem) baseQuickAdapter.getItem(currentPosition);
            switch (multipleItem.getItemType()) {
                default:
                    picBean = (PicBean) multipleItem.getObject();
                    if (HomePageContract.SHOP_PIC.equals(picBean.getPicName())) {
                        //跳转到裁剪头像的界面
                        startActivityForResult(new Intent(this, HeadCropActivity.class).putExtra(HeadCropActivity.HEAD_PIC,
                                path), BASE_REQUEST_RESULT);
                    } else {
                        // : 2022/6/10 上传图片
                        mPresenter.uploadFile(AppHttpPathMall.UPLOAD_ONE_PIC, path);
                    }
                    break;
            }


        }
    }

    @Override
    public void onLocationReceived(BDLocation bdLocation) {
        super.onLocationReceived(bdLocation);
        if (bdLocation != null) {
            lat = bdLocation.getLatitude();
            lng = bdLocation.getLongitude();
            address = bdLocation.getAddrStr();
            notifyLocationItem();
        }

    }

    private void notifyLocationItem() {
        List<MultipleItem> arrays = baseQuickAdapter.getData();
        for (int i = 0; i < arrays.size(); i++) {
            MultipleItem array = arrays.get(i);
            if (MultipleItem.ITEM_LOCATION == array.getItemType()) {
                //定位
                LocationBean locationBean = (LocationBean) array.getObject();
                locationBean.setAddress(address);
                locationBean.setLatitude(String.valueOf(lat));
                locationBean.setLongitude(String.valueOf(lng));
                baseQuickAdapter.notifyItemChanged(i);
                break;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == BASE_REQUEST_RESULT && resultCode == BASE_REQUEST_RESULT) {
            if (data != null) {
                String path = data.getStringExtra(HeadCropActivity.CROPED_HEAD_PIC);
                // : 2022/6/10 上传头像图片到后台
                mPresenter.uploadFile(AppHttpPathMall.UPLOAD_ONE_PIC, path);
            }

        }
        if (requestCode == LocateSelectionActivity.SELECT_ADDR && resultCode == BASE_REQUEST_RESULT) {
            //地址选择
            lat = data.getDoubleExtra(LocateSelectionActivity.LAT, 0.0);
            lng = data.getDoubleExtra(LocateSelectionActivity.LNG, 0.0);
            address = data.getStringExtra(LocateSelectionActivity.ADDRNAME);
            notifyLocationItem();

        }

        if (resultCode == ChoseCategoryActivity.ACTIVITY_RESULT) {
            if (data == null) {
                return;
            }
            //返回选择的经营类目
            String categoryids = data.getStringExtra(BASE_STRING);
            String categoryNames = data.getStringExtra(BASE_STRING2);
            if (selectBean != null) {
                mSelectTv.setText(categoryNames);
                selectBean.setValue(categoryNames);
                selectBean.setIds(categoryids);
            }

        }


    }


    protected abstract String getTitleName();

    @Override
    public void onClick(View v) {

    }

    /**
     * 获取adapter中的数据
     *
     * @return
     */
    protected BaseAdapterDataBean getBaseOfAdapterData() {
        BaseAdapterDataBean baseAdapterDataBean = new BaseAdapterDataBean();
        CommodityDetailBean commodityDetailBean = new CommodityDetailBean();
        FormBody.Builder builder = getBaseBuilder();
        builder.add("userAccount", UserInfoManagerMall.getAccount());
        List<MultipleItem> arrays = baseQuickAdapter.getData();
        for (MultipleItem array : arrays) {
            switch (array.getItemType()) {
                case MultipleItem.ITEM_RADIO:
                    //户口类别 家庭经济状况 项目级别 这几个上传的字段对应的是从1开始的 所以需要从默认的索引+1  相反的 获取到详情展示的时候 就需要-1了
                    RadioBean radioBean = (RadioBean) array.getObject();
                    switch (radioBean.getKey()) {
                        case HomePageContract.COMMODITY_POST_FREE:
                            commodityDetailBean.setIsPostage(radioBean.getDefaultSelectedIndex());
                            break;
                        default:
                            break;
                    }
                    break;
                case MultipleItem.ITEM_HEAD_PIC:
                    PicBean headPicBean = (PicBean) array.getObject();
                    if (TextUtils.isEmpty(headPicBean.getPicPath())) {
                        ToastUtils.toast(mContext, "请选择店铺照片");
                        return null;
                    }
                    builder.add("headPortrait", headPicBean.getPicPath());
                    break;

                case MultipleItem.ITEM_LOCATION:
                    LocationBean locationBean = (LocationBean) array.getObject();
                    builder.add("gpsAddress", locationBean.getAddress());
                    builder.add("longitude", locationBean.getLongitude());
                    builder.add("latitude", locationBean.getLatitude());
                    break;

                case MultipleItem.ITEM_FRAGMENT:
                case MultipleItem.ITEM_FRAGMENT2:
                case MultipleItem.ITEM_FRAGMENT_VIDEO:
                    //多选图片
                    ItemFragmentBean fragmentPicBean = (ItemFragmentBean) array.getObject();
                    List<String> photos = fragmentPicBean.getFragmentPics();
                    String name = fragmentPicBean.getKey();
                    String msg = String.format("请选择%s", name);
                    if (!HomePageContract.COMMODITY_VIDEO.equals(name)) {
                        if (photos == null || photos.isEmpty()) {
                            ToastUtils.toast(mContext, msg);
                            return null;
                        }
                    }
                    if (photos != null&&photos.size()>0) {
                        String path = photos.get(0);
                        switch (name) {
                            case HomePageContract.COMMODITY_PRIMARY_PIC:
                                commodityDetailBean.setCoverImg(path);

                                break;
                            case HomePageContract.COMMODITY_VIDEO:
                                commodityDetailBean.setVideoUrl(path);
                                break;
                            case HomePageContract.COMMODITY_BANNER_PICS:
                                List<CommodityDetailBean.ImagesBean> imagesBeans = new ArrayList<>();

                                for (String photo : photos) {
                                    imagesBeans.add(new CommodityDetailBean.ImagesBean(photo));
                                }
                                commodityDetailBean.setImages(imagesBeans);
                                commodityDetailBean.setCommodityImg(imagesBeans);

                                break;
                            default:
                                break;
                        }
                    }


                    break;
                case MultipleItem.ITEM_PIC:
                    PicBean picBean = (PicBean) array.getObject();
                    String picKey = picBean.getPicName();
                    String picPath = picBean.getPicPath();
                    if (picKey != null) {
                        switch (picKey) {
                            case HomePageContract.SHOP_LICENSE:
                                if (!StringTools.isStringValueOk(picPath)) {
                                    ToastUtils.toast(mContext, "请选择店铺营业执照");
                                    return null;
                                }
                                builder.add("businessLicense", picPath);
                                break;
                            case HomePageContract.ID_CARD_FRONT:
                                if (!StringTools.isStringValueOk(picPath)) {
                                    ToastUtils.toast(mContext, "法人身份证正面照");
                                    return null;
                                }
                                builder.add("idPositive", picPath);
                                break;
                            case HomePageContract.ID_CARD_BACK:
                                if (!StringTools.isStringValueOk(picPath)) {
                                    ToastUtils.toast(mContext, "请选择法人身份证反面照");
                                    return null;
                                }
                                builder.add("idSide", picPath);
                                break;
                            case HomePageContract.SHOP_INNER_PICS:
                                if (!StringTools.isStringValueOk(picPath)) {
                                    ToastUtils.toast(mContext, "请选择店铺实景相片");
                                    return null;
                                }
                                builder.add("shopRealScene", picPath);
                                break;
                        }
                    }
                    break;
                case MultipleItem.ITEM_EDIT:
                    TextKeyValueBean textValueEditBean = (TextKeyValueBean) array
                            .getObject();
                    String textValue = textValueEditBean.getValue();
                    if (textValueEditBean.isImportant() && TextUtils.isEmpty(textValue)) {
                        String key = textValueEditBean.getKey();
                        ToastUtils.toast(mContext, "请输入" + key);
                        return null;
                    }
                    switch (textValueEditBean.getKey()) {
                        case HomePageContract.SHOP_NAME:
                            builder.add("name", textValue);
                            break;
                        case HomePageContract.SEND_COMPANY:
                            builder.add("logisticsName", textValue);
                            break;
                        case HomePageContract.SEND_NO:
                            builder.add("logisticsNumber", textValue);
                            break;
                        case HomePageContract.SEND_LINK:
                            builder.add("logisticsLink", textValue);
                            break;
                        case HomePageContract.SHOP_INTRODUCTION:
                            builder.add("introduction", textValue);
                            break;
                        case HomePageContract.ASSETS_WITHDRAW_PHONE:
                        case HomePageContract.SHOP_TEL:
                            builder.add("phoneNumber", textValue);
                            break;
                        case HomePageContract.ASSETS_WITHDRAW_BANK_CARD:
                            builder.add("bankCode", textValue);
                            break;
                        case HomePageContract.ASSETS_WITHDRAW_BANK:
                            builder.add("bankAddress", textValue);
                            break;
                        case HomePageContract.ASSETS_WITHDRAW_REAL_NAME:
                            builder.add("realName", textValue);
                            break;
                        case HomePageContract.ASSETS_WITHDRAW_IDCARD:
                            builder.add("idCode", textValue);
                            break;
                        case HomePageContract.COMMODITY_NAME:
                            commodityDetailBean.setName(textValue);
                            break;
                        case HomePageContract.COMMODITY_PRICE:
                            commodityDetailBean.setPrice(Double.parseDouble(textValue));
                            break;
                        case HomePageContract.COMMODITY_STOCK:
                            commodityDetailBean.setStock(Integer.parseInt(textValue));
                            break;
                        case HomePageContract.COMMODITY_POSTAGE:
                            commodityDetailBean.setTransportCharges(Double.parseDouble(textValue));
                            break;
                    }
                    break;

                case MultipleItem.ITEM_SELECT:
                    TextKeyValueBean textValueSelectBean = (TextKeyValueBean) array.getObject();
                    String textSelectValue = textValueSelectBean.getIds();

                    if (textValueSelectBean.isImportant() && !StringTools.isStringValueOk(textSelectValue)) {
                        ToastUtils.toast(mContext, "请选择" + textValueSelectBean.getKey());
                        return null;
                    }
                    switch (textValueSelectBean.getKey()) {
                        case HomePageContract.SHOP_CATEGORY:
                            builder.add("category", textSelectValue);
                            break;
                        case HomePageContract.COMMODITY_CATEGORY_NAME:
                            commodityDetailBean.setCategoryId(Integer.parseInt(textValueSelectBean.getIds()));
                            commodityDetailBean.setCategoryName(textSelectValue);
                            break;
                        case HomePageContract.COMMODITY_SORT:
                            commodityDetailBean.setShopClassifyId(Integer.parseInt(textValueSelectBean.getIds()));
                            commodityDetailBean.setShopClassifyName(textSelectValue);
                            break;
                    }
                    break;

            }
        }
        baseAdapterDataBean.setBuilder(builder);
        baseAdapterDataBean.setCommodityDetailBean(commodityDetailBean);
        return baseAdapterDataBean;
    }

    /**
     * 是否是详情
     *
     * @return
     */
    protected abstract boolean isDetail();

    @Override
    protected ShopPresent createPresenter() {
        return new ShopPresent();
    }

    @Override
    public void onVedioPicClick(BaseQuickAdapter adapter, int position) {
        showPicVideo(adapter, position);
    }

    @Override
    public void onPicClick(BaseQuickAdapter adapter, int position) {
        showPicVideo(adapter, position);
    }

    private void showPicVideo(BaseQuickAdapter adapter, int position) {
        List<BannerObject> bannerObjects = new ArrayList<>();
        List<String> arrays = adapter.getData();
        for (String pic : arrays) {
            if (pic.endsWith(".mp4")) {
                bannerObjects.add(new BannerObject(BannerObject.BANNER_TYPE_VIDEO, new BannerObject.VideoBean(pic, "")));
            }else {
                bannerObjects.add(new BannerObject(BannerObject.BANNER_TYPE_IMAGE, pic));
            }
        }
        PicVideoDisplayActivity.startPicVideoPlayActivity(mContext,bannerObjects,position);
    }

    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);

        switch (tag) {
            case AppHttpPathMall.UPLOAD_ONE_PIC:
                List<String> paths = (List<String>) o;
                if (paths != null && !paths.isEmpty()) {
                    String firstPic = paths.get(0);
                    if (picBean != null && baseQuickAdapter != null) {
                        picBean.setPicPath(firstPic);
                        baseQuickAdapter.notifyItemChanged(currentPosition);
                    }
                }
                break;
            case AppHttpPathMall.ALL_COMMODITY_CATEGORY:
                ShopCommodityCategoryListBean categoryListBean = (ShopCommodityCategoryListBean) o;
                if (categoryListBean != null) {
                    List<ShopCommodityCategoryListBean.DataBean> arrays = categoryListBean.getData();
                    PickerManager.getInstance().showOptionPicker(mContext, arrays, new PickerManager.OnOptionPickerSelectedListener() {
                        @Override
                        public void onOptionsSelect(int options1, int option2, int options3, View v) {
                            String name = arrays.get(options1).getShopClassifyName();
                            selectBean.setValue(name);
                            selectBean.setIds(String.valueOf(arrays.get(options1).getId()));
                            mSelectTv.setText(name);
                        }
                    });

                }

                break;
            default:
                break;
        }
    }

}
