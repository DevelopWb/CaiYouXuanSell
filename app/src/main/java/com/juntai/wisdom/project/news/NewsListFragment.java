package com.juntai.wisdom.project.news;

import android.support.v7.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.chat.util.MultipleItem;
import com.juntai.wisdom.project.AppHttpPathMall;
import com.juntai.wisdom.project.R;
import com.juntai.wisdom.project.base.BaseRecyclerviewFragment;
import com.juntai.wisdom.project.beans.NewsListBean;
import com.juntai.wisdom.project.home.HomePageContract;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/5/19 10:43
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/19 10:43
 */
public class NewsListFragment extends BaseRecyclerviewFragment<NewsPresent> implements HomePageContract.IHomePageView {
    @Override
    protected LinearLayoutManager getBaseAdapterManager() {
        return null;
    }

    @Override
    protected void getRvAdapterData() {
        mPresenter.getNewsList(getBaseAppActivity().getBaseBuilder().build(), AppHttpPathMall.NEWS_LIST);
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.news_list_fg;
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
        return new ChatListAdapter(null);
    }

    @Override
    protected NewsPresent createPresenter() {
        return new NewsPresent();
    }


    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);

        switch (tag) {
            case AppHttpPathMall.NEWS_LIST:
                NewsListBean newsListBean = (NewsListBean) o;
                if (newsListBean != null) {
                    List<NewsListBean.DataBean> dataBeans = newsListBean.getData();
                    if (dataBeans != null) {
                        List<MultipleItem>  arrays = new ArrayList<>();
                        for (NewsListBean.DataBean array : dataBeans) {
                            arrays.add(new MultipleItem(MultipleItem.ITEM_CHAT_LIST_CONTACT,array));
                        }
                        baseQuickAdapter.setNewData(arrays);
                    }
                }
                break;
            default:
                break;
        }
    }
}
