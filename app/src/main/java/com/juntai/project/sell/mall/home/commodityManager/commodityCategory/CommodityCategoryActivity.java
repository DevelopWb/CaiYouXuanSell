package com.juntai.project.sell.mall.home.commodityManager.commodityCategory;

import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.project.sell.mall.AppHttpPathMall;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseRecyclerviewActivity;
import com.juntai.project.sell.mall.beans.sell.ShopCommodityCategoryListBean;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.juntai.project.sell.mall.home.shop.ShopPresent;

import java.util.List;

/**
 * @aouther tobato
 * @description 描述 商品类目管理
 * @date 2022/6/12 11:13
 */
public class CommodityCategoryActivity extends BaseRecyclerviewActivity<ShopPresent> implements HomePageContract.IHomePageView, View.OnClickListener {

    /**
     * 输入类目名称
     */
    private EditText mCategoryNameEt;
    /**
     * 添加
     */
    private TextView mAddCategoryTv;

    @Override
    protected View getAdapterHeadView() {
        return getHeadView();
    }

    private View getHeadView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.add_commodity_category, null);
        mCategoryNameEt = (EditText) view.findViewById(R.id.category_name_et);
        mAddCategoryTv = (TextView) view.findViewById(R.id.add_category_tv);
        mAddCategoryTv.setOnClickListener(this);
        return view;
    }

    @Override
    protected View getAdapterFootView() {
        return null;
    }

    @Override
    public void initData() {
        super.initData();
        setTitleName("商品类目");
    }

    @Override
    protected LinearLayoutManager getBaseAdapterManager() {
        return null;
    }

    @Override
    protected void getRvAdapterData() {
        mPresenter.getCommodityCategorys(getBaseBuilder().build(), AppHttpPathMall.ALL_COMMODITY_CATEGORY);

    }

    @Override
    protected boolean enableRefresh() {
        return true;
    }

    @Override
    protected boolean enableLoadMore() {
        return false;
    }

    @Override
    protected BaseQuickAdapter getBaseQuickAdapter() {
        return new ShopCommodityCategoryAdapter(R.layout.shop_commodity_category_item);
    }

    @Override
    protected ShopPresent createPresenter() {
        return new ShopPresent();
    }

    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);

        switch (tag) {
            case AppHttpPathMall.ALL_COMMODITY_CATEGORY:
                ShopCommodityCategoryListBean categoryListBean = (ShopCommodityCategoryListBean) o;
                if (categoryListBean != null) {
                    List<ShopCommodityCategoryListBean.DataBean> arrays = categoryListBean.getData();
                    baseQuickAdapter.setNewData(arrays);
                }

                break;
            case AppHttpPathMall.ADD_COMMODITY_CATEGORY:
                mCategoryNameEt.setText("");
                getRvAdapterData();
                break;
            default:
                break;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.add_category_tv:
                // : 2022/6/12 添加类目
                if (TextUtils.isEmpty(getTextViewValue(mCategoryNameEt))) {
                    ToastUtils.toast(mContext, "请输入类目名称");
                    return;
                }

                mPresenter.addCommodityCategorys(getBaseBuilder()
                        .add("classifyName", getTextViewValue(mCategoryNameEt))
                        .build(), AppHttpPathMall.ADD_COMMODITY_CATEGORY);
                break;
        }
    }
}
