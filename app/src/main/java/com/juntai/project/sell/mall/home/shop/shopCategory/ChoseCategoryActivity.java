package com.juntai.project.sell.mall.home.shop.shopCategory;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.project.sell.mall.AppHttpPathMall;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseRecyclerviewActivity;
import com.juntai.project.sell.mall.beans.IdNameBean;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.juntai.project.sell.mall.home.shop.ShopPresent;

import java.util.List;

/**
 * @aouther tobato
 * @description 描述 选择主营类目
 * @date 2022/6/10 14:35
 */
public class ChoseCategoryActivity extends BaseRecyclerviewActivity<ShopPresent> implements HomePageContract.IHomePageView {

    public static int ACTIVITY_RESULT = 10900;
    /**
     * 0是单选 1是多选
     */
    private int selectType;

    @Override
    protected ShopPresent createPresenter() {
        return new ShopPresent();
    }


    @Override
    protected LinearLayoutManager getBaseAdapterManager() {
        return null;
    }

    @Override
    protected View getAdapterHeadView() {
        return null;
    }

    @Override
    protected View getAdapterFootView() {
        return null;
    }

    @Override
    public void initData() {
        super.initData();
        selectType = getIntent().getIntExtra(BASE_ID, 0);
        setTitleName("主营类目");
        getTitleRightTv().setText("确定");
        getTitleRightTv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // : 2022/6/10 返回选择的类型
                String categorys = getSelectedCategoryIds();
                String categoryNames = getSelectedCategoryNames();
                if (TextUtils.isEmpty(categorys)) {
                    ToastUtils.toast(mContext, "请选择经营类目");
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra(BASE_STRING, categorys);
                intent.putExtra(BASE_STRING2, categoryNames);
                setResult(ACTIVITY_RESULT, intent);
                finish();
            }
        });
        baseQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                IdNameBean.DataBean dataBean = (IdNameBean.DataBean) adapter.getItem(position);
                if (0 == selectType) {
                    List<IdNameBean.DataBean> dataBeans = adapter.getData();
                    for (int i = 0; i < dataBeans.size(); i++) {
                        IdNameBean.DataBean bean = dataBeans.get(i);
                        if (i == position) {
                            bean.setSelected(true);
                        } else {
                            bean.setSelected(false);
                        }
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    if (dataBean.isSelected()) {
                        dataBean.setSelected(false);
                    } else {
                        dataBean.setSelected(true);
                    }
                    adapter.notifyItemChanged(position);

                }


            }
        });
    }

    @Override
    protected void getRvAdapterData() {
        mPresenter.getAllCategory(getBaseBuilderWithoutParama().build(), AppHttpPathMall.ALL_SHOP_CATEGORY);

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
        return new ShopCategoryAdapter(R.layout.shop_category_item);
    }

    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);
        switch (tag) {
            case AppHttpPathMall.ALL_SHOP_CATEGORY:
                IdNameBean idNameBean = (IdNameBean) o;
                if (idNameBean != null) {
                    List<IdNameBean.DataBean> dataBeans = idNameBean.getData();
                    baseQuickAdapter.setNewData(dataBeans);
                }
                break;
            default:
                break;
        }
    }

    private String getSelectedCategoryIds() {
        StringBuffer sb = new StringBuffer();
        List<IdNameBean.DataBean> arrays = baseQuickAdapter.getData();
        for (IdNameBean.DataBean array : arrays) {
            if (array.isSelected()) {
                sb.append(array.getId() + ",");
            }
        }
        String str = sb.toString().trim();
        if (str.length() == 0) {
            return null;
        }
        if (str.endsWith(",")) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    private String getSelectedCategoryNames() {
        StringBuffer sb = new StringBuffer();
        List<IdNameBean.DataBean> arrays = baseQuickAdapter.getData();
        for (IdNameBean.DataBean array : arrays) {
            if (array.isSelected()) {
                sb.append(array.getName() + ",");
            }
        }
        String str = sb.toString().trim();
        if (str.length() == 0) {
            return null;
        }
        if (str.endsWith(",")) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }
}
