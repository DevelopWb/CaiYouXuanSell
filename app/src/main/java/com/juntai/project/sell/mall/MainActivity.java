package com.juntai.project.sell.mall;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.LinearLayout;

import com.baidu.location.BDLocation;
import com.juntai.disabled.basecomponent.utils.ActivityManagerTool;
import com.juntai.disabled.basecomponent.utils.HawkProperty;
import com.juntai.disabled.basecomponent.utils.eventbus.EventBusObject;
import com.juntai.disabled.basecomponent.utils.eventbus.EventManager;
import com.juntai.disabled.bdmap.service.LocateAndUpload;
import com.juntai.project.sell.mall.base.BaseAppActivity;
import com.juntai.project.sell.mall.base.customview.CustomViewPager;
import com.juntai.project.sell.mall.base.customview.MainPagerAdapter;
import com.juntai.project.sell.mall.home.HomeShopFragment;
import com.juntai.project.sell.mall.mine.MyCenterFragment;
import com.juntai.project.sell.mall.news.NewsListFragment;
import com.juntai.project.sell.mall.utils.UserInfoManagerMall;
import com.juntai.project.sell.mall.webSocket.MyWsManager;
import com.mob.MobSDK;

public class MainActivity extends BaseAppActivity<MainPagePresent> implements
        View.OnClickListener, MainPageContract.IMainPageView {
    private MainPagerAdapter adapter;
    private LinearLayout mainLayout;
    private CustomViewPager mainViewpager;

    private TabLayout mainTablayout;
    private String[] title = new String[]{"首页", "消息", "个人中心"};
    private int[] tabDrawables = new int[]{R.drawable.home_index,  R.drawable.notice_index,  R.drawable.mine_index};
    private SparseArray<Fragment> mFragments = new SparseArray<>();
    //


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (mainViewpager != null) {
            mainViewpager.setCurrentItem(0);
        }
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        HawkProperty.clearRedPoint(mContext.getApplicationContext());

        initToolbarAndStatusBar(false);
        mainViewpager = findViewById(R.id.main_viewpager);
        mainTablayout = findViewById(R.id.main_tablayout);
        mainLayout = findViewById(R.id.main_layout);
        mainViewpager.setScanScroll(false);
        mFragments.append(0, new HomeShopFragment());//
        mFragments.append(1, new NewsListFragment());//
        mFragments.append(2, new MyCenterFragment());//
        mainViewpager.setOffscreenPageLimit(5);
        initTab();
    }


    @Override
    public void initData() {
        update(false);
    }


    public void initTab() {
        adapter = new MainPagerAdapter(getSupportFragmentManager(), getApplicationContext(), title, tabDrawables,
                mFragments);
        mainViewpager.setAdapter(adapter);
        mainViewpager.setOffscreenPageLimit(5);
        /*viewpager切换监听，包含滑动点击两种*/
        for (int i = 0; i < title.length; i++) {
            TabLayout.Tab tab = mainTablayout.newTab();
            if (tab != null) {
                if (i == 1) {
                    tab.setCustomView(adapter.getTabView(i, true));
                } else {
                    tab.setCustomView(adapter.getTabView(i, false));
                }
                mainTablayout.addTab(tab);
            }
        }

        mainTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mainViewpager.setCurrentItem(tab.getPosition(), false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        /*viewpager切换默认第一个*/
        mainViewpager.setCurrentItem(0);
    }


    @Override
    public void onSuccess(String tag, Object o) {
        switch (tag) {
            default:
                break;
        }
    }


    @Override
    public void onClick(View v) {

    }


    @Override
    public void onEvent(EventBusObject eventBusObject) {
        super.onEvent(eventBusObject);
        switch (eventBusObject.getEventKey()) {
            case EventBusObject.RE_LOAD:
                reLogin(UserInfoManagerMall.getPhoneNumber());
                break;
            case EventBusObject.UNREAD_MSG_AMOUNT:
                int amount = (int) eventBusObject.getEventObj();
                adapter.setUnReadMsg(amount);
                break;
            default:
                break;
        }
    }

    @Override
    protected MainPagePresent createPresenter() {
        return new MainPagePresent();
    }

    @Override
    public void onLocationReceived(BDLocation bdLocation) {

        EventManager.getEventBus().post(new EventBusObject(EventBusObject.ON_LOCATION_RECEIVED, bdLocation));
    }

    @Override
    public boolean requestLocation() {
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyWsManager.getInstance().startConnect();
        // : 2022/7/13 mob的隐私授权 不能删
        MobSDK.submitPolicyGrantResult(true);
    }

    @Override
    protected void onDestroy() {
        Log.e("EEEEEEEEEE", " = ChatMainActivity  onDestroy");
        stopService(new Intent(MainActivity.this, LocateAndUpload.class));
        MyWsManager.getInstance().disconnect();

        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        showAlertDialog("请选择退出方式", "退出", "挂起", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyApp.app.isFinish = true;
                ActivityManagerTool.getInstance().finishApp();
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //模拟home键,发送广播
                //sendBroadcast(new Intent().setAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)
                // .putExtra("reason","homekey"));
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
    }


}
