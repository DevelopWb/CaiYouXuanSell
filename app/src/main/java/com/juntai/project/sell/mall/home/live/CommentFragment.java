package com.juntai.project.sell.mall.home.live;


import android.app.Activity;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.juntai.disabled.basecomponent.utils.ActionConfig;
import com.juntai.disabled.basecomponent.utils.GsonTools;
import com.juntai.project.sell.mall.MyApp;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.InputTextMsgDialog;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.squareup.wire.Message;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Emitter;

/**
 * @aouther tobato
 * @description 描述  评论
 * @date 2022/7/2 15:07
 */
public class CommentFragment extends BaseLiveMoreMenuFragment<LivePresent> implements HomePageContract.IHomePageView, View.OnClickListener {
    private static final String TAG = "ChatRoomFragment";
    private static final int REQUEST_LOGIN = 0;
    private static final int TYPING_TIMER_LENGTH = 600;

    private RecyclerView mMsgRv;
    private TextView mInputMessageView;
    private ImageView mLiveShareIv;
    private ImageView mLiveLikeIv;
    private List<Message> mMessages = new ArrayList<Message>();
    private RecyclerView.Adapter mAdapter;
    private boolean mTyping = false;
    private Handler mTypingHandler = new Handler();
    private String mUserNickname;
    private Boolean isConnected = true;
    private String liveRoomId;
    private CameraLiveDetailBean.DataBean mStreamCameraBean;//详情
    private boolean isShareCallBack = false;//是否分享回调


    private InputTextMsgDialog inputTextMsgDialog;

    private boolean isCanShare = true, isCanLike = true;

    public static CommentFragment newInstance(String liveId) {
        Bundle args = new Bundle();
        args.putString("liveId", liveId);
        CommentFragment fragment = new CommentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        liveRoomId = getArguments().getString("liveId");
    }

    @Override
    protected LivePresent createPresenter() {
        return new LivePresent();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mAdapter = new MessageAdapter(context, mMessages);
        if (context instanceof Activity) {
            //this.listener = (ChatRoomActivity) context;
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_comment;
    }

    @Override
    protected void initView() {
        if (MyApp.isLogin()) {
            mUserNickname = MyApp.getUser().getData().getNickname();
        }
        mSocket = MyApp.app.getSocket();
        for (String listener : listeners) {
            initListenner(listener);
        }

        mMsgRv = (RecyclerView) getView(R.id.messages);
        mMsgRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMsgRv.setAdapter(mAdapter);

        mInputMessageView = (TextView) getView(R.id.message_input);
        mInputMessageView.setOnClickListener(this);
        mLiveShareIv = getView(R.id.live_share_iv);
        mLiveShareIv.setOnClickListener(this);
        mLiveLikeIv = getView(R.id.live_like_iv);
        mLiveLikeIv.setOnClickListener(this);

        if (isCanLike) {
            mLiveLikeIv.setVisibility(View.VISIBLE);
        } else {
            mLiveLikeIv.setVisibility(View.GONE);
        }
        if (isCanShare) {
            mLiveShareIv.setVisibility(View.VISIBLE);
        } else {
            mLiveShareIv.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initData() {
        jionLiveRoom();
        //监听别人的加入
        mSocket.on(OrderManager.JOINED_LIVE_ROOM_OTHER, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                if (args != null && args.length > 0) {
                    JSONObject str = (JSONObject) args[0];
                    LiveRoomChatBean bean = GsonTools.changeGsonToBean(str.toString(), LiveRoomChatBean.class);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            addLog(bean.getNickname() + " 来了");
                            addParticipantsLog(Integer.parseInt(bean.getRoom_users()));
                        }
                    });
                }
            }
        });
        mSocket.on(OrderManager.MSG_TYPE_LIVE_CHAT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                if (args != null && args.length > 0) {
                    JSONObject str = (JSONObject) args[0];
                    LiveRoomChatBean bean = GsonTools.changeGsonToBean(str.toString(), LiveRoomChatBean.class);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            addMessage(bean.getNickname(), bean.getMessage());
                        }
                    });
                }
            }
        });
        mSocket.on(OrderManager.LEAVE_LIVE_ROOM_OTHERS, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                if (args != null && args.length > 0) {
                    JSONObject str = (JSONObject) args[0];
                    LiveRoomChatBean bean = GsonTools.changeGsonToBean(str.toString(), LiveRoomChatBean.class);
                    if (getActivity() != null) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
//                                addMessage(bean.getNickname(), "离开了");
                                addLog(bean.getNickname() + "离开了");
                                addParticipantsLog(Integer.parseInt(bean.getRoom_users()));
                            }
                        });
                    }
                }
            }
        });
    }

    private void jionLiveRoom() {
        if (MyApp.isLogin()) {
            LiveRoomChatBean chatBean = new LiveRoomChatBean(MyApp.getUser().getData().getAccount(), "加入直播间",
                    mUserNickname, liveRoomId, OrderManager.MSG_TYPE_LIVE_CHAT);

            mSocket.emit(OrderManager.JOIN_LIVE_ROOM, GsonTools.createGsonString(chatBean));
            mSocket.on(OrderManager.JOINED_LIVE_ROOM, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    if (args != null && args.length > 0) {
                        JSONObject str = (JSONObject) args[0];
                        LiveRoomChatBean bean = GsonTools.changeGsonToBean(str.toString(), LiveRoomChatBean.class);
                        if (getActivity() != null) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    addLog(getString(R.string.live_welcome));
//                                    addLog("欢迎您使用视觉派直播服务。");
                                    addLog("你 来了");
                                    addParticipantsLog(Integer.parseInt(bean.getRoom_users()));
                                }
                            });
                        }
                    } else {
//                    ToastUtils.warning(mContext, "进入聊天室失败");
                    }
                }
            });
        }
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void lazyloadGone() {

    }

    @Override
    public void onSuccess(String tag, Object o) {
        switch (tag) {
            default:
                break;
            case NewsContract.LIKE_TAG:
                mStreamCameraBean.setIsLike(((BaseIntDataBean) o).getData());
                if (mStreamCameraBean.getIsLike() > 0) {
                    mLiveLikeIv.setSelected(true);
                } else {
                    mLiveLikeIv.setSelected(false);
                }
                break;
        }
    }

    /**
     * 初始化输入框
     */
    private void initInputTextMsgDialog() {
        dismissInputDialog();
        if (null == mUserNickname) {
            return;
        }
        if (!mSocket.connected()) {
            return;
        }
        if (inputTextMsgDialog == null) {
            inputTextMsgDialog = new InputTextMsgDialog(mContext, R.style.dialog);
            inputTextMsgDialog.setmOnTextSendListener(new InputTextMsgDialog.OnTextSendListener() {
                @Override
                public void onTextSend(String msg) {
                    sendMsg(msg);
                }

                @Override
                public void dismiss() {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (!mTyping) {
                        mTyping = true;
                        mSocket.emit(OrderManager.USER_TYPING);
                    }
                    mTypingHandler.removeCallbacks(onTypingTimeout);
                    mTypingHandler.postDelayed(onTypingTimeout, TYPING_TIMER_LENGTH);
                }
            });
        }
        showInputTextMsgDialog();
    }

    private void dismissInputDialog() {
        if (inputTextMsgDialog != null) {
            if (inputTextMsgDialog.isShowing()) inputTextMsgDialog.dismiss();
            inputTextMsgDialog.cancel();
            inputTextMsgDialog = null;
        }
    }

    private void showInputTextMsgDialog() {
        inputTextMsgDialog.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (MyApp.isLogin()) {
            mSocket.emit(OrderManager.LEAVE_LIVE_ROOM,
                    GsonTools.createGsonString(new LiveRoomChatBean(MyApp.getUser().getData().getAccount(), "离开直播间",
                            mUserNickname, liveRoomId, OrderManager.MSG_TYPE_LIVE_CHAT)));
        }
        mSocket.off(OrderManager.LEAVE_LIVE_ROOM);
        mSocket.off(Socket.EVENT_DISCONNECT);
        mSocket.off(Socket.EVENT_CONNECT_ERROR);
        mSocket.off(Socket.EVENT_CONNECT_TIMEOUT);
        mSocket.off(OrderManager.LEAVE_LIVE_ROOM_OTHERS);
        mSocket.off(OrderManager.MSG_TYPE_LIVE_CHAT);
        mSocket.off(OrderManager.JOINED_LIVE_ROOM_OTHER);
        mSocket.off(OrderManager.JOINED_LIVE_ROOM);
    }

    @Override
    protected boolean canCancelLoadingDialog() {
        return false;
    }

    @Override
    public void onShareCallBack() {
        if (isShareCallBack && mStreamCameraBean != null) {
            mPresenter.addShare(0, 1, mStreamCameraBean.getId(), NewsContract.SHARE_TAG);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Activity.RESULT_OK != resultCode) {
            getActivity().finish();
            return;
        }
    }

    private void addLog(String message) {
        mMessages.add(new Message.Builder(Message.TYPE_LOG)
                .message(message).build());
        mAdapter.notifyItemInserted(mMessages.size() - 1);
        scrollToBottom();
    }

    private void addParticipantsLog(int numUsers) {
//        addLog(getResources().getQuantityString(R.plurals.message_participants, numUsers, numUsers));
        if (onLineUsersListener != null) {
            onLineUsersListener.onUsersCountChange(numUsers > 0 ? (numUsers - 1) : numUsers);
        }
    }

    private void addMessage(String username, String message) {
        mMessages.add(new Message.Builder(Message.TYPE_MESSAGE)
                .username(username).message(message).build());
        mAdapter.notifyItemInserted(mMessages.size() - 1);
        scrollToBottom();
    }

    private void removeTyping(String username) {
        for (int i = mMessages.size() - 1; i >= 0; i--) {
            Message message = mMessages.get(i);
            if (message.getType() == Message.TYPE_ACTION && message.getUsername().equals(username)) {
                mMessages.remove(i);
                mAdapter.notifyItemRemoved(i);
            }
        }
    }

    /**
     * 发送消息
     */
    private void sendMsg(String message) {
        mTyping = false;
        if (TextUtils.isEmpty(message)) {
            mInputMessageView.requestFocus();
            return;
        }
        mInputMessageView.setText("");
        addMessage(mUserNickname, message);

        // perform the sending message attempt.
        mSocket.emit(OrderManager.MSG_TYPE_LIVE_CHAT,
                GsonTools.createGsonString(new LiveRoomChatBean(MyApp.getUser().getData().getAccount(), message,
                        mUserNickname, liveRoomId, OrderManager.MSG_TYPE_LIVE_CHAT)));
    }

    private void scrollToBottom() {
        mMsgRv.scrollToPosition(mAdapter.getItemCount() - 1);
    }

    private void initListenner(String orderStr) {
        MutableLiveData<Object[]> onConnectBean =
                LiveDataManager.getInstance().with(orderStr, null);
        switch (orderStr) {
            case Socket.EVENT_CONNECT:
                onConnectBean.observe(this, new Observer<Object[]>() {
                    @Override
                    public void onChanged(@Nullable Object[] jsonObject) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (!isConnected) {
                                    if (null != mUserNickname) {
                                        mSocket.emit("add user", mUserNickname);
                                    }
                                    Toast.makeText(getActivity().getApplicationContext(),
                                            R.string.connect, Toast.LENGTH_LONG).show();
                                    isConnected = true;
                                }
                            }
                        });
                    }
                });
                break;
            case Socket.EVENT_DISCONNECT:
                onConnectBean.observe(this, new Observer<Object[]>() {
                    @Override
                    public void onChanged(@Nullable Object[] jsonObject) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.i(TAG, "diconnected");
                                isConnected = false;
                                Toast.makeText(getActivity().getApplicationContext(),
                                        R.string.disconnect, Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
                break;
            case Socket.EVENT_CONNECT_ERROR:
                onConnectBean.observe(this, new Observer<Object[]>() {
                    @Override
                    public void onChanged(@Nullable Object[] jsonObject) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.e(TAG, "Error connecting");
                                Toast.makeText(getActivity().getApplicationContext(),
                                        R.string.error_connect, Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
                break;
            default:
                break;
        }
    }

    private Runnable onTypingTimeout = new Runnable() {
        @Override
        public void run() {
            if (!mTyping) {
                return;
            }
            mTyping = false;
            mSocket.emit(OrderManager.USER_STOP_TYPING);
        }
    };

    @Override
    public void onClick(View v) {
        if (!MyApp.isLogin()) {
            MyApp.goLogin();
            return;
        }
        switch (v.getId()) {
            case R.id.message_input:
                initInputTextMsgDialog();
                break;
            case R.id.live_share_iv:
                //分享
                if (mStreamCameraBean == null) {
                    return;
                }
                if (mStreamCameraBean.getCameraType() == 1) {//1监控；2个人直播
                    initBottomDialog(mStreamCameraBean.getId(), mStreamCameraBean.getName(), mStreamCameraBean.getShareUrl(),
                            mStreamCameraBean.getEzOpen(), mPresenter.getMoreMenu());
                } else {
                    initBottomDialog(mStreamCameraBean.getId(), mStreamCameraBean.getName(), mStreamCameraBean.getPhoneShareUrl(),
                            mStreamCameraBean.getEzOpen(), mPresenter.getMoreMenu());
                }
                break;
            case R.id.live_like_iv:
                if (mStreamCameraBean == null) {
                    return;
                }
//                if (mStreamCameraBean.getIsLike() > 0) {//取消
//                    mPresenter.like(-1, -1, 1, 0, mStreamCameraBean.getIsLike(), NewsContract.LIKE_TAG);
//                } else {
//                    mPresenter.like(mStreamCameraBean.getIsLike(), MyApp.getUid(), 0, 1, mStreamCameraBean.getId(), NewsContract.LIKE_TAG);
//                }
                break;
            default:
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void receiveMsg(String test) {
        if (ActionConfig.BROAD_LOGIN_AFTER.equals(test)) {
            if (MyApp.isLogin()) {
                mUserNickname = MyApp.getUser().getData().getNickname();
            }
            jionLiveRoom();
        }
    }

    public void setmStreamCameraBean(CameraLiveDetailBean.DataBean mStreamCameraBean) {
        this.mStreamCameraBean = mStreamCameraBean;
    }

    public CommentFragment setCanShare(boolean canShare) {
        isCanShare = canShare;
        return this;
    }

    public CommentFragment setCanLike(boolean canLike) {
        isCanLike = canLike;
        return this;
    }

    public interface OnLineUsersListener {
        void onUsersCountChange(int userCount);
    }

    private OnLineUsersListener onLineUsersListener;

    public CommentFragment setOnLineUsersListener(OnLineUsersListener onLineUsersListener) {
        this.onLineUsersListener = onLineUsersListener;
        return this;
    }

    public CommentFragment setShareCallBack(boolean shareCallBack) {
        isShareCallBack = shareCallBack;
        return this;
    }
}
