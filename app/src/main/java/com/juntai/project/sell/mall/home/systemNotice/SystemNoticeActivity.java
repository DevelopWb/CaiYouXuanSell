package com.juntai.project.sell.mall.home.systemNotice;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.project.sell.mall.AppHttpPathMall;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseRecyclerviewActivity;
import com.juntai.project.sell.mall.beans.sell.SystemNoticeListBean;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.juntai.project.sell.mall.home.HomePagePresent;

import java.util.List;

/**
 * @aouther tobato
 * @description 描述  系统消息
 * @date 2022/6/8 14:31
 */
public class SystemNoticeActivity extends BaseRecyclerviewActivity<HomePagePresent> implements HomePageContract.IHomePageView {

    @Override
    protected LinearLayoutManager getBaseAdapterManager() {
        return null;
    }

    @Override
    protected void getRvAdapterData() {
        mPresenter.getSystemNotice(getBaseBuilder()
                .add("type", "1")
                .add("page", String.valueOf(page))
                .add("limit", String.valueOf(limit))
                .build(), AppHttpPathMall.GET_SYSTEM_NOTICE
        );
    }


    @Override
    protected boolean enableRefresh() {
        return true;
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
        setTitleName("系统公告");
        baseQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SystemNoticeListBean.DataBean.ListBean bean = (SystemNoticeListBean.DataBean.ListBean) adapter.getItem(position);
// : 2022/6/8 跳转到系统公告详情
                startActivityForResult(new Intent(mContext,PicTextActivity.class)
                .putExtra(BASE_ID,bean.getId()),BASE_REQUEST_RESULT
                );
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (BASE_REQUEST_RESULT==requestCode) {
            page = 1;
            mPresenter.getSystemNotice(getBaseBuilder()
                    .add("type", "1")
                    .add("page", String.valueOf(page))
                    .add("limit", String.valueOf(limit))
                    .build(), AppHttpPathMall.GET_SYSTEM_NOTICE
            );
        }
    }

    @Override
    protected boolean enableLoadMore() {
        return true;
    }

    @Override
    protected BaseQuickAdapter getBaseQuickAdapter() {
        return new SystemNoticeAdapter(R.layout.system_notice_item);
    }

    @Override
    protected HomePagePresent createPresenter() {
        return new HomePagePresent();
    }

    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);

        switch (tag) {
            case AppHttpPathMall.GET_SYSTEM_NOTICE:
                SystemNoticeListBean systemNoticeBean = (SystemNoticeListBean) o;
                if (systemNoticeBean != null) {
                    SystemNoticeListBean.DataBean dataBean = systemNoticeBean.getData();
                    if (dataBean != null) {
                        List<SystemNoticeListBean.DataBean.ListBean> arrays = dataBean.getList();
                        setData(arrays, dataBean.getTotalCount());
                    }
                }
                break;
            default:
                break;
        }
    }
}