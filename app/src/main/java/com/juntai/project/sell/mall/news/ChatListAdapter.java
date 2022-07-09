package com.juntai.project.sell.mall.news;

import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.utils.CalendarUtil;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.disabled.basecomponent.utils.MultipleItem;
import com.juntai.disabled.basecomponent.utils.UrlFormatUtil;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.beans.NewsListBean;
import com.negier.emojifragment.util.EmojiUtils;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2020/11/11 15:43
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/11/11 15:43
 */
public class ChatListAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {

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
    public ChatListAdapter(List<MultipleItem> data) {
        super(data);
        addItemType(MultipleItem.ITEM_CHAT_LIST_CONTACT, R.layout.item_chat_list);
        addItemType(MultipleItem.ITEM_CHAT_LIST_GROUP, R.layout.item_chat_list);
    }


    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        helper.addOnClickListener(R.id.sender_pic_iv);
        helper.addOnClickListener(R.id.receiver_pic_iv);
        switch (item.getItemType()) {

            case MultipleItem.ITEM_CHAT_LIST_CONTACT:
                NewsListBean.DataBean dataBean = (NewsListBean.DataBean) item.getObject();
                helper.setText(R.id.item_name_tv, dataBean.getFromNickname());
                if (TextUtils.isEmpty(dataBean.getFromHead())) {
                    helper.setImageResource(R.id.contact_name_iv,R.mipmap.default_msg_icon);
                }else {
                    ImageLoadUtil.loadHeadCirclePic(mContext, UrlFormatUtil.getImageThumUrl(dataBean.getFromHead()), (ImageView) helper.getView(R.id.contact_name_iv));
                }
                String content = dataBean.getContent();
                // TODO: 2022/4/18 新增消息类型的时候 这个地方需要注意
                switch (dataBean.getMsgType()) {
                    case 0:
                        EmojiUtils.showEmojiTextView(mContext, (TextView) helper.getView(R.id.item_content_tv), content, 14);

                        break;
                    case 1:
                        helper.setText(R.id.item_content_tv, "[图片]");
                        break;
                    case 2:
                        helper.setText(R.id.item_content_tv, "[视频]");
                        break;
                    case 3:
                        helper.setText(R.id.item_content_tv, "[语音]");
                        break;
                    default:
                        helper.setText(R.id.item_content_tv, content);
                        break;
                }

                helper.setGone(R.id.amount_tv, dataBean.getUnread()>0);
                helper.setText(R.id.amount_tv, String.valueOf(dataBean.getUnread()));
                helper.setText(R.id.msg_time_tv, CalendarUtil.formatDataOfChatList(String.valueOf(dataBean.getCreateTime())));
                break;
            default:
                break;
        }
    }

}
