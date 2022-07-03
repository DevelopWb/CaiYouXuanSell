package com.juntai.project.sell.mall.mine;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.utils.MultipleItem;
import com.juntai.disabled.basecomponent.base.BaseActivity;
import com.juntai.disabled.basecomponent.bean.MyMenuBean;
import com.juntai.disabled.basecomponent.utils.DialogUtil;
import com.juntai.disabled.basecomponent.utils.HawkProperty;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.project.sell.mall.AppHttpPathMall;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseAppFragment;
import com.juntai.project.sell.mall.beans.UserBeanMall;
import com.juntai.project.sell.mall.mine.guide.NewHandGuideActivity;
import com.juntai.project.sell.mall.mine.modifyPwd.ModifyPwdActivity;
import com.juntai.project.sell.mall.mine.verified.VerifiedActivity;
import com.juntai.project.sell.mall.utils.UserInfoManagerMall;
import com.orhanobut.hawk.Hawk;

/**
 * @aouther tobato
 * @description 描述
 * @date 2021/4/17 16:12
 */
public class MyCenterFragment extends BaseAppFragment<MyCenterPresent> implements MyCenterContract.ICenterView, View.OnClickListener {

    MyMenuAdapter myMenuAdapter;

    private TextView mStatusTopTitle;
    private ImageView mHeadImage;
    private TextView mNickname;
    /**
     * 18763739973
     */
    private TextView mInfoDesTv;
    private RecyclerView mMenuRecycler;
    /**
     * 退出账号
     */
    private TextView mLoginOut;
    private AlertDialog dialog;
    private String headUrl = "";
    private ConstraintLayout mBaseInfoCl;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_my_center;
    }

    @Override
    protected void initView() {
        mStatusTopTitle = getView(R.id.status_top_title);
        mHeadImage = getView(R.id.headImage);
        mBaseInfoCl = getView(R.id.head_cl);
        mBaseInfoCl.setOnClickListener(this);
        mNickname = getView(R.id.nickname);
        mInfoDesTv = getView(R.id.info_des_tv);

        mMenuRecycler = getView(R.id.menu_recycler);
        mLoginOut = getView(R.id.login_out);
        mLoginOut.setOnClickListener(this);
        myMenuAdapter = new MyMenuAdapter(mPresenter.getMenuBeans());
        getBaseActivity().initRecyclerview(mMenuRecycler, myMenuAdapter, LinearLayoutManager.VERTICAL);
        mStatusTopTitle.setText("个人中心");

        myMenuAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MultipleItem multipleItem = (MultipleItem) adapter.getData().get(position);
                switch (multipleItem.getItemType()) {
                    case MultipleItem.ITEM_MENUS:
                        MyMenuBean myMenuBean = (MyMenuBean) multipleItem.getObject();
                        switch (myMenuBean.getName()) {
                            case MyCenterContract.MENU_MODIFY_PHONE:
                                // : 2022/6/12 修改手机号

                                startActivity(new Intent(mContext, ModifyPhoneActivity.class));

                                break;
                            case MyCenterContract.MENU_MODIFY_PWD:
                                startActivity(new Intent(mContext, ModifyPwdActivity.class));
                                break;
                            case MyCenterContract.MENU_MODIFY_AUTH:
                                // : 2022/6/6 实名认证
                                startActivity(new Intent(mContext, VerifiedActivity.class).putExtra(VerifiedActivity.VERIFIED_STATUS, UserInfoManagerMall.getRealNameStatus()));
                                break;
//                            case MyCenterContract.MENU_MODIFY_SUGGESTION:
//                                // : 2022/6/12 投诉建议
//                                break;
                            case MyCenterContract.MENU_MODIFY_BIND:
                                // TODO: 2022/6/12 绑定第三方
                                break;
                            case MyCenterContract.MENU_MODIFY_GUIDE:
                                // : 2022/6/12 新手教程
                                startActivity(new Intent(mContext, NewHandGuideActivity.class));

                                break;
                            case MyCenterContract.MENU_NEWS:
                                //我的消息
//                                startActivity(new Intent(mContext, MyMessageActivity.class));
                                break;
                            default:
                                break;
                        }
                        break;

                    default:
                        break;
                }
            }
        });
    }

    @Override
    protected void initData() {
        mHeadImage.setImageResource(R.mipmap.default_user_head_icon);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (UserInfoManagerMall.isLogin()) {
            mLoginOut.setVisibility(View.VISIBLE);
            mPresenter.getUserInfo(getBaseAppActivity().getBaseBuilder().build(), AppHttpPathMall.GET_USER_INFO);
        } else {
            mLoginOut.setVisibility(View.GONE);
        }
    }

    @Override
    protected void lazyLoad() {
    }

    @Override
    protected MyCenterPresent createPresenter() {
        return new MyCenterPresent();
    }


    @Override
    public void onClick(View v) {
        if (!UserInfoManagerMall.isLogin()) {
            return;
        }
        switch (v.getId()) {
            case R.id.login_out:
                //退出登录
                DialogUtil.getMessageDialog(mContext, "是否退出登录", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        // : 2022/5/16 调用退出登录的接口
                        mPresenter.logout(getBaseAppActivity().getBaseBuilder().build(), AppHttpPathMall.LOGOUT);

                    }
                }).show();
                break;
            case R.id.head_cl:
                //基本信息


                break;

            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == BaseActivity.BASE_REQUEST_RESULT) {
            lazyLoad();
        }

    }

    @Override
    public void onSuccess(String tag, Object o) {
        switch (tag) {
            case AppHttpPathMall.LOGOUT:
                getBaseAppActivity().reLogin(UserInfoManagerMall.getPhoneNumber());

                break;
            case AppHttpPathMall.GET_USER_INFO:
                UserBeanMall loginBean = (UserBeanMall) o;
                if (loginBean != null) {
                    Hawk.put(HawkProperty.SP_KEY_USER, loginBean.getData());
                    ImageLoadUtil.loadHeadCirclePic(mContext, UserInfoManagerMall.getHeadPic(), mHeadImage);
                    mNickname.setText(UserInfoManagerMall.getUserNickName());
                    mInfoDesTv.setText(UserInfoManagerMall.getPhoneNumber());
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onError(String tag, Object o) {
        ToastUtils.error(mContext, String.valueOf(o));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
