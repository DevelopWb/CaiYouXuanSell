package com.juntai.project.sell.mall.order.refund;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.base.BaseBottomSheetFragment;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.beans.order.RefundReasonBean;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/5/15 14:18
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/15 14:18
 */
public class SelectRefundReasonFragment extends BaseBottomSheetFragment {

    private List<RefundReasonBean.DataDTO> datas;
    OnReasonSelectedCallBack onReasonSelectedCallBack;

    public void setOnReasonSelectedCallBack(OnReasonSelectedCallBack onReasonSelectedCallBack) {
        this.onReasonSelectedCallBack = onReasonSelectedCallBack;
    }

    public void setDatas(List<RefundReasonBean.DataDTO> datas) {
        this.datas = datas;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.base_bottomsheet_layout, null);
        initView(view);
        view.findViewById(R.id.selector_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        baseQuickAdapter.setNewData(datas);
        return view;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        baseQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<RefundReasonBean.DataDTO> datas = adapter.getData();
                RefundReasonBean.DataDTO data = (RefundReasonBean.DataDTO) adapter.getItem(position);
                for (int i = 0; i < datas.size(); i++) {
                    RefundReasonBean.DataDTO dataDTO = datas.get(i);
                    if (i == position) {
                        dataDTO.setSelect(true);
                    } else {
                        dataDTO.setSelect(false);
                    }
                }
                adapter.notifyDataSetChanged();
                if (onReasonSelectedCallBack != null) {
                    onReasonSelectedCallBack.selectedReason(data);
                }
                dismiss();
            }
        });


    }

    @Override
    protected LinearLayoutManager getBaseAdapterManager() {
        return null;
    }

    @Override
    protected BaseQuickAdapter getBaseQuickAdapter() {
        return new SelectRefundReasonAdapter(R.layout.single_text_select_layout);
    }

    @Override
    protected int getBottomSheetDialogLayout() {
        return 0;
    }


    public interface OnReasonSelectedCallBack {

        void selectedReason(RefundReasonBean.DataDTO dataDTO);
    }
}
