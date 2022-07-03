package com.juntai.project.sell.mall.home.shop;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.ImageViewCompat;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.utils.MultipleItem;
import com.juntai.disabled.basecomponent.base.BaseActivity;
import com.juntai.disabled.basecomponent.bean.TextKeyValueBean;
import com.juntai.disabled.basecomponent.utils.DisplayUtil;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.disabled.basecomponent.utils.UrlFormatUtil;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.selectPics.SelectPhotosFragment;
import com.juntai.project.sell.mall.base.web.BaseWebviewFragment;
import com.juntai.project.sell.mall.beans.ItemFragmentBean;
import com.juntai.project.sell.mall.beans.RadioBean;
import com.juntai.project.sell.mall.beans.sell.adapterbean.BaseNormalRecyclerviewBean;
import com.juntai.project.sell.mall.beans.sell.adapterbean.ImportantTagBean;
import com.juntai.project.sell.mall.beans.sell.adapterbean.LocationBean;
import com.juntai.project.sell.mall.beans.sell.adapterbean.PicBean;
import com.juntai.project.sell.mall.home.HomePageContract;

import java.util.List;

import cn.qzb.richeditor.RE;
import cn.qzb.richeditor.RichEditor;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/4/22 11:11
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/22 11:11
 */
public class BaseShopAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {
    public static String SDCARD_TAG = "/storage/emulated";
    private int iconDefaultColor = Color.parseColor("#CDCDCD");
    private int iconSelectColor = Color.BLACK;

    private boolean isDetail = false;//是否是详情模式
    private FragmentManager mFragmentManager;
    private OnCheckEdittextValueFormatCallBack checkEdittextValueFormatCallBack;
    private BaseActivity baseActivity;
    private OnPicVideoLoadSuccessCallBack onPicVideoLoadSuccessCallBack;
    private RichEditor mEditor;
    private RE re;


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
    public BaseShopAdapter(List<MultipleItem> data, boolean isDetail, FragmentManager mFragmentManager, OnPicVideoLoadSuccessCallBack onPicVideoLoadSuccessCallBack) {
        super(data);
        addItemType(MultipleItem.ITEM_HEAD_PIC, R.layout.item_layout_type_head_pic);
        addItemType(MultipleItem.ITEM_TITILE_BIG, R.layout.item_layout_type_title_big);
        addItemType(MultipleItem.ITEM_TITILE_SMALL, R.layout.item_layout_type_title_small);
        addItemType(MultipleItem.ITEM_NOTICE, R.layout.item_layout_type_notice);
        addItemType(MultipleItem.ITEM_EDIT, R.layout.item_layout_type_edit);
        addItemType(MultipleItem.ITEM_EDIT2, R.layout.item_layout_type_edit2);
        addItemType(MultipleItem.ITEM_SELECT, R.layout.item_layout_type_select);
        addItemType(MultipleItem.ITEM_NORMAL_RECYCLEVIEW, R.layout.item_layout_type_recyclerview);
        addItemType(MultipleItem.ITEM_LOCATION, R.layout.item_layout_location);
        addItemType(MultipleItem.ITEM_TEXT, R.layout.item_text);
        addItemType(MultipleItem.ITEM_PIC, R.layout.item_pic);
        addItemType(MultipleItem.ITEM_RADIO, R.layout.item_layout_type_radio);
        addItemType(MultipleItem.ITEM_FRAGMENT, R.layout.item_pic_fragment);
        addItemType(MultipleItem.ITEM_FRAGMENT2, R.layout.item_pic_fragment2);
        addItemType(MultipleItem.ITEM_FRAGMENT_VIDEO, R.layout.item_pic_fragment3);
        addItemType(MultipleItem.ITEM_RICH_TEXT, R.layout.item_rich_text);
        this.isDetail = isDetail;
        this.mFragmentManager = mFragmentManager;
        this.onPicVideoLoadSuccessCallBack = onPicVideoLoadSuccessCallBack;
    }


    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        baseActivity = (BaseActivity) mContext;
        switch (item.getItemType()) {

            case MultipleItem.ITEM_NOTICE:
                helper.setText(R.id.item_notice_tv, (String) item.getObject());
                break;
            case MultipleItem.ITEM_RICH_TEXT:
                String richText = (String) item.getObject();
                BaseWebviewFragment webviewFragment = (BaseWebviewFragment) mFragmentManager.findFragmentById(R.id.base_webview_fg);
                webviewFragment.setWebData(richText);
                break;
            case MultipleItem.ITEM_RADIO:
                RadioBean radioBean = (RadioBean) item.getObject();
                RadioGroup radioGroup = helper.getView(R.id.item_radio_g);
                if (isDetail) {
                    for (int i = 0; i < radioGroup.getChildCount(); i++) {
                        radioGroup.getChildAt(i).setEnabled(false);
                    }
                } else {
                    for (int i = 0; i < radioGroup.getChildCount(); i++) {
                        radioGroup.getChildAt(i).setEnabled(true);
                    }
                }
                radioGroup.setTag(radioBean);
                RadioButton radioButton0 = helper.getView(R.id.radio_zero_rb);
                RadioButton radioButton1 = helper.getView(R.id.radio_first_rb);
                RadioButton radioButton2 = helper.getView(R.id.radio_second_rb);
                RadioButton radioButton3 = helper.getView(R.id.radio_third_rb);
                String[] values = radioBean.getValues();
                radioButton2.setVisibility(View.GONE);
                radioButton3.setVisibility(View.GONE);
                if (values != null) {
                    if (values.length > 1) {
                        radioButton0.setText(values[0]);
                        radioButton1.setText(values[1]);
                        if (values.length == 3) {
                            radioButton2.setVisibility(View.VISIBLE);
                            radioButton2.setText(values[2]);
                        }
                        if (values.length == 4) {
                            radioButton2.setVisibility(View.VISIBLE);
                            radioButton2.setText(values[2]);
                            radioButton3.setVisibility(View.VISIBLE);
                            radioButton3.setText(values[3]);
                        }
                    }

                } else {
                    radioButton0.setText("是");
                    radioButton1.setText("否");
                }
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
//                        if (radioCheckedCallBack != null) {
//                            radioCheckedCallBack.radioChecked(group, checkedId);
//                        }
                        RadioBean radioBean = (RadioBean) group.getTag();
                        switch (checkedId) {
                            case R.id.radio_zero_rb:
                                radioBean.setDefaultSelectedIndex(0);
                                break;
                            case R.id.radio_first_rb:
                                radioBean.setDefaultSelectedIndex(1);
                                break;
                            case R.id.radio_second_rb:
                                radioBean.setDefaultSelectedIndex(2);
                                break;
                            case R.id.radio_third_rb:
                                radioBean.setDefaultSelectedIndex(3);
                                break;
                            default:
                                break;
                        }
                    }
                });
                int defaultIndex = radioBean.getDefaultSelectedIndex();

                switch (defaultIndex) {
                    case 0:
                        radioButton0.setChecked(true);
                        radioButton1.setChecked(false);
                        radioButton2.setChecked(false);
                        radioButton3.setChecked(false);
                        break;
                    case 1:
                        radioButton0.setChecked(false);
                        radioButton1.setChecked(true);
                        radioButton2.setChecked(false);
                        radioButton3.setChecked(false);
                        break;
                    case 2:
                        radioButton0.setChecked(false);
                        radioButton1.setChecked(false);
                        radioButton2.setChecked(true);
                        radioButton3.setChecked(false);
                        break;
                    case 3:
                        radioButton0.setChecked(false);
                        radioButton1.setChecked(false);
                        radioButton2.setChecked(false);
                        radioButton3.setChecked(true);
                        break;
                    default:
                        radioButton0.setChecked(false);
                        radioButton1.setChecked(false);
                        radioButton2.setChecked(false);
                        radioButton3.setChecked(false);
                        break;
                }

                break;
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
                    ImageLoadUtil.loadImage(mContext, 0, picIv);
                }
                break;
            case MultipleItem.ITEM_FRAGMENT:
            case MultipleItem.ITEM_FRAGMENT2:
            case MultipleItem.ITEM_FRAGMENT_VIDEO:
                //上传材料时 多选照片
                ItemFragmentBean itemFragmentBean = (ItemFragmentBean) item.getObject();
                SelectPhotosFragment fragment = null;
                switch (itemFragmentBean.getKey()) {
                    case HomePageContract.COMMODITY_PRIMARY_PIC:
                        fragment = (SelectPhotosFragment) mFragmentManager.findFragmentById(R.id.photo_fg);

                        break;
                    case HomePageContract.COMMODITY_BANNER_PICS:
                        fragment = (SelectPhotosFragment) mFragmentManager.findFragmentById(R.id.photo_fg2);

                        break;
                    case HomePageContract.COMMODITY_VIDEO:
                        fragment = (SelectPhotosFragment) mFragmentManager.findFragmentById(R.id.photo_fg3);
                        break;
                    default:
                        break;
                }

                fragment.setMaxCount(itemFragmentBean.getmMaxCount()).setObject(itemFragmentBean);
                List<String> pics = itemFragmentBean.getFragmentPics();
                if (pics.size()>0) {
                    fragment.setIcons(pics);
                }
                if (isDetail) {
                    fragment.setPhotoDelateable(false).setMaxCount(itemFragmentBean.getFragmentPics().size());
                    if (!itemFragmentBean.getFragmentPics().isEmpty()) {
                        fragment.setIcons(itemFragmentBean.getFragmentPics());
                    }
                } else {
                    fragment.setPhotoDelateable(true).setMaxCount(itemFragmentBean.getmMaxCount());
                }

                SelectPhotosFragment finalFragment = fragment;
                fragment.setSpanCount(itemFragmentBean.getmSpanCount()).setOnPicLoadSuccessCallBack(new SelectPhotosFragment.OnPicLoadSuccessCallBack() {
                    @Override
                    public void loadSuccess(List<String> icons) {

                        ItemFragmentBean itemFragmentBean = (ItemFragmentBean) finalFragment.getObject();
                        if (onPicVideoLoadSuccessCallBack != null) {
                            onPicVideoLoadSuccessCallBack.uploadPicVideo(itemFragmentBean, icons);
                        }

                    }
                });
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

    // 改变底部图标颜色
    private void changeIconColor(ImageView imageView, int color) {
        ImageViewCompat.setImageTintList(imageView, ColorStateList.valueOf(color));
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


    public interface OnPicVideoLoadSuccessCallBack {
        void uploadPicVideo(ItemFragmentBean itemFragmentBean, List<String> icons);
    }

}
