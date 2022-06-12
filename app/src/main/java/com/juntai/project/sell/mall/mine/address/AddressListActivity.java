package com.juntai.project.sell.mall.mine.address;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.bean.address.AddressListBean;
import com.juntai.disabled.basecomponent.utils.HawkProperty;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.project.sell.mall.AppHttpPathMall;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseRecyclerviewActivity;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

/**
 * @aouther tobato
 * @description 描述  收货地址列表
 * @date 2022/5/9 15:55
 */
public class AddressListActivity extends BaseRecyclerviewActivity<AddrPresent> implements View.OnClickListener, HomePageContract.IHomePageView {
    /**
     * 新增收货地址
     */
    private TextView mAddReceiveAddrTv;
    private int type;

    @Override
    public int getLayoutView() {
        return R.layout.activity_address_list;
    }

    @Override
    public void initView() {
        super.initView();
        type = getIntent().getIntExtra(BASE_ID,0);
        setTitleName("我的收货地址");
        baseQuickAdapter.setEmptyView(getAdapterEmptyView("一个收货地址都没有,快去添加吧~",-1));

        baseQuickAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                AddressListBean.DataBean dataBean = (AddressListBean.DataBean) adapter.getItem(position);

                switch (view.getId()) {
                    case R.id.item_address_edit:
                        // : 2022/5/8 编辑地址
                        startToAddAddress(dataBean);
                        break;
                    case R.id.delete_addr_tv:
                        // 删除地址
                        deleteAddr(dataBean);

                        break;
                    case R.id.default_addr_cb:
                        CheckBox defaultAddrCb = (CheckBox) adapter.getViewByPosition(mRecyclerview, position, R.id.default_addr_cb);
                        if (defaultAddrCb.isChecked()) {
                            // : 2022/5/8 设为默认地址
                            mPresenter.setDefaultAdddr(getBaseBuilder().add("id", String.valueOf(dataBean.getId())).build(), AppHttpPathMall.SET_DEFAULT_ADDR);
                        } else {
                            getRvAdapterData();
                        }
                        break;
                    default:
                        break;
                }
            }
        });
        mAddReceiveAddrTv = (TextView) findViewById(R.id.add_receive_addr_tv);
        mAddReceiveAddrTv.setOnClickListener(this);
        baseQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                if (1==type) {
//                    AddressListBean.DataBean dataBean = (AddressListBean.DataBean) adapter.getItem(position);
//                    setResult(BASE_RSULT, new Intent(mContext, ConfirmOrderActivity.class).putExtra(BASE_PARCELABLE,dataBean));
//                    finish();
//                }

            }
        });
    }

    @Override
    protected View getAdapterHeadView() {
        return null;
    }

    @Override
    protected View getAdapterFootView() {
        return null;
    }

    /**
     * 删除收货地址
     *
     * @param dataBean
     */
    private void deleteAddr(AddressListBean.DataBean dataBean) {
        showAlertDialog("确定删除当前地址吗？", "确定", "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                List<Integer> ids = new ArrayList<>();
                ids.add(dataBean.getId());
                mPresenter.deleteAddr(ids, AppHttpPathMall.DELETE_ADDR);
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
    }


    @Override
    protected LinearLayoutManager getBaseAdapterManager() {
        return null;
    }

    @Override
    protected void getRvAdapterData() {
        mPresenter.getAddrList(getBaseBuilder().build(), AppHttpPathMall.ADDR_LIST);
    }

    @Override
    protected boolean enableRefresh() {
        return true;
    }

    @Override
    protected boolean enableLoadMore() {
        return false;
    }

    @Override
    protected BaseQuickAdapter getBaseQuickAdapter() {
        return new AddressListAdapter(R.layout.item_address);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == BASE_REQUEST_RESULT) {
            getRvAdapterData();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.add_receive_addr_tv:
                startToAddAddress(null);
                break;
        }
    }



    @Override
    protected AddrPresent createPresenter() {
        return new AddrPresent();
    }

    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);

        switch (tag) {
            case AppHttpPathMall.ADDR_LIST:
                AddressListBean addressListBean = (AddressListBean) o;
                if (addressListBean != null) {
                    List<AddressListBean.DataBean> arrays = addressListBean.getData();
                    if (arrays != null) {
                        for (AddressListBean.DataBean array : arrays) {
                            if (1== array.getDefaultAddress()) {
                                //默认地址
                                Hawk.put(HawkProperty.DEFAULT_ADDR,array);
                            }
                        }
                    }
                    baseQuickAdapter.setNewData(arrays);
                }

                break;

            case AppHttpPathMall.DELETE_ADDR:
                ToastUtils.toast(mContext, "已删除");
                getRvAdapterData();
                break;
            case AppHttpPathMall.SET_DEFAULT_ADDR:
                getRvAdapterData();
                break;
            default:
                break;
        }
    }
}
