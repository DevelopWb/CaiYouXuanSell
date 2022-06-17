package com.juntai.project.sell.mall.home.commodityManager.allCommodity.commodityProperty;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.base.BaseBottomSheetFragment;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.beans.CommodityFormatListBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @UpdateUser: 更新者
 */
public class ModifyPriceStockFragment extends BaseBottomSheetFragment implements View.OnClickListener {
    private OnConfirmCallBack onConfirmCallBack;
    private CommodityFormatListBean.DataBean dataBean;
    private int position;

    public void setDataBean(CommodityFormatListBean.DataBean dataBean, int position) {
        this.dataBean = dataBean;
        this.position = position;
    }

    public void setOnConfirmCallBack(OnConfirmCallBack onConfirmCallBack) {
        this.onConfirmCallBack = onConfirmCallBack;
    }

    private ImageView mSelectorClose;
    private ImageView mCommodityPicIv;
    /**
     * 规格
     */
    private TextView mFormatTv;
    /**
     * 请输入价格
     */
    private EditText mCommodityPriceEt;
    /**
     * 请输入库存
     */
    private EditText mCommodityStockEt;
    /**
     * 取消
     */
    private TextView mCancelTv;
    /**
     * 确定
     */
    private TextView mConfirmTv;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_tv:
            case R.id.selector_close:
                dismiss();
                break;
            case R.id.confirm_tv:
                String price = mCommodityPriceEt.getText().toString().trim();
                if (TextUtils.isEmpty(price)) {
                    ToastUtils.toast(getContext(), "请输入价格");
                    return;
                }
                String stock = mCommodityStockEt.getText().toString().trim();
                if (TextUtils.isEmpty(stock)) {
                    ToastUtils.toast(getContext(), "请输入库存");
                    return;
                }
                if (onConfirmCallBack != null) {
                    onConfirmCallBack.confirm(Double.parseDouble(price), Integer.parseInt(stock), position);
                    dismiss();
                }

                break;
            default:
                break;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.modify_commodity_stock_price, null);
        initView(view);
        return view;
    }

    @Override
    protected LinearLayoutManager getBaseAdapterManager() {
        return null;
    }

    @Override
    protected BaseQuickAdapter getBaseQuickAdapter() {
        return null;
    }

    @Override
    protected int getBottomSheetDialogLayout() {
        return 0;
    }

    public void initView(View view) {
        mSelectorClose = (ImageView) view.findViewById(R.id.selector_close);
        mSelectorClose.setOnClickListener(this);
        mCommodityPicIv = (ImageView) view.findViewById(R.id.commodity_pic_iv);
        mFormatTv = (TextView) view.findViewById(R.id.format_tv);
        mCommodityPriceEt = (EditText) view.findViewById(R.id.commodity_price_et);
        mCommodityStockEt = (EditText) view.findViewById(R.id.commodity_stock_et);
        mCancelTv = (TextView) view.findViewById(R.id.cancel_tv);
        mCancelTv.setOnClickListener(this);
        mConfirmTv = (TextView) view.findViewById(R.id.confirm_tv);
        mConfirmTv.setOnClickListener(this);
        ImageLoadUtil.loadSquareImage(getContext(), dataBean.getImage(), mCommodityPicIv);
        Map<String, String> map = dataBean.getDetail();
        List<String> detail = new ArrayList<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            detail.add(entry.getKey() + ":" + entry.getValue());
        }
        mFormatTv.setText(TextUtils.join("\n", detail));
        mCommodityPriceEt.setText(String.valueOf(dataBean.getPrice()));
        mCommodityStockEt.setText(String.valueOf(dataBean.getStock()));
    }

    public interface OnConfirmCallBack {

        void confirm(double price, int stock, int positon);
    }
}
