package com.juntai.project.sell.mall.home.commodityManager.allCommodity.commodityProperty;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.utils.GsonTools;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.basecomponent.utils.eventbus.EventBusObject;
import com.juntai.disabled.basecomponent.utils.eventbus.EventManager;
import com.juntai.project.sell.mall.AppHttpPathMall;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseRecyclerviewActivity;
import com.juntai.project.sell.mall.beans.CommodityFormatBean;
import com.juntai.project.sell.mall.beans.CommodityFormatDataBean;
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
    private CommodityFormatPropertyAdapter formatPropertyAdapter;
    private CommodityFormatBean commodityFormatBean;
    private int commodityId;
    private TextView mSetPriceStockTv;

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
        commodityId = getIntent().getIntExtra(BASE_ID, 0);
        // : 2022/6/16 获取商品规格属性
        mPresenter.getCommodityFormat(getBaseBuilder().add("id", String.valueOf(commodityId)).build(), AppHttpPathMall.GET_COMMODITY_FORMAT);
        setTitleName("商品规格属性");
        getTitleRightTv().setText("生成");
        getTitleRightTv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // : 2022/6/15 生成规格属性
                commodityFormatBean = null;
                if (formatPropertyAdapter != null) {
                    formatPropertyAdapter.setNewData(null);
                    mSetPriceStockTv.setVisibility(View.GONE);
                }
                List<CommodityFormatBean.ResultBean> data = baseQuickAdapter.getData();
                if (data.size() > 0) {
                    List<CommodityFormatBean.ResultBean> arrays = new ArrayList<>();
                    for (CommodityFormatBean.ResultBean datum : data) {
                        if (datum.getDetail() != null && datum.getDetail().size() > 0 && !TextUtils.isEmpty(datum.getValue())) {
                            arrays.add(datum);
                        }
                    }
                    if (arrays.size() > 0) {
                        commodityFormatBean = new CommodityFormatBean();
                        commodityFormatBean.setAttr(data);
                        mPresenter.createCommodityFormatList(getBaseBuilder().add("id", String.valueOf(commodityId)
                                ).add("json", GsonTools.createGsonString(commodityFormatBean)).build()
                                , AppHttpPathMall.CREATE_COMMODITY_FORMAT);

                    } else {
                        ToastUtils.toast(mContext, "请填写规格或对应的属性");
                    }

                } else {
                    ToastUtils.toast(mContext, "请先添加规格");
                }

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
                // : 2022/6/15 提交更改
                commit(AppHttpPathMall.EDIT_COMMODITY_FORMAT);


                break;
            default:
                break;
        }
    }

    private void commit(String tag) {
        if (commodityFormatBean == null || commodityFormatBean.getValue() == null) {
            ToastUtils.toast(mContext, "请先生成规则属性");
            return;
        }
        mPresenter.editCommodityProperty(getBaseBuilder()
                .add("id", String.valueOf(commodityId))
                .add("json", GsonTools.createGsonString(commodityFormatBean)).build(), tag
        );
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
                        // : 2022/6/16 添加属性
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
                    List<CommodityFormatListBean.DataBean> formatList = listBean.getData();
                    commodityFormatBean.setValue(formatList);
                    if (formatList != null) {
                        loadFootView(formatList);
                    }
                }

                break;
            case AppHttpPathMall.EDIT_COMMODITY_FORMAT:
                ToastUtils.toast(mContext, "提交成功");
                EventManager.getEventBus().post(new EventBusObject(EventBusObject.REFRESH_COMMODITY_LIST,""));
                finish();
                break;
            case AppHttpPathMall.MODIFY_COMMODITY_PRICE_STOCK:
                ToastUtils.toast(mContext, "修改成功");
                break;
            case AppHttpPathMall.GET_COMMODITY_FORMAT:
                CommodityFormatDataBean formatDataBean = (CommodityFormatDataBean) o;
                if (formatDataBean != null) {
                    CommodityFormatBean formatBean = formatDataBean.getData();
                    if (formatBean != null) {
                        commodityFormatBean = formatBean;
                        List<CommodityFormatListBean.DataBean> value = formatBean.getValue();
                        List<CommodityFormatBean.ResultBean> attr = formatBean.getAttr();
                        if (attr != null) {
                            baseQuickAdapter.setNewData(attr);
                        }
                        if (value != null) {
                            loadFootView(value);

                        }
                    }

                }

                break;
            default:
                break;
        }
    }

    private void loadFootView(List<CommodityFormatListBean.DataBean> formatList) {
        if (formatPropertyAdapter == null) {
            // : 2022/6/15 加载尾布局
            View view = LayoutInflater.from(mContext).inflate(R.layout.set_format_layout, null);
            mSetPriceStockTv = view.findViewById(R.id.item_small_title_tv);
            mSetPriceStockTv.setText("设置价格库存");
            RecyclerView mCommodityFormatRv = view.findViewById(R.id.commodity_format_rv);
            formatPropertyAdapter = new CommodityFormatPropertyAdapter(R.layout.format_property_item);
            initRecyclerview(mCommodityFormatRv, formatPropertyAdapter, LinearLayoutManager.VERTICAL);
            baseQuickAdapter.addFooterView(view);
        }
        mSetPriceStockTv.setVisibility(View.VISIBLE);
        formatPropertyAdapter.setNewData(formatList);
        formatPropertyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CommodityFormatListBean.DataBean dataBean = (CommodityFormatListBean.DataBean) adapter.getItem(position);

                // : 2022/6/16 修改库存和价格
                ModifyPriceStockFragment modifyPriceStockFragment = new ModifyPriceStockFragment();
                modifyPriceStockFragment.show(getSupportFragmentManager(), "modifyPriceStockFragment");
                modifyPriceStockFragment.setDataBean(dataBean, position);
                modifyPriceStockFragment.setOnConfirmCallBack(new ModifyPriceStockFragment.OnConfirmCallBack() {
                    @Override
                    public void confirm(double price, int stock, int positon) {
                        // : 2022/6/17 更改适配器数据  调用更改接口
                        CommodityFormatListBean.DataBean dataBean = (CommodityFormatListBean.DataBean) formatPropertyAdapter.getItem(position);
                        dataBean.setPrice(price);
                        dataBean.setStock(stock);
                        formatPropertyAdapter.notifyItemChanged(position);
                        commit(AppHttpPathMall.MODIFY_COMMODITY_PRICE_STOCK);
                    }
                });
            }
        });
    }
}
