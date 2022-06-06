package com.juntai.project.sell.mall.mine;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.chat.util.MultipleItem;
import com.juntai.disabled.basecomponent.base.BaseActivity;
import com.juntai.disabled.basecomponent.base.BaseResult;
import com.juntai.disabled.basecomponent.bean.MyMenuBean;
import com.juntai.disabled.basecomponent.utils.DialogUtil;
import com.juntai.disabled.basecomponent.utils.FileCacheUtils;
import com.juntai.disabled.basecomponent.utils.HawkProperty;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.disabled.basecomponent.utils.RxScheduler;
import com.juntai.disabled.basecomponent.utils.RxTask;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.basecomponent.utils.UrlFormatUtil;
import com.juntai.project.sell.mall.AppHttpPathMall;
import com.juntai.project.sell.mall.AppHttpPathMallMall;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseAppFragment;
import com.juntai.project.sell.mall.entrance.LoginActivity;
import com.juntai.project.sell.mall.mine.modifyPwd.ModifyPwdActivity;
import com.juntai.project.sell.mall.mine.setting.MyInformationActivity;
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
        mNickname.setAlpha(0.3f);
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
                        switch (myMenuBean.getTag()) {
                            case MyCenterContract.MODIFY_PWD:
                                startActivity(new Intent(mContext, ModifyPwdActivity.class));
                                break;

                            case MyCenterContract.SET_ABOUT_TAG:
                                // 关于我们
                                startActivity(new Intent(mContext, AboutActivity.class));
                                break;
                            case MyCenterContract.SET_CLEAR_TAG:
                                // 清理缓存
                                getBaseActivity().setAlertDialogHeightWidth(DialogUtil.getMessageDialog(mContext, "将清理掉应用本地的图片和视频缓存文件",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                RxScheduler.doTask(getBaseAppActivity(), new RxTask<String>() {
                                                    @Override
                                                    public String doOnIoThread() {
                                                        FileCacheUtils.clearAll(mContext.getApplicationContext());
                                                        return "清理成功";
                                                    }

                                                    @Override
                                                    public void doOnUIThread(String s) {
                                                        ToastUtils.success(mContext.getApplicationContext(), s);
                                                    }
                                                });
                                            }
                                        }).show(), -1, 0);
                                break;
                            case MyCenterContract.SET_UPDATE_TAG:
                                //检查更新
                                getBaseAppActivity().update(true);
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
        mPresenter.getMyMsgUnread(getBaseBuilder().build(), AppHttpPathMall.MY_NEWS_UNREAD);
    }

    @Override
    protected void lazyLoad() {
        if (UserInfoManager.isLogin()) {
            //  获取用户基本信息的接口
            mPresenter.getUserInfo(getBaseAppActivity().getBaseBuilder().build(), AppHttpPathMall.USER_INFO);
        }
    }

    @Override
    protected MyCenterPresent createPresenter() {
        return new MyCenterPresent();
    }


    @Override
    public void onClick(View v) {
        if (!UserInfoManager.isLogin()) {
            return;
        }
        switch (v.getId()) {
            case R.id.login_out:
                //退出登录
                dialog = getBaseActivity().setAlertDialogHeightWidth(DialogUtil.getMessageDialog(mContext, "是否退出登录", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //  调用退出登录接口
                        mPresenter.logout(getBaseAppActivity().getBaseBuilder().build(), AppHttpPathMall.LOGOUT);

                    }
                }).show(), -1, 0);
                break;

            case R.id.head_cl:
                //基本信息

                if (UserInfoManager.getAccountStatus() == 1) {
                    ToastUtils.toast(mContext, "待审核,请耐心等待");
                } else if (UserInfoManager.getAccountStatus() == 2) {
                    //审核成功
                    //  跳转到用户详情页面
                    startActivityForResult(new Intent(mContext, MyInformationActivity.class), BaseActivity.BASE_REQUEST_RESULT);
                } else {
                    //跳转到补充资料的界面
                    startActivityForResult(new Intent(mContext, AddInformationActivity.class), BaseActivity.BASE_REQUEST_RESULT);
                }

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
                dialog.dismiss();
                ToastUtils.success(mContext, "退出成功");
                Hawk.delete(HawkProperty.LOGIN_KEY);
                Hawk.delete(HawkProperty.TOKEN_KEY);
                startActivity(new Intent(mContext, LoginActivity.class));

                break;
            case AppHttpPathMall.MY_NEWS_UNREAD:
                BaseResult result = (BaseResult) o;
                if (result != null) {
                    if (!TextUtils.isEmpty(result.message)) {
                        MultipleItem  multipleItem = myMenuAdapter.getData().get(1);
                        MyMenuBean  myMenuBean = (MyMenuBean) multipleItem.getObject();
                        myMenuBean.setNumber(Integer.parseInt(result.message));
                        myMenuAdapter.notifyItemChanged(1);
                    }
                }

                break;
            case AppHttpPathMall.USER_INFO:
                UserBean userBean = (UserBean) o;
                UserBean.DataBean dataBean = userBean.getData();
                if (dataBean != null) {
                    mNickname.setText(dataBean.getNickname());
                    mNickname.setAlpha(0.8f);
                    switch (dataBean.getState()) {
                        case 1:
                            mInfoDesTv.setTextColor(ContextCompat.getColor(mContext, R.color.textColorPrimary));
                            mInfoDesTv.setText("待审核");
                            break;
                        case 2:
                            mInfoDesTv.setTextColor(ContextCompat.getColor(mContext, R.color.black));
                            mInfoDesTv.setText(UserInfoManager.getUserDetailInfoWithoutName());
                            break;
                        default:
                            mInfoDesTv.setTextColor(ContextCompat.getColor(mContext, R.color.textColorPrimary));
                            mInfoDesTv.setText("待补充信息");
                            break;
                    }
                    if (!headUrl.equals(userBean.getData().getHeadPortrait())) {
                        headUrl = userBean.getData().getHeadPortrait();
                        ImageLoadUtil.loadCirImgNoCrash(mContext.getApplicationContext(),
                                UrlFormatUtil.getImageOriginalUrl(headUrl), mHeadImage,
                                R.mipmap.default_user_head_icon, R.mipmap.default_user_head_icon);
                    }
                    Hawk.put(HawkProperty.LOGIN_KEY, userBean);
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
