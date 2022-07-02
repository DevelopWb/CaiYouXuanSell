package com.juntai.project.sell.mall.mine.verified;

import android.support.constraint.Group;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.base.BaseResult;
import com.juntai.disabled.basecomponent.utils.HawkProperty;
import com.juntai.disabled.basecomponent.utils.PubUtil;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.video.img.DisPlayPicsActivity;
import com.juntai.project.sell.mall.AppHttpPathMall;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.selectPics.SelectPhotosFragment;
import com.juntai.project.sell.mall.base.sendcode.SmsCheckCodeActivity;
import com.juntai.project.sell.mall.beans.UserBeanMall;
import com.juntai.project.sell.mall.beans.VerifiedInfoBean;
import com.juntai.project.sell.mall.utils.StringTools;
import com.juntai.project.sell.mall.utils.UserInfoManagerMall;
import com.orhanobut.hawk.Hawk;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @aouther tobato
 * @description 描述  实名认证
 * @date 2020/3/11 15:52
 */
public class VerifiedActivity extends SmsCheckCodeActivity implements View.OnClickListener, SelectPhotosFragment.OnPhotoItemClick {

    /**
     * 请填写真实姓名
     */
    private EditText mNameEt;
    /**
     * 请填写身份证号
     */
    private EditText mIdCardTv;
    private Group mVerifiedInfoFillingG;
    private Group mVerifiedInfoFilledG;
    public static String VERIFIED_STATUS = "status";//认证状态
    /**
     * 确认
     */
    private TextView mVerifiedSucceedConfirmTv;
    /**
     * 确认
     */
    private TextView mVerifiedConfirmTv;
    /**
     * 提交成功！\n  我们需要人工审核, 最长1-3个工作日\n如遇紧急事情，请及时拨打110！
     */
    private TextView mVerifiedSuccessTagTv;
    private RecyclerView mRecyclerview;
    private SmartRefreshLayout mSmartrefreshlayout;
    private VerifiedAdapter adapter;
    private int currentPosition;
    File positiveIvsfile = null;
    File obverseIvsfile = null;
    File handIvsfile = null;
    /**
     * 请输入手机号码
     */
    private EditText mPhoneEt;
    /**
     * 请输入短信验证码
     */
    private EditText mCheckCodeEt;
    /**
     * 发送验证码
     */
    private TextView mSendCheckCodeTv;
    private LinearLayout mCompleteInfoPhoneLl;

    @Override
    public int getLayoutView() {
        return R.layout.activity_verified;
    }

    @Override
    public void initView() {
        setTitleName("实名认证");
        mVerifiedInfoFillingG = (Group) findViewById(R.id.verified_info_filling_g);
        mVerifiedInfoFilledG = (Group) findViewById(R.id.verified_info_filled_g);
        mVerifiedSucceedConfirmTv = (TextView) findViewById(R.id.verified_succeed_confirm_tv);
        mVerifiedSucceedConfirmTv.setOnClickListener(this);
        mVerifiedConfirmTv = (TextView) findViewById(R.id.verified_confirm_tv);
        mVerifiedConfirmTv.setOnClickListener(this);
        mVerifiedSuccessTagTv = (TextView) findViewById(R.id.verified_success_tag_tv);
        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        mSmartrefreshlayout = (SmartRefreshLayout) findViewById(R.id.smartrefreshlayout);
        mSmartrefreshlayout.setEnableRefresh(false);
        mSmartrefreshlayout.setEnableLoadMore(false);
        mSmartrefreshlayout.setBackgroundResource(R.drawable.stroke_gray_square_bg);
        mRecyclerview.setBackgroundResource(0);
        adapter = new VerifiedAdapter(R.layout.business_need_info_item);
        initRecyclerview(mRecyclerview, adapter, LinearLayoutManager.VERTICAL);
        adapter.setHeaderView(getHeadLayout());
        adapter.setFooterView(getFootLayout());
        mCompleteInfoPhoneLl.setVisibility(View.GONE);

//        if (UserInfoManager.getAccountStatus() == 1) {
//            mCompleteInfoPhoneLl.setVisibility(View.GONE);
//        } else {
//            mCompleteInfoPhoneLl.setVisibility(View.VISIBLE);
//        }

    }

    /**
     * 获取适配器数据
     *
     * @return
     */
    private List<VerifiedInfoBean> getAdapterData() {
        List<VerifiedInfoBean> arrays = new ArrayList<>();
        arrays.add(new VerifiedInfoBean("身份证正面照片", R.mipmap.sample_positive_icon));
        arrays.add(new VerifiedInfoBean("身份证反面照片", R.mipmap.sample_obverse_icon));
        arrays.add(new VerifiedInfoBean(getResources().getString(R.string.id_card_notice), R.mipmap.sample_hand_icon));
        return arrays;
    }

    /**
     * 获取头布局
     *
     * @return
     */
    private View getHeadLayout() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.verified_layout_head, null);
        mNameEt = (EditText) view.findViewById(R.id.name_et);
        mIdCardTv = (EditText) view.findViewById(R.id.id_card_tv);
        return view;
    }

    /**
     * 获取尾布局
     *
     * @return
     */
    private View getFootLayout() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.verified_layout_foot, null);
        mPhoneEt = (EditText) view.findViewById(R.id.phone_et);
        mCheckCodeEt = (EditText) view.findViewById(R.id.check_code_et);
        mSendCheckCodeTv = (TextView) view.findViewById(R.id.send_check_code_tv);
        mSendCheckCodeTv.setOnClickListener(this);
        mCompleteInfoPhoneLl = (LinearLayout) view.findViewById(R.id.complete_info_phone_ll);
        return view;
    }

    @Override
    public void initData() {
        if (getIntent() != null) {
            //实名认证状态（0：未提交）（1：提交审核中）（2：审核通过）（3：审核失败）
            mVerifiedInfoFillingG.setVisibility(View.VISIBLE);
            mVerifiedInfoFilledG.setVisibility(View.GONE);
            int status = getIntent().getIntExtra(VERIFIED_STATUS, 0);
            if (3 == status) {
                ToastUtils.warning(mContext, "审核失败,请重新提交");
            } else if (1 == status) {
                mVerifiedInfoFillingG.setVisibility(View.GONE);
                mVerifiedInfoFilledG.setVisibility(View.VISIBLE);
            }
        }
        adapter.setNewData(getAdapterData());
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                VerifiedInfoBean dataBean =
                        (VerifiedInfoBean) adapter.getData().get(position);
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add(String.valueOf(dataBean.getSampleRes()));
                currentPosition = position;
                switch (view.getId()) {
                    case R.id.info_img_old_iv:
                        // : 2022/6/6 展示图片大图
                        DisPlayPicsActivity.startDisplayPics(mContext,arrayList,0);
                        break;
                    case R.id.info_img_new_iv:
                        choseImage(0, VerifiedActivity.this, 1);
                        break;
                    default:
                        break;
                }
            }
        });

    }

    @Override
    protected TextView getSendCodeTv() {
        return mSendCheckCodeTv;
    }

    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);
        if (AppHttpPathMall.USER_AUTH.equals(tag)) {
            ToastUtils.success(mContext, ((BaseResult) o).message);
            UserBeanMall loginBean = (UserBeanMall) o;
            if (loginBean != null) {
                Hawk.put(HawkProperty.SP_KEY_USER, loginBean.getData());
                mVerifiedInfoFillingG.setVisibility(View.GONE);
                mVerifiedInfoFilledG.setVisibility(View.VISIBLE);
            }
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.verified_succeed_confirm_tv:
                //提醒审核后  关闭当前活动
                onBackPressed();
                break;
            case R.id.verified_confirm_tv:

                if (!StringTools.isStringValueOk(getTextViewValue(mNameEt))) {
                    ToastUtils.warning(mContext, "真实姓名不能为空");
                    return;
                }
                //填写完信息后提交
                String idCard = getTextViewValue(mIdCardTv);
                if (!PubUtil.checkIdCard(idCard)) {
                    ToastUtils.error(mContext, "身份证号格式输入有误");
                    return;
                }

                if (!checkSelectedPics()) {
                    return;
                }
                MultipartBody.Builder builder = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("account", UserInfoManagerMall.getAccount())
                        .addFormDataPart("userId", String.valueOf(UserInfoManagerMall.getUserId()))
                        .addFormDataPart("realName", getTextViewValue(mNameEt))
                        .addFormDataPart("idNumber", getTextViewValue(mIdCardTv))
                        .addFormDataPart("IDCardHead", "IDCardHead",
                                RequestBody.create(MediaType.parse("file"),
                                        positiveIvsfile))
                        .addFormDataPart("IDCardNationalEmblem", "IDCardNationalEmblem",
                                RequestBody.create(MediaType.parse("file"), obverseIvsfile))
                        .addFormDataPart("IDCardinHand", "IDCardinHand",
                                RequestBody.create(MediaType.parse("file"),
                                        handIvsfile));
//                if (UserInfoManager.getAccountStatus() != 1) {
//                    if (!mPresenter.checkMobile(getTextViewValue(mPhoneEt))) {
//                        return;
//                    }
//                    if (!StringTools.isStringValueOk(getTextViewValue(mCheckCodeEt))) {
//                        checkFormatError("验证码不能为空");
//                        return;
//                    }
//
//                    builder.addFormDataPart("phoneNumber", getTextViewValue(mPhoneEt))
//                            .addFormDataPart("code", getTextViewValue(mCheckCodeEt));
//                }
                mPresenter.userAuth(AppHttpPathMall.USER_AUTH, builder.build());
                break;
            case R.id.send_check_code_tv:
                sendCheckCode(getTextViewValue(mPhoneEt));
                break;
        }
    }

    /**
     * 检测照片选择情况
     *
     * @return
     */
    private boolean checkSelectedPics() {
        boolean isSelected = false;
        List<VerifiedInfoBean> arrays = adapter.getData();
        for (int i = 0; i < arrays.size(); i++) {
            VerifiedInfoBean bean = arrays.get(i);
            String realPath = bean.getRealPicPath();
            if (realPath == null || "".equals(realPath)) {
                isSelected = false;
                if (0 == i) {
                    ToastUtils.toast(mContext, "请选择身份证正面照片");
                } else if (1 == i) {
                    ToastUtils.toast(mContext, "请选择身份证反面照片");
                } else {
                    ToastUtils.toast(mContext, "请选择手持身份证照片");
                }
                break;
            } else {
                isSelected = true;
                if (0 == i) {
                    positiveIvsfile = new File(bean.getRealPicPath());
                } else if (1 == i) {
                    obverseIvsfile = new File(bean.getRealPicPath());
                } else {
                    handIvsfile = new File(bean.getRealPicPath());
                }
            }
        }
        return isSelected;
    }


    @Override
    public void onVedioPicClick(BaseQuickAdapter adapter, int position) {

    }

    @Override
    public void onPicClick(BaseQuickAdapter adapter, int position) {

    }

    @Override
    protected void onPicsAndEmpressed(List<String> icons) {
        if (icons.size() > 0) {
            String path = icons.get(0);
            List<VerifiedInfoBean> arrays = adapter.getData();
            for (int i = 0; i < arrays.size(); i++) {
                VerifiedInfoBean bean = arrays.get(i);
                if (currentPosition == i) {
                    bean.setRealPicPath(path);
                    adapter.notifyDataSetChanged();
                    break;
                }
            }
        }
    }


    @Override
    protected void onDestroy() {
        adapter.removeAllHeaderView();
        adapter.removeAllFooterView();
        super.onDestroy();
    }
}
