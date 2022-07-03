package com.example.live_moudle.live;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.live_moudle.LivePresent;
import com.example.live_moudle.R;
import com.juntai.disabled.basecomponent.base.BaseMvpActivity;
import com.juntai.disabled.basecomponent.mvp.IView;


/**
 * @aouther tobato
 * @description 描述 直播结束
 * @date 2022/7/2 17:08
 */
public class LiveEndActivity extends BaseMvpActivity<LivePresent> implements IView, View.OnClickListener {

    private ImageView mLiveCloseBtn;
    /**
     * 00:00~05:00
     */
    private TextView mStartEndTimeTv;
    private ImageView mHeadImage;
    /**
     * 姓名
     */
    private TextView mUserNameTv;
    /**
     * 01:20:20
     */
    private TextView mDurationTv;

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
        return R.layout.activity_live_end;
    }

    @Override
    public void initView() {
        getToolbar().setVisibility(View.GONE);
        mLiveCloseBtn = (ImageView) findViewById(R.id.live_close_btn);
        mLiveCloseBtn.setOnClickListener(this);
        mStartEndTimeTv = (TextView) findViewById(R.id.start_end_time_tv);
        mHeadImage = (ImageView) findViewById(R.id.head_image);
        mUserNameTv = (TextView) findViewById(R.id.user_name_tv);
        mDurationTv = (TextView) findViewById(R.id.duration_tv);
    }

    @Override
    public void initData() {
//        mPresenter.stopPersonalLive(mPresenter.getPublishMultipartBody().build(), ILiveContract.STOP_PERSONAL_LIVE);
    }

    @Override
    public void onSuccess(String tag, Object o) {
        switch (tag) {
            default:
                break;
//            case ILiveContract.STOP_PERSONAL_LIVE:
//                EndLiveBean endLiveBean = (EndLiveBean) o;
//                mDurationTv.setText(endLiveBean.getData().getDuration());
//                ImageLoadUtil.loadCircularImage(mContext.getApplicationContext(), MyApp.getUserHeadImg(),
//                        R.mipmap.default_user_head_icon, R.mipmap.default_user_head_icon, mHeadImage);
//                mUserNameTv.setText(MyApp.getUser().getData().getNickname());
//                mStartEndTimeTv.setText(endLiveBean.getData().getStartTime() + "~" + DateUtil.getCurrentTime("HH:mm"));
//                break;
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.live_close_btn) {
            onBackPressed();
        }
    }
}
