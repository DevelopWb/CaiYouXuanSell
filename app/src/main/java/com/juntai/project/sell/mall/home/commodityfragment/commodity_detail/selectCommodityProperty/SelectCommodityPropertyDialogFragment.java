package com.juntai.project.sell.mall.home.commodityfragment.commodity_detail.selectCommodityProperty;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.base.BaseBottomSheetFragment;
import com.juntai.disabled.basecomponent.base.view.NumberButton;
import com.juntai.disabled.basecomponent.bean.objectboxbean.CommodityPropertyBean;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.beans.CommodityDetailBean;
import com.juntai.project.sell.mall.beans.CommodityPropertyListBean;
import com.juntai.project.sell.mall.utils.ObjectBoxMallUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/5/5 14:51
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/5 14:51
 */
public class SelectCommodityPropertyDialogFragment extends BaseBottomSheetFragment implements View.OnClickListener {

    private View view;
    private ImageView mCommodityPicIv;
    /**
     * ￥:1016.69
     */
    private TextView mAllPriceTv;
    private ImageView mCloseDialogIv;
    private RecyclerView mCommodityPropertyRv;
    private NumberButton mNumberButton;
    /**
     * 确定
     */
    private TextView mComfirmTv;

    private OnConfirmCallBack onConfirmCallBack;
    private CommodityPropertyAdapter commodityPropertyAdapter;
    private List<CommodityPropertyListBean> propertyListBeans = new ArrayList<>();
    private CommodityDetailBean.DataBean dataBean;
    private HashMap<String, String> propertyMap = new HashMap<>();
    private CommodityPropertyBean commodityPropertyBean;

    public void setOnConfirmCallBack(OnConfirmCallBack onConfirmCallBack) {
        this.onConfirmCallBack = onConfirmCallBack;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shop_join_cart, null);
        initView(view);
        return view;
    }

    public void initView(View view) {
        mCommodityPicIv = (ImageView) view.findViewById(R.id.commodity_pic_iv);
        mAllPriceTv = (TextView) view.findViewById(R.id.all_price_tv);
        mCloseDialogIv = (ImageView) view.findViewById(R.id.close_dialog_iv);
        mCloseDialogIv.setOnClickListener(this);
        mCommodityPropertyRv = (RecyclerView) view.findViewById(R.id.commodity_property_rv);
        mNumberButton = (NumberButton) view.findViewById(R.id.number_button);
        mComfirmTv = (TextView) view.findViewById(R.id.comfirm_tv);
        mComfirmTv.setOnClickListener(this);
        commodityPropertyAdapter = new CommodityPropertyAdapter(R.layout.commodity_property_item);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mCommodityPropertyRv.setLayoutManager(manager);
        mCommodityPropertyRv.setAdapter(commodityPropertyAdapter);
        commodityPropertyAdapter.setNewData(propertyListBeans);
        commodityPropertyAdapter.setOnAdapterItemCallBack(new CommodityPropertyAdapter.OnAdapterItemCallBack() {


            @Override
            public void propertySelected(CommodityPropertyListBean.PropertyContentBean propertyContentBean) {
                // : 2022/5/5 属性选择之后的逻辑
                if (propertyMap.containsKey(propertyContentBean.getPresentName())) {
                    propertyMap.remove(propertyContentBean.getPresentName());
                }
                propertyMap.put(propertyContentBean.getPresentName(), propertyContentBean.getContent());
// TODO: 2022/5/5  获取对应的图片和价格
                commodityPropertyBean = ObjectBoxMallUtil.getCommodityProperty(dataBean, propertyMap);
                if (commodityPropertyBean != null) {
                    ImageLoadUtil.loadSquareImageHasCorner(getContext(), commodityPropertyBean.getImage(), mCommodityPicIv);
                    mAllPriceTv.setText(String.valueOf(commodityPropertyBean.getPrice()));
                }

            }
        });
        ImageLoadUtil.loadSquareImageHasCorner(getContext(), dataBean.getCoverImg(), mCommodityPicIv);
        mAllPriceTv.setText(String.valueOf(dataBean.getPrice()));
        mNumberButton.setCurrentNumber(1).setInventory(dataBean.getStock());
        mNumberButton.setOnWarnListener(new NumberButton.OnWarnListener() {
            @Override
            public void onWarningForInventory(int inventory) {
                ToastUtils.toast(getContext(), "库存不足");
            }

            @Override
            public void onWarningForBuyMax(int max) {

            }

            @Override
            public void onTextChanged(int num) {

            }

        });
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

    /**
     * @param dataBean
     */
    public void setData(CommodityDetailBean.DataBean dataBean) {
        this.dataBean = dataBean;
        if (dataBean != null) {
            List<CommodityDetailBean.DataBean.ResultBean> resultBeans = dataBean.getResult();
            if (resultBeans != null) {
                for (CommodityDetailBean.DataBean.ResultBean resultBean : resultBeans) {
                    String propertyName = resultBean.getValue();
                    List<String> propertys = resultBean.getDetail();
                    List<CommodityPropertyListBean.PropertyContentBean> propertyContentBeans = new ArrayList<>();
                    for (String property : propertys) {
                        propertyContentBeans.add(new CommodityPropertyListBean.PropertyContentBean(propertyName, property));
                    }
                    propertyListBeans.add(new CommodityPropertyListBean(propertyName, propertyContentBeans));
                }
            }
        }
        if (commodityPropertyAdapter != null) {
            commodityPropertyAdapter.setNewData(propertyListBeans);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.close_dialog_iv:
                dismiss();
                break;
            case R.id.comfirm_tv:
                // : 2022/5/5  加入购物车
                if (onConfirmCallBack != null) {
                    if (commodityPropertyBean == null || propertyMap.size() != propertyListBeans.size()) {
                        ToastUtils.toast(getContext(), "请选择商品属性");
                        return;
                    }
                    onConfirmCallBack.confirm(commodityPropertyBean, mNumberButton.getNumber());
                    dismiss();
                }
                break;
        }
    }

    public interface OnConfirmCallBack {

        void confirm(CommodityPropertyBean commodityPropertyBean, int amount);
    }


    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        propertyMap.clear();
        propertyMap = null;
    }


}
