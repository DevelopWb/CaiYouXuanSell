package com.juntai.project.sell.mall.home.shop;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.chat.bean.UploadFileBean;
import com.example.chat.util.MultipleItem;
import com.juntai.disabled.basecomponent.base.BaseObserver;
import com.juntai.disabled.basecomponent.bean.TextKeyValueBean;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.bdmap.act.LocateSelectionActivity;
import com.juntai.project.sell.mall.AppHttpPathMall;
import com.juntai.project.sell.mall.AppNetModuleMall;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseRecyclerviewActivity;
import com.juntai.project.sell.mall.base.selectPics.SelectPhotosFragment;
import com.juntai.project.sell.mall.beans.ItemFragmentBean;
import com.juntai.project.sell.mall.beans.sell.adapterbean.LocationBean;
import com.juntai.project.sell.mall.beans.sell.adapterbean.PicBean;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.juntai.project.sell.mall.home.shop.shopCategory.ChoseCategoryActivity;
import com.juntai.project.sell.mall.mine.myinfo.HeadCropActivity;
import com.juntai.project.sell.mall.utils.StringTools;
import com.juntai.project.sell.mall.utils.UserInfoManagerMall;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
            MultipartBody.Builder builder = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM);
            for (String filePath : icons) {
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

    @Override
    public void initData() {
        super.initData();
        setTitleName(getTitleName());
        if (getFootView() != null) {
            baseQuickAdapter.setFooterView(getFootView());
        }
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
                                // : 2022/6/10 选择店铺主营类目
                                startActivityForResult(new Intent(mContext, ChoseCategoryActivity.class), ChoseCategoryActivity.ACTIVITY_RESULT);
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
    protected void selectedPicsAndEmpressed(List<String> icons) {
        if (icons.size() > 0) {
            String path = icons.get(0);
            MultipleItem multipleItem = (MultipleItem) baseQuickAdapter.getItem(currentPosition);
            switch (multipleItem.getItemType()) {
                case MultipleItem.ITEM_RICH_TEXT:
                    mPresenter.uploadFile(icons,AppHttpPathMall.UPLOAD_MORE_PIC);
                    break;
                default:
                    picBean = (PicBean) multipleItem.getObject();
                    if (HomePageContract.SHOP_PIC.equals(picBean.getPicName())) {
                        //跳转到裁剪头像的界面
                        startActivityForResult(new Intent(this, HeadCropActivity.class).putExtra(HeadCropActivity.HEAD_PIC,
                                path), BASE_REQUEST_RESULT);
                    } else {
                        // TODO: 2022/6/10 上传图片
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

    protected abstract View getFootView();

    protected abstract String getTitleName();

    @Override
    public void onClick(View v) {

    }

    /**
     * 获取adapter中的数据
     *
     * @return
     */
    protected FormBody.Builder getBuilderOfAdapterData() {
        FormBody.Builder builder = getBaseBuilder();
        builder.add("userAccount", UserInfoManagerMall.getAccount());
        List<MultipleItem> arrays = baseQuickAdapter.getData();
        for (MultipleItem array : arrays) {
            switch (array.getItemType()) {
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
                    //多选图片
                    PicBean fragmentPicBean = (PicBean) array.getObject();
                    List<String> photos = fragmentPicBean.getFragmentPics();

                    String name = fragmentPicBean.getPicName();
                    String msg = String.format("请选择%s", name);
                    if (photos == null || photos.isEmpty()) {
                        ToastUtils.toast(mContext, msg);
                        return null;
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
                        case HomePageContract.SHOP_INTRODUCTION:
                            builder.add("introduction", textValue);
                            break;
                        case HomePageContract.SHOP_TEL:
                            builder.add("phoneNumber", textValue);
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
                    }
                    break;

            }
        }

        return builder;
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

    }

    @Override
    public void onPicClick(BaseQuickAdapter adapter, int position) {

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
            case AppHttpPathMall.UPLOAD_MORE_PIC:
                List<String> richImages = (List<String>) o;
                if (richImages != null && !richImages.isEmpty()) {
                    TextKeyValueBean keyValueBean = (TextKeyValueBean) ((MultipleItem)baseQuickAdapter.getItem(currentPosition)).getObject();
                    if (keyValueBean != null) {
                        ((BaseShopAdapter)baseQuickAdapter).insertImage(keyValueBean,currentPosition,richImages);
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((BaseShopAdapter)baseQuickAdapter).destroyWebView();
    }
}
