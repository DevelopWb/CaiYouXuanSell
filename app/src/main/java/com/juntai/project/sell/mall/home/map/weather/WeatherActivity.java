package com.juntai.project.sell.mall.home.map.weather;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.juntai.disabled.basecomponent.bean.weather.ResponseForcastWeather;
import com.juntai.disabled.basecomponent.bean.weather.ResponseRealTimeWeather;
import com.juntai.disabled.basecomponent.bean.weather.WeatherDaysBean;
import com.juntai.disabled.basecomponent.bean.weather.WeatherEveryDayBean;
import com.juntai.disabled.basecomponent.bean.weather.WeatherHoursBean;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseAppActivity;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.juntai.project.sell.mall.home.HomePagePresent;
import com.juntai.project.sell.mall.utils.StringTools;

import java.util.ArrayList;
import java.util.List;

/**
 * @aouther tobato   天气模块
 * @description 描述
 * @date 2020/3/21 14:12
 */
public class WeatherActivity extends BaseAppActivity<HomePagePresent> implements HomePageContract.IHomePageView,
        View.OnClickListener {
    private String province = "山东省";
    private String city = "临沂市", area = "河东区";
    private List<WeatherHoursBean> hourlyBeans = new ArrayList<>();
    private List<WeatherDaysBean> daysBeans = new ArrayList<>();
    private WeatherDaysAdapter daysAdapter;
    private WeatherHoursAdapter hoursAdapter;
    private ImageView mBackBtn;
    /**
     * 临沂市 河东区
     */
    private TextView mWeatherPlace;
    private ImageView mPlaceChooseBtn;
    private ImageView mWindVaneIv;
    /**
     * 27
     */
    private TextView mRealNumTemp;
    /**
     * 晴转多云
     */
    private TextView mSkycon;
    /**
     * 空气良78
     */
    private TextView mAqi;
    private RecyclerView mWeatherDays;
    private RecyclerView mWeatherHours;
    /**
     * 日出 04:54
     */
    private TextView mSunupTimeTv;
    /**
     * 日落 06:54
     */
    private TextView mSunsetTimeTv;
    private RecyclerView mWeatherOther;
    private List<WeatherEveryDayBean> otherDatas = new ArrayList<>();//其他指数
    private WeatherOtherDataAdapter otherDataAdapter;
    /**
     * 76
     */
    private TextView mAirQualityNum;
    /**
     * 空气良
     */
    private TextView mAirQualityTv;
    /**
     * 76
     */
    private TextView mRainfallRateNum;
    private RecyclerView mEverydayRecyclerview;
    private List<WeatherEveryDayBean> everyDayBeans = new ArrayList<>();//生活指数
    private WeatherEveryDayAdapter everyDayAdapter;
    private LinearLayout mActivityBg;
    private TextView mWeatherWarnTv;


    @Override
    public int getLayoutView() {
        return R.layout.activity_weather;
    }

    @Override
    public void initView() {
        getToolbar().setVisibility(View.GONE);
        mImmersionBar.reset();
        mBaseRootCol.setFitsSystemWindows(false);
        mImmersionBar.statusBarDarkFont(false)
                .init();
        mBackBtn = (ImageView) findViewById(R.id.back_btn);
        mBackBtn.setOnClickListener(this);
        mWeatherPlace = (TextView) findViewById(R.id.weather_place);
        mWeatherPlace.setOnClickListener(this);
        mPlaceChooseBtn = (ImageView) findViewById(R.id.place_choose_btn);
        mPlaceChooseBtn.setOnClickListener(this);
        mWindVaneIv = (ImageView) findViewById(R.id.wind_vane_iv);
        mRealNumTemp = (TextView) findViewById(R.id.real_num_temp);
        mSkycon = (TextView) findViewById(R.id.skycon);
        mAqi = (TextView) findViewById(R.id.aqi);
        mWeatherDays = (RecyclerView) findViewById(R.id.weather_days);
        mWeatherHours = (RecyclerView) findViewById(R.id.weather_hours);
        mSunupTimeTv = (TextView) findViewById(R.id.sunup_time_tv);
        mSunsetTimeTv = (TextView) findViewById(R.id.sunset_time_tv);
        mWeatherOther = (RecyclerView) findViewById(R.id.weather_other);
        mAirQualityNum = (TextView) findViewById(R.id.air_quality_num);
        mAirQualityTv = (TextView) findViewById(R.id.air_quality_tv);
        mRainfallRateNum = (TextView) findViewById(R.id.rainfall_rate_num);
        mEverydayRecyclerview = (RecyclerView) findViewById(R.id.everyday_recyclerview);
        mActivityBg = (LinearLayout) findViewById(R.id.activity_bg);
        mWeatherWarnTv = findViewById(R.id.weather_warn_tv);


        mWeatherDays.setLayoutManager(new LinearLayoutManager(mContext));
        mWeatherHours.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL,false));
        daysAdapter = new WeatherDaysAdapter(R.layout.item_weather_days, daysBeans);
        mWeatherDays.setAdapter(daysAdapter);
        hoursAdapter = new WeatherHoursAdapter(R.layout.item_weather_hours, hourlyBeans);
        mWeatherHours.setNestedScrollingEnabled(false);
        mWeatherHours.setAdapter(hoursAdapter);

        mWeatherOther.setLayoutManager(new GridLayoutManager(mContext,4));
        mEverydayRecyclerview.setLayoutManager(new GridLayoutManager(mContext,3));
        otherDataAdapter = new WeatherOtherDataAdapter(R.layout.item_weather_other,otherDatas);
        mWeatherOther.setAdapter(otherDataAdapter);
        everyDayAdapter = new WeatherEveryDayAdapter(R.layout.item_weather_everyday,everyDayBeans);
        mEverydayRecyclerview.setAdapter(everyDayAdapter);

    }

    @Override
    public boolean requestLocation() {
        return true;
    }


    @Override
    public void onLocationReceived(BDLocation bdLocation) {
        if (bdLocation != null){
            province = bdLocation.getProvince();
            city = bdLocation.getCity();
            area = bdLocation.getDistrict();
            requestWeather(String.valueOf(bdLocation.getLongitude()), String.valueOf(bdLocation.getLatitude()));
        }
        mWeatherPlace.setText(city + " " + area);

    }

    /**
     * 配置实时天气数据
     *
     * @param o
     */
    @SuppressLint("SetTextI18n")
    private void setRealTimeWeather(ResponseRealTimeWeather o) {
        int aqi = o.getData().getResult().getAqi();
        mRealNumTemp.setText(String.valueOf(Math.round(o.getData().getResult().getTemperature())));
        mAqi.setText("空气" + WeatherHelper.switchPM25(aqi) + " " + aqi);
        mSkycon.setText(WeatherHelper.switchSkycon(o.getData().getResult().getSkycon()));
        initViewLeftDrawable(mAqi,R.drawable.weather_aqi,13,13);
        mActivityBg.setBackgroundResource(WeatherHelper.checkBg(o.getData().getResult().getSkycon()));
        mWindVaneIv.setImageResource(WeatherHelper.switchWindDirIcon(o.getData().getResult().getWind().getDirection()));

        WeatherEveryDayBean otherData1 = new WeatherEveryDayBean(
                WeatherHelper.switchWindSpeed(o.getData().getResult().getWind().getSpeed()),
                WeatherHelper.switchWindDir(o.getData().getResult().getWind().getDirection()));
        WeatherEveryDayBean otherData2 = new WeatherEveryDayBean(Math.round(o.getData().getResult().getHumidity()*100) + "％", "湿度");
        WeatherEveryDayBean otherData3 = new WeatherEveryDayBean(Math.round(o.getData().getResult().getApparent_temperature())+"°","体感");
        WeatherEveryDayBean otherData4 = new WeatherEveryDayBean(Math.round(o.getData().getResult().getPres()/100)+"hPa", "气压");
        otherDatas.clear();
        otherDatas.add(otherData1);
        otherDatas.add(otherData2);
        otherDatas.add(otherData3);
        otherDatas.add(otherData4);
        otherDataAdapter.notifyDataSetChanged();

        initViewLeftDrawable(mAirQualityNum,R.drawable.weather_aqi,20,20);
        initViewLeftDrawable(mRainfallRateNum,R.drawable.weather_distance,18,18);
        mAirQualityNum.setText(aqi+"");
        mAirQualityTv.setText("空气" + WeatherHelper.switchPM25(aqi));
        try {
            if (o.getData().getResult().getPrecipitation().getNearest().getDistance() > 1000){
                mRainfallRateNum.setText("周边无雨");
            }else {
                mRainfallRateNum.setText(o.getData().getResult().getPrecipitation().getNearest().getDistance()+"km");
            }
        } catch (Exception e) {
            mRainfallRateNum.setText("周边无雨");
            e.printStackTrace();
        }
    }

    /**
     * 配置预报天气数据
     *
     * @param o
     */
    private void setForcastWeather(ResponseForcastWeather o) {
        mWeatherWarnTv.setText(o.getData().getResult().getForecast_keypoint());
        mSunupTimeTv.setText(o.getData().getResult().getDaily().getAstro().get(0).getSunrise().getTime());
        mSunsetTimeTv.setText(o.getData().getResult().getDaily().getAstro().get(0).getSunset().getTime());
        initViewLeftDrawable(mSunsetTimeTv,R.drawable.weather_sunset,15,15);

        WeatherEveryDayBean everyDayBean1 = new WeatherEveryDayBean("紫外线" + o.getData().getResult().getDaily().getUltraviolet().get(0).getDesc(), R.drawable.weather_ultraviolet);
        WeatherEveryDayBean everyDayBean2 = new WeatherEveryDayBean(o.getData().getResult().getDaily().getCarWashing().get(0).getDesc(), R.drawable.weather_carwashing);
        WeatherEveryDayBean everyDayBean3 = new WeatherEveryDayBean(WeatherHelper.switchDress(o.getData().getResult().getDaily().getDressing().get(0).getIndex()), R.drawable.weather_dressing);
        WeatherEveryDayBean everyDayBean4 = new WeatherEveryDayBean("流感" + o.getData().getResult().getDaily().getColdRisk().get(0).getDesc(), R.drawable.weather_cold_risk);
        WeatherEveryDayBean everyDayBean5 = new WeatherEveryDayBean(WeatherHelper.switchUmbrella(o.getData().getResult().getDaily().getSkycon().get(0).getValue()), R.drawable.weather_umbrella);
        everyDayBeans.clear();
        everyDayBeans.add(everyDayBean1);
        everyDayBeans.add(everyDayBean2);
        everyDayBeans.add(everyDayBean3);
        everyDayBeans.add(everyDayBean4);
        everyDayBeans.add(everyDayBean5);
        everyDayAdapter.notifyDataSetChanged();
    }

    @SuppressLint("CheckResult")
    private void requestWeather(String lng, String lat) {
        hourlyBeans.clear();
        daysBeans.clear();
        mPresenter.getWeatherRealTime(HomePageContract.GET_WEATHER_REAL_TIME, lng, lat);
        mPresenter.getForcastWeather(HomePageContract.GET_FORCAST_WEATHER, lng, lat);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == SelectLocationForWeatherActivity.SELECT_LOCATION && resultCode == SelectLocationForWeatherActivity.RESULT_OK) {
            String lat = data.getStringExtra("lat");
            String lng = data.getStringExtra("lng");
            String privince = data.getStringExtra("privince");
            String city = data.getStringExtra("city");
            String town = data.getStringExtra("town");
            if (StringTools.isStringValueOk(town)){
                mWeatherPlace.setText(String.format("%s %s", city,town));
            }else {
                mWeatherPlace.setText(String.format("%s %s", privince,city));
            }
            requestWeather(lng, lat);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void initData() {
        //        Intent intent = getIntent();

    }

    @Override
    protected HomePagePresent createPresenter() {
        return new HomePagePresent();
    }

    //    @Override
    //    protected void onCreate(Bundle savedInstanceState) {
    //        super.onCreate(savedInstanceState);
    //        overridePendingTransition(R.anim.in_from_bottom, R.anim.no_slide);
    //    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.weather_place:
                startActivityForResult(new Intent(mContext, SelectLocationForWeatherActivity.class).putExtra("city", city), SelectLocationForWeatherActivity.SELECT_LOCATION);
                break;
            default:
                break;
            case R.id.back_btn:
                onBackPressed();
                break;
            case R.id.place_choose_btn:
                break;
        }
    }

    @Override
    public void onSuccess(String tag, Object o) {
        switch (tag) {
            case HomePageContract.GET_WEATHER_REAL_TIME:
                setRealTimeWeather((ResponseRealTimeWeather) o);
                break;
            case HomePageContract.GET_FORCAST_WEATHER:
                ResponseForcastWeather forcastWeather = (ResponseForcastWeather) o;
                for (int i = 0; i < forcastWeather.getData().getResult().getHourly().getSkycon().size(); i++) {
                    String time = forcastWeather.getData().getResult().getHourly().getSkycon().get(i).getDatetime();
                    String skycon = forcastWeather.getData().getResult().getHourly().getSkycon().get(i).getValue();
                    String temp = String.valueOf(Math.round(forcastWeather.getData().getResult().getHourly().getTemperature().get(i).getValue()));
                    String aqi = WeatherHelper.switchPM25((int) forcastWeather.getData().getResult().getHourly().getAqi().get(i).getValue());
                    String windSpeed = WeatherHelper.switchWindSpeed(forcastWeather.getData().getResult().getHourly().getWind().get(i).getSpeed());
                    double windDirection = forcastWeather.getData().getResult().getHourly().getWind().get(i).getDirection();
                    WeatherHoursBean hoursBean = new WeatherHoursBean(time, skycon, temp, aqi, windSpeed, windDirection);
                    hourlyBeans.add(hoursBean);
                }
                for (int j = 0; j < forcastWeather.getData().getResult().getDaily().getSkycon().size(); j++) {
                    String date = forcastWeather.getData().getResult().getDaily().getSkycon().get(j).getDate();
                    String skycon = forcastWeather.getData().getResult().getDaily().getSkycon().get(j).getValue();
                    String minTemp = String.valueOf(Math.round(forcastWeather.getData().getResult().getDaily().getTemperature().get(j).getMin()));
                    String maxTemp = String.valueOf(Math.round(forcastWeather.getData().getResult().getDaily().getTemperature().get(j).getMax()));
                    String aqi = WeatherHelper.switchPM25((int)(forcastWeather.getData().getResult().getDaily().getAqi().get(j).getAvg()));
                    WeatherDaysBean daysBean = new WeatherDaysBean(date, skycon, minTemp, maxTemp,aqi);
                    daysBeans.add(daysBean);
                }
                hoursAdapter.notifyDataSetChanged();
                daysAdapter.notifyDataSetChanged();
                setForcastWeather(forcastWeather);
                break;
            default:
                break;
        }
    }

    @Override
    public void onError(String tag, Object o) {
        ToastUtils.toast(this, (String) o);
    }

    //    @Override
    //    public void onBackPressed() {
    //        super.onBackPressed();
    //        overridePendingTransition(R.anim.out_to_bottom, R.anim.no_slide);
    //    }

}
