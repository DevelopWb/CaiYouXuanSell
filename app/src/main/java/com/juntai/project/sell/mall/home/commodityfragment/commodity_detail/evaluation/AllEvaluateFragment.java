package com.juntai.project.sell.mall.home.commodityfragment.commodity_detail.evaluation;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;

import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseAppFragment;
import com.juntai.project.sell.mall.base.customview.CustomViewPager;
import com.juntai.project.sell.mall.base.customview.MainPagerAdapter;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.juntai.project.sell.mall.home.commodityfragment.CommodityPresent;
import com.juntai.project.sell.mall.home.commodityfragment.commodity_detail.CommodityDetailActivity;

import java.util.Objects;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/5/4 14:04
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/4 14:04
 */
public class AllEvaluateFragment extends BaseAppFragment<CommodityPresent> implements HomePageContract.IHomePageView {
    private TabLayout mTabTb;
    private CustomViewPager mViewpageVp;
    private String[] titles = {"全部", "有图/视频", "好评", "中评", "差评"};

    @Override
    protected CommodityPresent createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.shop_commodity_evaluate_fg;
    }

    @Override
    protected void initView() {

        mTabTb = (TabLayout) getView(R.id.tab_tb);
        mViewpageVp = (CustomViewPager) getView(R.id.viewpage_vp);
    }

    @Override
    protected void initData() {
        initTab();
    }

    private void initTab() {
        MainPagerAdapter adapter = new MainPagerAdapter(getChildFragmentManager(), mContext,
                titles,
                getFragments());
        mViewpageVp.setAdapter(adapter);
        mViewpageVp.setOffscreenPageLimit(titles.length);
        /*viewpager切换监听，包含滑动点击两种*/
        mViewpageVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        mTabTb.setupWithViewPager(mViewpageVp);
        /**
         * 添加自定义tab布局
         * */
        for (int i = 0; i < mTabTb.getTabCount(); i++) {
            TabLayout.Tab tab = mTabTb.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(adapter.getTabViewOfEvaluate(i));
            }
        }
        /*viewpager切换默认第一个*/
        mViewpageVp.setCurrentItem(0);
    }

    /**
     * 0好评；1中评；2差评；3有图或视频  其他 是全部
     *
     * @return
     */
    private SparseArray<Fragment> getFragments() {
        SparseArray<Fragment> fragmentSparseArray = new SparseArray<>();
        fragmentSparseArray.append(0, EvaluateFragment.getInstance(8, ((CommodityDetailActivity) Objects.requireNonNull(getActivity())).commodityId));
        fragmentSparseArray.append(1, EvaluateFragment.getInstance(3, ((CommodityDetailActivity) Objects.requireNonNull(getActivity())).commodityId));
        fragmentSparseArray.append(2, EvaluateFragment.getInstance(0, ((CommodityDetailActivity) Objects.requireNonNull(getActivity())).commodityId));
        fragmentSparseArray.append(3, EvaluateFragment.getInstance(1, ((CommodityDetailActivity) Objects.requireNonNull(getActivity())).commodityId));
        fragmentSparseArray.append(4, EvaluateFragment.getInstance(2, ((CommodityDetailActivity) Objects.requireNonNull(getActivity())).commodityId));
        return fragmentSparseArray;
    }

    @Override
    public void onSuccess(String tag, Object o) {

    }

}
