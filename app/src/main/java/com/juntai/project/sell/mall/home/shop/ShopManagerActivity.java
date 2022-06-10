package com.juntai.project.sell.mall.home.shop;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.project.sell.mall.R;

import okhttp3.FormBody;

/**
 * @aouther tobato
 * @description 描述 店铺管理
 * @date 2022/6/9 14:15
 */
public class ShopManagerActivity extends BaseShopActivity {


    private TextView mShopProtocalTv;
    private boolean isAgree = false;

    @Override
    protected boolean isDetail() {
        return false;
    }


    @Override
    public void initData() {
        super.initData();
        baseQuickAdapter.setNewData(mPresenter.getShopManagerData(null, false));

    }

    @Override
    protected View getFootView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.footview_commit, null);
        TextView commitTv = view.findViewById(R.id.commit_business_form_tv);
        mShopProtocalTv = view.findViewById(R.id.shop_protocal_tv);
        initViewLeftDrawable(mShopProtocalTv, R.mipmap.radiobutton_normal, 20, 20);
        commitTv.setOnClickListener(this);
        commitTv.setText("提交店铺申请");
        mShopProtocalTv.setOnClickListener(this);
        return view;
    }

    @Override
    protected String getTitleName() {
        return "店铺管理";
    }



    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.commit_business_form_tv:
                // TODO: 2022/6/9 提交店铺申请
                FormBody.Builder builder =  getBuilderOfAdapterData();
                if (builder == null) {
                    return;
                }
                if (!isAgree) {
                    ToastUtils.toast(mContext, "请同意开店协议之后再提交");
                }


                break;
            case R.id.shop_protocal_tv:
                // : 2022/6/9 协议
                isAgree = !isAgree;
                if (isAgree) {
                    initViewLeftDrawable(mShopProtocalTv, R.mipmap.radiobutton_press, 20, 20);
                } else {
                    initViewLeftDrawable(mShopProtocalTv, R.mipmap.radiobutton_normal, 20, 20);

                }
                break;
            default:
                break;
        }
    }
}
