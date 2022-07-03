package com.example.live_moudle.live;


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

import com.example.live_moudle.LivePresent;
import com.example.live_moudle.R;
import com.example.live_moudle.base.InputTextMsgDialog;
import com.example.live_moudle.bean.LiveMsgBean;
import com.juntai.disabled.basecomponent.mvp.IView;
import com.juntai.disabled.basecomponent.utils.GsonTools;
import com.juntai.disabled.basecomponent.utils.LiveDataManager;
import com.juntai.disabled.basecomponent.utils.MultipleItem;

import org.json.JSONObject;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.Emitter;

/**
 * @aouther tobato
 * @description 描述  评论
 * @date 2022/7/2 15:07
 */
public class CommentFragment extends BaseLiveMoreMenuFragment<LivePresent> implements IView, View.OnClickListener {
    private static final String TAG = "ChatRoomFragment";
    private static final int REQUEST_LOGIN = 0;
    private static final int TYPING_TIMER_LENGTH = 600;

    private RecyclerView mMsgRv;
    private TextView mInputMessageView;
    private ImageView mLiveShareIv;
    private ImageView mLiveLikeIv;
    private List<MultipleItem> mMessages = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private boolean mTyping = false;
    private Handler mTypingHandler = new Handler();
    private Boolean isConnected = true;
    private String liveRoomId;
    private boolean isShareCallBack = false;//是否分享回调


    private InputTextMsgDialog inputTextMsgDialog;

    private boolean isCanShare = true, isCanLike = true;

    public static CommentFragment newInstance(String liveId) {
        Bundle args = new Bundle();
        args.putString("liveRoomId", liveId);
        CommentFragment fragment = new CommentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        liveRoomId = getArguments().getString("liveRoomId");
    }

    @Override
    protected LivePresent createPresenter() {
        return new LivePresent();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mAdapter = new MessageAdapter(null);
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
//            case NewsContract.LIKE_TAG:
//                mStreamCameraBean.setIsLike(((BaseIntDataBean) o).getData());
//                if (mStreamCameraBean.getIsLike() > 0) {
//                    mLiveLikeIv.setSelected(true);
//                } else {
//                    mLiveLikeIv.setSelected(false);
//                }
//                break;
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
    }

    @Override
    public void onShareCallBack() {

    }

    @Override
    protected boolean canCancelLoadingDialog() {
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Activity.RESULT_OK != resultCode) {
            Objects.requireNonNull(getActivity()).finish();
        }
    }

    /**
     * 添加日志
     * @param message
     */
    private void addLog(String message) {
        mMessages.add(new MultipleItem(MultipleItem.LIVE_LOG,message));
        mAdapter.notifyItemInserted(mMessages.size() - 1);
        scrollToBottom();
    }

    private void addParticipantsLog(int numUsers) {
        if (onLineUsersListener != null) {
            onLineUsersListener.onUsersCountChange(numUsers > 0 ? (numUsers - 1) : numUsers);
        }
    }

    /**
     * 添加消息
     * @param username
     * @param message
     */
    private void addMessage(String username, String message) {
        mMessages.add(new MultipleItem(MultipleItem.LIVE_MSG,new LiveMsgBean.DataBean(username,message)));
        mAdapter.notifyItemInserted(mMessages.size() - 1);
        scrollToBottom();
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
// TODO: 2022/7/3 发送消息
        // perform the sending message attempt.
//        mSocket.emit(OrderManager.MSG_TYPE_LIVE_CHAT,
//                GsonTools.createGsonString(new LiveRoomChatBean(MyApp.getUser().getData().getAccount(), message,
//                        mUserNickname, liveRoomId, OrderManager.MSG_TYPE_LIVE_CHAT)));
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

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.message_input) {
            initInputTextMsgDialog();
        } else if (id == R.id.live_share_iv) {//分享
//            if (mStreamCameraBean == null) {
//                return;
//            }
//            initBottomDialog(mStreamCameraBean.getId(), mStreamCameraBean.getName(), mStreamCameraBean.getPhoneShareUrl(),
//                    mStreamCameraBean.getEzOpen(), mPresenter.getMoreMenu());
        } else if (id == R.id.live_like_iv) {
            // TODO: 2022/7/3 喜欢

        }
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
