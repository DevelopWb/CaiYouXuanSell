package com.juntai.disabled.basecomponent.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.R;
import com.juntai.disabled.basecomponent.bean.MoreMenuBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Describe:底部分享菜单
 * Create by zhangzhenlong
 * 2021-1-12
 * email:954101549@qq.com
 */
public class BaseMoreBottomDialog extends BottomSheetDialog implements View.OnClickListener {
    private Context mContext;
    private List<MoreMenuBean> arrays = new ArrayList<>();
    private RecyclerView mBaseBottomDialogRv;
    private OnItemClick onItemClick;
    /**
     * 取消
     */
    private TextView mBaseBottomDialogCancelTv;
    private BottomDialogGridAdapter adapter;

    public BaseMoreBottomDialog(@NonNull Context context) {
        super(context, R.style.dialog_2);
        this.mContext = context;
        initView();
    }

    public BaseMoreBottomDialog(@NonNull Context context, int theme) {
        super(context, theme);
        this.mContext = context;
        initView();
    }

    protected BaseMoreBottomDialog(@NonNull Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
        initView();
    }

    public BaseMoreBottomDialog setOnBottomDialogCallBack(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
        return this;
    }

    public void setData(List<MoreMenuBean> arrays) {
        this.arrays = arrays;
        refrashAdapter();
    }

    private void initView() {
        setContentView(R.layout.more_bottom_dialog);
        mBaseBottomDialogRv = (RecyclerView) findViewById(R.id.base_bottom_dialog_rv);
        mBaseBottomDialogCancelTv = (TextView) findViewById(R.id.base_bottom_dialog_cancel_tv);
        mBaseBottomDialogCancelTv.setOnClickListener(this);
        adapter = new BottomDialogGridAdapter(R.layout.more_menu_layout, arrays);
        mBaseBottomDialogRv.setAdapter(adapter);
        mBaseBottomDialogRv.setLayoutManager(new GridLayoutManager(mContext, 5));
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (onItemClick != null) {
                    onItemClick.onItemClick(adapter,view,position);
                }
            }
        });
        setCanceledOnTouchOutside(true);
    }

    public BottomDialogGridAdapter getAdapter() {
        return adapter;
    }

    public void refrashAdapter(){
        if (adapter != null){
//            adapter.notifyDataSetChanged();
            adapter.getData().clear();
            adapter.addData(arrays);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.base_bottom_dialog_cancel_tv) {
            dismiss();
        }
    }

    public  interface OnItemClick{
        void  onItemClick(BaseQuickAdapter adapter, View view, int position);
    }
}
