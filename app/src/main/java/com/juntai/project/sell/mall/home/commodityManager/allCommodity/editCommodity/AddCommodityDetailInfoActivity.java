package com.juntai.project.sell.mall.home.commodityManager.allCommodity.editCommodity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v4.widget.ImageViewCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.juntai.disabled.basecomponent.utils.GsonTools;
import com.juntai.disabled.basecomponent.utils.HawkProperty;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.basecomponent.utils.eventbus.EventBusObject;
import com.juntai.disabled.basecomponent.utils.eventbus.EventManager;
import com.juntai.project.sell.mall.AppHttpPathMall;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseAppActivity;
import com.juntai.project.sell.mall.beans.sell.CommodityDetailBean;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.juntai.project.sell.mall.home.shop.ShopPresent;
import com.juntai.project.sell.mall.utils.UserInfoManagerMall;
import com.orhanobut.hawk.Hawk;

import java.util.List;

import cn.qzb.richeditor.RE;
import cn.qzb.richeditor.RichEditor;

/**
 * @aouther tobato
 * @description 描述  添加商品详情
 * @date 2022/6/14 15:27
 */
public class AddCommodityDetailInfoActivity extends BaseAppActivity<ShopPresent> implements HomePageContract.IHomePageView, View.OnClickListener {
    private int iconSelectColor = Color.BLACK;
    private int iconDefaultColor = Color.parseColor("#CDCDCD");
    private ImageView mActionImg;
    private ImageView mActionBold;
    private ImageView mActionItalic;
    private ImageView mActionUnderline;
    private ImageView mActionAlignLeft;
    private ImageView mActionAlignCenter;
    private ImageView mActionAlignRight;
    private ImageView mActionUndo;
    private ImageView mActionRedo;
    private FrameLayout mWebContainer;
    /**
     * 提交
     */
    private TextView mCommitTv;

    private RichEditor mEditor;//
    public RE re;
    private CommodityDetailBean commodityDetailBean;
private boolean  isEdit = false;
    @Override
    protected ShopPresent createPresenter() {
        return new ShopPresent();
    }

    @Override
    public int getLayoutView() {
        return R.layout.add_commodity_detail_activity;
    }

    @Override
    public void initView() {
        commodityDetailBean = getIntent().getParcelableExtra(BASE_PARCELABLE);
        setTitleName("添加商品详情");
        mActionImg = (ImageView) findViewById(R.id.action_img);
        mActionImg.setOnClickListener(this);
        mActionBold = (ImageView) findViewById(R.id.action_bold);
        mActionBold.setOnClickListener(this);
        mActionItalic = (ImageView) findViewById(R.id.action_italic);
        mActionItalic.setOnClickListener(this);
        mActionUnderline = (ImageView) findViewById(R.id.action_underline);
        mActionUnderline.setOnClickListener(this);
        mActionAlignLeft = (ImageView) findViewById(R.id.action_align_left);
        mActionAlignLeft.setOnClickListener(this);
        mActionAlignCenter = (ImageView) findViewById(R.id.action_align_center);
        mActionAlignCenter.setOnClickListener(this);
        mActionAlignRight = (ImageView) findViewById(R.id.action_align_right);
        mActionAlignRight.setOnClickListener(this);
        mActionUndo = (ImageView) findViewById(R.id.action_undo);
        mActionUndo.setOnClickListener(this);
        mActionRedo = (ImageView) findViewById(R.id.action_redo);
        mActionRedo.setOnClickListener(this);
        mWebContainer = (FrameLayout) findViewById(R.id.web_container);
        mCommitTv = (TextView) findViewById(R.id.commit_tv);
        mCommitTv.setOnClickListener(this);
    }

    @Override
    public void initData() {
        changeIconColor(mActionBold, iconDefaultColor);
        changeIconColor(mActionItalic, iconDefaultColor);
        changeIconColor(mActionUnderline, iconDefaultColor);
        changeIconColor(mActionAlignLeft, iconDefaultColor);
        changeIconColor(mActionAlignCenter, iconDefaultColor);
        changeIconColor(mActionAlignRight, iconDefaultColor);

        mEditor = new RichEditor(mContext.getApplicationContext());
        mWebContainer.addView(mEditor);
        re = RE.getInstance(mEditor);
        re.setPlaceHolder("请输入商品详情");
        re.setPadding(20, 10, 20, 10);
        if (!TextUtils.isEmpty(commodityDetailBean.getDescription())) {
            isEdit = true;
            re.setHtml(commodityDetailBean.getDescription());
        }else {
            isEdit = false;
        }
    }

    // 改变底部图标颜色
    private void changeIconColor(ImageView imageView, int color) {
        ImageViewCompat.setImageTintList(imageView, ColorStateList.valueOf(color));
    }


    @Override
    public void onSuccess(String tag, Object o) {
        switch (tag) {
            case AppHttpPathMall.UPLOAD_MORE_PIC:
                List<String> richImages = (List<String>) o;
                if (richImages != null && !richImages.isEmpty()) {
                    for (String richImage : richImages) {
                        re.insertImage(richImage, "image");
                        re.moveToEndEdit();
                    }
                }
                break;

            default:
                break;
            case AppHttpPathMall.ADD_COMMODITY_BASE_INFO:
                ToastUtils.toast(mContext, "添加成功");
                startAllCommodityActivity();
                EventManager.getEventBus().post(new EventBusObject(EventBusObject.REFRESH_COMMODITY_LIST,""));
                if (Hawk.contains(HawkProperty.COMMODITY_DETAIL)) {
                    Hawk.delete(HawkProperty.COMMODITY_DETAIL);

                }

                break;
            case AppHttpPathMall.UPDATE_COMMODITY_BASE_INFO:
                ToastUtils.toast(mContext, "修改成功");
                startAllCommodityActivity();
                EventManager.getEventBus().post(new EventBusObject(EventBusObject.REFRESH_COMMODITY_LIST,""));

                break;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.action_img:
                //图片选择
                choseImage(0, AddCommodityDetailInfoActivity.this, 9);
                break;
            case R.id.action_bold:
                re.setBold();
                if (re.isBold()) {
                    changeIconColor(mActionBold, iconSelectColor);
                } else {
                    changeIconColor(mActionBold, iconDefaultColor);
                }
                break;
            case R.id.action_italic:
                re.setItalic();
                if (re.isItalic()) {
                    changeIconColor(mActionItalic, iconSelectColor);
                } else {
                    changeIconColor(mActionItalic, iconDefaultColor);
                }
                break;
            case R.id.action_underline:
                re.setUnderLine();
                if (re.isUnderline()) {
                    changeIconColor(mActionUnderline, iconSelectColor);
                } else {
                    changeIconColor(mActionUnderline, iconDefaultColor);
                }
                break;
            case R.id.action_align_left:
                if (re.getTextAlign() == 1) {
                    return;
                }
                re.setAlignLeft();
                changeIconColor(mActionAlignLeft, iconSelectColor);
                changeIconColor(mActionAlignCenter, iconDefaultColor);
                changeIconColor(mActionAlignRight, iconDefaultColor);
                break;
            case R.id.action_align_center:
                if (re.getTextAlign() == 2) {
                    return;
                }
                re.setAlignCenter();
                changeIconColor(mActionAlignLeft, iconDefaultColor);
                changeIconColor(mActionAlignCenter, iconSelectColor);
                changeIconColor(mActionAlignRight, iconDefaultColor);
                break;
            case R.id.action_align_right:
                if (re.getTextAlign() == 3) {
                    return;
                }
                re.setAlignRight();
                changeIconColor(mActionAlignLeft, iconDefaultColor);
                changeIconColor(mActionAlignCenter, iconDefaultColor);
                changeIconColor(mActionAlignRight, iconSelectColor);
                break;
            case R.id.action_undo:
                re.undo();
                break;
            case R.id.action_redo:
                re.redo();
                break;
            case R.id.commit_tv:
                String des = re.getHtml().replaceAll("\n", "</br>");
                if (TextUtils.isEmpty(des)) {
                    ToastUtils.toast(mContext, "请输入商品详情");
                    return;
                }

                commodityDetailBean.setDescription(des);
                commodityDetailBean.setAccount(UserInfoManagerMall.getAccount());
                commodityDetailBean.setToken(UserInfoManagerMall.getUserToken());
                commodityDetailBean.setShopId(UserInfoManagerMall.getShopId());
                commodityDetailBean.setTypeEnd("app_seller");
                if (isEdit) {
                    mPresenter.updateCommodityBaseInfo(getJsonRequestBody(GsonTools.createGsonString(commodityDetailBean)), AppHttpPathMall.UPDATE_COMMODITY_BASE_INFO);

                }else {
                    mPresenter.addCommodityBaseInfo(getJsonRequestBody(GsonTools.createGsonString(commodityDetailBean)), AppHttpPathMall.ADD_COMMODITY_BASE_INFO);

                }
                break;
        }
    }

    @Override
    protected void onPicsAndEmpressed(List<String> icons) {
        if (icons.size() > 0) {
            mPresenter.uploadFile(icons, AppHttpPathMall.UPLOAD_MORE_PIC);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        destroyWebView();
    }

    public void destroyWebView() {
        if (mEditor != null) {
            ViewParent parent = mEditor.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(mEditor);
            }
            mEditor.stopLoading();
            mEditor.getSettings().setJavaScriptEnabled(false);
            mEditor.clearHistory();
            mEditor.removeAllViews();
            mEditor.destroy();
        }
    }
}
