package com.juntai.project.sell.mall.home.commodityManager.allCommodity.editCommodity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.beans.sell.CommodityDetailBean;
import com.juntai.project.sell.mall.home.shop.BaseShopActivity;

/**
 * @aouther tobato
 * @description 描述 添加商品
 * @date 2022/6/13 16:20
 */
public class AddCommodityActivity extends BaseShopActivity {

    private CommodityDetailBean detailBean;

    @Override
    public void initData() {
        super.initData();
        detailBean = getIntent().getParcelableExtra(BASE_PARCELABLE);
        baseQuickAdapter.setNewData(mPresenter.getCommodityBaseInfoData(detailBean, false));


    }

    @Override
    protected View getFootView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.footview_commit, null);
        view.findViewById(R.id.shop_protocal_rb).setVisibility(View.GONE);
        TextView commitTv = view.findViewById(R.id.commit_business_form_tv);
        commitTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, AddCommodityDetailInfoActivity.class)
                        .putExtra(BASE_PARCELABLE,detailBean));
            }
        });
        commitTv.setText("下一步");
        return view;
    }

    @Override
    protected String getTitleName() {
        return "添加商品";
    }

    @Override
    protected boolean isDetail() {
        return false;
    }

    @Override
    protected View getAdapterHeadView() {
        return null;
    }

    @Override
    protected View getAdapterFootView() {
        return null;
    }


}
