package com.juntai.project.sell.mall.base.search;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.mvp.BasePresenter;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseRecyclerviewActivity;
import com.juntai.project.sell.mall.search.BaseSearchAdapter;

/**
 * @aouther tobato
 * @description 描述  这种搜索是跳转到一个新的界面搜索
 * @date 2022-01-25 9:32
 */
public abstract class BaseSearchActivity<P extends BasePresenter> extends BaseRecyclerviewActivity<P> implements View.OnClickListener {

    public SearchView mSearchContentSv;
    /**
     * 取消
     */
    private TextView mBackTv;


    @Override
    public int getLayoutView() {
        return R.layout.activity_base_search;
    }

    @Override
    public void initView() {
        super.initView();
        setTitleName(getTitleName());
        initToolbarAndStatusBar(false);
        mImmersionBar.statusBarDarkFont(true).init();
        mSearchContentSv = (SearchView) findViewById(R.id.search_content_sv);
        mBackTv = (TextView) findViewById(R.id.cancel_tv);
        mBackTv.setOnClickListener(this);
        SearchView.SearchAutoComplete textView = (SearchView.SearchAutoComplete) mSearchContentSv.findViewById(R.id.search_src_text);
        textView.setTextSize(14);
        mSearchContentSv.requestFocus();
        mSearchContentSv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (TextUtils.isEmpty(s)) {
                    return true;
                }
                commitSearch(s);
                getViewFocus(mRecyclerview);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s)) {
                    return true;
                }
                commitSearch(s);
                return false;
            }
        });


    }

    protected abstract String getTitleName();

    /**
     * 提交搜索
     * @param s
     * @return
     */
    protected abstract boolean commitSearch(String s);




    /**
     * chatType  聊天类型（1：搜索私聊信息；2搜索公聊信息； 3搜索联系人  4首页搜索  搜索各种类型数据  5消息转发的时候跳转的界面  里面包含群组和通讯录  6 收藏的内容
     *
     * @return
     */
    protected abstract int getSearchType();

    @Override
    public void initData() {
        baseQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                onAdapterItemClick(adapter,position);
            }
        });
    }

    @Override
    protected LinearLayoutManager getBaseAdapterManager() {
        return null;
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
        return new BaseSearchAdapter(null);
    }

    protected abstract void onAdapterItemClick(BaseQuickAdapter adapter, int position);




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.cancel_tv:
                finish();
                break;
        }
    }

}
