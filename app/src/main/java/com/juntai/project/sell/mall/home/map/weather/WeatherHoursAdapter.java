package com.juntai.project.sell.mall.home.map.weather;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.bean.weather.WeatherHoursBean;
import com.juntai.disabled.basecomponent.utils.DisplayUtil;
import com.juntai.disabled.bdmap.utils.DateUtil;
import com.juntai.project.sell.mall.R;

import java.util.List;

/**
 * author:wong
 * Date: 2019/4/29
 * Description:
 */
public class WeatherHoursAdapter extends BaseQuickAdapter<WeatherHoursBean, BaseViewHolder> {

    public WeatherHoursAdapter(int layoutResId, @Nullable List<WeatherHoursBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WeatherHoursBean item) {
        helper.setText(R.id.weather_hours_item_time, DateUtil.hourOfDay(item.getTime()))
                .setText(R.id.weather_hours_item_temp,item.getTemp()+"°")
                .setText(R.id.weather_hours_item_aqi,item.getAqi());
        ((ImageView)helper.getView(R.id.weather_hours_item_skycon)).setImageResource(WeatherHelper.switchSkyconInt(item.getSkycon()));
        helper.setText(R.id.weather_hours_item_wind,item.getWindSpeed());
        initViewLeftDrawable(helper.getView(R.id.weather_hours_item_wind),WeatherHelper.switchWindDirIcon(item.getWindDirection()),9,9);
    }

    /**
     * 设置左边图标
     * @param textView
     * @param drawableId
     */
    public void initViewLeftDrawable(TextView textView, int drawableId, int width, int height) {
        Drawable drawable = mContext.getResources().getDrawable(drawableId);
        drawable.setBounds(0, 0, DisplayUtil.dp2px(mContext, width), DisplayUtil.dp2px(mContext, height));//第一个 0 是距左边距离，第二个 0 是距上边距离，40 分别是长宽
        textView.setCompoundDrawables(drawable, null, null, null);//放左边
    }
}
