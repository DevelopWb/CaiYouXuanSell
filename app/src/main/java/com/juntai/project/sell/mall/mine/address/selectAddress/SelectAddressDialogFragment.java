package com.juntai.project.sell.mall.mine.address.selectAddress;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.github.promeg.pinyinhelper.Pinyin;
import com.juntai.disabled.basecomponent.base.BaseBottomSheetFragment;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.beans.CitysBean;
import com.juntai.project.sell.mall.mine.address.AddOrEditAddressActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 地址选择器
 *
 * @aouther tobato
 * @date 2019/12/10
 */
public class SelectAddressDialogFragment extends BaseBottomSheetFragment implements View.OnClickListener {
    RecyclerView mAllCitiesRv, mSelectedCitiesRv;
    SelectAddrAdapter selectorAdapter;
    List<CitysBean.DistrictsBean> list;
    private Context mContext;
    private SelectedAddrAdapter mSelectedAdapter;
    private NestedScrollView nestedScrollView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mContext = null;
    }

    public void setList(List<CitysBean.DistrictsBean> list) {
        this.list = list;
        if (selectorAdapter != null && list != null) {
            pinyin(list);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.address_selector_layout, null);

        initView(view);
        return view;
    }

    public void initView(View view) {
        view.findViewById(R.id.selector_close).setOnClickListener(this);
        mAllCitiesRv = view.findViewById(R.id.all_cities_rv);
        nestedScrollView = view.findViewById(R.id.addr_vv);
        mAllCitiesRv.setLayoutManager(new LinearLayoutManager(mContext));
        selectorAdapter = new SelectAddrAdapter(R.layout.item_address_selector, new ArrayList());
        mAllCitiesRv.setAdapter(selectorAdapter);

        mSelectedCitiesRv = view.findViewById(R.id.selected_cities_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mSelectedCitiesRv.setLayoutManager(linearLayoutManager);
        mSelectedAdapter = new SelectedAddrAdapter(R.layout.item_selected_cities);
        mSelectedCitiesRv.setAdapter(mSelectedAdapter);
        pinyin(list);
        selectorAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (!getAddOrEditAddressActivity().canClick) {
                    return;
                }else {
                    getAddOrEditAddressActivity().canClick = false;
                }
                nestedScrollView.scrollTo(0, 0);
                CitysBean.DistrictsBean cityBean = (CitysBean.DistrictsBean) adapter.getItem(position);

                List<CitysBean.DistrictsBean> citys = mSelectedAdapter.getData();
                for (CitysBean.DistrictsBean city : citys) {
                    if (city.getAdcode().equals(cityBean.getAdcode()) && city.getName().equals(cityBean.getName())) {
                        return;
                    }
                    city.setSelected(false);
                }
                cityBean.setSelected(true);
                mSelectedAdapter.addData(cityBean);
                mSelectedAdapter.notifyDataSetChanged();
                if (!cityBean.getDistricts().isEmpty()) {
                    getAddOrEditAddressActivity().getAllCitys(cityBean.getAdcode());
                } else {
                    //没有下级城镇了
                    comfirmAddr();
                    getAddOrEditAddressActivity().canClick = true;
                }
            }
        });
        mSelectedAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CitysBean.DistrictsBean cityBean = (CitysBean.DistrictsBean) adapter.getItem(position);
                List<CitysBean.DistrictsBean> citys = adapter.getData();
                List<CitysBean.DistrictsBean> aa = new ArrayList<>();
                for (int i = 0; i < citys.size(); i++) {
                    if (i < position + 1) {
                        aa.add(citys.get(i));
                    }
                }
                adapter.setNewData(aa);
                if (!cityBean.getDistricts().isEmpty()) {
                    getAddOrEditAddressActivity().getAllCitys(cityBean.getAdcode());
                } else {
                    comfirmAddr();
                }
            }
        });

    }

    private AddOrEditAddressActivity getAddOrEditAddressActivity() {
        return (AddOrEditAddressActivity) Objects.requireNonNull(getActivity());
    }

    @Override
    protected LinearLayoutManager getBaseAdapterManager() {
        return null;
    }

    @Override
    protected BaseQuickAdapter getBaseQuickAdapter() {
        return null;
    }

    @Override
    protected int getBottomSheetDialogLayout() {
        return 0;
    }

    private void comfirmAddr() {
        //没有下级城镇了
        List<CitysBean.DistrictsBean> citys = mSelectedAdapter.getData();
        StringBuffer sb = new StringBuffer();
        for (CitysBean.DistrictsBean city : citys) {
            sb.append(city.getName() + "\u3000");
        }
        getAddOrEditAddressActivity().setAddrDes(sb.toString());
        dismiss();
    }


    /**
     * 获取首字母-并排序
     *
     * @param list
     */
    private void pinyin(List<CitysBean.DistrictsBean> list) {
        if (list == null) {
            return;
        }
        for (CitysBean.DistrictsBean bean : list) {
            bean.setPinYin((Pinyin.toPinyin(bean.getName().charAt(0))));
            bean.setPinYin(bean.getPinYin().substring(0, 1));
        }
        //字母排序
        Collections.sort(list, (o1, o2) -> (o1.getPinYin().compareTo(o2.getPinYin())));
        selectorAdapter.setNewData(list);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.selector_close:
                dismiss();
                break;
            default:
                break;
        }
    }
}
