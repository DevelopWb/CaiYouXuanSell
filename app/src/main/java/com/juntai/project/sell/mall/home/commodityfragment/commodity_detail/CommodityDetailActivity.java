package com.juntai.project.sell.mall.home.commodityfragment.commodity_detail;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.base.BaseResult;
import com.juntai.disabled.basecomponent.bean.objectboxbean.CommodityPropertyBean;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.project.sell.mall.AppHttpPathMall;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseAppActivity;
import com.juntai.project.sell.mall.base.selectPics.SelectPhotosFragment;
import com.juntai.project.sell.mall.beans.CommodityDetailBean;
import com.juntai.project.sell.mall.beans.CommodityEvaluationBean;
import com.juntai.project.sell.mall.beans.PicTextBean;
import com.juntai.project.sell.mall.beans.UserBeanMall;
import com.juntai.project.sell.mall.beans.order.CreatOrderBean;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.juntai.project.sell.mall.home.commodityfragment.CommodityPresent;
import com.juntai.project.sell.mall.home.commodityfragment.commodity_detail.evaluation.AllEvaluateFragment;
import com.juntai.project.sell.mall.home.commodityfragment.commodity_detail.selectCommodityProperty.SelectCommodityPropertyDialogFragment;
import com.juntai.project.sell.mall.share.ShareActivity;
import com.juntai.project.sell.mall.utils.ObjectBoxMallUtil;
import com.juntai.project.sell.mall.utils.UserInfoManagerMall;

import java.util.List;

import okhttp3.FormBody;

/**
 * @aouther tobato
 * @description 描述  商品详情
 * @date 2022/5/3 15:55
 */
public class CommodityDetailActivity extends BaseAppActivity<CommodityPresent> implements HomePageContract.IHomePageView, View.OnClickListener, SelectPhotosFragment.OnPhotoItemClick {

    public int commodityId;
    private RecyclerView mCommodityBottomRv;
    /**
     * 加入购物车
     */
    private TextView mAddToCartTv;
    /**
     * 立即购买
     */
    private TextView mBuyNowTv;
    private CommodityDetailFragment commodityDetailFragment;
    private AllEvaluateFragment mEvaluateFragment;
    private CommodityDetailBean.DataBean dataBean;
    private PicTextAdapter picTextAdapter;
    private int collectId = 0;


    @Override
    protected CommodityPresent createPresenter() {
        return new CommodityPresent();
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_commodity_detail;
    }


    @Override
    public void initView() {
        setTitleName("商品详情");
        initFragmentSelected(0);
        // : 2022/5/3 这个地方的分享图标需要更换
        setRightTvDrawable(R.mipmap.share_icon);
        getTitleRightTv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // : 2022/5/3 跳转到 分享商品的界面
                if (dataBean == null) {
                    ToastUtils.toast(mContext, "商品信息获取失败 无法分享");
                    return;
                }
                ShareActivity.startShareActivity(mContext, 1, dataBean.getCoverImg(), dataBean.getName(),dataBean.getShareUrl());
            }
        });
        commodityId = getIntent().getIntExtra(BASE_ID, 0);
        mCommodityBottomRv = (RecyclerView) findViewById(R.id.commodity_bottom_rv);
        picTextAdapter = new PicTextAdapter(R.layout.custom_tabitem);
        GridLayoutManager manager = new GridLayoutManager(mContext, 3);
        mCommodityBottomRv.setLayoutManager(manager);
        mCommodityBottomRv.setAdapter(picTextAdapter);
        mAddToCartTv = (TextView) findViewById(R.id.add_to_cart_tv);
        mAddToCartTv.setOnClickListener(this);
        mBuyNowTv = (TextView) findViewById(R.id.buy_now_tv);
        mBuyNowTv.setOnClickListener(this);
        picTextAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                PicTextBean picTextBean = (PicTextBean) adapter.getItem(position);
                switch (picTextBean.getTextName()) {
                    case HomePageContract.SHOP:
                        // : 2022/5/3 进入店铺
                        startToShop(dataBean.getShopId());

                        break;
                    case HomePageContract.CUSTOMER:
                        // : 2022/5/3 联系店铺客服
                        // : 2022/5/19 获取客服人员的信息
                        FormBody.Builder builder = new FormBody.Builder()
                                .add("account", UserInfoManagerMall.getAccount())
                                .add("token", UserInfoManagerMall.getUserToken())
                                .add("typeEnd", UserInfoManagerMall.DEVICE_TYPE)
                                .add("userId", String.valueOf(dataBean.getUserId()));
                        mPresenter.getUserInfo(builder.build(), AppHttpPathMall.GET_USER_INFO);

                        break;
                    case HomePageContract.COLLECT:
                        // : 2022/5/3 收藏商品
                        if (collectId > 0) {
                            //取消收藏
                            mPresenter.collectCommodity(getBaseBuilder()
                                    .add("isCollect", "1")
                                    .add("id", String.valueOf(collectId))
                                    .add("commodityId", String.valueOf(commodityId)).build(), HomePageContract.UN_COLLECT_COMMODITY_SHOP
                            );

                        } else {
                            //收藏
                            mPresenter.collectCommodity(getBaseBuilder()
                                    .add("isCollect", "0")
                                    .add("commodityId", String.valueOf(commodityId)).build(), HomePageContract.COLLECT_COMMODITY_SHOP
                            );

                        }


                        break;
                    default:
                        break;
                }
            }
        });

    }

    @Override
    public void initData() {
        mPresenter.getCommodityDetail(getBaseBuilderWithoutParama()
                .add("userId", String.valueOf(UserInfoManagerMall.getUserId()))
                .add("commodityId", String.valueOf(commodityId)).build(), AppHttpPathMall.COMMODIFY_DETAIL);
    }


    /**
     * 获取各个fragment对象
     */
    private void initFragments() {
        if (commodityDetailFragment == null) {
            commodityDetailFragment = new CommodityDetailFragment();
        }
        if (mEvaluateFragment == null) {
            mEvaluateFragment = new AllEvaluateFragment();
        }
    }

    /**
     * 隐藏所有的fragment
     *
     * @param fragmentTransaction
     */
    private void hindFragments(FragmentTransaction fragmentTransaction) {
        if (commodityDetailFragment != null) {
            fragmentTransaction.hide(commodityDetailFragment);
        }
        if (mEvaluateFragment != null) {
            fragmentTransaction.hide(mEvaluateFragment);
        }
    }

    /**
     * 初始化fragment
     *
     * @param i
     */
    public void initFragmentSelected(int i) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hindFragments(fragmentTransaction);
        initFragments();
        switch (i) {
            case 0:
                if (!commodityDetailFragment.isAdded()) {
                    fragmentTransaction.add(R.id.commodity_detail_fl, commodityDetailFragment, "commodityDetailFragment");
                }
                fragmentTransaction.show(commodityDetailFragment);

                break;
            case 1:
                if (!mEvaluateFragment.isAdded()) {
                    fragmentTransaction.add(R.id.commodity_detail_fl, mEvaluateFragment, "mapfragment");
                }
                fragmentTransaction.show(mEvaluateFragment);
                break;
            default:
                break;
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.add_to_cart_tv:
                // : 2022/5/5 加入购物车
            case R.id.buy_now_tv:
                // : 2022/5/5 立即购买
                SelectCommodityPropertyDialogFragment selectCommodityPropertyFragment = new SelectCommodityPropertyDialogFragment();
                selectCommodityPropertyFragment.show(getSupportFragmentManager(), "selectCommodityPropertyFragment");
                selectCommodityPropertyFragment.setOnConfirmCallBack(new SelectCommodityPropertyDialogFragment.OnConfirmCallBack() {
                    @Override
                    public void confirm(CommodityPropertyBean commodityPropertyBean, int amount) {
                        if (v.getId() == R.id.add_to_cart_tv) {
                            mPresenter.editCart(getBaseBuilder().add("shopId", String.valueOf(dataBean.getShopId()))
                                    .add("commodityId", String.valueOf(commodityId))
                                    .add("attributeUnique", commodityPropertyBean.getUnique())
                                    .add("commodityNum", String.valueOf(amount))
                                    .add("commodityAttr", commodityPropertyBean.getSku())
                                    .build(), AppHttpPathMall.EDIT_CART
                            );
                        } else {
                            mPresenter.creatOrderBuy(getBaseBuilder()
                                    .add("shopId", String.valueOf(commodityPropertyBean.getShopId()))
                                    .add("commodityId", String.valueOf(commodityPropertyBean.getCommodityId()))
                                    .add("unique", commodityPropertyBean.getUnique())
                                    .add("commodityNum", String.valueOf(amount)).build(), AppHttpPathMall.CREAT_ORDER_BUY
                            );
                        }

                    }
                });
                selectCommodityPropertyFragment.setData(dataBean);
                break;
        }
    }

    @Override
    public void onVedioPicClick(BaseQuickAdapter adapter, int position) {

    }

    @Override
    public void onPicClick(BaseQuickAdapter adapter, int position) {

    }

    @Override
    public void onBackPressed() {
        if ("商品评价".equals(getTextViewValue(getTitleTv()))) {
            initFragmentSelected(0);
            setTitleName("商品详情");
            return;
        }
        super.onBackPressed();


    }


    @Override
    public void onSuccess(String tag, Object o) {

        switch (tag) {
            case AppHttpPathMall.COMMODIFY_DETAIL:
                CommodityDetailBean commodityDetailBean = (CommodityDetailBean) o;
                if (commodityDetailBean != null) {
                    dataBean = commodityDetailBean.getData();
                    collectId = dataBean.getIsCollect();
                    picTextAdapter.setNewData(mPresenter.getCommodityBottomMenus(dataBean.getIsCollect() > 0));
                    commodityDetailFragment.initAdapterData(dataBean);
                    //获取商品评价
                    mPresenter.getCommodityEvaluation(getBaseBuilderWithoutParama()
                            .add("type", "8")
                            .add("commodityId", String.valueOf(commodityId)).build(), AppHttpPathMall.COMMODIFY_EVALUATION);

                    List<CommodityPropertyBean> commodityPropertyBeans = dataBean.getValue();
                    ObjectBoxMallUtil.addCommodityProperty(dataBean, commodityPropertyBeans);

                }
                break;

            case AppHttpPathMall.COMMODIFY_EVALUATION:
                CommodityEvaluationBean commodityEvaluationBean = (CommodityEvaluationBean) o;
                if (commodityEvaluationBean != null) {
                    List<CommodityEvaluationBean.DataBean> arrays = commodityEvaluationBean.getData();
                    commodityDetailFragment.addEvaluationData(arrays);
                }
                commodityDetailFragment.addDetailData(dataBean.getDescription());

                break;

            case AppHttpPathMall.EDIT_CART:
                ToastUtils.toast(mContext, "已加入购物车");
                break;
            case HomePageContract.UN_COLLECT_COMMODITY_SHOP:
                collectId = 0;
                picTextAdapter.setNewData(mPresenter.getCommodityBottomMenus(false));
                break;
            case HomePageContract.COLLECT_COMMODITY_SHOP:
                BaseResult baseResult = (BaseResult) o;
                collectId = Integer.parseInt(baseResult.getMessage());
                picTextAdapter.setNewData(mPresenter.getCommodityBottomMenus(true));

                break;
            case AppHttpPathMall.CREAT_ORDER_BUY:
                CreatOrderBean creatOrderBean = (CreatOrderBean) o;
                if (creatOrderBean != null) {
                    CreatOrderBean.DataBean dataBean = creatOrderBean.getData();
                    startToConfirmOrder(dataBean);

                }
                break;

            case AppHttpPathMall.GET_USER_INFO:
                UserBeanMall loginBean = (UserBeanMall) o;
                if (loginBean != null) {
                    // : 2022/5/19 进入到聊天的界面
                    startToChatActivity(loginBean.getData());

                }
                break;
            default:
                break;
        }
    }
}
