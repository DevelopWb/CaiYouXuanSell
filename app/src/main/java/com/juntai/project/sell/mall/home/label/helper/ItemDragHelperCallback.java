package com.juntai.project.sell.mall.home.label.helper;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.juntai.project.sell.mall.home.label.label_manage.ChannelAdapter;


/**
 * ItemDragHelperCallback
 * Created by YoKeyword on 15/12/29.
 */
public class ItemDragHelperCallback extends ItemTouchHelper.Callback {
    /**
     * 滑动或者拖拽的方向，上下左右
     * @param recyclerView 目标RecyclerView
     * @param viewHolder 目标ViewHolder
     * @return 方向
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags;
        //针对特定标记不能拖拽
        if(viewHolder instanceof ChannelAdapter.MyViewHolder){
            if(((ChannelAdapter.MyViewHolder) viewHolder).isResident){
                return 0;
            }
        }

        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager || manager instanceof StaggeredGridLayoutManager) {
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        } else {
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        }
        // 如果想支持滑动(删除)操作, swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END
        int swipeFlags = 0;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    /**
     * 拖拽item移动时产生回调
     * @param recyclerView 目标RecyclerView
     * @param viewHolder 拖拽的item对应的viewHolder
     * @param target 拖拽目的地的ViewHOlder
     * @return 是否消费拖拽事件
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        // 不同Type之间不可移动
        if (viewHolder.getItemViewType() != target.getItemViewType()) {
            return false;
        }

        //针对特定标记不能拖拽
        if(target instanceof ChannelAdapter.MyViewHolder){
            if(((ChannelAdapter.MyViewHolder) target).isResident){
                return false;
            }
        }

        if (recyclerView.getAdapter() instanceof OnItemMoveListener) {
            OnItemMoveListener listener = ((OnItemMoveListener) recyclerView.getAdapter());
            listener.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        }
        return true;
    }

    /**
     * 滑动删除时回调
     * @param viewHolder 当前操作的Item对应的viewHolder
     * @param direction 方向
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        // 不在闲置状态
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            if (viewHolder instanceof OnDragVHListener) {
                OnDragVHListener itemViewHolder = (OnDragVHListener) viewHolder;
                itemViewHolder.onItemSelected();
            }
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof OnDragVHListener) {
            OnDragVHListener itemViewHolder = (OnDragVHListener) viewHolder;
            itemViewHolder.onItemFinish();
        }
        super.clearView(recyclerView, viewHolder);
    }

    /**
     * 是否可以长按拖拽
     * @return
     */
    @Override
    public boolean isLongPressDragEnabled() {
        // 不支持长按拖拽功能 手动控制
        return false;
    }

    /**
     * 是否可以滑动删除
     */
    @Override
    public boolean isItemViewSwipeEnabled() {
        // 不支持滑动功能
        return false;
    }
}
