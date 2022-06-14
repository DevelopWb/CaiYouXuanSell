package com.juntai.project.sell.mall.base.selectPics;

import android.os.Bundle;

import com.juntai.project.sell.mall.R;

/**
 * @aouther tobato
 * @description 描述  这是一个选择图片的fragment 图片的高和宽通过设置一行的个数 间距 magin等动态控制
 * @date 2020/3/17 14:41
 * <p>
 * 例子
 * FragmentManager fragmentManager = getSupportFragmentManager();
 * //开启事务
 * FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
 * beginTransaction.replace(R.id.id_card_positive_fl, SelectPhotosFragment.newInstance().setPhotoTitle("身份证正面照片")
 * .setPhotoSpace(45)
 * .setMaxCount(1));
 * beginTransaction.replace(R.id.id_card_obverse_fl, SelectPhotosFragment.newInstance().setPhotoTitle("身份证反面照片")
 * .setPhotoSpace(45)
 * .setMaxCount(1));
 * beginTransaction.replace(R.id.id_card_with_hand_fl, SelectPhotosFragment.newInstance().setPhotoTitle(getString(R
 * .string.id_card_notice))
 * .setPhotoSpace(45)
 * .setMaxCount(1));
 * //最后一步 记得commit
 * beginTransaction.commit();
 */
public class SelectPhotosFragment extends BaseSelectPhotosFragment {


    /**
     * 在这里我们提供一个静态的方法来实例化PageFragment
     * 在这里我们传入一个参数，用来得到title，然后我们拿到这个title设置给内容
     *
     * @return
     */
    public static SelectPhotosFragment newInstance(String tag) {
        Bundle bundle = new Bundle();
        bundle.putString(BASE_STRING,tag);
        SelectPhotosFragment fragment = new SelectPhotosFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected ShowSelectedPicsAdapter getPicAdapter() {
        return new ShowSelectedPicsAdapter(R.layout.show_selected_pic_item,false);
    }
}
