package com.juntai.project.sell.mall.home.label.label_manage;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseAppActivity;
import com.juntai.project.sell.mall.home.label.LabelContract;
import com.juntai.project.sell.mall.home.label.LabelPresenter;
import com.juntai.project.sell.mall.home.label.helper.ItemDragHelperCallback;
import com.juntai.project.sell.mall.home.label.labelbean.LabelListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @description 频道编辑
 * @aouther ZhangZhenlong
 * @date 2021-2-24
 */
public class ChannelManageActivity extends BaseAppActivity<LabelPresenter> implements LabelContract.ILabelView {

    private RecyclerView mRecy;
    private ChannelAdapter channelAdapter;
    List<LabelListBean.LabelBean> choosedChannels = new ArrayList<>();//已选中频道
    List<LabelListBean.LabelBean> otherChannels = new ArrayList<>();//未选中频道

    @Override
    protected LabelPresenter createPresenter() {
        return new LabelPresenter();
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_label_manage;
    }

    @Override
    public void initView() {
        mRecy = (RecyclerView) findViewById(R.id.recy);

        GridLayoutManager manager = new GridLayoutManager(this, 4);
        mRecy.setLayoutManager(manager);

        ItemDragHelperCallback callback = new ItemDragHelperCallback();
        final ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(mRecy);

        channelAdapter = new ChannelAdapter(this, helper, choosedChannels, otherChannels);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int viewType = channelAdapter.getItemViewType(position);
                return viewType == ChannelAdapter.TYPE_MY || viewType == ChannelAdapter.TYPE_OTHER ? 1 : 4;
            }
        });
        mRecy.setAdapter(channelAdapter);

        channelAdapter.setOnMyChannelItemClickListener(new ChannelAdapter.OnMyChannelItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                if (choosedChannels.get(position).isResident){//常驻不可修改
                    return;
                }

                Toast.makeText(mContext, choosedChannels.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void initData() {
        mPresenter.getLabelList(LabelContract.GET_ALL_LABEL_LIST);
    }


    @Override
    public void onSuccess(String tag, Object o) {
        switch (tag) {
            case LabelContract.GET_ALL_LABEL_LIST:
                LabelListBean labelListBean = (LabelListBean) o;
                if (labelListBean.getData().size() > 0){
                    choosedChannels.clear();
                    otherChannels.clear();
                    choosedChannels.addAll(mPresenter.getDefaultLabels());
                    for (LabelListBean.LabelBean labelBean: labelListBean.getData()){
                        if (labelBean.getTypeX() == 1) {
                            choosedChannels.add(labelBean);
                        }else {
                            otherChannels.add(labelBean);
                        }
                    }
                    channelAdapter.notifyDataSetChanged();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
//        if (choosedChannels.size()>0){
//            EventManager.getLibraryEvent().post(choosedChannels);
//        }
        super.onBackPressed();
    }
}
