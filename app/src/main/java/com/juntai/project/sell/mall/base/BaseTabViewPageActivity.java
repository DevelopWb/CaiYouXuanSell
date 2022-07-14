package com.juntai.project.sell.mall.base;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.util.SparseArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.juntai.disabled.basecomponent.mvp.BasePresenter;
import com.juntai.disabled.basecomponent.utils.eventbus.EventBusObject;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.customview.CustomViewPager;
import com.juntai.project.sell.mall.base.customview.MainPagerAdapter;
import com.juntai.project.sell.mall.utils.StringTools;


/**
 * @aouther tobato
 * @description 描述  tab+viewpage
 * @date 2021/6/1 15:52
 */
public abstract class BaseTabViewPageActivity<P extends BasePresenter> extends BaseAppActivity<P> {
    public static final String ORDER_ALL= "全部";
    public static final String ORDER_PAY = "待付款";
    public static final String ORDER_SEND = "待发货";
    public static final String ORDER_IS_SEND = "待收货";
    public static final String ORDER_EVALUATE = "待评价";
    public static final String ORDER_FINISHED = "已完成";
    public static final String ORDER_REFUND = "退款订单";
    public SearchView mSearchContentSv;


    public TabLayout mTabTb;
    public CustomViewPager mViewpageVp;
    private FrameLayout mTabHeadFl;
    private FrameLayout mTabFootFl;
    protected LinearLayout mSearchLl;
    private TextView mFinishTv;
    private MainPagerAdapter baseTabAdapter;


    @Override
    public int getLayoutView() {
        return R.layout.base_tab_page_layout;
    }

    @Override
    public void initView() {

        mSearchContentSv = (SearchView) findViewById(R.id.search_content_sv);
        mSearchLl = (LinearLayout) findViewById(R.id.search_ll);
        mFinishTv = (TextView) findViewById(R.id.cancel_tv);
        SearchView.SearchAutoComplete textView = (SearchView.SearchAutoComplete) mSearchContentSv.findViewById(com.juntai.disabled.basecomponent.R.id.search_src_text);
        textView.setTextSize(14);
        mTabTb = (TabLayout) findViewById(R.id.tab_tb);
        mTabTb.setTabMode(getTabMode());
        mViewpageVp = (CustomViewPager) findViewById(R.id.viewpage_vp);
        mTabHeadFl = (FrameLayout) findViewById(R.id.tab_head_fl);
        mTabFootFl = (FrameLayout) findViewById(R.id.tab_foot_fl);
        if (getTabHeadLayout() > 0) {
            mTabHeadFl.setVisibility(View.VISIBLE);
            mTabHeadFl.addView(View.inflate(this, getTabHeadLayout(), null));
        }
        if (getTabFootLayout() > 0) {
            mTabFootFl.setVisibility(View.VISIBLE);
            mTabFootFl.addView(View.inflate(this, getTabFootLayout(), null));
        }
        if (getTitleName() == null) {
            initToolbarAndStatusBar(false);
            mFinishTv.setVisibility(View.VISIBLE);
            mFinishTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            setMargin(mSearchLl, 15, 30, 15, 0);
        } else {
            setTitleName(getTitleName());
            mFinishTv.setVisibility(View.GONE);

        }
        mSearchContentSv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
//                if (!StringTools.isStringValueOk(s)) {
//                    ToastUtils.warning(mContext, "请输入要搜索的内容");
//                    return false;
//                }
                // 调用搜索接口
                commitSearch(mSearchContentSv.getQuery().toString().trim());

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (!StringTools.isStringValueOk(s)) {
                    commitSearch(mSearchContentSv.getQuery().toString().trim());
                }
                return false;
            }
        });
    }

    /**
     * TabLayout.MODE_FIXED  居中显示
     * MODE_SCROLLABLE  居左显示
     *
     * @return
     */
    protected abstract int getTabMode();

    protected abstract int getTabHeadLayout();

    protected abstract int getTabFootLayout();

    protected abstract void commitSearch(String s);

    protected abstract String getTitleName();

    @Override
    public void initData() {
        initTab();
    }


    private void initTab() {
        baseTabAdapter = new MainPagerAdapter(getSupportFragmentManager(), getApplicationContext(),
                getTabTitles(),
                getFragments());
        mViewpageVp.setAdapter(baseTabAdapter);
        mViewpageVp.setOffscreenPageLimit(getTabTitles().length);
        /*viewpager切换监听，包含滑动点击两种*/
        mViewpageVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                onTabSelected(i);
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
                tab.setCustomView(baseTabAdapter.getCustomTabView(i));
            }
        }
        /*viewpager切换默认第一个*/
        mViewpageVp.setCurrentItem(0);
    }

    protected abstract void onTabSelected(int i);


    protected abstract SparseArray<Fragment> getFragments();

    protected abstract String[] getTabTitles();

    @Override
    public void onEvent(EventBusObject eventBusObject) {
       switch (eventBusObject.getEventKey()) {
           case EventBusObject.UNHANDLER_ORDER_AMOUNT:
               int amount = (int) eventBusObject.getEventObj();
               baseTabAdapter.setUnReadMsg(amount);
               break;
           default:
               break;
       }
    }
}
