package com.juntai.project.sell.mall.home.commodityManager.allCommodity.editCommodity;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.juntai.disabled.basecomponent.utils.HawkProperty;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.beans.BaseAdapterDataBean;
import com.juntai.project.sell.mall.beans.sell.CommodityDetailBean;
import com.juntai.project.sell.mall.home.shop.BaseShopActivity;
import com.orhanobut.hawk.Hawk;

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
        baseQuickAdapter.setNewData(mPresenter.getCommodityBaseInfoData(null, false,false));
        if (Hawk.contains(HawkProperty.COMMODITY_DETAIL)) {
            detailBean = Hawk.get(HawkProperty.COMMODITY_DETAIL);
            showAlertDialog("您上次还有未提交的草稿,是否进入草稿？", "进入", "取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    baseQuickAdapter.setNewData(mPresenter.getCommodityBaseInfoData(detailBean, false,true));
                }
            });
        }else {
            detailBean = getIntent().getParcelableExtra(BASE_PARCELABLE);
            baseQuickAdapter.setNewData(mPresenter.getCommodityBaseInfoData(detailBean, false,false));
        }


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
        View view = LayoutInflater.from(mContext).inflate(R.layout.footview_commit, null);
        TextView commitTv = view.findViewById(R.id.commit_tv);
        commitTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseAdapterDataBean baseAdapterDataBean = getBaseOfAdapterData();
                if (baseAdapterDataBean != null) {
                    CommodityDetailBean commodityDetailBean = baseAdapterDataBean.getCommodityDetailBean();
                    Hawk.put(HawkProperty.COMMODITY_DETAIL,commodityDetailBean);
                    startActivity(new Intent(mContext, AddCommodityDetailInfoActivity.class)
                            .putExtra(BASE_PARCELABLE, commodityDetailBean));
                }


            }
        });
        commitTv.setText("下一步");
        return view;
    }


}
