package com.juntai.project.sell.mall.base.selectPics;

import com.juntai.project.sell.mall.R;

/**
 * @Author: tobato
 * @Description: 作用描述  选择大图 一行一个那种
 * @CreateDate: 2022/6/9 16:55
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/9 16:55
 */
public class SelectBigPicFragment  extends BaseSelectPhotosFragment{
    @Override
    protected ShowSelectedPicsAdapter getPicAdapter() {
        return new ShowSelectedPicsAdapter(R.layout.show_selected_pic_item_big,true);
    }
}
