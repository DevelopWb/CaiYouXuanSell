package com.juntai.project.sell.mall.home.commodityfragment;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.utils.eventbus.EventBusObject;
import com.juntai.project.sell.mall.AppHttpPathMall;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseRecyclerviewFragment;
import com.juntai.project.sell.mall.beans.CommodityBean;
import com.juntai.project.sell.mall.beans.CommodityDesListBean;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.util.List;

import okhttp3.FormBody;

/**
 * @Author: tobato
 * @Description: 作用描述  商品列表
 * @CreateDate: 2022/4/29 17:37
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/4/29 17:37
 */
public abstract class BaseCommodityListFragment extends BaseRecyclerviewFragment<CommodityPresent> implements HomePageContract.IHomePageView {

    private int labelId;


    @Override
    protected void lazyLoad() {
        labelId = getArguments().getInt("label");
        super.lazyLoad();


    }


    @Override
    protected BaseQuickAdapter getBaseQuickAdapter() {
        return new CommodityListAdapter(R.layout.shop_commodity_list);
    }

    @Override

    protected boolean enableLoadMore() {
        return true;
    }

    @Override
    protected CommodityPresent createPresenter() {
        return new CommodityPresent();
    }

    @Override
    protected void initView() {
        super.initView();
        mRecyclerview.setBackgroundColor(ContextCompat.getColor(mContext, R.color.gray_lighter));
        mSmartrefreshlayout.setPrimaryColors(ContextCompat.getColor(mContext, R.color.gray_lighter));
        ClassicsHeader classicsHeader = (ClassicsHeader) mSmartrefreshlayout.getRefreshHeader();
        if (classicsHeader != null) {
            classicsHeader.setAccentColor(ContextCompat.getColor(mContext, R.color.black));

        }
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerview.setLayoutManager(manager);
        baseQuickAdapter.setEmptyView(getBaseAppActivity().getAdapterEmptyView("一个商品也没有-_-",-1));
        baseQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CommodityBean bean = (CommodityBean) adapter.getItem(position);
                getBaseAppActivity().startToCommodityDetail(bean.getId());


            }
        });

    }


    @Override
    protected LinearLayoutManager getBaseAdapterManager() {
        return null;
    }

    @Override
    public void onEvent(EventBusObject eventBusObject) {
        super.onEvent(eventBusObject);
        switch (eventBusObject.getEventKey()) {
            case EventBusObject.REFRESH_SEARCH_COMMODITY_LIST:
                if (1 == getType()) {
                    // : 2022/5/20 搜索中的商品列表
                    String key = (String) eventBusObject.getEventObj();
                    startSearch(key);
                }
                break;
            case EventBusObject.REFRESH_HOMEPAGE_COMMODITY_LIST:
                page=1;
                getRvAdapterData();
                break;
            default:
                break;
        }
    }

    @Override
    protected void getRvAdapterData() {
        if (1 == getType()) {
            // : 2022/5/20 搜索中的商品列表
            startSearch(null);
        } else {
            FormBody.Builder builder = new FormBody.Builder();
            builder.add("page", String.valueOf(page))
                    .add("limit", String.valueOf(limit));
            mPresenter.getCommodityRecommendList(0 == labelId ? builder.build() : builder
                    .add("categoryId", String.valueOf(labelId)).build(), AppHttpPathMall.COMMODIFY_RECOMMEND);
        }

    }

    private void startSearch(String key) {
        if (TextUtils.isEmpty(key)) {
            return;
        }
        mPresenter.startSearchCommodity(getBaseAppActivity().getBaseBuilderWithoutParama()
                .add("key", key)
                .add("type", "1").build(), AppHttpPathMall.SEARCH_COMMODITY
        );

    }

    /**
     * 0代表首页展示的商品列表  1代表搜索模块中展示的商品列表
     *
     * @return
     */
    protected abstract int getType();

    @Override
    protected boolean enableRefresh() {
        return true;
    }


    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);

        switch (tag) {
            case AppHttpPathMall.COMMODIFY_RECOMMEND:
            case AppHttpPathMall.SEARCH_COMMODITY:
                CommodityDesListBean desListBean = (CommodityDesListBean) o;
                if (desListBean != null) {
                    CommodityDesListBean.DataBean dataBean = desListBean.getData();
                    if (dataBean != null) {
                        List<CommodityBean> data = dataBean.getList();
                        setData(data, dataBean.getTotalCount());
                    }
                }
                break;
            default:
                break;
        }
    }
}
