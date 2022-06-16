package com.juntai.disabled.basecomponent.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/5/5 14:57
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/5 14:57
 */
public abstract  class BaseBottomSheetFragment extends BottomSheetDialogFragment {
    protected RecyclerView mRecyclerview;
    protected SmartRefreshLayout mSmartrefreshlayout;
    protected BaseQuickAdapter baseQuickAdapter;
    protected LinearLayoutManager linearLayoutManager;
    protected TextView mTitleTv;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (getContext() == null) {
            return super.onCreateDialog(savedInstanceState);
        }
        return new BottomSheetDialog(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getBottomSheetDialogLayout() == 0) {
            View view = inflater.inflate(R.layout.base_bottomsheet_layout,null);
            initView(view);
            return view;
        }
        return inflater.inflate(getBottomSheetDialogLayout(),null);

    }
    public void initView(View view) {
        mTitleTv = (TextView) view.findViewById(R.id.selector_title);

        mRecyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        mSmartrefreshlayout = (SmartRefreshLayout) view.findViewById(R.id.smartrefreshlayout);
        mSmartrefreshlayout.setEnableRefresh(false);
        mSmartrefreshlayout.setEnableLoadMore(false);
        baseQuickAdapter = getBaseQuickAdapter();
        linearLayoutManager = getBaseAdapterManager() == null ? new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false) : getBaseAdapterManager();
        if (baseQuickAdapter != null) {
            initRecyclerview(mRecyclerview, baseQuickAdapter, LinearLayoutManager.VERTICAL);
        }

    }


    /**
     * 初始化recyclerview LinearLayoutManager
     */
    public void initRecyclerview(RecyclerView recyclerView, BaseQuickAdapter baseQuickAdapter, @RecyclerView.Orientation int orientation) {
        LinearLayoutManager managere = new LinearLayoutManager(getContext(), orientation, false);
        //        baseQuickAdapter.setEmptyView(getAdapterEmptyView("一条信息也没有",0));
        recyclerView.setLayoutManager(managere);
        recyclerView.setAdapter(baseQuickAdapter);
    }
    protected abstract LinearLayoutManager getBaseAdapterManager();

    protected abstract BaseQuickAdapter getBaseQuickAdapter();


    protected abstract int getBottomSheetDialogLayout();

    @Override
    public void onStart() {
        super.onStart();
//         <!--   解决bottomsheetdialog顶部不能圆角的问题-->
        getDialog().getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
//        // 设置软键盘不自动弹出
//        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
//        BottomSheetDialog dialog = (BottomSheetDialog) getDialog();
//        FrameLayout bottomSheet = dialog.getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet);
//        if (bottomSheet != null) {
//            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomSheet.getLayoutParams();
//            layoutParams.height = getHeight();
//            behavior = BottomSheetBehavior.from(bottomSheet);
//            // 初始为展开状态
//            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//        }
    }

}
