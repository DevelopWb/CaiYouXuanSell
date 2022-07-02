package com.juntai.project.sell.mall.home.assets;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.utils.DisplayUtil;
import com.juntai.project.sell.mall.AppHttpPathMall;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseAppActivity;
import com.juntai.project.sell.mall.base.SingleTextAdapter;
import com.juntai.project.sell.mall.beans.AssetsMenuBean;
import com.juntai.project.sell.mall.beans.BillBaseInfoBean;
import com.juntai.project.sell.mall.beans.MonthStatisticsBean;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.juntai.project.sell.mall.home.HomePagePresent;
import com.juntai.project.sell.mall.home.assets.assetsDetail.AssetsDetailActivity;
import com.juntai.project.sell.mall.home.assets.withdraw.AssetsWithDrawActivity;
import com.juntai.project.sell.mall.home.assets.withdraw.AssetsWithDrawRecordActivity;
import com.juntai.project.sell.mall.home.assets.withdraw.BindBankCardActivity;
import com.lixs.charts.BarChart.LBarChartView;

import java.util.ArrayList;
import java.util.List;

/**
 * @aouther tobato
 * @description 描述  财务管理
 * @date 2022/6/23 16:38
 */
public class AssetsActivity extends BaseAppActivity<HomePagePresent> implements HomePageContract.IHomePageView, View.OnClickListener {

    /**
     * 全部
     */
    private TextView mAssetsTypeTv;
    private RecyclerView mAssetsMenuRv;
    private TextView mItemBigTitleTv;
    private PopupWindow popupWindow;
    private AssetsAdapter assetsAdapter;
    private LBarChartView mLbarChartView;
    private BillBaseInfoBean.DataBean dataBean;

    @Override
    protected HomePagePresent createPresenter() {
        return new HomePagePresent();
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_assets;
    }

    @Override
    public void initView() {

        mAssetsTypeTv = (TextView) findViewById(R.id.assets_type_tv);
        mAssetsTypeTv.setOnClickListener(this);
        mAssetsMenuRv = (RecyclerView) findViewById(R.id.assets_menu_rv);
        mItemBigTitleTv = (TextView) findViewById(R.id.item_big_title_tv);
        mLbarChartView = (LBarChartView) findViewById(R.id.frameNewBase);
        initChartData(null);
        mItemBigTitleTv.setText("月收入金额统计");
        assetsAdapter = new AssetsAdapter(R.layout.assets_item);
        mAssetsMenuRv.setAdapter(assetsAdapter);
        GridLayoutManager manager = new GridLayoutManager(mContext, 3);
        mAssetsMenuRv.setLayoutManager(manager);
        assetsAdapter.setNewData(getAdapterData(null));

        assetsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 2:
                        startActivity(new Intent(mContext, AssetsWithDrawRecordActivity.class));
                        break;
                    default:
                        break;
                }
            }
        });

        assetsAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(mContext, AssetsDetailActivity.class));
                        break;
                    case 2:

                        if (dataBean != null&& !TextUtils.isEmpty(dataBean.getBankCode())) {
                            // : 2022/6/29 提现
                            startActivity(new Intent(mContext, AssetsWithDrawActivity.class)
                                    .putExtra(BASE_ID,getAssetsType())
                                    .putExtra(BASE_PARCELABLE,dataBean));

                        }else {
                            // : 2022/7/1 绑定银行卡
                            startActivity(new Intent(mContext, BindBankCardActivity.class)
                                    .putExtra(BASE_ID,getAssetsType())
                                    .putExtra(BASE_PARCELABLE,dataBean));

                        }

                        break;
                    default:
                        break;
                }
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        getData(String.valueOf(getAssetsType()));
    }

    private int getAssetsType() {
        switch (getTextViewValue(mAssetsTypeTv)) {
            case "普通商城":
              return 1;
            case "对公财务管理":
              return 2;
            default:
                break;
        }
        return 1;
    }

    private void initChartData(MonthStatisticsBean.DataBean dataBean) {
        final List<Double> datas = new ArrayList<>();
        final List<String> description = new ArrayList<>();
        if (dataBean == null) {
            datas.add(0d);
            datas.add(0d);
            datas.add(0d);
            datas.add(0d);
            datas.add(0d);
            datas.add(0d);
            datas.add(0d);
            datas.add(0d);
            datas.add(0d);
            datas.add(0d);
            datas.add(0d);
            datas.add(0d);
        } else {
            datas.add(Double.parseDouble(dataBean.getJan()));
            datas.add(Double.parseDouble(dataBean.getFeb()));
            datas.add(Double.parseDouble(dataBean.getMar()));
            datas.add(Double.parseDouble(dataBean.getApr()));
            datas.add(Double.parseDouble(dataBean.getMay()));
            datas.add(Double.parseDouble(dataBean.getJune()));
            datas.add(Double.parseDouble(dataBean.getJuly()));
            datas.add(Double.parseDouble(dataBean.getAug()));
            datas.add(Double.parseDouble(dataBean.getSept()));
            datas.add(Double.parseDouble(dataBean.getOct()));
            datas.add(Double.parseDouble(dataBean.getNov()));
            datas.add(Double.parseDouble(dataBean.getDec()));
        }


        description.add("1月");
        description.add("2月");
        description.add("3月");
        description.add("4月");
        description.add("5月");
        description.add("6月");
        description.add("7月");
        description.add("8月");
        description.add("9月");
        description.add("10月");
        description.add("11月");
        description.add("12月");

        mLbarChartView.setDatas(datas, description, true);
    }

    private List<AssetsMenuBean> getAdapterData(BillBaseInfoBean.DataBean dataBean) {
        List<AssetsMenuBean> arrays = new ArrayList<>();
        arrays.add(new AssetsMenuBean("总营业额(元)", dataBean == null ? "" : String.valueOf(dataBean.getTurnover()), "明细", true));
        arrays.add(new AssetsMenuBean("待结算金额(元)", dataBean == null ? "" : String.valueOf(dataBean.getSettled()), null, false));
        arrays.add(new AssetsMenuBean("可提现金额(元)", dataBean == null ? "" : String.valueOf(dataBean.getWithdrawalCash()), "提现", false));
        return arrays;
    }

    @Override
    public void initData() {
        setTitleName("财务管理");
    }


    @Override
    public void onSuccess(String tag, Object o) {
        switch (tag) {
            case AppHttpPathMall.BILL_BASE_INFO:
                BillBaseInfoBean baseInfoBean = (BillBaseInfoBean) o;
                if (baseInfoBean != null) {
                    dataBean = baseInfoBean.getData();
                    if (dataBean != null) {
                        assetsAdapter.setNewData(getAdapterData(dataBean));

                    }
                }
                break;
            case AppHttpPathMall.MONTH_STATISTICS:
                MonthStatisticsBean statisticsBean = (MonthStatisticsBean) o;
                if (statisticsBean != null) {
                    List<MonthStatisticsBean.DataBean> dataBeans = statisticsBean.getData();
                    if (dataBeans != null && dataBeans.size() > 0) {
                        MonthStatisticsBean.DataBean dataBean = dataBeans.get(0);
                        initChartData(dataBean);

                    }
                }
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
            case R.id.assets_type_tv:
                List<String> arrays = new ArrayList<>();
                arrays.add("普通商城");
                arrays.add("对公财务管理");
                View popView = LayoutInflater.from(mContext).inflate(R.layout.pop_recycler, null);
                if (popupWindow == null) {
                    popupWindow = new PopupWindow(popView, DisplayUtil.dp2px(mContext, 120), WindowManager.LayoutParams.WRAP_CONTENT,
                            false);
                    popupWindow.setOutsideTouchable(true);
                    SingleTextAdapter singleTextAdapter = new SingleTextAdapter(R.layout.pop_text_item);
                    RecyclerView mRecyclerview = (RecyclerView) popView.findViewById(R.id.pop_rv);
                    mRecyclerview.setAdapter(singleTextAdapter);
                    LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                    mRecyclerview.setLayoutManager(manager);
                    singleTextAdapter.setNewData(arrays);
                    singleTextAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            mAssetsTypeTv.setText((String) adapter.getItem(position));
                            popupWindow.dismiss();
                            switch (position) {
                                case 0:
                                    getData("1");
                                    break;
                                case 1:
                                    getData("2");
                                    break;
                                default:
                                    break;
                            }

                        }
                    });
                }


                popupWindow.showAsDropDown(v, DisplayUtil.dp2px(mContext, 15), 0);
                break;
        }
    }

    /**
     * 获取数据
     * @param s
     */
    private void getData(String s) {
        mPresenter.getBillBaseInfo(getBaseBuilder().add("type", s).build(), AppHttpPathMall.BILL_BASE_INFO);
        mPresenter.getMonthStatistics(getBaseBuilder().add("payType", s).build(), AppHttpPathMall.MONTH_STATISTICS);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (popupWindow != null) {
            popupWindow.dismiss();
            popupWindow = null;
        }
    }
}
