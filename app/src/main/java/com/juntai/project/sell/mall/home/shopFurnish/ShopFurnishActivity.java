package com.juntai.project.sell.mall.home.shopFurnish;

import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.project.sell.mall.AppHttpPathMall;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseAppActivity;
import com.juntai.project.sell.mall.base.displayPicVideo.PicVideoDisplayActivity;
import com.juntai.project.sell.mall.beans.sell.ShopDetailBean;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.juntai.project.sell.mall.home.shop.ShopPresent;
import com.juntai.project.sell.mall.home.shop.ijkplayer.PlayerLiveActivity;
import com.juntai.project.sell.mall.utils.bannerImageLoader.BannerObject;
import com.juntai.project.sell.mall.utils.bannerImageLoader.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @aouther tobato
 * @description 描述  店铺首页
 * @date 2022/5/8 8:50
 */
public class ShopFurnishActivity extends BaseAppActivity<ShopPresent> implements HomePageContract.IHomePageView, View.OnClickListener {

    private ImageView mShopOwnerHeadIv;
    /**
     * 店铺名称
     */
    private TextView mShopNameTv;
    private TextView mAddBannerPicsTv;
    /**
     * 开店时间
     */
    private TextView mShopCreatTimeTv, mScoreTv;
    /**
     * 描述
     */
    private TextView mShopDesTv;
    private Banner mShopBanner;

    private ShopDetailBean.DataBean shopBean;
    private List<BannerObject> bannerPics;
    private GlideImageLoader imageLoader;
    private ShopCommodityFragment shopCommodityFragment;

    @Override
    protected ShopPresent createPresenter() {
        return new ShopPresent();
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_furnish_shop;
    }

    @Override
    public void initView() {
        setTitleName("店铺装修");
        mShopOwnerHeadIv = (ImageView) findViewById(R.id.shop_owner_head_iv);
        mShopNameTv = (TextView) findViewById(R.id.shop_name_tv);
        mAddBannerPicsTv = (TextView) findViewById(R.id.add_banner_pics_tv);
        mShopCreatTimeTv = (TextView) findViewById(R.id.shop_creat_time_tv);
        mScoreTv = (TextView) findViewById(R.id.shop_score_tv);
        mShopDesTv = (TextView) findViewById(R.id.shop_des_tv);
        mShopBanner = (Banner) findViewById(R.id.shop_banner);
        mAddBannerPicsTv.setOnClickListener(this);
        shopCommodityFragment = (ShopCommodityFragment) getSupportFragmentManager().findFragmentById(R.id.shop_commodity_ft);
    }

    private void initBanner(ShopDetailBean.DataBean shopBean) {
        List<BannerObject> bannerObjects = new ArrayList<>();
        if (bannerPics == null) {
            bannerPics = new ArrayList<>();
        }
        bannerPics.clear();
        mShopBanner.isAutoPlay(false);
        mShopBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                // : 2022/5/4 查看图片大图
                BannerObject bannerObject = bannerObjects.get(position);
                switch (bannerObject.getEventKey()) {
                    case BannerObject.BANNER_TYPE_IMAGE:
                    case BannerObject.BANNER_TYPE_VIDEO:
                        // : 2022/5/21 展示图片大图
                        PicVideoDisplayActivity.startPicVideoPlayActivity(mContext, bannerPics, bannerPics.size() == bannerObjects.size() ? position : position - 1);
                        break;
                    case BannerObject.BANNER_TYPE_RTMP:
                        BannerObject.StreamBean streamBean = bannerObject.getStreamBean();
                        PlayerLiveActivity.startPlayerLiveActivity(mContext, streamBean.getCameraNum(), streamBean.getCameraCover(), streamBean.getRtmpUrl());

                        break;
                    default:
                        break;
                }

            }
        });
        imageLoader = new GlideImageLoader().setOnFullScreenCallBack(new GlideImageLoader.OnFullScreenListener() {
            @Override
            public void onFullScreen() {

            }
        });

        mShopBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (0 != i) {
                    // : 2022/5/22 如果视频在播放 释放资源
                    imageLoader.pause();
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

//        if (!TextUtils.isEmpty(shopBean.getCameraCover()) && !TextUtils.isEmpty(shopBean.getCameraNumber())) {
//            bannerObjects.add(new BannerObject(BannerObject.BANNER_TYPE_RTMP, new BannerObject.StreamBean(shopBean.getCameraNumber(), shopBean.getCameraCover(), shopBean.getCameraUrl())));
//        }

        String bannerPic = shopBean.getShopImg();
        if (!TextUtils.isEmpty(bannerPic)) {
            if (bannerPic.contains(",")) {
                String[] pics = bannerPic.split(",");
                for (String pic : pics) {
                    bannerObjects.add(new BannerObject(BannerObject.BANNER_TYPE_IMAGE, pic));
                    bannerPics.add(new BannerObject(BannerObject.BANNER_TYPE_IMAGE, pic));
                }
            } else {
                bannerPics.add(new BannerObject(BannerObject.BANNER_TYPE_IMAGE, bannerPic));
                bannerObjects.add(new BannerObject(BannerObject.BANNER_TYPE_IMAGE, bannerPic));

            }
        }
        mShopBanner.setImageLoader(imageLoader);
        mShopBanner.update(bannerObjects);

    }

    /**
     * 配置基本数据
     *
     * @param shopBean
     */
    public void initOwnerBaseInfo(ShopDetailBean.DataBean shopBean) {
        this.shopBean = shopBean;
        ImageLoadUtil.loadSquareImageHasCorner(mContext, shopBean.getHeadPortrait(), mShopOwnerHeadIv);
        mShopNameTv.setText(shopBean.getName());
        mShopCreatTimeTv.setText("开店时间:" + shopBean.getCreateTime());
        mScoreTv.setText("店铺得分:" + shopBean.getShopFraction());
        mShopDesTv.setText("店铺简介:" + shopBean.getIntroduction());
        initBanner(shopBean);

    }

    @Override
    public void initData() {

    }


    @Override
    public void onSuccess(String tag, Object o) {
        switch (tag) {
            case AppHttpPathMall.UPLOAD_MORE_PIC:
                List<String> stringList = (List<String>) o;
                if (stringList != null && !stringList.isEmpty()) {
                    // : 2022/6/24 调用上传banner图片的接口
                    mPresenter.addShopBannerPics(getBaseBuilder().add("type", "2")
                            .build(), stringList, AppHttpPathMall.ADD_SHOP_BANNERS
                    );
                }
                break;
            case AppHttpPathMall.ADD_SHOP_BANNERS:
                shopCommodityFragment.lazyLoad();
                break;
            default:
                break;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_banner_pics_tv:
                // : 2022/6/24 添加banner图片
                choseImage(0, ShopFurnishActivity.this, 3);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onPicsAndEmpressed(List<String> icons) {
        if (icons != null) {
            mPresenter.uploadFile(icons, AppHttpPathMall.UPLOAD_MORE_PIC);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (imageLoader != null) {
            imageLoader.release();

        }
    }


}
