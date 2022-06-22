package com.juntai.project.sell.mall.order.send;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.basecomponent.utils.eventbus.EventBusObject;
import com.juntai.disabled.basecomponent.utils.eventbus.EventManager;
import com.juntai.project.sell.mall.AppHttpPathMall;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.beans.BaseAdapterDataBean;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.juntai.project.sell.mall.home.shop.BaseShopActivity;

import okhttp3.FormBody;

/**
 * @aouther tobato
 * @description 描述 发货
 * @date 2022/6/20 11:16
 */
public class SendActivity extends BaseShopActivity implements HomePageContract.IHomePageView {


    private int orderId;

    @Override
    public void initData() {
        super.initData();
        baseQuickAdapter.setNewData(mPresenter.sendGoods());
        orderId = getIntent().getIntExtra(BASE_ID,0);
    }

    @Override
    protected String getTitleName() {
        return "快递信息";
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
                if (baseAdapterDataBean == null) {
                    return;
                }
                FormBody.Builder builder = baseAdapterDataBean.getBuilder();
                builder.add("orderId",String.valueOf(orderId));
                mPresenter.sendGoods(builder.build(), AppHttpPathMall.SEND_GOODS);


            }
        });
        return view;
    }

    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);
        switch (tag) {
            case AppHttpPathMall.SEND_GOODS:
                ToastUtils.toast(mContext, "已提交");
                EventManager.getEventBus().post(new EventBusObject(EventBusObject.REFRESH_ORDER_LIST, ""));
                startToAllOrderActivity(1, 0);

                break;
            default:
                break;
        }
    }
}
