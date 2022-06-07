package com.juntai.project.sell.mall.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.bean.TextKeyValueBean;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseRecyclerviewFragment;
import com.juntai.project.sell.mall.beans.PicTextBean;
import com.juntai.project.sell.mall.home.commodityManager.CommodityManagerActivity;
import com.juntai.project.sell.mall.home.commodityfragment.commodity_detail.PicTextAdapter;
import com.juntai.project.sell.mall.home.live.LivePrepareActivity;
import com.juntai.project.sell.mall.home.shop.ShopFlowAdapter;
import com.juntai.project.sell.mall.mine.verified.VerifiedActivity;
import com.juntai.project.sell.mall.utils.UserInfoManagerMall;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述  店铺 首页
 * @CreateDate: 2022/5/9 17:26
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/9 17:26
 */
public class HomeShopFragment extends BaseRecyclerviewFragment<HomePagePresent> implements HomePageContract.IHomePageView, View.OnClickListener {
    private LinearLayout mSearchLl;
    private ImageView mSwitchModeIv;
    private View view;
    private ImageView mShopOwnerHeadIv;
    /**
     * 店铺名称
     */
    private TextView mShopNameTv;
    /**
     * 开店时间
     */
    private TextView mShopCreatTimeTv;
    /**
     * 店铺得分
     */
    private TextView mShopScoreTv;
    /**
     * 描述
     */
    private TextView mShopDesTv;
    private RecyclerView mShopFlowRv;
    private ShopFlowAdapter shopFlowAdapter;

    @Override
    protected HomePagePresent createPresenter() {
        return null;
    }


    private List<TextKeyValueBean> getFlowAdapterData() {
        List<TextKeyValueBean> arrays = new ArrayList<>();
        arrays.add(new TextKeyValueBean(HomePageContract.SHOP_FLOW_ORDER, "0"));
        arrays.add(new TextKeyValueBean(HomePageContract.SHOP_FLOW_BUSINESS, "0"));
        arrays.add(new TextKeyValueBean(HomePageContract.SHOP_FLOW_VISIT, "0"));
        return arrays;
    }

    @Override
    protected void initData() {
        super.initData();
        baseQuickAdapter.addHeaderView(getAdapterHeadView());
        baseQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                PicTextBean picTextBean = (PicTextBean) adapter.getItem(position);
                switch (picTextBean.getTextName()) {
                    case HomePageContract.SHOP_MANAGER_COMMODITY:
                        // : 2022/6/7 商品管理
                        startActivity(new Intent(mContext, CommodityManagerActivity.class));
                        break;
                    case HomePageContract.SHOP_MANAGER_ORDER:
                        // TODO: 2022/6/7 订单管理
                        break;
                    case HomePageContract.SHOP_MANAGER_LIVE:
                        // : 2022/6/7 直播
                        if (UserInfoManagerMall.getRealNameStatus() == 2) {
                            //初始化直播
                            startActivity(new Intent(mContext, LivePrepareActivity.class));
                        } else if (UserInfoManagerMall.getRealNameStatus() == 1) {
                            ToastUtils.warning(mContext, "实名认证审核中！");
                        } else {

                            getBaseAppActivity().showAlertDialog("需要完成身份认证，才可以发布直播内容", "去认证"
                                    , "取消", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            startActivity(new Intent(mContext, VerifiedActivity.class));

                                        }
                                    }, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    });
                        }
                        break;
                    case HomePageContract.SHOP_MANAGER_ASSENT:
                        // TODO: 2022/6/7 收入资产
                        break;
                    case HomePageContract.SHOP_MANAGER_FURNISH:
                        // TODO: 2022/6/7 店铺装修
                        break;
                    case HomePageContract.SHOP_MANAGER_SHOP:
                        // TODO: 2022/6/7 店铺管理
                        break;
                    case HomePageContract.SHOP_MANAGER_GUIDE:
                        // TODO: 2022/6/7 新手教程
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private View getAdapterHeadView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.shop_head_view,null);
        mSearchLl = (LinearLayout) view.findViewById(R.id.search_ll);
        mSwitchModeIv = (ImageView) view.findViewById(R.id.share_shop_iv);
        mSwitchModeIv.setOnClickListener(this);
        mSearchLl.setOnClickListener(this);
        view.findViewById(R.id.scan_iv).setOnClickListener(this);
        mShopOwnerHeadIv = (ImageView) view.findViewById(R.id.shop_owner_head_iv);
        mShopNameTv = (TextView) view.findViewById(R.id.shop_name_tv);
        mShopCreatTimeTv = (TextView) view.findViewById(R.id.shop_creat_time_tv);
        mShopScoreTv = (TextView) view.findViewById(R.id.shop_score_tv);
        mShopDesTv = (TextView) view.findViewById(R.id.shop_des_tv);
        mShopFlowRv = (RecyclerView) view.findViewById(R.id.shop_flow_rv);
        shopFlowAdapter = new ShopFlowAdapter(R.layout.text_key_value_item_vertical);
        GridLayoutManager manager = new GridLayoutManager(mContext, 3);
        mShopFlowRv.setLayoutManager(manager);
        mShopFlowRv.setAdapter(shopFlowAdapter);
        shopFlowAdapter.setNewData(getFlowAdapterData());
        return view;
    }

    @Override
    protected LinearLayoutManager getBaseAdapterManager() {
        return new GridLayoutManager(mContext, 3);
    }

    @Override
    protected void getRvAdapterData() {
        baseQuickAdapter.setNewData(getManagerMenus());
    }

    private List<PicTextBean> getManagerMenus() {
        // TODO: 2022/6/7 资源文件需要替换 
        List<PicTextBean> arrays = new ArrayList<>();
        arrays.add(new PicTextBean(R.mipmap.ic_launcher, HomePageContract.SHOP_MANAGER_COMMODITY));
        arrays.add(new PicTextBean(R.mipmap.ic_launcher, HomePageContract.SHOP_MANAGER_ORDER));
        arrays.add(new PicTextBean(R.mipmap.ic_launcher, HomePageContract.SHOP_MANAGER_LIVE));
        arrays.add(new PicTextBean(R.mipmap.ic_launcher, HomePageContract.SHOP_MANAGER_ASSENT));
        arrays.add(new PicTextBean(R.mipmap.ic_launcher, HomePageContract.SHOP_MANAGER_FURNISH));
        arrays.add(new PicTextBean(R.mipmap.ic_launcher, HomePageContract.SHOP_MANAGER_SHOP));
        arrays.add(new PicTextBean(R.mipmap.ic_launcher, HomePageContract.SHOP_MANAGER_GUIDE));
        return arrays;
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
        return new PicTextAdapter(R.layout.shop_manager_item);
    }

    @Override
    public void onSuccess(String tag, Object o) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.share_shop_iv:
                // TODO: 2022/6/7 分享店铺
                break;
            case R.id.search_ll:
                getBaseAppActivity().startToSearchActivity();
                break;
            case R.id.scan_iv:
                // : 2022/5/31 扫码
                startActivity(new Intent(mContext, QRScanActivity.class));

                break;
            default:
                break;
        }
    }
}
