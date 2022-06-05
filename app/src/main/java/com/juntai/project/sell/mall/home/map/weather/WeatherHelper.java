package com.juntai.project.sell.mall.home.map.weather;


import com.juntai.disabled.bdmap.utils.DateUtil;
import com.juntai.project.sell.mall.R;

/**
 * Describe:天气工具类
 * Create by zhangzhenlong
 * 2020-6-27
 * email:954101549@qq.com
 */
public class WeatherHelper {
    /**
     * 更改天气背景色
     * @param skycon
     */
    public static int checkBg(String skycon) {
        int bg;
        if (DateUtil.getHour() < 18 && DateUtil.getHour() > 7) {
            switch (skycon) {
                case "CLEAR_DAY":
                    bg = R.mipmap.weather_sunny_bg;
                    break;
                case "CLEAR_NIGHT":
                    bg = R.mipmap.weather_sunny_nightly;
                    break;
                case "PARTLY_CLOUDY_DAY":
                    bg = R.mipmap.weather_cloudy_bg;
                    break;
                case "PARTLY_CLOUDY_NIGHT":
                    bg = R.mipmap.weather_cloudy_nightly;
                    break;
                case "CLOUDY":
                    bg = R.mipmap.weather_cloudy_bg;
                    break;
                case "RAIN":
                case "LIGHT_RAIN":
                case "MODERATE_RAIN":
                case "HEAVY_RAIN":
                case "STORM_RAIN":
                    bg = R.mipmap.weather_rain_bg;
                    break;
                case "SNOW":
                case "LIGHT_SNOW":
                case "MODERATE_SNOW":
                case "HEAVY_SNOW":
                case "STORM_SNOW":
                    bg = R.mipmap.weather_snow_bg;
                    break;
                case "WIND":
                    bg = R.mipmap.weather_wind_bg;
                    break;
                case "HAZE":
                case "DUST":
                case "SAND":
                case "LIGHT_HAZE":
                case "MODERATE_HAZE":
                case "HEAVY_HAZE":
                case "FOG":
                    bg = R.mipmap.weather_haze_bg;
                    break;
                default:
                    bg = R.mipmap.weather_sunny_bg;
                    break;
            }
        } else {
            switch (skycon) {
                case "CLEAR_DAY":
                    bg = R.mipmap.weather_sunny_nightly;
                    break;
                case "CLEAR_NIGHT":
                    bg = R.mipmap.weather_sunny_nightly;
                    break;
                case "PARTLY_CLOUDY_DAY":
                    bg = R.mipmap.weather_cloudy_nightly;
                    break;
                case "PARTLY_CLOUDY_NIGHT":
                    bg = R.mipmap.weather_cloudy_nightly;
                    break;
                case "CLOUDY":
                    bg = R.mipmap.weather_cloudy_nightly;
                    break;
                case "RAIN":
                case "LIGHT_RAIN":
                case "MODERATE_RAIN":
                case "HEAVY_RAIN":
                case "STORM_RAIN":
                    bg = R.mipmap.weather_rain_nightly;
                    break;
                case "SNOW":
                case "LIGHT_SNOW":
                case "MODERATE_SNOW":
                case "HEAVY_SNOW":
                case "STORM_SNOW":
                    bg = R.mipmap.weather_snow_nightly;
                    break;
                case "WIND":
                    bg = R.mipmap.weather_wind_nightly;
                    break;
                case "HAZE":
                case "DUST":
                case "SAND":
                case "LIGHT_HAZE":
                case "MODERATE_HAZE":
                case "HEAVY_HAZE":
                case "FOG":
                    bg = R.mipmap.weather_haze_nightly;
                    break;
                default:
                    bg = R.mipmap.weather_sunny_nightly;
                    break;
            }
        }
        return bg;
    }

    /**
     * 天气图标选择
     * @param skycon
     * @return
     */
    public static int switchSkyconInt(String skycon) {
        switch (skycon) {
            case "CLEAR_DAY":
                return R.drawable.weather_sunny;
            case "CLEAR_NIGHT":
                return R.drawable.weather_moon;

            case "PARTLY_CLOUDY_DAY":
                return R.drawable.weather_cloudy_tag;

            case "PARTLY_CLOUDY_NIGHT":
                return R.drawable.weather_cloudy_tag;

            case "CLOUDY":
                return R.drawable.weather_partly_cloudy;
            case "RAIN":
            case "LIGHT_RAIN":
            case "MODERATE_RAIN":
            case "HEAVY_RAIN":
            case "STORM_RAIN":
                return R.drawable.weather_rain;

            case "SNOW":
            case "LIGHT_SNOW":
            case "MODERATE_SNOW":
            case "HEAVY_SNOW":
            case "STORM_SNOW":
                return R.drawable.weather_snow;

            case "WIND":
                return R.drawable.weather_wind;
            case "HAZE":
            case "DUST":
            case "SAND":
            case "LIGHT_HAZE":
            case "MODERATE_HAZE":
            case "HEAVY_HAZE":
            case "FOG":
                return R.drawable.weather_haze_tag;
            default:
                return R.drawable.weather_sunny;
        }
    }

    /**
     * 空气污染度判断
     * @param pm25
     * @return
     */
    public static String switchPM25(int pm25) {
        if (pm25 < 51)
            return "优";
        else if (pm25 >= 51 && pm25 < 101)
            return "良";
        else if (pm25 >= 101 && pm25 < 151)
            return "轻度污染";
        else if (pm25 >= 151 && pm25 < 201)
            return "中度污染";
        else if (pm25 >= 201 && pm25 < 300)
            return "重度污染";
        else if (pm25 >= 300)
            return "严重污染";
        else
            return "良";

    }

    /**
     * 天气判断
     * @param skycon
     * @return
     */
    public static String switchSkycon(String skycon) {
        switch (skycon) {
            case "CLEAR_DAY":
                return "晴";
            case "CLEAR_NIGHT":
                return "晴";
            case "PARTLY_CLOUDY_DAY":
                return "多云";
            case "PARTLY_CLOUDY_NIGHT":
                return "多云";
            case "CLOUDY":
                return "阴";
            case "RAIN":
                return "雨";
            case "LIGHT_RAIN":
                return "小雨";
            case "MODERATE_RAIN":
                return "中雨";
            case "HEAVY_RAIN":
                return "大雨";
            case "STORM_RAIN":
                return "暴雨";
            case "SNOW":
                return "雪";
            case "LIGHT_SNOW":
                return "小雪";
            case "MODERATE_SNOW":
                return "中雪";
            case "HEAVY_SNOW":
                return "大雪";
            case "STORM_SNOW":
                return "暴雪";
            case "WIND":
                return "大风";
            case "HAZE":
            case "DUST":
            case "SAND":
                return "雾霾沙尘";
            case "LIGHT_HAZE":
                return "轻度雾霾";
            case "MODERATE_HAZE":
                return "中度度雾霾";
            case "HEAVY_HAZE":
                return "重度雾霾";
            case "FOG":
                return "雾";
            default:
                return "晴";
        }
    }

    /**
     * 方向判断
     * @param windDir
     * @return
     */
    public static String switchWindDir(double windDir) {
        if (windDir < 22.5)
            return "北风";
        else if (windDir < 67.5)
            return "东北风";
        else if (windDir < 112.5)
            return "东风";
        else if (windDir < 157.5)
            return "东南风";
        else if (windDir < 202.5)
            return "南风";
        else if (windDir < 247)
            return "西南风";
        else if (windDir < 292.5)
            return "西风";
        else if (windDir < 337.5)
            return "西北风";
        else
            return "北风";
    }
    /**
     * 风向图标判断
     * @param windDir
     * @return
     */
    public static int switchWindDirIcon(double windDir) {
        if (windDir < 22.5)
            return R.drawable.weather_wind_north;//return "北风";
        else if (windDir < 67.5)
            return R.drawable.weather_wind_northeast;//return "东北风";
        else if (windDir < 112.5)
            return R.drawable.weather_wind_east;//return "东风";
        else if (windDir < 157.5)
            return R.drawable.weather_wind_southeast;//return "东南风";
        else if (windDir < 202.5)
            return R.drawable.weather_wind_south;//return "南风";
        else if (windDir < 247)
            return R.drawable.weather_wind_southwest;//return "西南风";
        else if (windDir < 292.5)
            return R.drawable.weather_wind_west;//return "西风";
        else if (windDir < 337.5)
            return R.drawable.weather_wind_northwest;//return "西北风";
        else
            return R.drawable.weather_wind_north;//return "北风";
    }

    /**
     * 风级判断
     * @param windSpeed
     * @return
     */
    public static String switchWindSpeed(double windSpeed) {
        if (windSpeed < 1)
            return "无风";
        else if (windSpeed < 5)
            return "1级";
        else if (windSpeed < 11)
            return "2级";
        else if (windSpeed < 19)
            return "3级";
        else if (windSpeed < 28)
            return "4级";
        else if (windSpeed < 38)
            return "5级";
        else if (windSpeed < 49)
            return "6级";
        else if (windSpeed < 61)
            return "7级";
        else if (windSpeed < 74)
            return "8级";
        else if (windSpeed < 88)
            return "9级";
        else if (windSpeed < 102)
            return "10级";
        else if (windSpeed < 117)
            return "11级";
        else
            return "12级";
    }

    /**
     * 穿衣指数判断
     * @param index
     * @return
     */
    public static String switchDress(String index) {
        switch (index) {
            case "0":
            case "1":
            case "2":
                return "适宜薄T恤";
            case "3":
                return "适宜T恤";
            case "4":
                return "适宜衬衫";
            case "5":
                return "适宜薄外套";
            case "6":
                return "适宜风衣";
            case "7":
                return "适宜毛衣";
            case "8":
                return "适宜羽绒服";
            default:
                return "适宜羽绒服";
        }
    }

    /**
     * 带伞判断
     * @param skycon
     * @return
     */
    public static String switchUmbrella(String skycon) {
        switch (skycon) {
            case "RAIN":
            case "LIGHT_RAIN":
            case "MODERATE_RAIN":
            case "HEAVY_RAIN":
            case "STORM_RAIN":
            case "SNOW":
            case "LIGHT_SNOW":
            case "MODERATE_SNOW":
            case "HEAVY_SNOW":
            case "STORM_SNOW":
                return "需要带伞";
            default:
                return "不用带伞";
        }
    }
}
