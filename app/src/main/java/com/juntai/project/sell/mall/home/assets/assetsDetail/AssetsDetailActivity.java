package com.juntai.project.sell.mall.home.assets.assetsDetail;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.utils.PickerManager;
import com.juntai.project.sell.mall.AppHttpPathMall;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseRecyclerviewActivity;
import com.juntai.project.sell.mall.beans.BillListBean;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.juntai.project.sell.mall.home.HomePagePresent;
import com.juntai.project.sell.mall.utils.CalendarUtil;

import java.util.Date;
import java.util.List;

/**
 * @aouther tobato
 * @description 描述 财务明细
 * @date 2022/6/29 14:18
 */
public class AssetsDetailActivity extends BaseRecyclerviewActivity<HomePagePresent> implements HomePageContract.IHomePageView, View.OnClickListener {

    /**
     * 全部
     */
    private TextView mBillDateTv;
    /**
     * 本月总收入:
     */
    private TextView mMonthRevenueTv;

    @Override
    protected HomePagePresent createPresenter() {
        return new HomePagePresent();
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_assets_detail;
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
    protected LinearLayoutManager getBaseAdapterManager() {
        return null;
    }

    @Override
    protected void getRvAdapterData() {
        mPresenter.getBillList(getBaseBuilder().add("date", CalendarUtil.getCurrentTime("yyyy-MM")).build(), AppHttpPathMall.BILL_LIST);
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
        return new AssetsDetailAdapter(R.layout.assets_detail_item);
    }


    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);
        switch (tag) {
            case AppHttpPathMall.BILL_LIST:
                BillListBean billListBean = (BillListBean) o;
                if (billListBean != null) {
                    BillListBean.DataBean dataBean = billListBean.getData();
                    if (dataBean != null) {
                        mMonthRevenueTv.setText(String.format("本月总收入:%s",dataBean.getRevenue()));
                        List<BillListBean.DataBean.ListBean> arrays = dataBean.getList();
                        baseQuickAdapter.setNewData(arrays);
                    }
                }
                break;
            default:
                break;
        }
    }


    public void initView() {
        super.initView();
        setTitleName("明细");
        mBillDateTv = (TextView) findViewById(R.id.bill_date_tv);
        mBillDateTv.setText(CalendarUtil.getCurrentTime("yyyy-MM"));
        mBillDateTv.setOnClickListener(this);
        mMonthRevenueTv = (TextView) findViewById(R.id.month_revenue_tv);
        baseQuickAdapter.setEmptyView(getAdapterEmptyView("一条账单也没有",-1));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bill_date_tv:
                PickerManager.getInstance().showTimePickerView(mContext, new boolean[]{true, true, false, false, false, false}, "选择年月", new PickerManager.OnTimePickerTimeSelectedListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        String time = CalendarUtil.getCurrentTime("yyyy-MM",date);
                        mBillDateTv.setText(time);
                        mPresenter.getBillList(getBaseBuilder().add("date", time).build(), AppHttpPathMall.BILL_LIST);


                    }
                });

                break;
        }
    }
}
