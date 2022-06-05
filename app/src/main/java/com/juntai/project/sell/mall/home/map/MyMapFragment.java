package com.juntai.project.sell.mall.home.map;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.utils.HawkProperty;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.basecomponent.utils.eventbus.EventBusObject;
import com.juntai.disabled.basecomponent.utils.eventbus.EventManager;
import com.juntai.disabled.bdmap.utils.MapUtil;
import com.juntai.disabled.bdmap.utils.clusterutil.clustering.Cluster;
import com.juntai.disabled.bdmap.utils.clusterutil.clustering.ClusterManager;
import com.juntai.project.sell.mall.AppHttpPathMall;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseAppFragment;
import com.juntai.project.sell.mall.beans.AroundShopBean;
import com.juntai.project.sell.mall.beans.IdNameBean;
import com.juntai.project.sell.mall.beans.PicTextBean;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.juntai.project.sell.mall.home.HomePagePresent;
import com.juntai.project.sell.mall.home.map.weather.WeatherActivity;
import com.juntai.project.sell.mall.utils.ImageUtil;
import com.juntai.project.sell.mall.utils.StringTools;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

/**
 * @aouther tobato
 * @description 更新  主页fragment
 * @date 2020/4/21 11:06
 */
public class MyMapFragment extends BaseAppFragment<HomePagePresent> implements BaiduMap.OnMapLoadedCallback,
        View.OnClickListener, ClusterManager.OnClusterClickListener<MapClusterItem>,
        ClusterManager.OnClusterItemClickListener<MapClusterItem>, HomePageContract.IHomePageView {
    private MapView mBmapView;
    private RecyclerView mHomePageRightMenuRv;
    private ImageView mZoomplus;
    private ImageView mZoomminus;
    private View infowindow = null;
    private Button mMylocation;
    private ImageView mSatalliteMapIv;
    private ImageView mTwoDMapIv;
    private ImageView mThreeDMapIv;
    private Switch mTrafficSv;
    private DrawerLayout mDrawerlayout;
    private MapMenuAdapter menuAdapter;
    private BaiduMap mBaiduMap;
    private RelativeLayout mDrawerRightLaytoutRl;
    public static String province = null;
    public static String city = null;
    public static String area = null;
    private double lat;
    private double lng;
    private boolean isFisrt = true;
    private PopupWindow popupWindow;
    private List<MapClusterItem> clusterItemList = new ArrayList<>();
    private ClusterManager clusterManager;
    private BitmapDescriptor bitmapDescriptor;
    //当前点击的marker
    Marker nowMarker;
    private int clickItemType = 2;//2单个marker点击，1聚合列表点击
    private MapStatus lastPosition;
    int nowMarkerId;//当前markerid
    private BottomSheetDialog mapBottomDialog;
    private ClusterClickAdapter clusterClickAdapter;
    private ImageView mSwitchModeIv;
    private BDLocation bdLocation;
    private TabLayout mTabTv;


    public void setBdLocation(BDLocation bdLocation) {
        this.bdLocation = bdLocation;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.satallite_map_iv:
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);//打开卫星地图
                mapType(v.getId());
                break;
            case R.id.two_d_map_iv:
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);//
                mapType(v.getId());
                break;
            case R.id.three_d_map_iv:
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);//
                mapType(v.getId());
                break;
            case R.id.search_ll:
                //搜索
                // : 2022/4/26 搜索
                getBaseAppActivity().startToSearchActivity();
                break;
            case R.id.zoomminus:
                MapUtil.mapZoom(MapUtil.MAP_ZOOM_OUT1, mBaiduMap);
                break;
            case R.id.zoomplus:
                MapUtil.mapZoom(MapUtil.MAP_ZOOM_IN1, mBaiduMap);
                break;

            case R.id.mylocation:
                //当前位置
                MapUtil.mapZoom(MapUtil.MAP_ZOOM_TO, mBaiduMap, 16);
                MapUtil.mapMoveTo(mBaiduMap, new LatLng(lat, lng));
                break;
            case R.id.switch_mode_iv:
                EventManager.getEventBus().post(new EventBusObject(EventBusObject.HOME_PAGE_DISPLAY_MODE, 0));
                break;
            default:
                break;
        }
    }

    /**
     * 地图模式切换
     *
     * @param viewId
     */
    private void mapType(int viewId) {
        switch (viewId) {
            case R.id.satallite_map_iv:
                mTwoDMapIv.setBackgroundColor(getResources().getColor(R.color.transparent));
                mThreeDMapIv.setBackgroundColor(getResources().getColor(R.color.transparent));
                mSatalliteMapIv.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                break;
            case R.id.two_d_map_iv:
                mTwoDMapIv.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                mThreeDMapIv.setBackgroundColor(getResources().getColor(R.color.transparent));
                mSatalliteMapIv.setBackgroundColor(getResources().getColor(R.color.transparent));
                break;
            case R.id.three_d_map_iv:
                mTwoDMapIv.setBackgroundColor(getResources().getColor(R.color.transparent));
                mThreeDMapIv.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                mSatalliteMapIv.setBackgroundColor(getResources().getColor(R.color.transparent));
                break;
        }
    }

    @Override
    public void onMapLoaded() {
    }

    @Override
    protected HomePagePresent createPresenter() {
        return new HomePagePresent();
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.my_map_fg;
    }

    @Override
    protected void initView() {
        getView(R.id.search_ll).setOnClickListener(this);
        mSwitchModeIv = (ImageView) getView(R.id.switch_mode_iv);
        mSwitchModeIv.setOnClickListener(this);
        mBmapView = (MapView) getView(R.id.bmapView);
        mBmapView.showZoomControls(false);
        mBaiduMap = mBmapView.getMap();
        onLocationReceived(bdLocation);
        mHomePageRightMenuRv = (RecyclerView) getView(R.id.home_page_right_menu_rv);
        mZoomplus = (ImageView) getView(R.id.zoomplus);
        mZoomplus.setOnClickListener(this);
        mZoomminus = (ImageView) getView(R.id.zoomminus);
        mZoomminus.setOnClickListener(this);
        mMylocation = (Button) getView(R.id.mylocation);
        mMylocation.setOnClickListener(this);
        initUISetting();
        initMenuAdapter();
        initDrawerLayout();
        initClusterManagerAndMap();
        initTab();
    }

    /**
     * 自定义底部消息tab
     *
     * @return
     */
    public View getTabView(IdNameBean.DataBean dataBean) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.chat_home_shop_tabitem, null);
        TextView title = v.findViewById(R.id.tabitem_text);
        title.setText(dataBean.getName());
        return v;
    }

    private void initTab() {
        mTabTv = (TabLayout) getView(R.id.mall_tablayout);
        if (Hawk.contains(HawkProperty.LOCAL_LABEL)) {
            List<IdNameBean.DataBean> dataBeans = Hawk.get(HawkProperty.LOCAL_LABEL);
            for (IdNameBean.DataBean dataBean : dataBeans) {
                TabLayout.Tab tab = mTabTv.newTab();
                tab.setCustomView(getTabView(dataBean));
                mTabTv.addTab(tab);
            }
            getAroudShopes(0);
        }

        mTabTv.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                // : 2022/5/10 查询店铺分布
                getAroudShopes(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void getAroudShopes(int position) {
        clearMapPoints();
        List<IdNameBean.DataBean> dataBeans = Hawk.get(HawkProperty.LOCAL_LABEL);
        if (dataBeans == null) {
            return;
        }
        int labelId = dataBeans.get(position).getId();
        mPresenter.getAroundShopes(getBaseAppActivity().getBaseBuilderWithoutParama()
                .add("longitude", String.valueOf(lng))
                .add("latitude", String.valueOf(lat))
                .add("categoryId", String.valueOf(labelId)).build(), AppHttpPathMall.GET_SHOPES_AROUND
        );
    }

    /**
     * 配置地图相关控件
     */
    private void initUISetting() {
        //实例化UiSettings类对象
        UiSettings mUiSettings = mBaiduMap.getUiSettings();
        //通过设置enable为true或false 选择是否显示指南针
        mUiSettings.setCompassEnabled(false);
        //开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        MyLocationConfiguration config = new MyLocationConfiguration(
                MyLocationConfiguration.LocationMode.NORMAL, true, null);
        mBaiduMap.setMyLocationConfiguration(config);
    }

    /**
     * 初始化ClusterManager和map
     */
    private void initClusterManagerAndMap() {
        clusterManager = new ClusterManager<>(mContext, mBaiduMap);
        clusterManager.setOnClusterItemClickListener(MyMapFragment.this);//点点击
        clusterManager.setOnClusterClickListener(MyMapFragment.this);//聚合展开
        //地图图标点击事件的监听
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                //不是大的聚合点
                if (!clusterManager.getClusterMarkerCollection().getMarkers().contains(marker)) {
                    if (nowMarker != null) {
                        if (bitmapDescriptor != null) {
                            nowMarker.setIcon(bitmapDescriptor);
                        }

                    }
                    //marker.setIcon(BitmapDescriptorFactory.fromBitmap(compoundBitmap
                    // (BitmapFactory.decodeResource(getResources(),R.mipmap
                    // .ic_client_location_pre), BitmapFactory.decodeResource(getResources(),R
                    // .mipmap.ic_my_default_head))));
                    nowMarker = marker;
                    clickItemType = 2;
                }
                //聚合管理类响应点击事件
                clusterManager.onMarkerClick(marker);
                return false;
            }
        });
        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mBmapView.removeView(infowindow);
            }

            @Override
            public void onMapPoiClick(MapPoi mapPoi) {
                mBmapView.removeView(infowindow);
            }
        });
        //
        //手动缩放地图的时候 聚合图标
        mBaiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus) {
            }

            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus, int i) {
            }

            @Override
            public void onMapStatusChange(MapStatus mapStatus) {
                if (lastPosition != null && lastPosition.zoom != mBaiduMap.getMapStatus().zoom) {
                    mBmapView.removeView(infowindow);
                    clickItemType = 2;
                    if (nowMarker != null) {
                        nowMarker.setIcon(bitmapDescriptor);
                        nowMarker = null;
                    }
                    nowMarkerId = 0;
                }
                lastPosition = mBaiduMap.getMapStatus();
                clusterManager.onMapStatusChange(mapStatus);
            }

            @Override
            public void onMapStatusChangeFinish(MapStatus mapStatus) {
            }
        });
    }

    /**
     * 初始化抽屉式布局
     */
    private void initDrawerLayout() {
        mSatalliteMapIv = (ImageView) getView(R.id.satallite_map_iv);
        mSatalliteMapIv.setOnClickListener(this);
        mTwoDMapIv = (ImageView) getView(R.id.two_d_map_iv);
        mTwoDMapIv.setOnClickListener(this);
        mThreeDMapIv = (ImageView) getView(R.id.three_d_map_iv);
        mThreeDMapIv.setOnClickListener(this);
        mTrafficSv = (Switch) getView(R.id.traffic_sv);
        mDrawerlayout = (DrawerLayout) getView(R.id.drawerlayout);
        mDrawerRightLaytoutRl = getView(R.id.drawer_right_layout_rl);
        //关闭侧滑功能
        mDrawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        //路况图监听
        mTrafficSv.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                mBaiduMap.setTrafficEnabled(true);
            } else {
                mBaiduMap.setTrafficEnabled(false);
            }

        });
    }

    /**
     * 初始化菜单适配器
     */
    private void initMenuAdapter() {
        menuAdapter = new MapMenuAdapter(R.layout.may_menu_layout);
        getBaseActivity().initRecyclerview(mHomePageRightMenuRv, menuAdapter, LinearLayoutManager.VERTICAL);
        menuAdapter.setNewData(mPresenter.getMenus());
        menuAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                nowMarkerId = 0;
                nowMarker = null;
                PicTextBean menuBean = (PicTextBean) adapter.getData().get(position);
                switch (menuBean.getTextName()) {
                    case HomePageContract.MENUE_MAP_TYPE:
                        if (mDrawerlayout.isDrawerVisible(mDrawerRightLaytoutRl)) {
                            mDrawerlayout.closeDrawers();
                        } else {
                            mDrawerlayout.openDrawer(mDrawerRightLaytoutRl);

                        }
                        break;
                    case HomePageContract.MENUE_WEATHER:
                        if (!StringTools.isStringValueOk(province)) {
                            ToastUtils.warning(mContext, "定位未获取");
                        } else {
                            startActivity(new Intent(mContext, WeatherActivity.class)
                                    .putExtra("province", province)
                                    .putExtra("city", city)
                                    .putExtra("area", area == null ? "" : area));
                        }
                        break;

                    case HomePageContract.SHOP:
                        getAroudShopes(mTabTv.getSelectedTabPosition());

                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void onResume() {
        mBmapView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        releaseBottomListDialog();
        mBmapView.onPause();
        super.onPause();
    }


    @Override
    public void onDestroy() {
        if (popupWindow != null) {
            if (popupWindow.isShowing()) {
                popupWindow.dismiss();
            }
        }
        mBmapView.onDestroy();
        mBmapView = null;
        super.onDestroy();
    }

    @Override
    protected void initData() {
    }


    @Override
    public void onSuccess(String tag, Object o) {
        switch (tag) {
            case AppHttpPathMall.GET_SHOPES_AROUND:
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                AroundShopBean aroundShopBean = (AroundShopBean) o;
                if (aroundShopBean != null) {
                    List<AroundShopBean.DataBean> shopes = aroundShopBean.getData();
                    if (shopes != null) {
                        for (AroundShopBean.DataBean shope : shopes) {
                            LatLng latLng = new LatLng(Double.parseDouble(shope.getLatitude()), Double.parseDouble(shope.getLongitude()));
                            MapClusterItem mCItem = new MapClusterItem(
                                    latLng, shope);
                            clusterItemList.add(mCItem);
                            builder.include(latLng);
                        }

                        clusterManager.addItems(clusterItemList);
                        clusterManager.cluster();
                        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLngBounds(builder.build());
                        mBaiduMap.setMapStatus(mapStatusUpdate);
                    }
                }

                break;
            default:
                break;
        }
    }

    @Override
    public void onError(String tag, Object o) {
        ToastUtils.toast(mContext, (String) o);
    }


    public void onLocationReceived(BDLocation bdLocation) {
        //  161是网络定位的结果   61是GPS定位成功的结果
        if (bdLocation == null) {
            return;
        }
        lat = bdLocation.getLatitude();
        lng = bdLocation.getLongitude();
        city = bdLocation.getCity();
        area = bdLocation.getDistrict();
        province = bdLocation.getProvince();
        if (isFisrt) {
            MapUtil.mapMoveTo(mBaiduMap, new LatLng(lat,
                    lng));
            isFisrt = false;
        }
        MyLocationData data = new MyLocationData.Builder()//构建定位信息包
                .latitude(lat)//经度
                .longitude(lng)//纬度
                .build();//创建
        mBaiduMap.setMyLocationData(data);

    }


    /**
     * 清理地图标点
     */
    private void clearMapPoints() {
        mBaiduMap.clear();
        clusterItemList.clear();
        clusterManager.clearItems();
        mBmapView.removeView(infowindow);
    }

    /**
     * 点聚合展开
     *
     * @param cluster
     * @return
     */
    @Override
    public boolean onClusterClick(Cluster<MapClusterItem> cluster) {
        List<MapClusterItem> items = new ArrayList<MapClusterItem>(cluster.getItems());
        if (mapBottomDialog == null) {
            mapBottomDialog = new BottomSheetDialog(mContext);
            View view = LayoutInflater.from(mContext).inflate(R.layout.bottom_list_layout, null);
            mapBottomDialog.setContentView(view);
            RecyclerView bottomRv = view.findViewById(R.id.map_bottom_list_rv);
            clusterClickAdapter = new ClusterClickAdapter(R.layout.mall_collect_shop_item);
            getBaseActivity().initRecyclerview(bottomRv, clusterClickAdapter, LinearLayoutManager.VERTICAL);
            clusterClickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    MapClusterItem item = (MapClusterItem) adapter.getData().get(position);
                    if (infowindow != null) {
                        mBmapView.removeView(infowindow);
                    }
                    if (nowMarker != null) {
                        nowMarker.setIcon(bitmapDescriptor);
                    }
                    nowMarker = null;
                    mBaiduMap.hideInfoWindow();
                    clickItemType = 1;
                    onClusterItemClick(item);
                    releaseBottomListDialog();
                }
            });
        }
        clusterClickAdapter.setNewData(items);
        mapBottomDialog.show();
        return false;
    }


    /**
     * 地图点点击监听
     *
     * @param item
     * @return
     */
    @Override
    public boolean onClusterItemClick(MapClusterItem item) {
        if (infowindow != null) {
            mBmapView.removeView(infowindow);
        }
        switch (item.getType()) {
            case MapClusterItem.AROUND_SHOP:
                bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.mipmap.shop_map_icon);
                updateMarkerIcon(item.shop.getHeadPortrait());
                if (clickItemType == 1 || nowMarkerId == item.shop.getId()) {
                    getBaseAppActivity().startToShop(item.shop.getId());
                }
                nowMarkerId = item.shop.getId();
                break;

        }
        return false;
    }

    /**
     * 更新marker图标
     *
     * @param path
     */
    public void updateMarkerIcon(String path) {
        if (nowMarker == null) {
            return;
        }
        ImageLoadUtil.getBitmap(getContext().getApplicationContext(), path, R.mipmap.ic_error,
                new ImageLoadUtil.BitmapCallBack() {
                    @Override
                    public void getBitmap(Bitmap bitmap) {
                        try {
                            nowMarker.setIcon(BitmapDescriptorFactory.fromBitmap(ImageUtil.combineBitmap(
                                    BitmapFactory.decodeStream(getResources().getAssets().open(
                                            "ic_map_shop_bg.png")),
                                    ImageUtil.getRoundedCornerBitmap(ImageUtil.zoomImg(bitmap), 200))));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


    /**
     * 释放dialog
     */
    private void releaseBottomListDialog() {
        if (mapBottomDialog != null) {
            mapBottomDialog.dismiss();
            mapBottomDialog = null;
        }
    }


}
