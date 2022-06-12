package com.juntai.project.sell.mall.home.shop;

import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.chat.bean.UploadFileBean;
import com.example.chat.util.MultipleItem;
import com.juntai.disabled.basecomponent.base.BaseActivity;
import com.juntai.disabled.basecomponent.base.BaseObserver;
import com.juntai.disabled.basecomponent.bean.TextKeyValueBean;
import com.juntai.disabled.basecomponent.utils.DisplayUtil;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.disabled.basecomponent.utils.UrlFormatUtil;
import com.juntai.project.sell.mall.AppNetModuleMall;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.selectPics.SelectBigPicFragment;
import com.juntai.project.sell.mall.base.selectPics.SelectPhotosFragment;
import com.juntai.project.sell.mall.beans.sell.adapterbean.BaseNormalRecyclerviewBean;
import com.juntai.project.sell.mall.beans.sell.adapterbean.ImportantTagBean;
import com.juntai.project.sell.mall.beans.sell.adapterbean.LocationBean;
import com.juntai.project.sell.mall.beans.sell.adapterbean.PicBean;
import com.juntai.project.sell.mall.home.HomePageContract;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/4/22 11:11
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/22 11:11
 */
public class BaseShopAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {
    public static String SDCARD_TAG = "/storage/emulated";

    private boolean isDetail = false;//是否是详情模式
    private FragmentManager mFragmentManager;
    private OnCheckEdittextValueFormatCallBack checkEdittextValueFormatCallBack;
    private BaseActivity baseActivity;


    public void setCheckEdittextValueFormatCallBack(OnCheckEdittextValueFormatCallBack checkEdittextValueFormatCallBack) {
        this.checkEdittextValueFormatCallBack = checkEdittextValueFormatCallBack;
    }


    public void setDetail(boolean detail) {
        isDetail = detail;
    }

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public BaseShopAdapter(List<MultipleItem> data, boolean isDetail, FragmentManager mFragmentManager) {
        super(data);
        addItemType(MultipleItem.ITEM_HEAD_PIC, R.layout.item_layout_type_head_pic);
        addItemType(MultipleItem.ITEM_TITILE_BIG, R.layout.item_layout_type_title_big);
        addItemType(MultipleItem.ITEM_TITILE_SMALL, R.layout.item_layout_type_title_small);
        addItemType(MultipleItem.ITEM_EDIT, R.layout.item_layout_type_edit);
        addItemType(MultipleItem.ITEM_EDIT2, R.layout.item_layout_type_edit2);
        addItemType(MultipleItem.ITEM_SELECT, R.layout.item_layout_type_select);
        addItemType(MultipleItem.ITEM_NORMAL_RECYCLEVIEW, R.layout.item_layout_type_recyclerview);
        addItemType(MultipleItem.ITEM_LOCATION, R.layout.item_layout_location);
        addItemType(MultipleItem.ITEM_TEXT, R.layout.item_text);
        addItemType(MultipleItem.ITEM_PIC, R.layout.item_pic);
        addItemType(MultipleItem.ITEM_FRAGMENT, R.layout.item_pic_fragment);
        this.isDetail = isDetail;
        this.mFragmentManager = mFragmentManager;
    }


    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        baseActivity = (BaseActivity) mContext;
        switch (item.getItemType()) {
            case MultipleItem.ITEM_PIC:
                PicBean businessPicBean = (PicBean) item.getObject();
                String picPath = businessPicBean.getPicPath();
                String name = businessPicBean.getPicName();
                int index = businessPicBean.getPicNameIndex();
                if (index > 0) {
                    helper.setText(R.id.form_pic_title_tv, String.format("%s%s%s", String.valueOf(index), ".",
                            name));
                } else {
                    helper.setText(R.id.form_pic_title_tv, name);
                }
                ImageView picIv = helper.getView(R.id.form_pic_src_iv);
                helper.setGone(R.id.pic_form_notice_tv, false);
                //详情时 图片不可点击  示例图不可点击
                if (!isDetail) {
                    helper.addOnClickListener(R.id.form_pic_src_iv);
                }

                if (!TextUtils.isEmpty(picPath)) {
                    ImageLoadUtil.loadImage(mContext, picPath, picIv);

                } else {
                    ImageLoadUtil.loadImage(mContext,0, picIv);
                }
                break;
            case MultipleItem.ITEM_FRAGMENT:
                //上传材料时 多选照片
                PicBean picBean = (PicBean) item.getObject();
                SelectBigPicFragment fragment = (SelectBigPicFragment) mFragmentManager.findFragmentById(R.id.photo_fg);
                fragment.setObject(picBean);

                if (isDetail) {
                    fragment.setPhotoDelateable(false).setMaxCount(picBean.getFragmentPics().size());
                    if (!picBean.getFragmentPics().isEmpty()) {
                        fragment.setIcons(picBean.getFragmentPics());
                    }
                } else {
                    fragment.setPhotoDelateable(true).setMaxCount(1);
                }

                fragment.setSpanCount(1).setOnPicLoadSuccessCallBack(new SelectPhotosFragment.OnPicLoadSuccessCallBack() {
                    @Override
                    public void loadSuccess(List<String> icons) {
                        PicBean picBean = (PicBean) fragment.getObject();
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
                                    .subscribe(new BaseObserver<UploadFileBean>(null) {
                                        @Override
                                        public void onSuccess(UploadFileBean o) {
                                            picBean.setFragmentPics(o.getFilePaths());
                                        }

                                        @Override
                                        public void onError(String msg) {
                                        }
                                    });
                        }else {
                            // : 2022/6/10 删没了
                            picBean.setFragmentPics(null);
                        }
                    }
                });


                int picNameIndex = picBean.getPicNameIndex();
                String picName = picBean.getPicName();
                if (picNameIndex > 0) {
                    helper.setText(R.id.form_pic_title_tv, String.format("%s%s%s", String.valueOf(picNameIndex), ".",
                            picBean.getPicName()));
                } else {
                    helper.setText(R.id.form_pic_title_tv, picBean.getPicName());
                }
                helper.setGone(R.id.pic_form_notice_tv, false);
                //                //详情时 图片不可点击  示例图不可点击
                //                if (!isDetail) {
                //                    helper.addOnClickListener(R.id.form_pic_src_iv);
                //                }
                break;
            case MultipleItem.ITEM_TEXT:
//                BaseStringBean baseStringBean = (BaseStringBean) item.getObject();
////                String des = mContext.getString(R.string.test);
//                TextView textView = helper.getView(R.id.single_text_tv);
//                helper.setText(R.id.single_text_tv, baseStringBean.getContent());
//                textView.setGravity(baseStringBean.getGrivityType());
                break;
            case MultipleItem.ITEM_HEAD_PIC:
                helper.addOnClickListener(R.id.form_head_pic_iv);
                PicBean headPicBean = (PicBean) item.getObject();
                ImageView headIv = helper.getView(R.id.form_head_pic_iv);
                if (!isDetail) {
                    helper.addOnClickListener(R.id.form_head_pic_iv);
                }
                String headPicPath = headPicBean.getPicPath();
                if (!TextUtils.isEmpty(headPicPath)) {
                    if (headPicPath.contains(SDCARD_TAG)) {
                        //本地照片
                        ImageLoadUtil.loadImageNoCache(mContext, headPicPath, headIv);
                    } else {
                        //网络照片
                        ImageLoadUtil.loadImageNoCache(mContext, UrlFormatUtil.getImageOriginalUrl(headPicPath),
                                headIv, R.mipmap.ic_error);
                    }

                } else {
                    ImageLoadUtil.loadImage(mContext, R.mipmap.two_inch_pic, headIv);
                }
                break;
            case MultipleItem.ITEM_TITILE_BIG:
                helper.setText(R.id.item_big_title_tv, (String) item.getObject());
                break;
            case MultipleItem.ITEM_TITILE_SMALL:
                ImportantTagBean importantTagBean = (ImportantTagBean) item.getObject();
                helper.setGone(R.id.important_tag_tv, importantTagBean.isImportant());
                helper.setText(R.id.item_small_title_tv, importantTagBean.getTitleName());
                break;
            case MultipleItem.ITEM_EDIT:
                TextKeyValueBean textValueEditBean = (TextKeyValueBean) item.getObject();
                EditText editText = helper.getView(R.id.edit_value_et);
                if (isDetail) {
                    editText.setClickable(false);
                    editText.setFocusable(false);
                    helper.setBackgroundRes(R.id.edit_value_et, R.drawable.sp_filled_gray_lighter);
                } else {
                    editText.setClickable(true);
                    editText.setFocusable(true);
                    helper.setBackgroundRes(R.id.edit_value_et, R.drawable.stroke_gray_square_bg);

                }
                int editType = textValueEditBean.getType();
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) editText.getLayoutParams();
                if (0 == editType) {
                    lp.height = DisplayUtil.dp2px(mContext, 32);
                    editText.setGravity(Gravity.CENTER_VERTICAL);
                    editText.setSingleLine(true);
                } else {
                    lp.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                    editText.setMinimumHeight(DisplayUtil.dp2px(mContext, 100));
                    editText.setGravity(Gravity.TOP);
                    editText.setSingleLine(false);
                }
                editText.setLayoutParams(lp);
                editText.setTag(textValueEditBean);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        TextKeyValueBean editBean = (TextKeyValueBean) editText.getTag();
                        String str = s.toString().trim();
                        editBean.setValue(str);
                    }
                });
                editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        TextKeyValueBean editBean = (TextKeyValueBean) editText.getTag();
                        if (!hasFocus) {
                            if (checkEdittextValueFormatCallBack != null) {
                                checkEdittextValueFormatCallBack.checkEdittextValueFormat(editBean);
                            }
                        }
                    }
                });
                editText.setHint(textValueEditBean.getHint());
                editText.setText(textValueEditBean.getValue());
                String editKey = ((TextKeyValueBean) editText.getTag()).getKey();
                //正则
                switch (editKey) {
                    case HomePageContract.SHOP_TEL:
                        //联系方式
                        editText.setInputType(InputType.TYPE_CLASS_PHONE);
                        break;
                    default:
                        //输入类型为普通文本
                        editText.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                }
                break;
            case MultipleItem.ITEM_EDIT2:
                TextKeyValueBean textValueEditBean2 = (TextKeyValueBean) item.getObject();
                EditText editText2 = helper.getView(R.id.value_et);
                initEdittextFocuseStatus(editText2);
                TextView textView2 = helper.getView(R.id.key_tv);
                editText2.setTag(textValueEditBean2);
                addTextChange(editText2);
                editText2.setText(textValueEditBean2.getValue());
                break;
            case MultipleItem.ITEM_SELECT:
                TextKeyValueBean textValueSelectBean = (TextKeyValueBean) item.getObject();
                TextView textViewTv = helper.getView(R.id.select_value_tv);
                String selectTextValue = textValueSelectBean.getValue();
                if (!isDetail) {
                    helper.addOnClickListener(R.id.select_value_tv);
                    helper.addOnClickListener(R.id.tool_pic_iv);
                    helper.setBackgroundRes(R.id.select_value_tv, R.drawable.stroke_gray_square_bg);
                    helper.setGone(R.id.select_arrow_right_iv, true);
                } else {
                    helper.setGone(R.id.select_arrow_right_iv, false);
                    helper.setBackgroundRes(R.id.select_value_tv, R.drawable.sp_filled_gray_lighter);
                }
                textViewTv.setTag(textValueSelectBean);
                textViewTv.setHint(textValueSelectBean.getHint());
                if (selectTextValue.contains("\\n")) {
                    selectTextValue = selectTextValue.replace("\\n", "\n");
                }
                textViewTv.setText(selectTextValue);
                break;
            case MultipleItem.ITEM_NORMAL_RECYCLEVIEW:
                //recycleview
                BaseNormalRecyclerviewBean baseNormalRecyclerviewBean = (BaseNormalRecyclerviewBean) item.getObject();
                RecyclerView recyclerView = helper.getView(R.id.item_normal_rv);
                LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL
                        , false);
                BaseQuickAdapter adapter = baseNormalRecyclerviewBean.getAdapter();
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(manager);
//                switch (baseNormalRecyclerviewBean.getType()) {
//                    case BaseInspectContract.BASE_RECYCLERVIEW_TYPE_TEXT_VALUE:
//                        List<TextKeyValueBean> arrays = (List<TextKeyValueBean>) baseNormalRecyclerviewBean.getObject();
//                        adapter.setNewData(arrays);
//                        break;
//                    default:
//                        break;
//                }
                break;
            case MultipleItem.ITEM_LOCATION:
                LocationBean locationBean = (LocationBean) item.getObject();
                if (!isDetail) {
                    helper.addOnClickListener(R.id.location_ll);
                    helper.setGone(R.id.location_iv, true);
                } else {
                    helper.setGone(R.id.location_iv, false);
                }
                if (!TextUtils.isEmpty(locationBean.getAddress())) {

                    helper.setText(R.id.location_tv, locationBean.getAddress());
                }

                break;
        }
    }
    private void addTextChange(EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                switch (editText.getId()) {
                    case R.id.value_et:
                        TextKeyValueBean editBean = (TextKeyValueBean) editText.getTag();
                        editBean.setValue(s.toString().trim());
                        break;
                    default:
                        break;
                }
            }
        });
    }
    private void initEdittextFocuseStatus(EditText editText) {
        if (isDetail) {
            editText.setClickable(false);
            editText.setFocusable(false);
        } else {
            editText.setClickable(true);
            editText.setFocusable(true);
        }
    }

    /**
     * 控件失去焦点后  检测edittext控件输入内容的格式
     */
    interface OnCheckEdittextValueFormatCallBack {
        void checkEdittextValueFormat(TextKeyValueBean keyValueBean);
    }

}
