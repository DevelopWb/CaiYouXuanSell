package com.juntai.project.sell.mall.home.commodityManager.allCommodity.commodityProperty;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.utils.GsonTools;
import com.juntai.disabled.basecomponent.utils.eventbus.EventBusObject;
import com.juntai.project.sell.mall.AppHttpPathMall;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseRecyclerviewActivity;
import com.juntai.project.sell.mall.beans.CommodityFormatBean;
import com.juntai.project.sell.mall.beans.CommodityFormatListBean;
import com.juntai.project.sell.mall.beans.StringBean;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.juntai.project.sell.mall.home.shop.ShopPresent;

import java.util.ArrayList;
import java.util.List;

/**
 * @aouther tobato
 * @description 描述 商品属性
 * @date 2022/6/15 11:11
 */
public class CommodityFormatPropertyActivity extends BaseRecyclerviewActivity<ShopPresent> implements HomePageContract.IHomePageView, View.OnClickListener {

    /**
     * 添加规格
     */
    private TextView mAddFormatTv;
    /**
     * 提交
     */
    private TextView mCommitTv;

    @Override
    protected ShopPresent createPresenter() {
        return new ShopPresent();
    }

    @Override
    public int getLayoutView() {
        return R.layout.commodity_property_activity;
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
        baseQuickAdapter.setHeaderFooterEmpty(true, false);
        int commodityId = getIntent().getIntExtra(BASE_ID, 0);
        setTitleName("商品规格属性");
        getTitleRightTv().setText("生成");
        getTitleRightTv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2022/6/15 生成规格属性
                List<CommodityFormatBean.ResultBean> data = baseQuickAdapter.getData();
                CommodityFormatBean commodityFormatBean = new CommodityFormatBean();
                commodityFormatBean.setItems(data);
                mPresenter.createCommodityFormatList(getBaseBuilder().add("id", String.valueOf(commodityId)
                        ).add("json", GsonTools.createGsonString(commodityFormatBean)).build()
                        , AppHttpPathMall.CREATE_COMMODITY_FORMAT);
            }
        });
    }


    @Override
    public void onEvent(EventBusObject eventBusObject) {
        super.onEvent(eventBusObject);
        switch (eventBusObject.getEventKey()) {
            case EventBusObject.REFRESH_COMMODITY_FORMAT_DATA:
                int positon = (int) eventBusObject.getEventObj();
                CommodityFormatBean.ResultBean resultBean = (CommodityFormatBean.ResultBean) baseQuickAdapter.getItem(positon);
                TextView addPropertyTv = (TextView) baseQuickAdapter.getViewByPosition(mRecyclerview, positon, R.id.add_property_tv);
                CommodityPropertyAdapter propertyAdapter = (CommodityPropertyAdapter) addPropertyTv.getTag();
                List<StringBean> data = propertyAdapter.getData();
                List<String> detail = new ArrayList<>();
                for (StringBean datum : data) {
                    detail.add(datum.getContent());
                }
                resultBean.setDetail(detail);
                break;
            default:
                break;
        }
    }

    @Override
    protected LinearLayoutManager getBaseAdapterManager() {
        return null;
    }

    @Override
    protected void getRvAdapterData() {

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
        return new CommodityFormatAdapter(R.layout.commodity_format_item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_format_tv:
                // : 2022/6/15 添加规格
                baseQuickAdapter.addData(new CommodityFormatBean.ResultBean());
                break;
            case R.id.commit_tv:
                // TODO: 2022/6/15 提交更改
                break;
            default:
                break;
        }
    }


    public void initView() {
        super.initView();
        mAddFormatTv = (TextView) findViewById(R.id.add_format_tv);
        mAddFormatTv.setOnClickListener(this);
        mCommitTv = (TextView) findViewById(R.id.commit_tv);
        mCommitTv.setOnClickListener(this);
        baseQuickAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.delete_iv:
                        adapter.remove(position);
                        break;
                    case R.id.add_property_tv:
                        TextView addPropertyTv = (TextView) adapter.getViewByPosition(mRecyclerview, position, R.id.add_property_tv);
                        CommodityPropertyAdapter propertyAdapter = (CommodityPropertyAdapter) addPropertyTv.getTag();
                        propertyAdapter.addData(new StringBean("", position));
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);

        switch (tag) {
            case AppHttpPathMall.CREATE_COMMODITY_FORMAT:
                CommodityFormatListBean listBean = (CommodityFormatListBean) o;
                if (listBean != null) {
                    List<CommodityFormatListBean.DataBean> dataBeans = listBean.getData();
                    if (dataBeans != null) {
                        // TODO: 2022/6/15 加载尾布局

                    }
                }
                break;
            default:
                break;
        }
    }
}
