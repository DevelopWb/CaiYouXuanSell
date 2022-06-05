package com.juntai.project.sell.mall.base.displayPicVideo;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.chat.MainContract;
import com.juntai.disabled.PicVideoViewPagerAdapter;
import com.juntai.disabled.basecomponent.mvp.BasePresenter;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.basecomponent.utils.UrlFormatUtil;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseAppActivity;
import com.juntai.project.sell.mall.utils.bannerImageLoader.BannerObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @aouther tobato
 * @description 描述  图片和视频全屏展示
 * @date 2022-02-25 16:04
 */
public class PicVideoDisplayActivity extends BaseAppActivity implements MainContract.IBaseView {
    PicVideoViewPagerAdapter myViewPagerAdapter;
    ViewPager viewPager;
    List<Fragment> fragmentList = new ArrayList<>();
    private List<BannerObject> bannerObjects;
    private String diaplayPath;

    public static String IMAGEITEM = "imageitem";


    public static void startPicVideoPlayActivity(Context mContext, List<BannerObject> bannerObjects, int position) {
        Intent intent = new Intent(mContext,PicVideoDisplayActivity.class);
        intent.putParcelableArrayListExtra(BASE_PARCELABLE, (ArrayList<BannerObject>) bannerObjects)
                .putExtra(IMAGEITEM, position);
        mContext.startActivity(intent);
    }


    @Override
    public int getLayoutView() {
        return R.layout.activity_imagezoom;
    }

    @Override
    public void initView() {
        initToolbarAndStatusBar(false);
        viewPager = findViewById(R.id.imagezoom_viewpager);
        mImmersionBar.reset().statusBarColor(R.color.black)
                .statusBarDarkFont(true)
                .init();
    }


    @Override
    public void initData() {
        bannerObjects =getIntent().getParcelableArrayListExtra(BASE_PARCELABLE);
        if (bannerObjects == null) {
            ToastUtils.toast(mContext, "请传入需要展示图片的路径");
            finish();
            return;
        }
        int item = getIntent().getIntExtra(IMAGEITEM, 0);
        if (item >= bannerObjects.size()) {
            ToastUtils.toast(mContext, "图片的索引越界");
            finish();
            return;
        }

        diaplayPath = UrlFormatUtil.getImageOriginalUrl((String) bannerObjects.get(item).getPicPath());
        for (BannerObject bannerObject : bannerObjects) {
            switch (bannerObject.getEventKey()) {
                case BannerObject.BANNER_TYPE_IMAGE:
                    fragmentList.add(DisplayPhotoFragment.getInstance(UrlFormatUtil.getImageOriginalUrl((String) bannerObject.getPicPath())));
                    break;
                case BannerObject.BANNER_TYPE_VIDEO:
                    fragmentList.add(DisplayVideoFragment.getInstance(bannerObject));
                    break;
                default:
                    break;
            }
        }
        myViewPagerAdapter = new PicVideoViewPagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.setCurrentItem(item);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                diaplayPath = UrlFormatUtil.getImageOriginalUrl((String) bannerObjects.get(i).getPicPath());

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {
//                EventManager.getEventBus().post(DisplayVideoFragment.STOP_VIDEO_PLAY);

            }
        });
    }


    @Override
    protected String getDownloadTitleRightName() {
        return "保存图片";
    }

    @Override
    protected String getDownLoadPath() {
        return diaplayPath;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected boolean canCancelLoadingDialog() {
        return true;
    }

    @Override
    public void onSuccess(String tag, Object o) {

    }
}
