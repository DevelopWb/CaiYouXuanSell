package com.juntai.project.sell.mall.home.map.weather;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.bean.weather.WeatherEveryDayBean;
import com.juntai.project.sell.mall.R;

import java.util.List;

/**
 * Describe: 其他指数
 * Create by zhangzhenlong
 * 2020-6-27
 * email:954101549@qq.com
 */
public class WeatherOtherDataAdapter extends BaseQuickAdapter<WeatherEveryDayBean, BaseViewHolder> {

    public WeatherOtherDataAdapter(int layoutResId, @Nullable List<WeatherEveryDayBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WeatherEveryDayBean item) {
        helper.setText(R.id.item_title_tv,item.getName())
                .setText(R.id.item_content_tv,item.getContent());
    }
}
