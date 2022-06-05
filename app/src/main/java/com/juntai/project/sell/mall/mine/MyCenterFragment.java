package com.juntai.project.sell.mall.mine;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.utils.DialogUtil;
import com.juntai.disabled.basecomponent.utils.HawkProperty;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.project.sell.mall.AppHttpPathMall;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseAppFragment;
import com.juntai.project.sell.mall.beans.PicTextBean;
import com.juntai.project.sell.mall.beans.UserBeanMall;
import com.juntai.project.sell.mall.beans.order.OrderStatusAmountBean;
import com.juntai.project.sell.mall.home.commodityfragment.commodity_detail.PicTextAdapter;
import com.juntai.project.sell.mall.mine.collect.CollectCommoditiesActivity;
import com.juntai.project.sell.mall.mine.collect.CollectShopesActivity;
import com.juntai.project.sell.mall.mine.setting.MyInformationActivity;
import com.juntai.project.sell.mall.utils.UserInfoManagerMall;
import com.orhanobut.hawk.Hawk;

import java.util.List;

/**
 * @aouther tobato
 * @description 描述
 * @date 2021/4/17 16:12
 */
public class MyCenterFragment extends BaseAppFragment<MyCenterPresent> implements MyCenterContract.ICenterView, View.OnClickListener {

    MyMenuAdapter myMenuAdapter;

    private ImageView mHeadImage;
    private TextView mNickname;
    /**
     * 退出账号
     */
    private TextView mLoginOut;
    private View view;
    private ImageView mSetIv;
    /**
     * tobato
     */
    private TextView mDesTv;
    private RecyclerView mCollectRv;
    /**
     * 全部
     */
    private TextView mAllOrdersTv;
    private RecyclerView mOrderManagerRv;
    /**
     * 退出登录
     */
    private TextView mLoginOutTv;
    private PicTextAdapter orderMenuAdapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_my_center;
    }

    @Override
    protected void initView() {
        mHeadImage = getView(R.id.headImage_iv);
        mHeadImage.setOnClickListener(this);
        ImageLoadUtil.loadHeadCirclePic(mContext, UserInfoManagerMall.getHeadPic(), mHeadImage);
        mNickname = getView(R.id.nickname_tv);
        mNickname.setText(UserInfoManagerMall.getUserNickName());
        mLoginOut = getView(R.id.login_out_tv);
        mLoginOut.setOnClickListener(this);
        mSetIv = (ImageView) getView(R.id.set_iv);
        mSetIv.setOnClickListener(this);
        mDesTv = (TextView) getView(R.id.des_tv);
        mDesTv.setText(String.format("账号:%s", UserInfoManagerMall.getAccount()));
        mCollectRv = (RecyclerView) getView(R.id.collect_rv);
        mAllOrdersTv = (TextView) getView(R.id.all_orders_tv);
        mAllOrdersTv.setOnClickListener(this);
        mOrderManagerRv = (RecyclerView) getView(R.id.order_manager_rv);
        mLoginOutTv = (TextView) getView(R.id.login_out_tv);
        mLoginOutTv.setOnClickListener(this);
        PicTextAdapter topMenuAdapter = new PicTextAdapter(R.layout.custom_tabitem);
        orderMenuAdapter = new PicTextAdapter(R.layout.my_center_order_menu);
        GridLayoutManager topManager = new GridLayoutManager(mContext, 3);
        mCollectRv.setLayoutManager(topManager);
        mCollectRv.setAdapter(topMenuAdapter);
        GridLayoutManager orderManager = new GridLayoutManager(mContext, 4);
        mOrderManagerRv.setLayoutManager(orderManager);
        mOrderManagerRv.setAdapter(orderMenuAdapter);
        topMenuAdapter.setNewData(mPresenter.getMyCenterTopMenus());
        orderMenuAdapter.setNewData(mPresenter.getMyCenterOrderMenus());
        topMenuAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                PicTextBean picTextBean = (PicTextBean) adapter.getItem(position);
                switch (picTextBean.getTextName()) {
                    case MyCenterContract.TOP_MENU_COLLECT_COMMODITY:
                        // : 2022/5/6 商品收藏
                        startActivity(new Intent(mContext, CollectCommoditiesActivity.class));

                        break;
                    case MyCenterContract.TOP_MENU_COLLECT_SHOP:
                        // : 2022/5/6 店铺收藏
                        startActivity(new Intent(mContext, CollectShopesActivity.class));

                        break;
                    case MyCenterContract.TOP_MENU_ADDR_MANAGER:
                        // : 2022/5/6 收货地址
                        getBaseAppActivity().startToAddressListActivity(0);
                        break;
                    default:
                        break;
                }
            }
        });
        orderMenuAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                PicTextBean picTextBean = (PicTextBean) adapter.getItem(position);
                switch (picTextBean.getTextName()) {
                    case MyCenterContract.ORDER_TO_PAY:
                        // : 2022/5/6 待付款
                        getBaseAppActivity().startToAllOrderActivity(1, 1);

                        break;
                    case MyCenterContract.ORDER_TO_SEND:
                        // : 2022/5/6 待发货
                        getBaseAppActivity().startToAllOrderActivity(1, 2);

                        break;
                    case MyCenterContract.ORDER_TO_RECEIVE:
                        // : 2022/5/6 待收货
                        getBaseAppActivity().startToAllOrderActivity(1, 3);

                        break;
                    case MyCenterContract.ORDER_TO_EVALUATE:
                        // : 2022/5/6 待评价
                        getBaseAppActivity().startToAllOrderActivity(1, 4);

                        break;
                    case MyCenterContract.ORDER_TO_BACK:
                        // : 2022/5/6 退货售后
                        getBaseAppActivity().startToAllOrderActivity(1, 5);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    protected void initData() {
        mHeadImage.setImageResource(R.mipmap.default_user_head_icon);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (UserInfoManagerMall.isLogin()) {
            mLoginOut.setVisibility(View.VISIBLE);
            mPresenter.getOrderStatusAmount(getBaseAppActivity().getBaseBuilder().build(), AppHttpPathMall.ORDER_STATUS_AMOUNT);
            mPresenter.getUserInfo(getBaseAppActivity().getBaseBuilder().build(),AppHttpPathMall.GET_USER_INFO);
        } else {
            mLoginOut.setVisibility(View.GONE);
        }
    }


    @Override
    protected void lazyloadGone() {

    }

    @Override
    protected MyCenterPresent createPresenter() {
        return new MyCenterPresent();
    }


    @Override
    public void onClick(View v) {
        if (!UserInfoManagerMall.isLogin()) {
//            MyChatApp.goLogin();
            return;
        }
        switch (v.getId()) {
            case R.id.login_out_tv:
                //退出登录
                DialogUtil.getMessageDialog(mContext, "是否退出登录", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        // : 2022/5/16 调用退出登录的接口
                        mPresenter.logout(getBaseAppActivity().getBaseBuilder().build(), AppHttpPathMall.LOGOUT);

                    }
                }).show();
                break;
            case R.id.set_iv:
                startActivity(new Intent(mContext, MyInformationActivity.class));

                break;
            case R.id.all_orders_tv:
                // : 2022/5/12 所有订单
                getBaseAppActivity().startToAllOrderActivity(1, 0);
                break;
        }
    }

    @Override
    public void onSuccess(String tag, Object o) {
        switch (tag) {
            case AppHttpPathMall.ORDER_STATUS_AMOUNT:
                OrderStatusAmountBean statusAmountBean = (OrderStatusAmountBean) o;
                if (statusAmountBean != null) {
                    OrderStatusAmountBean.DataBean dataBean = statusAmountBean.getData();
                    if (dataBean != null) {
                        List<PicTextBean> picTextBeans = orderMenuAdapter.getData();
                        for (PicTextBean picTextBean : picTextBeans) {
                            switch (picTextBean.getTextName()) {
                                case MyCenterContract.ORDER_TO_PAY:
                                    picTextBean.setUnReadAmount(dataBean.getWaitOrder());
                                    break;
                                case MyCenterContract.ORDER_TO_SEND:
                                    picTextBean.setUnReadAmount(dataBean.getShipmentsOrder());
                                    break;
                                case MyCenterContract.ORDER_TO_RECEIVE:
                                    picTextBean.setUnReadAmount(dataBean.getReceivingOrder());
                                    break;
                                case MyCenterContract.ORDER_TO_EVALUATE:
                                    picTextBean.setUnReadAmount(dataBean.getEvaluateOrder());
                                    break;
                                case MyCenterContract.ORDER_TO_BACK:
                                    picTextBean.setUnReadAmount(dataBean.getAfterOrder());
                                    break;
                                default:
                                    break;
                            }
                        }
                        orderMenuAdapter.notifyDataSetChanged();
                    }
                }
                break;
            case AppHttpPathMall.LOGOUT:
                getBaseAppActivity().reLogin(UserInfoManagerMall.getPhoneNumber());

                break;
            case AppHttpPathMall.GET_USER_INFO:
                UserBeanMall loginBean = (UserBeanMall) o;
                if (loginBean != null) {
                    Hawk.put(HawkProperty.SP_KEY_USER, loginBean.getData());
                    ImageLoadUtil.loadHeadCirclePic(mContext, UserInfoManagerMall.getHeadPic(), mHeadImage);
                    mNickname.setText(UserInfoManagerMall.getUserNickName());
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onError(String tag, Object o) {
        ToastUtils.toast(mContext, String.valueOf(o));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    protected boolean canCancelLoadingDialog() {
        return false;
    }

}
