package com.juntai.project.sell.mall.base.search;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.juntai.disabled.basecomponent.utils.DisplayUtil;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.customview.flowlayout.FlowLayout;
import com.juntai.project.sell.mall.base.customview.flowlayout.TagAdapter;
import com.juntai.project.sell.mall.base.customview.flowlayout.TagFlowLayout;
import com.juntai.project.sell.mall.utils.StringTools;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述   搜索控件
 * @CreateDate: 2020/3/15 9:18
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/3/15 9:18
 */
public abstract class BaseSearchHeadFragment extends Fragment implements View.OnClickListener {

    private View view;
    /**
     * 返回
     */
    private TextView mBackTv;
    private SearchView mSearchContentSv;
    /**
     * 搜索
     */
    private TextView mSearchTv;
    private ImageView mDeleteHistoryIv;
    private TagFlowLayout tagFlowLayout;
    private TagAdapter mRecordsAdapter;
    private ImageView mShowMoreArrowIv;
    private ConstraintLayout mHisRecordCl;

    private OnSearchCallBack searchCallBack;
    private LinearLayout mHisLl;

    @Override
    public void onAttach(Context context) {
        this.searchCallBack = (OnSearchCallBack) context;
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Hawk.delete(getHiskey());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_search_head_layout, null);
        initView(view);
        return view;
    }
    /**
     * 设置图标
     *
     * @param textView
     * @param drawableId
     */
    private void initViewLeftDrawable(TextView textView, int drawableId) {
        Drawable drawable = getResources().getDrawable(drawableId);
        drawable.setBounds(0, 0, DisplayUtil.dp2px(getContext(), 23), DisplayUtil.dp2px(getContext(), 23));//第一个 0 是距左边距离，第二个 0 是距上边距离，40 分别是长宽
        textView.setCompoundDrawables(drawable, null, null, null);//只放左边
    }
    private void initView(View view) {
        mBackTv = (TextView) view.findViewById(R.id.back_tv);
        initViewLeftDrawable(mBackTv,R.drawable.app_back);
        mBackTv.setOnClickListener(this);
        mSearchContentSv = (SearchView) view.findViewById(R.id.search_content_sv);
        mSearchTv = (TextView) view.findViewById(R.id.search_tv);
        mSearchTv.setOnClickListener(this);
        mDeleteHistoryIv = (ImageView) view.findViewById(R.id.delete_history_iv);
        mDeleteHistoryIv.setOnClickListener(this);
        tagFlowLayout = (TagFlowLayout) view.findViewById(R.id.history_item_tfl);
        mShowMoreArrowIv = (ImageView) view.findViewById(R.id.show_more_arrow_iv);
        mHisRecordCl = (ConstraintLayout) view.findViewById(R.id.his_record_cl);
        mHisLl = (LinearLayout) view.findViewById(R.id.his_ll);
        initFlowLayoutData();
        mSearchContentSv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (!StringTools.isStringValueOk(s)) {
                    ToastUtils.warning(getContext(), "请输入要搜索的内容");
                    return false;
                }
                initSearchClick(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s)) {
                    initSearchClick(s);
                    return false;
                }

                return false;
            }
        });
    }

    /**
     * 初始化流式布局数据
     */
    private void initFlowLayoutData() {
        List<String> recordList = Hawk.get(getHiskey());
        if (recordList != null && !recordList.isEmpty()) {
            mHisRecordCl.setVisibility(View.VISIBLE);
        } else {
            mHisRecordCl.setVisibility(View.GONE);
        }
        //创建历史标签适配器
        //为标签设置对应的内容
        mRecordsAdapter = new TagAdapter<String>(recordList) {

            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.search_item_textview,
                        tagFlowLayout, false);
                //为标签设置对应的内容
                tv.setText(s);
                return tv;
            }
        };


        tagFlowLayout.setAdapter(mRecordsAdapter);
        tagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public void onTagClick(View view, int position, FlowLayout parent) {
                List<String> his_data = Hawk.get(getHiskey());
                mSearchContentSv.setQuery(his_data.get(position), true);
            }
        });
        //删除某个条目
        tagFlowLayout.setOnLongClickListener(new TagFlowLayout.OnLongClickListener() {
            @Override
            public void onLongClick(View view, final int position) {
                showDialog("确定要删除该条历史记录？", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //删除某一条记录
                        deletHisItem(position);
                    }
                });
            }
        });

        //view加载完成时回调
        tagFlowLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                boolean isOverFlow = tagFlowLayout.isOverFlow();
                boolean isLimit = tagFlowLayout.isLimit();
                if (isLimit && isOverFlow) {
                    mShowMoreArrowIv.setVisibility(View.VISIBLE);
                } else {
                    mShowMoreArrowIv.setVisibility(View.GONE);
                }
            }
        });

        mShowMoreArrowIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tagFlowLayout.setLimit(false);
                mRecordsAdapter.notifyDataChanged();
                mHisRecordCl.setVisibility(View.GONE);
            }
        });

    }

    protected abstract String getHiskey();

    /**
     * 删除条目的对话框
     *
     * @param dialogTitle
     * @param onClickListener
     */
    private void showDialog(String dialogTitle, @NonNull DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(dialogTitle);
        builder.setPositiveButton("确定", onClickListener);
        builder.setNegativeButton("取消", null);
        builder.create().show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back_tv:
                getActivity().onBackPressed();
                break;
            case R.id.search_tv:
                //搜索

                String content = mSearchContentSv.getQuery().toString();
                if (!StringTools.isStringValueOk(content)) {
                    ToastUtils.warning(getContext(), "请输入要搜索的内容");
                    return;
                }
                initSearchClick(content);
                break;
            case R.id.delete_history_iv:
                //删除所有的记录
                showDialog("确定要删除所有历史记录？", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //删除所有记录
                        Hawk.delete(getHiskey());
                        initFlowLayoutData();
                    }
                });

                break;
        }
    }

    private void initSearchClick(String content) {
        //将搜索的内容添加到本地
        List<String> his_data = null;
        if (Hawk.contains(getHiskey())) {
            his_data = Hawk.get(getHiskey());
        } else {
            his_data = new ArrayList<>();
        }
        if (!TextUtils.isEmpty(content)&&!his_data.contains(content)) {
            his_data.add(0, content);
            Hawk.put(getHiskey(), his_data);
            initFlowLayoutData();
        }
        if (searchCallBack != null) {
            searchCallBack.onSearch(content);
            mSearchContentSv.setFocusable(false);
            mSearchContentSv.setFocusableInTouchMode(false);
            mSearchContentSv.clearFocus();
            // : 2022/5/28 隐藏历史记录
            if (TextUtils.isEmpty(content)) {
                mHisLl.setVisibility(View.VISIBLE);
            }else {
                mHisLl.setVisibility(View.GONE);

            }
        }
    }

    /**
     * 删除历史条目
     *
     * @param position
     */
    private void deletHisItem(int position) {
        List<String> his_data = Hawk.get(getHiskey());
        his_data.remove(position);
        Hawk.put(getHiskey(), his_data);
        initFlowLayoutData();
    }

    /**
     * 搜索的回调
     */
    public interface OnSearchCallBack {
        void onSearch(String textContent);
    }
}
