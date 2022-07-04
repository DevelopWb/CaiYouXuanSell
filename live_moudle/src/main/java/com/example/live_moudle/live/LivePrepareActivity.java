package com.example.live_moudle.live;

import android.content.Intent;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.live_moudle.LivePresent;
import com.example.live_moudle.R;
import com.example.live_moudle.bean.LiveResultBean;
import com.example.live_moudle.bean.LiveTypeListBean;
import com.example.live_moudle.net.AppHttpPathLive;
import com.juntai.disabled.basecomponent.base.BaseActivity;
import com.juntai.disabled.basecomponent.base.BaseSelectPicsActivity;
import com.juntai.disabled.basecomponent.bean.ContactBean;
import com.juntai.disabled.basecomponent.mvp.IView;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.disabled.basecomponent.utils.ToastUtils;

import java.util.List;

/**
 * @aouther tobato
 * @description 描述  直播
 * @date 2022/6/7 16:32
 */
public class LivePrepareActivity extends BaseSelectPicsActivity<LivePresent> implements IView, View.OnClickListener {
    private BottomSheetDialog bottomSheetDialog;//直播类型选择弹窗

    private ImageView mLiveCoverIv;
    private LinearLayout mCoverLayout;
    /**
     * 请输入直播标题
     */
    private EditText mLiveTitle;
    /**
     * 请选择直播类型
     */
    private TextView mLiveType;
    /**
     * 开启直播
     */
    private TextView mOpenLiveBtn;
    private String liveCover;
    private LiveTypesAdapter liveTypesAdapter;
    private List<LiveTypeListBean.DataBean> liveTypes;
    private int liveTypeId = 0;

    @Override
    protected LivePresent createPresenter() {
        return new LivePresent();
    }

    @Override
    protected boolean canCancelLoadingDialog() {
        return false;
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_live_prepare;
    }

    @Override
    public void initView() {
        setTitleName("开启直播");
        mLiveCoverIv = (ImageView) findViewById(R.id.live_cover_big);
        mCoverLayout = (LinearLayout) findViewById(R.id.cover_layout);
        mCoverLayout.setOnClickListener(this);
        mLiveTitle = (EditText) findViewById(R.id.live_title);
        mLiveType = (TextView) findViewById(R.id.live_type);
        mLiveType.setOnClickListener(this);
        mOpenLiveBtn = (TextView) findViewById(R.id.open_live_btn);
        mOpenLiveBtn.setOnClickListener(this);
    }

    @Override
    public void initData() {
        mPresenter.getLiveType(AppHttpPathLive.GET_LIVE_TYPE);
    }


    @Override
    public void onSuccess(String tag, Object o) {
        switch (tag) {
            case AppHttpPathLive.UPLOAD_PICS:
                List<String> paths = (List<String>) o;
                if (paths != null && !paths.isEmpty()) {
                    liveCover = paths.get(0);
                    ImageLoadUtil.loadImage(mContext,liveCover, mLiveCoverIv);
                }
                break;

            case AppHttpPathLive.GET_LIVE_TYPE:
                LiveTypeListBean liveTypeListBean = (LiveTypeListBean) o;
                if (liveTypeListBean != null) {
                    liveTypes = liveTypeListBean.getData();
                }

                break;
            case AppHttpPathLive.START_LIVE:
                LiveResultBean liveResultBean = (LiveResultBean) o;
                if (liveResultBean != null) {
                    LiveResultBean.DataBean resultBean = liveResultBean.getData();

                    startActivity(new Intent(mContext,StartLiveActivity.class).putExtra(BaseActivity.BASE_PARCELABLE,resultBean));
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onPicsAndEmpressed(List<String> icons) {
        String pic = icons.get(0);
        mPresenter.uploadFile(AppHttpPathLive.UPLOAD_PICS, pic);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.cover_layout) {
            choseImage(0, LivePrepareActivity.this, 1);
        } else if (id == R.id.live_type) {// : 2022/7/2 获取直播类型
            showSheetDialog();
        } else if (id == R.id.open_live_btn) {
            if (TextUtils.isEmpty(liveCover)) {
                ToastUtils.toast(mContext, "请添加直播封面");
                return;
            }
            if (TextUtils.isEmpty(getTextViewValue(mLiveTitle))) {
                ToastUtils.toast(mContext, "请输入直播标题");
                return;
            }
            if (0 == liveTypeId) {
                ToastUtils.toast(mContext, "请选择直播类型");
                return;
            }
            mPresenter.startLive(mPresenter.getBaseBuilder()
                    .add("title", getTextViewValue(mLiveTitle))
                    .add("coverImg", liveCover)
                    .add("type", String.valueOf(liveTypeId)).build(), AppHttpPathLive.START_LIVE
            );
        }
    }


    /**
     * 类型弹窗
     */
    private void showSheetDialog() {
        if (bottomSheetDialog != null) {
            bottomSheetDialog.show();
            return;
        }
        View view = View.inflate(this, R.layout.dialog_live_type_choose, null);
        RecyclerView mItemRecyclerView = (RecyclerView) view.findViewById(R.id.item_recycler_view);
        liveTypesAdapter = new LiveTypesAdapter(R.layout.item_live_type_dialog, liveTypes);
        mItemRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));
        mItemRecyclerView.setAdapter(liveTypesAdapter);
        liveTypesAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                liveTypesAdapter.setChoosedItem(liveTypesAdapter.getItem(position));
                liveTypeId = liveTypesAdapter.getItem(position).getId();
                mLiveType.setText(liveTypesAdapter.getItem(position).getName());
                liveTypesAdapter.notifyDataSetChanged();
                bottomSheetDialog.dismiss();
            }
        });

        bottomSheetDialog = new BottomSheetDialog(this, R.style.shop_ActionSheetDialogStyle);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.show();
    }

    @Override
    protected String getUpdateHttpUrl() {
        return null;
    }

    @Override
    protected String getDownloadTitleRightName() {
        return null;
    }

    @Override
    protected String getDownLoadPath() {
        return null;
    }
}
