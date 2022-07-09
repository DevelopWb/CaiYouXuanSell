package com.juntai.project.sell.mall.home.commodityManager.allCommodity.editCommodity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.juntai.project.sell.mall.AppHttpPathMall;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.beans.BaseAdapterDataBean;
import com.juntai.project.sell.mall.beans.sell.CommodityDetailBean;
import com.juntai.project.sell.mall.beans.sell.CommodityDetailDataBean;
import com.juntai.project.sell.mall.home.shop.BaseShopActivity;

/**
 * @aouther tobato
 * @description 描述 添加商品
 * @date 2022/6/13 16:20
 */
public class EditCommodityActivity extends BaseShopActivity {

    private CommodityDetailBean detailBean;

    @Override
    public void initData() {
        super.initData();
        baseQuickAdapter.setNewData(mPresenter.getCommodityBaseInfoData(null, false,false));
        int  commodityId = getIntent().getIntExtra(BASE_ID,0);

        // : 2022/6/15 获取商品详情
        mPresenter.getCommodityDetail(getBaseBuilder().add("commodityId",String.valueOf(commodityId)).build(), AppHttpPathMall.GET_COMMODITY_DETAIL);



    }

    @Override
    protected String getTitleName() {
        return "修改商品";
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
        View view = LayoutInflater.from(mContext).inflate(R.layout.footview_commit, null);
        TextView commitTv = view.findViewById(R.id.commit_tv);
        commitTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseAdapterDataBean baseAdapterDataBean = getBaseOfAdapterData();
                if (baseAdapterDataBean != null) {
                    CommodityDetailBean commodityDetailBean = baseAdapterDataBean.getCommodityDetailBean();
                    commodityDetailBean.setId(detailBean.getId());
                    commodityDetailBean.setDescription(detailBean.getDescription());
                    startActivity(new Intent(mContext, AddCommodityDetailInfoActivity.class)
                            .putExtra(BASE_PARCELABLE, commodityDetailBean));
                }


            }
        });
        commitTv.setText("下一步");
        return view;
    }
    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);
        switch (tag) {
            case AppHttpPathMall.GET_COMMODITY_DETAIL:
                CommodityDetailDataBean detailDataBean = (CommodityDetailDataBean) o;
                if (detailDataBean != null) {
                    detailBean = detailDataBean.getData();
                    baseQuickAdapter.setNewData(mPresenter.getCommodityBaseInfoData(detailBean, false,false));
                }
                break;
            default:
                break;
        }
    }

}
