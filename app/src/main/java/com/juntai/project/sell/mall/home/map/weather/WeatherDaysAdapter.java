package com.juntai.project.sell.mall.home.map.weather;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.bean.weather.WeatherDaysBean;
import com.juntai.disabled.basecomponent.utils.DisplayUtil;
import com.juntai.disabled.basecomponent.utils.LogUtil;
import com.juntai.disabled.bdmap.utils.DateUtil;
import com.juntai.project.sell.mall.R;

import java.util.List;


/**
 * author:wong
 * Date: 2019/4/29
 * Description:
 */
public class WeatherDaysAdapter extends BaseQuickAdapter<WeatherDaysBean, BaseViewHolder> {
    public WeatherDaysAdapter(int layoutResId, @Nullable List<WeatherDaysBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WeatherDaysBean item) {
        LogUtil.d(item.getDate());
        if (helper.getAdapterPosition() == 0){
            helper.setText(R.id.weather_days_item_date, "今天·"+ WeatherHelper.switchSkycon(item.getSkycon()));
        }else if(helper.getAdapterPosition() == 1){
            helper.setText(R.id.weather_days_item_date, "明天·"+ WeatherHelper.switchSkycon(item.getSkycon()));
        }else {
            helper.setText(R.id.weather_days_item_date, DateUtil.dateToDay(item.getDate())+"·" + WeatherHelper.switchSkycon(item.getSkycon()));
        }
        helper.setText(R.id.weather_days_item_temp, item.getMaxTemp() + "° / " + item.getMinTemp() + "°");
        helper.setText(R.id.weather_days_item_aqi,item.getAqi());
        initViewLeftDrawable(helper.getView(R.id.weather_days_item_date),WeatherHelper.switchSkyconInt(item.getSkycon()),25,25);
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
