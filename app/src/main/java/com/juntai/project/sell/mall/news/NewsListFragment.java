package com.juntai.project.sell.mall.news;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.bean.ContactBean;
import com.juntai.disabled.basecomponent.utils.MultipleItem;
import com.juntai.disabled.basecomponent.utils.eventbus.EventBusObject;
import com.juntai.disabled.basecomponent.utils.eventbus.EventManager;
import com.juntai.project.sell.mall.AppHttpPathMall;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseRecyclerviewFragment;
import com.juntai.project.sell.mall.beans.NewsListBean;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.juntai.project.sell.mall.home.systemNotice.SystemNoticeActivity;

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
    protected void initView() {
        super.initView();
        baseQuickAdapter.setEmptyView(getBaseAppActivity().getAdapterEmptyView("一条消息也没有-_-",-1));
        baseQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MultipleItem multipleItem = (MultipleItem) adapter.getItem(position);
                switch (multipleItem.getItemType()) {
                    case MultipleItem.ITEM_CHAT_LIST_CONTACT:
                        NewsListBean.DataBean dataBean = (NewsListBean.DataBean) multipleItem.getObject();
                        int fromUserId = dataBean.getFromUserId();
                        if (-1==fromUserId) {
                            //通知消息
                            startActivity(new Intent(mContext, SystemNoticeActivity.class).putExtra(BASE_ID,2));

                        }else {
                            ContactBean contactBean = new ContactBean();
                            contactBean.setNickname(dataBean.getFromNickname());
                            contactBean.setHeadPortrait(dataBean.getFromHead());
                            contactBean.setUserId(dataBean.getFromUserId());
                            getBaseAppActivity().startToChatActivity(contactBean);
                        }



                        break;
                    default:
                        break;
                }
            }
        });
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
    public void onEvent(EventBusObject eventBusObject) {
        super.onEvent(eventBusObject);
        switch (eventBusObject.getEventKey()) {
            case EventBusObject.REFRESH_NEWS_LIST:
                getRvAdapterData();
                break;
            default:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getRvAdapterData();
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
                        int unreadAmount = 0;
                        List<MultipleItem> arrays = new ArrayList<>();
                        for (NewsListBean.DataBean array : dataBeans) {
                            unreadAmount += array.getUnread();
                            arrays.add(new MultipleItem(MultipleItem.ITEM_CHAT_LIST_CONTACT, array));
                        }
                        EventManager.getEventBus().post(new EventBusObject(EventBusObject.UNREAD_MSG_AMOUNT, unreadAmount));
                        baseQuickAdapter.setNewData(arrays);
                    }
                }
                break;
            default:
                break;
        }
    }
}
