package com.juntai.project.sell.mall.home.commodityManager.commodityCategory;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
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
        baseQuickAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ShopCommodityCategoryListBean.DataBean dataBean = (ShopCommodityCategoryListBean.DataBean) adapter.getItem(position);
                switch (view.getId()) {
                    case R.id.modify_tv:
                        AlertDialog alertDialog = new AlertDialog.Builder(mContext)
                                .setCancelable(false)
                                .create();
                        alertDialog.setView(getEditCategoryLayout(alertDialog, dataBean));
                        setAlertDialogHeightWidth(alertDialog, 0, 0);
                        alertDialog.show();
                        break;
                    case R.id.delete_tv:
                        // : 2022/6/13 删除类目
                        showAlertDialogOfOneBt("删除商品类目", "若删除当前类目，此类目下所有商品会一 起删除。", "确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                mPresenter.deleteCommodityCategorys(getBaseBuilder().add("classifyId", String.valueOf(dataBean.getId()))
                                       .build(), AppHttpPathMall.DELETE_COMMODITY_CATEGORY);
                            }
                        });
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * 获取修改类目的弹窗
     *
     * @param alertDialog
     * @return
     */
    private View getEditCategoryLayout(AlertDialog alertDialog, ShopCommodityCategoryListBean.DataBean dataBean) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.edit_category, null);
        view.findViewById(R.id.close_dialog_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        EditText mCategoryEt = view.findViewById(R.id.category_name_et);
        mCategoryEt.setText(dataBean.getShopClassifyName());
        view.findViewById(R.id.confirm_edit_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(getTextViewValue(mCategoryEt))) {
                    ToastUtils.toast(mContext, "请输入新的类目名");
                    return;
                }
                alertDialog.dismiss();
                // : 2022/6/13 修改类目名称
                mPresenter.modifyCommodityCategorys(getBaseBuilder().add("classifyId", String.valueOf(dataBean.getId()))
                        .add("classifyName", getTextViewValue(mCategoryEt)).build(), AppHttpPathMall.MODIFY_COMMODITY_CATEGORY);

            }
        });
        return view;
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
            case AppHttpPathMall.MODIFY_COMMODITY_CATEGORY:
                ToastUtils.toast(mContext, "修改成功");
                getRvAdapterData();
                break;
            case AppHttpPathMall.DELETE_COMMODITY_CATEGORY:
                ToastUtils.toast(mContext, "删除成功");
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
