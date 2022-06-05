package com.juntai.project.sell.mall.home.map.weather;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.juntai.disabled.basecomponent.bean.weather.CityBean;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseAppActivity;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.juntai.project.sell.mall.home.HomePagePresent;

import java.util.List;

import static com.baidu.mapapi.BMapManager.getContext;

/**
 * @aouther tobato
 * @description 描述  选择地址
 * @date 2020/4/10 11:14
 */
public class SelectLocationForWeatherActivity extends BaseAppActivity<HomePagePresent> implements
        HomePageContract.IHomePageView, AdapterView.OnItemSelectedListener {
    Spinner first, second, third, four;
    TextView dangqiandiqu;
    Button confirm;
    ArrayAdapter firstAdapter;
    ArrayAdapter secondAdapter;
    ArrayAdapter thirdAdapter;
    ArrayAdapter fourAdapter;
    public static final int SELECT_LOCATION = 9887;
    public static final int RESULT_OK = 9790;
    private boolean hasTown = false;//是否有城镇
    private boolean hasStreet = false;//是否有街道

    int type;//0天气用（三级），1其他（四级）

    private List<CityBean.DataBean> allPrivince, allCitys, allTowns, allStreets;

    @Override
    public int getLayoutView() {
        return R.layout.activity_select_location2;
    }

    @Override
    public void initView() {
        setTitleName("");
        getTitleRightTv().setText("确定");
        type = getIntent().getIntExtra("type",0);
        getTitleRightTv().setOnClickListener(v -> {
            confirmAddress();
        });
        first = findViewById(R.id.firstspinner);
        second = findViewById(R.id.secondspinner);
        third = findViewById(R.id.thirdspinner);
        four = findViewById(R.id.fourspinner);
        dangqiandiqu = findViewById(R.id.dangqiandiqu);
        confirm = findViewById(R.id.confirm);
        confirm.setVisibility(View.GONE);
        /*---------------------------------------------------------------------------------------------*/
        Intent intent = getIntent();
        String place = intent.getStringExtra("city");
//        if (!StringTools.isStringValueOk(place) && LocateAndUpload.bdLocation != null){
//            place = LocateAndUpload.bdLocation.getCity();
//        }
        dangqiandiqu.setText("[" + place + "]");
        /*---------------------------------------------------------------------------------------------*/

        mPresenter.getPrivince(HomePageContract.GET_PRIVINCE);
    }

    @Override
    public boolean requestLocation() {
        return true;
    }

    @Override
    public void onLocationReceived(BDLocation bdLocation) {
        if (bdLocation != null) {

        }
    }

    /**
     * 提交选择地址
     */
    private void confirmAddress() {
        CityBean.DataBean dataBeanPrivince = getSelectedBean(first, allPrivince);
        CityBean.DataBean dataBeanCity = getSelectedBean(second, allCitys);
        CityBean.DataBean dataBeanTown = null;
        CityBean.DataBean dataBeanStreet = null;
        if (hasTown){
            dataBeanTown = getSelectedBean(third, allTowns);
        }
        if (hasStreet){
            dataBeanStreet = getSelectedBean(four, allStreets);
        }
        if (type == 1){//添加场所
            setResult(RESULT_OK, new Intent().putExtra("privince", dataBeanPrivince)
                    .putExtra("city", dataBeanCity)
                    .putExtra("town", dataBeanTown)
                    .putExtra("street",dataBeanStreet));
        }else {
            if (hasTown) {
                setResult(RESULT_OK, new Intent().putExtra("lat", String.valueOf(dataBeanTown.getLatitude()))
                        .putExtra("lng", String.valueOf(dataBeanTown.getLongitude()))
                        .putExtra("privince", dataBeanPrivince.getName())
                        .putExtra("city", dataBeanCity.getName())
                        .putExtra("town", dataBeanTown.getName()));
            } else {
                setResult(RESULT_OK, new Intent().putExtra("lat", String.valueOf(dataBeanCity.getLatitude()))
                        .putExtra("lng", String.valueOf(dataBeanCity.getLongitude()))
                        .putExtra("privince", dataBeanPrivince.getName())
                        .putExtra("city", dataBeanCity.getName()));
            }
        }
        onBackPressed();
    }
    @Override
    public void initData() {}

    @Override
    protected HomePagePresent createPresenter() {
        return new HomePagePresent();
    }

    /**
     * 获取当前选中得实体
     *
     * @param spinner
     * @param arrays
     * @return
     */
    private CityBean.DataBean getSelectedBean(Spinner spinner, List<CityBean.DataBean> arrays) {
        CityBean.DataBean cityBean = null;
        String selectedProvince = spinner.getSelectedItem().toString();

        for (CityBean.DataBean dataBean : arrays) {
            String cityName = dataBean.getName();
            if (selectedProvince.equals(cityName)) {
                cityBean = dataBean;
            }
        }
        return cityBean;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.firstspinner) {
            mPresenter.getCitys(HomePageContract.GET_CITY, getSelectedBean(first, allPrivince).getCityNum());
        } else if (parent.getId() == R.id.secondspinner) {
            mPresenter.getTowns(HomePageContract.GET_TOWN,getSelectedBean(second, allCitys).getCityNum());
        } else if (parent.getId() == R.id.thirdspinner){
            mPresenter.getStreets(HomePageContract.GET_STREET,getSelectedBean(third, allTowns).getCityNum());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    @Override
    public void onSuccess(String tag, Object o) {
        CityBean bean = (CityBean) o;
        switch (tag) {
            case HomePageContract.GET_PRIVINCE:
                allPrivince = bean.getData();
                String[] a = new String[bean.getData().size()];
                for (int i = 0; i < bean.getData().size(); i++) {
                    a[i] = bean.getData().get(i).getName();
                }
                firstAdapter = new ArrayAdapter<>(mContext, R.layout.item_spinner_text, a);
                first.setAdapter(firstAdapter);
                first.setOnItemSelectedListener(SelectLocationForWeatherActivity.this);
                break;

            case HomePageContract.GET_CITY:
                allCitys = bean.getData();
                String[] c = new String[bean.getData().size()];
                for (int i = 0; i < bean.getData().size(); i++) {
                    c[i] = bean.getData().get(i).getName();
                }
                secondAdapter = new ArrayAdapter<>(getContext(), R.layout.item_spinner_text, c);
                second.setAdapter(secondAdapter);
                second.setVisibility(View.VISIBLE);
                second.setOnItemSelectedListener(SelectLocationForWeatherActivity.this);
                break;
            case HomePageContract.GET_TOWN:
                allTowns = bean.getData();
                String[] t = new String[bean.getData().size()];
                for (int i = 0; i < bean.getData().size(); i++) {
                    t[i] = bean.getData().get(i).getName();
                }
                thirdAdapter = new ArrayAdapter<>(getContext(), R.layout.item_spinner_text, t);
                third.setAdapter(thirdAdapter);
                if (t.length == 0 || t[0].equals("无")) {
                    third.setVisibility(View.GONE);
                    four.setVisibility(View.GONE);
                    hasTown = false;
                } else {
                    hasTown = true;
                    third.setVisibility(View.VISIBLE);
                    third.setOnItemSelectedListener(this);
                }
                break;
            case HomePageContract.GET_STREET:
                allStreets = bean.getData();
                String[] d = new String[bean.getData().size()];
                for (int i = 0; i < bean.getData().size(); i++) {
                    d[i] = bean.getData().get(i).getName();
                }
                fourAdapter = new ArrayAdapter<>(getContext(), R.layout.item_spinner_text, d);
                four.setAdapter(fourAdapter);
                if (d.length == 0 || d[0].equals("无")) {
                    four.setVisibility(View.GONE);
                    hasStreet = false;
                } else {
                    hasStreet = true;
                    four.setVisibility(View.VISIBLE);
                }
                break;
            default:
                break;
        }
    }
}
