package com.juntai.project.sell.mall.news;

import com.example.chat.MainContract;
import com.example.chat.bean.HomePageMenuBean;
import com.juntai.disabled.basecomponent.base.BaseObserver;
import com.juntai.disabled.basecomponent.base.BaseResult;
import com.juntai.disabled.basecomponent.bean.MyMenuBean;
import com.juntai.disabled.basecomponent.bean.objectboxbean.MessageBodyBean;
import com.juntai.disabled.basecomponent.bean.objectboxbean.MessageBodyBean_;
import com.juntai.disabled.basecomponent.bean.objectboxbean.MessageListBean;
import com.juntai.disabled.basecomponent.utils.ObjectBox;
import com.juntai.disabled.basecomponent.utils.RxScheduler;
import com.juntai.project.sell.mall.AppNetModuleMall;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseAppMallPresent;
import com.juntai.project.sell.mall.beans.NewsListBean;
import com.juntai.project.sell.mall.utils.UserInfoManagerMall;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.query.QueryBuilder;
import okhttp3.RequestBody;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/5/19 10:43
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/19 10:43
 */
public class NewsPresent extends BaseAppMallPresent {
    public final static String MORE_ACTION_PIC = "照片";
    public final static String MORE_ACTION_TAKE_PHOTO = "拍照";
    public final static String MORE_ACTION_LOCATION = "位置";

    /**
     * 查找和联系人的聊天记录
     *
     * @param contactId
     * @return
     */
    public List<MessageBodyBean> findPrivateChatRecordList(int contactId) {
        ObjectBox.get().boxFor(MessageBodyBean.class).query(
                MessageBodyBean_.owner.equal(UserInfoManagerMall.getUserId())
                        .and(MessageBodyBean_.canDelete.equal(true))
                        .and(MessageBodyBean_.toUserId.oneOf(new int[]{contactId, UserInfoManagerMall.getUserId()}))
                        .and(MessageBodyBean_.fromUserId.oneOf(new int[]{contactId, UserInfoManagerMall.getUserId()}))
        ).build().remove();
        QueryBuilder<MessageBodyBean> builder = ObjectBox.get().boxFor(MessageBodyBean.class).query(
                MessageBodyBean_.owner.equal(UserInfoManagerMall.getUserId())
                        .and(MessageBodyBean_.canDelete.equal(false))
                        .and(MessageBodyBean_.toUserId.oneOf(new int[]{contactId, UserInfoManagerMall.getUserId()}))
                        .and(MessageBodyBean_.fromUserId.oneOf(new int[]{contactId, UserInfoManagerMall.getUserId()}))
        );
        return builder.order(MessageBodyBean_.createTime)
                .build()
                .find();
    }

    /**
     * 查找和联系人的聊天记录中最后一条记录
     *
     * @param contactId
     * @return
     */
    public MessageBodyBean findPrivateChatRecordLastMessage(int contactId) {
        List<MessageBodyBean> arrays = ObjectBox.get().boxFor(MessageBodyBean.class).query(
                MessageBodyBean_.owner.equal(UserInfoManagerMall.getUserId())
                        .and(MessageBodyBean_.toUserId.oneOf(new int[]{contactId, UserInfoManagerMall.getUserId()}))
                        .and(MessageBodyBean_.fromUserId.oneOf(new int[]{contactId, UserInfoManagerMall.getUserId()}))
        ).order(MessageBodyBean_.createTime).build().find();

        if (arrays == null || arrays.size() == 0) {
            return null;
        }

        return arrays.get(arrays.size() - 1);
    }

    /**
     * 获取更多功能菜单
     *
     * @return
     */
    public List<MyMenuBean> getMoreActionMenus() {
        List<MyMenuBean> arrays = new ArrayList<>();
        arrays.add(new MyMenuBean(MORE_ACTION_PIC, R.mipmap.more_action_pic));
        arrays.add(new MyMenuBean(MORE_ACTION_TAKE_PHOTO, R.mipmap.more_action_take_photo));
//        arrays.add(new MyMenuBean(MORE_ACTION_LOCATION, R.mipmap.more_action_location));

        return arrays;
    }


    /**
     * 发送私聊消息
     *
     * @param body
     * @param tag
     */
    public void sendPrivateMessage(RequestBody body, String tag) {
        AppNetModuleMall.createrRetrofit()
                .sendMessage(body)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<BaseResult>(null) {
                    @Override
                    public void onSuccess(BaseResult o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }
    /**
     * 发送私聊消息
     *
     * @param body
     * @param tag
     */
    public void messageRead(RequestBody body, String tag) {
        AppNetModuleMall.createrRetrofit()
                .messageRead(body)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<BaseResult>(null) {
                    @Override
                    public void onSuccess(BaseResult o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }

    /**
     * 发送私聊消息
     *
     * @param body
     * @param tag
     */
    public void getContactUnreadMsg(RequestBody body, String tag) {
        AppNetModuleMall.createrRetrofit()
                .getContactUnreadMsg(body)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<MessageListBean>(null) {
                    @Override
                    public void onSuccess(MessageListBean o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }

    public void getNewsList(RequestBody body, String tag) {
        AppNetModuleMall.createrRetrofit()
                .getNewsList(body)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<NewsListBean>(null) {
                    @Override
                    public void onSuccess(NewsListBean o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }

    /**
     * 获取适配器数据
     *
     * @return
     */
    public List<HomePageMenuBean> getEditChatMsgMenus(MessageBodyBean messageBodyBean) {
        List<HomePageMenuBean> arrays = new ArrayList<>();
        if (messageBodyBean.getMsgType()==0) {
            arrays.add(new HomePageMenuBean(MainContract.COPY_MSG, R.mipmap.edit_msg_copy_icon));
        }
        arrays.add(new HomePageMenuBean(MainContract.DELETE_MSG, R.mipmap.edit_msg_delete_icon));
        return arrays;
    }
}
