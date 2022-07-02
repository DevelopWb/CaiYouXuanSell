package com.juntai.project.sell.mall.home.live;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.base.BaseMvpFragment;
import com.juntai.disabled.basecomponent.bean.MoreMenuBean;
import com.juntai.disabled.basecomponent.mvp.BasePresenter;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.project.sell.mall.base.BaseMoreBottomDialog;

import java.util.HashMap;
import java.util.List;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;

/**
 * Describe: 直播更多菜单基类
 * Create by zhangzhenlong
 * 2021-4-2
 * email:954101549@qq.com
 */
public abstract class BaseLiveMoreMenuFragment<P extends BasePresenter> extends BaseMvpFragment<P> {
    protected BaseMoreBottomDialog moreBottomDialog;
    protected BaseMoreBottomDialog.OnItemClick onItemClick;

    /**
     * 初始化dialog
     */
    public void initBottomDialog(int liveId, String title, String shareUrl, String picPath, List<MoreMenuBean> moreMenuBeans) {
        if (shareUrl == null){
            return;
        }
        if (moreBottomDialog == null){
            moreBottomDialog = new BaseMoreBottomDialog(mContext);
            moreBottomDialog.setData(moreMenuBeans);
        }
//        if (moreBottomDialog == null) {
            onItemClick = new BaseMoreBottomDialog.OnItemClick() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    MoreMenuBean moreMenuBean = (MoreMenuBean) adapter.getItem(position);
//                    switch (moreMenuBean.getMenu_tag()){
//                        case BaseShareActivity.CREATE_POSTER:
//                            startActivity(new Intent(mContext, SharePosterActivity.class)
//                                    .putExtra(AppUtils.ID_KEY, liveId).putExtra(SharePosterActivity.POSTER_TYPE, 1));
//                            break;
//                        case BaseShareActivity.WECHAT://微信
//                            initShare(title, shareUrl, picPath, Wechat.NAME);
//                            break;
//                        case BaseShareActivity.WECHAT_MOMENTS://朋友圈
//                            initShare(title, shareUrl, picPath, WechatMoments.NAME);
//                            break;
//                        case BaseShareActivity.WECHAT_FAVORITE://微信收藏
//                            initShare(title, shareUrl, picPath, WechatFavorite.NAME);
//                            break;
//                        case BaseShareActivity.QQ_CHAT://qq
//                            initShare(title, shareUrl, picPath, QQ.NAME);
//                            break;
//                        case BaseShareActivity.QZONE://qq空间
//                            initShare(title, shareUrl, picPath, QZone.NAME);
//                            break;
//                        case BaseShareActivity.REPORT://举报
//                            if (UserInfoManager.getAccountStatus() == 0) {
//                                MyApp.goLogin();
//                                return;
//                            }
//                            startActivity(new Intent(mContext, ReportActivity.class)
//                                    .putExtra(ReportActivity.INFO_ID, liveId)
//                                    .putExtra(ReportActivity.TYPE_ID, 1));
//                            break;
//                        case BaseShareActivity.COPY_PATH://复制链接
//                            BaseAppUtils.copyContentToClipboard(shareUrl, mContext);
//                            break;
//                    }
                    moreBottomDialog.dismiss();
                }
            };
        moreBottomDialog.setOnBottomDialogCallBack(onItemClick);
        moreBottomDialog.show();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isVisibleToUser){
//            Jzvd.releaseAllVideos();
        }
    }

    /**
     * 分享初始化
     */
    public void initShare(String title, String shareUrl, String picPath, String platform) {
//        if (shareUrl != null) {
//            if (!StringTools.isStringValueOk(picPath)){
//                picPath = getString(R.string.logo_url);
//            }
//            ToolShare.shareForMob(mContext,
//                    title,
//                    shareUrl,
//                    title,
//                    picPath,
//                    callback,
//                    platform);
//        } else {
//            ToastUtils.warning(mContext, "分享失败！");
//        }
    }

    /**
     * 分享外部回调
     */
    protected PlatformActionListener callback = new PlatformActionListener() {
        @Override
        public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
            //  分享成功后的操作或者提示
            ToastUtils.success(mContext, "分享成功！");
            onShareCallBack();
        }

        @Override
        public void onError(Platform platform, int i, Throwable throwable) {
            //  失败，打印throwable为错误码
            ToastUtils.warning(mContext, "分享失败！");
        }

        @Override
        public void onCancel(Platform platform, int i) {
            //  分享取消操作
            ToastUtils.warning(mContext, "分享已取消！");
        }
    };

    /**
     * 分享回调
     */
    public abstract void onShareCallBack();
}
