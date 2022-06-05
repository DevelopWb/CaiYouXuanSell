package com.juntai.project.sell.mall.mine.address;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.juntai.disabled.basecomponent.bean.address.AddressListBean;
import com.juntai.disabled.basecomponent.utils.HawkProperty;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.project.sell.mall.AppHttpPathMall;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseAppActivity;
import com.juntai.project.sell.mall.beans.CitysBean;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.juntai.project.sell.mall.mine.address.selectAddress.SelectAddressDialogFragment;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.FormBody;


/**
 * @aouther tobato
 * @description 描述  添加或者编辑地址
 * @date 2022/5/9 15:55
 */
public class AddOrEditAddressActivity extends BaseAppActivity<AddrPresent> implements View.OnClickListener, HomePageContract.IHomePageView {
    ImageView imageView;
    TextInputEditText editTextName, editTextPhone, editeTextPlace;
    TextView addressTv;
    SelectAddressDialogFragment mSelectAddrDialogFragment = new SelectAddressDialogFragment();
    private SwitchCompat mDefaultAddrSc;
    private AddressListBean.DataBean dataBean;
    
    private String addrDes;
    /**
     * 是否可点击
     */
    public boolean canClick = true;

    @Override
    public int getLayoutView() {
        return R.layout.activity_address;
    }

    @Override
    public void initView() {
        dataBean = getIntent().getParcelableExtra(BASE_PARCELABLE);
        imageView = findViewById(R.id.address_add_save_contact);
        editTextName = findViewById(R.id.address_add_name);
        editTextPhone = findViewById(R.id.address_add_phone);
        editeTextPlace = findViewById(R.id.address_add_place);
        addressTv = findViewById(R.id.address_add_select);
        mDefaultAddrSc = findViewById(R.id.default_addr_sc);
        imageView.setOnClickListener(this);
        addressTv.setOnClickListener(this);
        findViewById(R.id.address_add_save).setOnClickListener(this);
        TextView tvDelete = getTitleRightTv();
        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // : 2022/5/9 删除收货地址
                deleteAddr(dataBean);
            }
        });
        if (dataBean == null) {
            setTitleName("添加收货地址");
            tvDelete.setVisibility(View.GONE);
        } else {
            setTitleName("编辑收货地址");
            tvDelete.setText("删除");
            tvDelete.setVisibility(View.VISIBLE);
            editeTextPlace.setText(dataBean.getDetailedAddress());
            editTextName.setText(dataBean.getName());
            editTextPhone.setText(dataBean.getPhone());
            setAddrDes(dataBean.getCityName());
            mDefaultAddrSc.setChecked(dataBean.getDefaultAddress()==1);

        }
    }

    /**
     * 删除收货地址
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
    public void initData() {
        getAllCitys("100000");
    }

    public void getAllCitys(String keyword) {
        mPresenter.getAllCitys(keyword, AppHttpPathMall.ALL_CITYS);
    }

    public void setAddrDes(String addrDes) {
        this.addrDes = addrDes;
        addressTv.setText(addrDes);
    }

    private void toSave() {
        if (editTextName.getText().toString().isEmpty()) {
            ToastUtils.toast(mContext, "请输入收货人姓名");
        } else if (editTextPhone.getText().toString().isEmpty()) {
            ToastUtils.toast(mContext, "请输入收货人联系方式");
        } else if (addressTv.getText().toString().isEmpty()) {
            ToastUtils.toast(mContext, "请选择地址");
        } else if (editeTextPlace.getText().toString().isEmpty()) {
            ToastUtils.toast(mContext, "请填写详细地址");
        } else {
            // : 2022/4/27 这个地方有些字段需要更改
            FormBody.Builder builder = getBaseBuilder()
                    .add("name", getTextViewValue(editTextName))
                    .add("phone", getTextViewValue(editTextPhone))
                    .add("cityName", addrDes)
                    .add("detailedAddress", getTextViewValue(editeTextPlace))
                    .add("defaultAddress", mDefaultAddrSc.isChecked() ? "1" : "0");
            if (dataBean != null) {
                builder.add("id", String.valueOf(dataBean.getId()));
            }
            mPresenter.addOrEditAddrList(builder.build(), AppHttpPathMall.ADD_OR_EDIT_ADDR);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.address_add_save_contact://从通讯录选择收货人
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(intent, BASE_REQUEST_RESULT);
                break;
            case R.id.address_add_select://选择收货地址
                mSelectAddrDialogFragment.show(getSupportFragmentManager(), "selector");
                mSelectAddrDialogFragment.setList(Hawk.get(HawkProperty.getAllProvinceKey("100000")));
                break;
            case R.id.address_add_save://添加收货地址
                toSave();
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == BASE_REQUEST_RESULT) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    Uri uri = data.getData();
                    String[] contact = getPhoneContacts(uri);
                    if (contact != null) {
                        String name = contact[0];//姓名
                        String number = contact[1];//手机号
                        String number2 = formatPhoneNum(number);//手机号

                        editTextName.setText(name);
                        editTextPhone.setText(number2);
                    }
                }
            }
        }
    }

    /**
     * 读取联系人信息
     *
     * @param uri
     */
    private String[] getPhoneContacts(Uri uri) {
        String[] contact = new String[2];
        //得到ContentResolver对象
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(uri, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            //取得联系人姓名
            int nameFieldColumnIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            contact[0] = cursor.getString(nameFieldColumnIndex);
            contact[1] = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Nickname.NAME));
            Log.e("fffffff", "contacts" + contact[0]);
            Log.e("ffffffff", "contactsUsername=" + contact[1]);
            cursor.close();
        } else {
            return null;
        }
        return contact;
    }

    /**
     * 去掉手机号内除数字外的所有字符
     *
     * @param phoneNum 手机号
     * @return
     */
    private String formatPhoneNum(String phoneNum) {
        String regex = "(\\+86)|[^0-9]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNum);
        return matcher.replaceAll("");
    }

    @Override
    protected AddrPresent createPresenter() {
        return new AddrPresent();
    }

    @Override
    public void onSuccess(String tag, Object o) {
        switch (tag) {
            case AppHttpPathMall.ALL_CITYS:
                CitysBean citysBean = (CitysBean) o;
                canClick = true;
                if (citysBean != null) {
                    CitysBean.DistrictsBean presentCity = citysBean.getDistricts().get(0);
                    List<CitysBean.DistrictsBean> citys = presentCity.getDistricts();
                    if ("100000".equals(presentCity.getAdcode())) {
                        Hawk.put(HawkProperty.getAllProvinceKey(presentCity.getAdcode()), citys);
                    }
                    mSelectAddrDialogFragment.setList(citys);
                }
                break;

            case AppHttpPathMall.ADD_OR_EDIT_ADDR:
                if (dataBean != null) {
                    ToastUtils.toast(mContext, "已修改");
                } else {
                    ToastUtils.toast(mContext, "添加成功");
                }
                finish();
                break;
            case AppHttpPathMall.DELETE_ADDR:
                ToastUtils.toast(mContext, "已删除");
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSelectAddrDialogFragment = null;
    }
}
