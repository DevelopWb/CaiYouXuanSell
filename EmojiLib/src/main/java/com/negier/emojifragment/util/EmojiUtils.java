package com.negier.emojifragment.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.widget.TextView;

import com.negier.emojifragment.R;
import com.negier.emojifragment.bean.Emoji;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/1/29 0029.
 */

public class EmojiUtils {
    public static ArrayList<Emoji> getEmojiLists() {
        ArrayList<Emoji> emojiLists = new ArrayList<>();
        for (int i = 0; i < emojiResArray.length; i++) {
            Emoji emoji = new Emoji();
            emoji.setImageUri(emojiResArray[i]);
            emoji.setName(emojiTextArray[i]);
            emojiLists.add(emoji);
        }
        return emojiLists;
    }

    public static Bitmap getEmojiItem(Resources resources, int resId, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, resId, options);
        options.inSampleSize = convertBitmapSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(resources, resId, options);
    }

    public static int convertBitmapSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int inSampleSize = 1;
        int width = options.outWidth;
        int height = options.outHeight;
        if (width > reqWidth || height > reqHeight) {
            int widthRadio = Math.round((float) width / reqWidth);
            int heightRadio = Math.round((float) height / reqHeight);
            return Math.min(widthRadio, heightRadio);
        }
        return inSampleSize;
    }
    /**
     * ?????????????????????????????????????????????????????????????????????
     * @param str
     * @return
     */
    public static String[] judgeString(String str){
        Matcher m = Pattern.compile("(((https|http)?://)?([a-z0-9]+[.])|(www.))"
                + "\\w+[.|\\/]([a-z0-9]{0,})?[[.]([a-z0-9]{0,})]+((/[\\S&&[^,;\u4E00-\u9FA5]]+)+)?([.][a-z0-9]{0,}+|/?)").matcher(str);
        String[] url = new String[str.length()];
        int count = 0;
        while(m.find()){
            count++;
            url[count] = m.group();
        }
        return url;
    }
    public static void showEmojiTextView(final Context context, TextView textView, String content, int textSize) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(content);

        if (content.contains("[??????@???]")) {
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#ccff4444"));
            spannableStringBuilder.setSpan(colorSpan, 0, content.indexOf("@???]")+3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        }


        /**
         * \s???????????????\S??????????????????????????????????????????
         * +???????????????????????????????????????????????????????????????????
         */
        String regex="\\[(\\S+?)\\]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        ArrayList<Emoji> emojiLists = getEmojiLists();
        while (matcher.find()){
            Iterator<Emoji> iterator = emojiLists.iterator();
            String contentGroup = matcher.group();
            while (iterator.hasNext()){
                Emoji emoji=iterator.next();
                if (emoji.getName().equals(contentGroup)){
                    spannableStringBuilder.setSpan(new ImageSpan(context,getEmojiItem(context.getResources(),emoji.getImageUri(),PxUtils.dpToPx(context,textSize),PxUtils.dpToPx(context,textSize)))
                            ,matcher.start(),matcher.end(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    break;
                }
            }
        }
        textView.setText(spannableStringBuilder);
    }
    public static void showEmojiTextView(Context context,TextView textView, String content,int textSize,boolean isSraft) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(content);

        if (isSraft) {
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#ccff4444"));
            spannableStringBuilder.setSpan(colorSpan, 0, content.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }
        if (content.contains("[??????@???]")) {
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#ccff4444"));
            spannableStringBuilder.setSpan(colorSpan, 0, content.indexOf("@???]")+3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        }

        /**
         * \s???????????????\S??????????????????????????????????????????
         * +???????????????????????????????????????????????????????????????????
         */
        String regex="\\[(\\S+?)\\]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        ArrayList<Emoji> emojiLists = getEmojiLists();
        while (matcher.find()){
            Iterator<Emoji> iterator = emojiLists.iterator();
            String contentGroup = matcher.group();
            while (iterator.hasNext()){
                Emoji emoji=iterator.next();
                if (emoji.getName().equals(contentGroup)){
                    spannableStringBuilder.setSpan(new ImageSpan(context,getEmojiItem(context.getResources(),emoji.getImageUri(),PxUtils.dpToPx(context,textSize),PxUtils.dpToPx(context,textSize)))
                            ,matcher.start(),matcher.end(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    break;
                }
            }
        }
        textView.setText(spannableStringBuilder);
    }

    public static final int[] emojiResArray = {
            R.mipmap.d_aini,
            R.mipmap.d_aoteman,
            R.mipmap.d_baibai,
            R.mipmap.d_beishang,
            R.mipmap.d_bishi,
            R.mipmap.d_bizui,
            R.mipmap.d_chanzui,
            R.mipmap.d_chijing,
            R.mipmap.d_dahaqi,
            R.mipmap.d_dalian,
            R.mipmap.d_ding,
            R.mipmap.d_doge,
            R.mipmap.d_feizao,
            R.mipmap.d_ganmao,
            R.mipmap.d_guzhang,
            R.mipmap.d_haha,
            R.mipmap.d_haixiu,
            R.mipmap.d_han,
            R.mipmap.d_hehe,
            R.mipmap.d_heixian,
            R.mipmap.d_heng,
            R.mipmap.d_huaxin,
            R.mipmap.d_jiyan,
            R.mipmap.d_keai,
            R.mipmap.d_kelian,
            R.mipmap.d_ku,
            R.mipmap.d_kun,
            R.mipmap.d_landelini,
            R.mipmap.d_lei,
            R.mipmap.d_madaochenggong,
            R.mipmap.d_miao,
            R.mipmap.d_nanhaier,
            R.mipmap.d_nu,
            R.mipmap.d_numa,
            R.mipmap.d_nvhaier,
            R.mipmap.d_qian,
            R.mipmap.d_qinqin,
            R.mipmap.d_shayan,
            R.mipmap.d_shengbing,
            R.mipmap.d_shenshou,
            R.mipmap.d_shiwang,
            R.mipmap.d_shuai,
            R.mipmap.d_shuijiao,
            R.mipmap.d_sikao,
            R.mipmap.d_taikaixin,
            R.mipmap.d_touxiao,
            R.mipmap.d_tu,
            R.mipmap.d_tuzi,
            R.mipmap.d_wabishi,
            R.mipmap.d_weiqu,
            R.mipmap.d_xiaoku,
            R.mipmap.d_xiongmao,
            R.mipmap.d_xixi,
            R.mipmap.d_xu,
            R.mipmap.d_yinxian,
            R.mipmap.d_yiwen,
            R.mipmap.d_youhengheng,
            R.mipmap.d_yun,
            R.mipmap.d_zhajipijiu,
            R.mipmap.d_zhuakuang,
            R.mipmap.d_zhutou,
            R.mipmap.d_zuiyou,
            R.mipmap.d_zuohengheng,
            R.mipmap.f_geili,
            R.mipmap.f_hufen,
            R.mipmap.f_jiong,
            R.mipmap.f_meng,
            R.mipmap.f_shenma,
            R.mipmap.f_v5,
            R.mipmap.f_xi,
            R.mipmap.f_zhi,
            R.mipmap.h_buyao,
            R.mipmap.h_good,
            R.mipmap.h_haha,
            R.mipmap.h_lai,
            R.mipmap.h_ok,
            R.mipmap.h_quantou,
            R.mipmap.h_ruo,
            R.mipmap.h_woshou,
            R.mipmap.h_ye,
            R.mipmap.h_zan,
            R.mipmap.h_zuoyi,
            R.mipmap.l_shangxin,
            R.mipmap.l_xin,
            R.mipmap.o_dangao,
            R.mipmap.o_feiji,
            R.mipmap.o_ganbei,
            R.mipmap.o_huatong,
            R.mipmap.o_lazhu,
            R.mipmap.o_liwu,
            R.mipmap.o_lvsidai,
            R.mipmap.o_weibo,
            R.mipmap.o_weiguan,
            R.mipmap.o_yinyue,
            R.mipmap.o_zhaoxiangji,
            R.mipmap.o_zhong,
            R.mipmap.w_fuyun,
            R.mipmap.w_shachenbao,
            R.mipmap.w_taiyang,
            R.mipmap.w_weifeng,
            R.mipmap.w_xianhua,
            R.mipmap.w_xiayu,
            R.mipmap.w_yueliang,
    };

    public static final String[] emojiTextArray = {
            "[??????]",
            "[?????????]",
            "[??????]",
            "[??????]",
            "[??????]",
            "[??????]",
            "[??????]",
            "[??????]",
            "[??????]",
            "[??????]",
            "[???]",
            "[doge]",
            "[??????]",
            "[??????]",
            "[??????]",
            "[??????]",
            "[??????]",
            "[???]",
            "[??????]",
            "[??????]",
            "[???]",
            "[???]",
            "[??????]",
            "[??????]",
            "[??????]",
            "[???]",
            "[???]",
            "[??????]",
            "[???]",
            "[????????????]",
            "[??????]",
            "[?????????]",
            "[???]",
            "[??????]",
            "[?????????]",
            "[???]",
            "[??????]",
            "[??????]",
            "[??????]",
            "[?????????]",
            "[??????]",
            "[???]",
            "[???]",
            "[??????]",
            "[?????????]",
            "[??????]",
            "[???]",
            "[??????]",
            "[??????]",
            "[??????]",
            "[???cry]",
            "[??????]",
            "[??????]",
            "[???]",
            "[??????]",
            "[??????]",
            "[?????????]",
            "[???]",
            "[????????????]",
            "[??????]",
            "[??????]",
            "[??????]",
            "[?????????]",
            "[??????]",
            "[??????]",
            "[???]",
            "[???]",
            "[??????]",
            "[??????]",
            "[???]",
            "[???]",
            "[NO]",
            "[good]",
            "[haha]",
            "[???]",
            "[OK]",
            "[??????]",
            "[???]",
            "[??????]",
            "[???]",
            "[???]",
            "[??????]",
            "[??????]",
            "[???]",
            "[??????]",
            "[??????]",
            "[??????]",
            "[??????]",
            "[??????]",
            "[??????]",
            "[?????????]",
            "[??????]",
            "[??????]",
            "[??????]",
            "[?????????]",
            "[???]",
            "[??????]",
            "[?????????]",
            "[??????]",
            "[??????]",
            "[??????]",
            "[??????]",
            "[??????]",
    };
}
