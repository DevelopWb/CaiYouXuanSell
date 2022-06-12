package com.juntai.project.sell.mall.home.commodityfragment.commodity_detail.evaluation;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.project.sell.mall.AppHttpPathMall;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseRecyclerviewFragment;
import com.juntai.project.sell.mall.beans.CommodityEvaluationBean;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.juntai.project.sell.mall.home.commodityfragment.CommodityPresent;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/5/4 14:27
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/4 14:27
 */
public class EvaluateFragment extends BaseRecyclerviewFragment<CommodityPresent> implements HomePageContract.IHomePageView {


    public static EvaluateFragment  getInstance(int type,int commodityId){
        EvaluateFragment  evaluateFragment = new EvaluateFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        bundle.putInt("commodityId", commodityId);
        evaluateFragment.setArguments(bundle);
        return evaluateFragment;
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
    protected void lazyLoad() {
        super.lazyLoad();
        getRvAdapterData();
    }

    @Override
    protected void getRvAdapterData() {
        int commodityId = getArguments().getInt("commodityId");
        int type = getArguments().getInt("type");
        mPresenter.getCommodityEvaluation(getBaseAppActivity().getBaseBuilderWithoutParama()
                .add("type",String.valueOf(type))
                .add("commodityId", String.valueOf(commodityId)).build(), AppHttpPathMall.COMMODIFY_EVALUATION);

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
        return new EvaluationAdapter(R.layout.shop_commodity_evaluta_child_item);
    }

    @Override
    protected CommodityPresent createPresenter() {
        return new CommodityPresent();
    }

    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);

        switch (tag) {
            case AppHttpPathMall.COMMODIFY_EVALUATION:
                CommodityEvaluationBean commodityEvaluationBean = (CommodityEvaluationBean) o;
                if (commodityEvaluationBean != null) {
                    List<CommodityEvaluationBean.DataBean> arrays = commodityEvaluationBean.getData();
                    if (arrays!=null&&!arrays.isEmpty()) {
                        baseQuickAdapter.setNewData(arrays);
                    }
                }

                break;
            default:
                break;
        }
    }
}
