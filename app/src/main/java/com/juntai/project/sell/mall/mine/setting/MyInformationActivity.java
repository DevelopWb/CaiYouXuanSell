package com.juntai.project.sell.mall.mine.setting;


import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.bean.ContactBean;
import com.juntai.disabled.basecomponent.base.BaseActivity;
import com.juntai.disabled.basecomponent.bean.TextKeyValueBean;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.project.sell.mall.AppHttpPathMall;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseRecyclerviewActivity;
import com.juntai.project.sell.mall.mine.MyCenterContract;
import com.juntai.project.sell.mall.mine.MyCenterPresent;
import com.juntai.project.sell.mall.mine.modifyPwd.ModifyPwdActivity;
import com.juntai.project.sell.mall.mine.myinfo.BaseModifyActivity;
import com.juntai.project.sell.mall.mine.myinfo.HeadCropActivity;
import com.juntai.project.sell.mall.mine.myinfo.ModifyNickNameActivity;
import com.juntai.project.sell.mall.utils.UserInfoManagerMall;

import java.util.ArrayList;
import java.util.List;

/**
 * @aouther tobato
 * @description 描述  我的信息
 * @date 2021/6/1 16:36
 */
public class MyInformationActivity extends BaseRecyclerviewActivity<MyCenterPresent> implements MyCenterContract.ICenterView {

    private final String MY_INFO_NICK_NAME = "昵称";
    private final String MY_INFO_ACCOUNT = "电话";
    private final String MY_INFO_MODIFY_PWD = "密码修改";
    private ImageView imageView;
    private TextView nicknameTv;

    @Override
    protected MyCenterPresent createPresenter() {
        return new MyCenterPresent();
    }


    @Override
    protected LinearLayoutManager getBaseAdapterManager() {
        return null;
    }

    @Override
    protected void getRvAdapterData() {

    }

    @Override
    protected boolean enableRefresh() {
        return false;
    }

    @Override
    protected boolean enableLoadMore() {
        return false;
    }

    @Override
    protected BaseQuickAdapter getBaseQuickAdapter() {
        return new MyInfoAdapter(R.layout.item_key_value);
    }

    /**
     * 添加头部
     */
    public View getHeadView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.include_myinfo_head, null);
        LinearLayout headLayout = view.findViewById(R.id.myinfo_headLayout);
        imageView = view.findViewById(R.id.myinfo_headimage);
        nicknameTv = view.findViewById(R.id.myinfo_nickname);
        headLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choseImage(0, MyInformationActivity.this, 1);
            }
        });
        return view;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        initAdapterData();
    }

    @Override
    protected void onPicsAndEmpressed(List<String> icons) {
        super.onPicsAndEmpressed(icons);
        if (icons.size() > 0) {
            String picPath = icons.get(0);
            ImageLoadUtil.loadHeadCirclePic(getApplicationContext(), picPath,
                    imageView);
            //跳转到裁剪头像的界面
            mPresenter.uploadFile(AppHttpPathMall.UPLOAD_FILES,picPath);

        }
    }


    @Override
    protected View getAdapterHeadView() {
        return null;
    }

    @Override
    protected View getAdapterFootView() {
        return null;
    }

    @Override
    public void initData() {
        super.initData();
        setTitleName("我的信息");
        baseQuickAdapter.setHeaderView(getHeadView());
        initAdapterData();

        baseQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TextKeyValueBean textKeyValueBean = (TextKeyValueBean) adapter.getData().get(position);
                switch (textKeyValueBean.getKey()) {
                    case MY_INFO_NICK_NAME:
                        startActivityForResult(new Intent(mContext, ModifyNickNameActivity.class).putExtra(BaseModifyActivity.DEFAULT_HINT, UserInfoManagerMall.getUserNickName()), BASE_REQUEST_RESULT);
                        break;
//                    case MY_INFO_ACCOUNT:
//                        startActivityForResult(new Intent(mContext, ModifyAccountActivity.class)
//                                .putExtra(BaseModifyActivity.DEFAULT_HINT, UserInfoManagerMall.getPhoneNumber()), BASE_REQUEST_RESULT);
//                        break;
                    case MY_INFO_MODIFY_PWD:
                        // : 2022/5/6 修改密码
                        startActivity(new Intent(mContext, ModifyPwdActivity.class));
                        break;
//                    default:
//                        break;
                }
            }
        });


    }

    private void initAdapterData() {
        ContactBean userBean = UserInfoManagerMall.getUser();
        if (userBean != null) {
            List<TextKeyValueBean> beanList = new ArrayList<>();
            beanList.add(new TextKeyValueBean(MY_INFO_NICK_NAME, userBean.getNickname()));
            beanList.add(new TextKeyValueBean(MY_INFO_ACCOUNT, userBean.getPhoneNumber()));
            beanList.add(new TextKeyValueBean(MY_INFO_MODIFY_PWD, "\u3000"));
            baseQuickAdapter.setNewData(beanList);
            ImageLoadUtil.loadHeadCirclePic(mContext,userBean.getHeadPortrait(),imageView);

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == BASE_REQUEST_RESULT) {
            if (data != null) {
                String path = data.getStringExtra(HeadCropActivity.CROPED_HEAD_PIC);
                ImageLoadUtil.loadHeadCirclePic(getApplicationContext(), path,
                        imageView);
                //  调用上传图片的接口

               mPresenter.uploadFile(AppHttpPathMall.UPLOAD_FILES,path);
            }
        } else {
            initAdapterData();
        }
    }

    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);
        switch (tag) {
            case AppHttpPathMall.UPLOAD_FILES:
                List<String> paths = (List<String>) o;
                if (paths!=null&&!paths.isEmpty()) {
                    // 调用修改头像的接口
                    mPresenter.modifyUserInfo(getBaseBuilder().add("headPortrait",paths.get(0)).build(),AppHttpPathMall.MODIFY_USER_INFO);
                }

                break;
            case AppHttpPathMall.MODIFY_USER_INFO:
                ToastUtils.toast(mContext, "更改成功");
                break;
            default:
                break;
        }

    }

    @Override
    public void onBackPressed() {
        setResult(BaseActivity.BASE_REQUEST_RESULT);
        finish();

    }
}
