package com.juntai.project.sell.mall.home.systemNotice;

import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.utils.CalendarUtil;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.beans.sell.SystemNoticeListBean;

/**
 * @Author: tobato
 * @Description: 作用描述 系统公告适配器
 * @CreateDate: 2022/6/8 15:16
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/8 15:16
 */
public class SystemNoticeAdapter extends BaseQuickAdapter<SystemNoticeListBean.DataBean.ListBean, BaseViewHolder> {
    public SystemNoticeAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, SystemNoticeListBean.DataBean.ListBean item) {
        if (1== item.getType()) {
            //系统消息 默认都是已读
            helper.setGone(R.id.unread_tag_tv, false);
            helper.setGone(R.id.content_tv, false);
        }else {
            helper.setGone(R.id.content_tv, true);

            if (1 == item.getIsRead()) {
                //未读
                helper.setGone(R.id.unread_tag_tv, true);
                helper.setTextColor(R.id.title_tv, ContextCompat.getColor(mContext, R.color.black));
                helper.setTextColor(R.id.content_tv, ContextCompat.getColor(mContext, R.color.black));
            } else {
                helper.setGone(R.id.unread_tag_tv, false);
                helper.setTextColor(R.id.title_tv, ContextCompat.getColor(mContext, R.color.gray_deeper));
                helper.setTextColor(R.id.content_tv, ContextCompat.getColor(mContext, R.color.gray_deeper));
            }
        }

        helper.setText(R.id.title_tv, item.getTitle());
        helper.setText(R.id.content_tv, item.getContent());
        helper.setText(R.id.msg_time_tv, CalendarUtil.formatDataOfChatList(String.valueOf(item.getCreateTime())));
    }
}
