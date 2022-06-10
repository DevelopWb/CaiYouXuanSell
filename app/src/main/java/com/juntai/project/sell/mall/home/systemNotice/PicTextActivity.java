package com.juntai.project.sell.mall.home.systemNotice;

import com.juntai.project.sell.mall.AppHttpPathMall;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseAppActivity;
import com.juntai.project.sell.mall.base.web.BaseWebviewFragment;
import com.juntai.project.sell.mall.beans.sell.SystemNoticeBean;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.juntai.project.sell.mall.home.HomePagePresent;

/**
 * @aouther tobato
 * @description 描述  图文  富文本
 * @date 2022/6/8 16:04
 */
public class PicTextActivity extends BaseAppActivity<HomePagePresent> implements HomePageContract.IHomePageView {


    private int msgId;
    private BaseWebviewFragment webviewFragment;

//    public static void startPicTextActivity(Context context, String url, int msgId) {
//        Intent intent = new Intent(context, PicTextActivity.class);
//        intent.putExtra(BASE_STRING, url);
//        intent.putExtra(BASE_ID, msgId);
//        context.startActivity(intent);
//    }


    @Override
    protected HomePagePresent createPresenter() {
        return new HomePagePresent();
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_pic_text;
    }

    @Override
    public void initView() {
        msgId = getIntent().getIntExtra(BASE_ID,0);
        webviewFragment = (BaseWebviewFragment) getSupportFragmentManager().findFragmentById(R.id.base_webview_fg);
    }

    @Override
    public void initData() {
        mPresenter.getSystemNoticeDetail(getBaseBuilder().add("id",String.valueOf(msgId)).build(), AppHttpPathMall.GET_SYSTEM_NOTICE_DETAIL);
    }


    @Override
    public void onSuccess(String tag, Object o) {
        SystemNoticeBean noticeBean = (SystemNoticeBean) o;
        if (noticeBean != null) {
            SystemNoticeBean.DataBean dataBean =  noticeBean.getData();
            if (dataBean != null) {
                String content = dataBean.getContent();
                webviewFragment.setWebData(content);
                setTitleName(dataBean.getTitle());
            }
        }
    }
}
