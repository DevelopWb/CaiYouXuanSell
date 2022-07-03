package com.example.chat.chatmodule;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.chat.R;
import com.example.chat.chatcustomview.waveproview.WaveProgress;
import com.example.chat.util.UserInfoManagerChat;
import com.juntai.disabled.basecomponent.utils.MultipleItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.juntai.disabled.basecomponent.bean.objectboxbean.MessageBodyBean;
import com.juntai.disabled.basecomponent.utils.CalendarUtil;
import com.juntai.disabled.basecomponent.utils.DisplayUtil;
import com.juntai.disabled.basecomponent.utils.FileCacheUtils;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.disabled.basecomponent.utils.UrlFormatUtil;
import com.negier.emojifragment.util.EmojiUtils;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2020/11/11 15:43
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/11/11 15:43
 */
public class ChatAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {

    private boolean isEdit = false;

    public boolean isEdit() {
        return isEdit;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ChatAdapter(List<MultipleItem> data) {
        super(data);
        addItemType(MultipleItem.ITEM_CHAT_TEXT_MSG, R.layout.chat_item_text);
        addItemType(MultipleItem.ITEM_CHAT_PIC_VIDEO, R.layout.chat_item_pic_video);
        addItemType(MultipleItem.ITEM_CHAT_DATE_MSG, R.layout.single_text_layout);
        addItemType(MultipleItem.ITEM_SEND_AUDIO, R.layout.chat_item_send_audio);
        addItemType(MultipleItem.ITEM_RECEIVE_AUDIO, R.layout.chat_item_receive_audio);
        addItemType(MultipleItem.ITEM_CHAT_LOCATE, R.layout.item_chat_locate);
        addItemType(MultipleItem.ITEM_CHAT_NOTICE, R.layout.chat_item_notice_text);
    }

    public List<MessageBodyBean> changeGsonToList(String gsonString) {
        Gson gson = new Gson();
        List<MessageBodyBean> list = gson.fromJson(gsonString, new TypeToken<List<MessageBodyBean>>() {
        }.getType());
        return list;
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        helper.addOnClickListener(R.id.sender_pic_iv);
        helper.addOnClickListener(R.id.receiver_pic_iv);
        helper.addOnLongClickListener(R.id.sender_pic_iv);
        helper.addOnLongClickListener(R.id.receiver_pic_iv);
        MessageBodyBean messageBodyBean = (MessageBodyBean) item.getObject();
        messageBodyBean.setAdapterPosition(helper.getAdapterPosition());
        switch (item.getItemType()) {

            case MultipleItem.ITEM_CHAT_NOTICE:
                helper.setText(R.id.msg_notice_tv, messageBodyBean.getContent());
                break;
//            case MultipleItem.ITEM_CHAT_OUTSIDE_SHARE:
//                //外部分享
//                helper.addOnClickListener(R.id.receiver_share_cl);
//                helper.addOnClickListener(R.id.sender_share_cl);
//                helper.addOnLongClickListener(R.id.receiver_share_cl);
//                helper.addOnLongClickListener(R.id.sender_share_cl);
//                initSelectedViewStatus(helper, messageBodyBean);
//
//
//                if (UserInfoManagerChat.getUserId() == messageBodyBean.getFromUserId()) {
//                    //我发送的消息
//                    initNickName(helper, messageBodyBean, 0);
//                    ImageLoadUtil.loadSquareImage(mContext, UrlFormatUtil.getImageThumUrl(UserInfoManagerChat.getHeadPic()), (ImageView) helper.getView(R.id.sender_pic_iv));
//// : 2022/4/11 我发送的外部链接
//                    helper.setText(R.id.sender_share_content_tv, messageBodyBean.getShareContent());
//                    helper.setText(R.id.sender_share_title_tv, messageBodyBean.getShareTitle());
//                    helper.setText(R.id.sender_share_source_tv, messageBodyBean.getShareAppName());
//                    ImageLoadUtil.loadSquareImage(mContext, messageBodyBean.getSharePic(), (ImageView) helper.getView(R.id.sender_share_pic_iv));
//
//                } else {
//                    //对方发送的消息
//                    initNickName(helper, messageBodyBean, 1);
//                    ImageLoadUtil.loadSquareImage(mContext, UrlFormatUtil.getImageThumUrl(messageBodyBean.getFromHead()), (ImageView) helper.getView(R.id.receiver_pic_iv));
//// : 2022/4/11 别人发送的外部链接
//                    helper.setText(R.id.receiver_share_content_tv, messageBodyBean.getShareContent());
//                    helper.setText(R.id.receiver_share_title_tv, messageBodyBean.getShareTitle());
//                    helper.setText(R.id.receiver_share_source_tv, messageBodyBean.getShareAppName());
//                    ImageLoadUtil.loadSquareImage(mContext, messageBodyBean.getSharePic(), (ImageView) helper.getView(R.id.receiver_share_pic_iv));
//
//                }
//                break;
            case MultipleItem.ITEM_CHAT_DATE_MSG:
                helper.setGone(R.id.single_text_div_v, false);
                if (!TextUtils.isEmpty(messageBodyBean.getContent())) {
                    helper.setText(R.id.single_text_tv, CalendarUtil.formatData(messageBodyBean.getContent()));
                    TextView dataTv = helper.getView(R.id.single_text_tv);
                    dataTv.setTextSize(12);
                }

                break;
            case MultipleItem.ITEM_CHAT_TEXT_MSG:
                helper.addOnLongClickListener(R.id.receiver_content_tv);
                helper.addOnLongClickListener(R.id.sender_content_tv);
                helper.addOnClickListener(R.id.sender_quote_content_tv);
                helper.addOnClickListener(R.id.receiver_quote_content_tv);
                int fromUserId = messageBodyBean.getFromUserId();
                EditText sendEt = helper.getView(R.id.sender_content_tv);
                EditText receiveEt = helper.getView(R.id.receiver_content_tv);
                setMaxWidth(mContext, (TextView) helper.getView(R.id.sender_content_tv),110);
                setMaxWidth(mContext, (TextView) helper.getView(R.id.receiver_content_tv),110);
                setMaxWidth(mContext, (TextView) helper.getView(R.id.sender_quote_content_tv),115);
                setMaxWidth(mContext, (TextView) helper.getView(R.id.receiver_quote_content_tv),115);
                helper.setGone(R.id.sender_quote_content_tv, false);
                helper.setGone(R.id.receiver_quote_content_tv, false);
                initSelectedViewStatus(helper, messageBodyBean);
                if (UserInfoManagerChat.getUserId() == fromUserId) {
                    //我发送的消息
                    initNickName(helper, messageBodyBean, 0);
                    EmojiUtils.showEmojiTextView(mContext, sendEt, messageBodyBean.getContent(), 22);
                    ImageLoadUtil.loadHeadSquareImageHasCorner(mContext, UrlFormatUtil.getImageThumUrl(UserInfoManagerChat.getHeadPic()), (ImageView) helper.getView(R.id.sender_pic_iv));
                } else {
                    //对方发送的消息
                    initNickName(helper, messageBodyBean, 1);
                    EmojiUtils.showEmojiTextView(mContext, receiveEt, messageBodyBean.getContent(), 22);
                    ImageLoadUtil.loadHeadSquareImageHasCorner(mContext, UrlFormatUtil.getImageThumUrl(messageBodyBean.getFromHead()), (ImageView) helper.getView(R.id.receiver_pic_iv));
                }
                break;
            case MultipleItem.ITEM_RECEIVE_AUDIO:
            case MultipleItem.ITEM_SEND_AUDIO:
                helper.addOnClickListener(R.id.audio_bg_rl);
                helper.addOnLongClickListener(R.id.audio_bg_rl);
                initSelectedViewStatus(helper, messageBodyBean);

                if (UserInfoManagerChat.getUserId() == messageBodyBean.getFromUserId()) {
                    //发送
//                    if (2 == messageBodyBean.getChatType()) {
//                        helper.setGone(R.id.sender_nick_name_tv, false);
//                    }
                    ImageLoadUtil.loadHeadSquareImageHasCorner(mContext, UrlFormatUtil.getImageThumUrl(UserInfoManagerChat.getHeadPic()), (ImageView) helper.getView(R.id.audio_head_iv));
                } else {
//                    if (2 == messageBodyBean.getChatType()) {
//                        helper.setGone(R.id.receiver_nick_name_tv, true);
//                        //如果是好友 并且有好友备注 就显示好友备注
//                        helper.setText(R.id.receiver_nick_name_tv, UserInfoManagerChat.getContactRemarkName(messageBodyBean));
//
//                    }
                    ImageLoadUtil.loadHeadSquareImageHasCorner(mContext, UrlFormatUtil.getImageThumUrl(messageBodyBean.getFromHead()), (ImageView) helper.getView(R.id.audio_head_iv));

                }
                RelativeLayout audioRl = helper.getView(R.id.audio_bg_rl);
                if (!TextUtils.isEmpty(messageBodyBean.getDuration())) {
                    int increment = (getWidth(mContext) - dip2px(165)) * (int) Integer.parseInt(messageBodyBean.getDuration()) / 60;
                    ViewGroup.LayoutParams params = audioRl.getLayoutParams();
                    params.width = increment + dip2px(65);
                    audioRl.setLayoutParams(params);
                }
                helper.setText(R.id.duration_tv, messageBodyBean.getDuration() + "''");

                break;

            case MultipleItem.ITEM_CHAT_PIC_VIDEO:
                initSelectedViewStatus(helper, messageBodyBean);
                ImageView sendIv = helper.getView(R.id.sender_pic_video_iv);
                ImageView receiveIv = helper.getView(R.id.receiver_pic_video_iv);
                helper.addOnClickListener(R.id.sender_pic_video_iv);
                helper.addOnClickListener(R.id.receiver_pic_video_iv);
                helper.addOnLongClickListener(R.id.sender_pic_video_iv);
                helper.addOnLongClickListener(R.id.receiver_pic_video_iv);
                ConstraintLayout.LayoutParams senderlayoutParams =
                        (ConstraintLayout.LayoutParams) sendIv.getLayoutParams();
                ConstraintLayout.LayoutParams receiverlayoutParams =
                        (ConstraintLayout.LayoutParams) receiveIv.getLayoutParams();
                if ("0".equals(messageBodyBean.getRotation()) || "180".equals(messageBodyBean.getRotation())) {
                    senderlayoutParams.width = (dip2px(200));
                    receiverlayoutParams.width = (dip2px(200));
                    sendIv.setMaxHeight(dip2px(150));
                    receiveIv.setMaxHeight(dip2px(150));
                } else {
                    senderlayoutParams.width = (dip2px(100));
                    receiverlayoutParams.width = (dip2px(100));
                    sendIv.setMaxHeight(dip2px(220));
                    receiveIv.setMaxHeight(dip2px(220));
                }
                sendIv.setLayoutParams(senderlayoutParams);
                receiveIv.setLayoutParams(receiverlayoutParams);
                String picVideoContent = messageBodyBean.getContent();
                int picFromUserId = messageBodyBean.getFromUserId();
                helper.setGone(R.id.sender_play_iv, false);
                helper.setGone(R.id.sender_video_duration_tv, false);
                helper.setGone(R.id.receiver_video_duration_tv, false);
                helper.setGone(R.id.receiver_play_iv, false);
                if (UserInfoManagerChat.getUserId() == picFromUserId) {
                    //我发送的消息
                    WaveProgress waveProgress = helper.getView(R.id.sender_progress);
                    waveProgress.setMaxValue(100);
                    if (messageBodyBean.getUploadProgress() > 0 && messageBodyBean.getUploadProgress() < 100) {
                        waveProgress.setValue(messageBodyBean.getUploadProgress());
                        helper.setGone(R.id.sender_progress, true);
                        if (2 == messageBodyBean.getMsgType()) {
                            helper.setGone(R.id.sender_play_iv, false);
                        }
                        return;
                    } else {
                        helper.setGone(R.id.sender_progress, false);
                    }

                    if (1 == messageBodyBean.getMsgType()) {
                        if (!FileCacheUtils.isFileExists(messageBodyBean.getLocalCatchPath())) {
                            //有可能是转发的网络图片 或者本地文件删除了
                            if (FileCacheUtils.isFileExists(FileCacheUtils.getAppImagePath(true) + getSavedFileName(messageBodyBean.getContent()))) {
                                //本地缓存存在图片
                                ImageLoadUtil.loadImage(mContext, FileCacheUtils.getAppImagePath(true) + getSavedFileName(messageBodyBean.getContent()),
                                        (ImageView) helper.getView(R.id.sender_pic_video_iv));
                            } else {
                                //加载网络图片
                                ImageLoadUtil.loadImage(mContext, messageBodyBean.getContent(),
                                        (ImageView) helper.getView(R.id.sender_pic_video_iv));
                                ImageLoadUtil.setGlideDownloadFileToLocal(null, mContext, messageBodyBean.getContent(), true);

                            }

                        } else {
                            ImageLoadUtil.loadImage(mContext, messageBodyBean.getLocalCatchPath(), (ImageView) helper.getView(R.id.sender_pic_video_iv));
                        }
                    } else {
                        //自己发的视频文件
                        helper.setGone(R.id.sender_play_iv, true);
                        helper.setGone(R.id.sender_video_duration_tv, true);
                        helper.setText(R.id.sender_video_duration_tv, messageBodyBean.getDuration());
                        if (FileCacheUtils.isFileExists(FileCacheUtils.getAppImagePath(true) + getSavedFileName(messageBodyBean.getVideoCover()))) {
                            //本地缩略图存在
                            ImageLoadUtil.loadImage(mContext, FileCacheUtils.getAppImagePath(true) + getSavedFileName(messageBodyBean.getVideoCover()), (ImageView) helper.getView(R.id.sender_pic_video_iv));
                        } else {
                            ImageLoadUtil.loadImage(mContext, messageBodyBean.getVideoCover(), (ImageView) helper.getView(R.id.sender_pic_video_iv));
                            ImageLoadUtil.setGlideDownloadFileToLocal(null, mContext, messageBodyBean.getVideoCover(), true);
                        }

                    }
                    initNickName(helper, messageBodyBean, 0);
                    ImageLoadUtil.loadHeadSquareImageHasCorner(mContext, UrlFormatUtil.getImageThumUrl(UserInfoManagerChat.getHeadPic()), (ImageView) helper.getView(R.id.sender_pic_iv));

                } else {
                    //对方发送的消息
                    helper.setGone(R.id.sender_progress, false);
                    if (1 == messageBodyBean.getMsgType()) {
                        /**
                         * 对方发的图片  优先展示缓存到本地的图片  如果没有缓存到本地 就加载线上缩略图
                         */
                        if (FileCacheUtils.isFileExists(FileCacheUtils.getAppImagePath(true) + getSavedFileName(messageBodyBean))) {
                            ImageLoadUtil.loadImage(mContext, FileCacheUtils.getAppImagePath(true) + getSavedFileName(messageBodyBean), (ImageView) helper.getView(R.id.receiver_pic_video_iv));
                        } else {
                            ImageLoadUtil.loadImage(mContext, picVideoContent, (ImageView) helper.getView(R.id.receiver_pic_video_iv));
                            ImageLoadUtil.setGlideDownloadFileToLocal(null, mContext, picVideoContent, true);

                        }
                    } else {
                        /**
                         * 对方发的视频
                         */
                        helper.setGone(R.id.receiver_play_iv, true);
                        helper.setGone(R.id.receiver_video_duration_tv, true);
                        helper.setText(R.id.receiver_video_duration_tv, messageBodyBean.getDuration());
                        if (FileCacheUtils.isFileExists(FileCacheUtils.getAppImagePath(true) + getSavedFileName(messageBodyBean.getVideoCover()))) {
                            //本地缩略图存在
                            ImageLoadUtil.loadImage(mContext, FileCacheUtils.getAppImagePath(true) + getSavedFileName(messageBodyBean.getVideoCover()), (ImageView) helper.getView(R.id.receiver_pic_video_iv));
                        } else {
                            ImageLoadUtil.loadImage(mContext, messageBodyBean.getVideoCover(), (ImageView) helper.getView(R.id.receiver_pic_video_iv));
                            ImageLoadUtil.setGlideDownloadFileToLocal(null, mContext, messageBodyBean.getVideoCover(), true);
                        }


                    }
                    initNickName(helper, messageBodyBean, 1);
                    ImageLoadUtil.loadHeadSquareImageHasCorner(mContext, UrlFormatUtil.getImageThumUrl(messageBodyBean.getFromHead()), (ImageView) helper.getView(R.id.receiver_pic_iv));
                }

                break;
            default:
                break;
        }
    }


    /**
     * @param helper
     * @param messageBodyBean
     * @param nickNameType    0代表sender 1代表 receiver
     */
    private void initNickName(BaseViewHolder helper, MessageBodyBean messageBodyBean, int nickNameType) {
        String nickname = UserInfoManagerChat.getContactRemarkName(messageBodyBean);
        helper.setGone(R.id.receiver_g, false);
        helper.setGone(R.id.sender_g, false);
        switch (nickNameType) {
            case 0:
                helper.setGone(R.id.sender_g, true);

                break;
            case 1:
                helper.setGone(R.id.receiver_g, true);

                break;
            default:
                break;
        }

    }

    /**
     * 选择控件的状态
     *
     * @param helper
     * @param messageBodyBean
     */
    private void initSelectedViewStatus(BaseViewHolder helper, MessageBodyBean messageBodyBean) {
        if (isEdit) {
            helper.setGone(R.id.select_status_iv, true);
            if (messageBodyBean.isSelected()) {
                helper.setImageResource(R.id.select_status_iv, R.mipmap.select_icon);
            } else {
                helper.setImageResource(R.id.select_status_iv, R.mipmap.unselect_icon);
            }
        } else {
            helper.setGone(R.id.select_status_iv, false);
        }
    }
    //获取屏幕宽度
    private int getWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        return width;
    }

    //获取屏幕宽度
    private int getHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        return height;
    }

    //dip转像素值
    private int dip2px(double d) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (d * scale + 0.5f);
    }

    /**
     * 设置左边图标
     *
     * @param textView
     * @param drawableId
     */
    public void initViewLeftDrawable(TextView textView, int drawableId, int width, int height) {
        Drawable drawable = mContext.getResources().getDrawable(drawableId);
        drawable.setBounds(0, 0, DisplayUtil.dp2px(mContext, width), DisplayUtil.dp2px(mContext, height));//第一个 0 是距左边距离，第二个 0 是距上边距离，40 分别是长宽
        textView.setCompoundDrawables(drawable, null, null, null);//放左边
    }

    /**
     * 获取文件名称
     *
     * @param messageBodyBean
     * @return
     */
    public String getSavedFileName(MessageBodyBean messageBodyBean) {
        String content = messageBodyBean.getContent();
        if (TextUtils.isEmpty(content)) {
            return null;
        }

        if (content.contains("/")) {
            content = content.substring(content.lastIndexOf("/") + 1, content.length());
        }
        return content;
    }

    /**
     * 获取文件名称
     *
     * @return
     */
    public String getSavedFileName(String content) {
        if (TextUtils.isEmpty(content)) {
            return null;
        }

        if (content.contains("/")) {
            content = content.substring(content.lastIndexOf("/") + 1, content.length());
        }
        return content;
    }

    /**
     * 设置最大宽度
     * @param mContext
     * @param textView
     */
    private void  setMaxWidth(Context mContext,TextView textView,int spaceSize){
        textView.setMaxWidth(getWidth(mContext) - dip2px(spaceSize));
    }
}
