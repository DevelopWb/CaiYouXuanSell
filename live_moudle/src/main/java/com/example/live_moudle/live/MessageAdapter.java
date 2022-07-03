package com.example.live_moudle.live;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.live_moudle.R;
import com.example.live_moudle.bean.LiveMsgBean;
import com.juntai.disabled.basecomponent.utils.MultipleItem;

import java.util.List;


public class MessageAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MessageAdapter(List<MultipleItem> data) {
        super(data);
        addItemType(MultipleItem.LIVE_MSG, R.layout.item_message);
        addItemType(MultipleItem.LIVE_LOG, R.layout.item_log);
    }



    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        switch (item.getItemType()) {
            case MultipleItem.LIVE_MSG:
                LiveMsgBean.DataBean  dataBean = (LiveMsgBean.DataBean) item.getObject();
                helper.setText(R.id.username,dataBean.getNickname());
                helper.setText(R.id.message,dataBean.getContent());
                break;
            case MultipleItem.LIVE_LOG:
                helper.setText(R.id.message,(String)item.getObject());
                break;
            default:
                break;
        }
    }
}
