package com.example.live_moudle.live;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.base.BaseMoreBottomDialog;
import com.juntai.disabled.basecomponent.base.BaseMvpFragment;
import com.juntai.disabled.basecomponent.bean.MoreMenuBean;
import com.juntai.disabled.basecomponent.mvp.BasePresenter;

import java.util.List;

/**
 * @aouther tobato
 * @description 描述 直播更多菜单基类
 * @date 2022/7/3 10:13
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
     * 分享回调
     */
    public abstract void onShareCallBack();
}
