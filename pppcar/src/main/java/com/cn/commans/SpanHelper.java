package com.cn.commans;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;

import com.cn.pppcar.R;
import com.cn.util.StringBuilderEx;
import com.cn.util.Util;

import java.util.Date;

/**
 * Created by nurmemet on 2016/4/23.
 */
public class SpanHelper {

    private Context mContext;
    StringBuilderEx builderEx;

    public SpanHelper(Context mContext) {
        this.mContext = mContext;
        builderEx = new StringBuilderEx();
    }

    public SpannableStringBuilder priceSpan(int resId, float price) {

        SpannableStringBuilder builder = new SpannableStringBuilder(mContext.getString(resId));
        int start = 0;
        builder.setSpan(new AbsoluteSizeSpan(mContext.getResources().getDimensionPixelSize(R.dimen.text_size_small), false), start, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        start = builder.length();
        builder.append("￥").append(Float.toString(price));
        builder.setSpan(new AbsoluteSizeSpan(mContext.getResources().getDimensionPixelSize(R.dimen.text_size_normal), false), start, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.main_red)), start, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;

    }

    public SpannableStringBuilder priceSpan(int resId, double price) {

        SpannableStringBuilder builder = new SpannableStringBuilder(mContext.getString(resId));
        int start = 0;
        builder.setSpan(new AbsoluteSizeSpan(mContext.getResources().getDimensionPixelSize(R.dimen.text_size_normal), false), start, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        start = builder.length();
        builder.append("￥").append(Double.toString(price));
        builder.setSpan(new AbsoluteSizeSpan(mContext.getResources().getDimensionPixelSize(R.dimen.text_size_large), false), start, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext,R.color.main_red)), start, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;

    }

    public SpannableStringBuilder priceSpanSmaller(int resId, float price) {

        SpannableStringBuilder builder = new SpannableStringBuilder(mContext.getString(resId));
        int start = 0;
        builder.setSpan(new AbsoluteSizeSpan(mContext.getResources().getDimensionPixelSize(R.dimen.text_size_smaller), false), start, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        start = builder.length();
        builder.append("￥").append(Float.toString(price));
        builder.setSpan(new AbsoluteSizeSpan(mContext.getResources().getDimensionPixelSize(R.dimen.text_size_smaller), false), start, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.main_red)), start, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;

    }

    public String convertToPrice(int resId, float price) {
        builderEx.clear();
        builderEx.append(mContext.getString(resId)).append("￥").append(Float.toString(price));
        return builderEx.toString();
    }

    public SpannableStringBuilder auctionTime(long leftTime) {
        String hour = DateUtil.getHour(leftTime);
        String minute = DateUtil.getMinute(leftTime);
        String second = DateUtil.getSecond(leftTime);
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.replace(0, builder.length(), "");
        builder.append(" ").append(hour).append(" ");
        builder.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.white)), 0, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new BackgroundColorSpan(ContextCompat.getColor(mContext, R.color.black)), 0, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.append("：");
        int start = builder.length();
        builder.append(" ").append(minute).append(" ");
        builder.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.white)), start, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new BackgroundColorSpan(ContextCompat.getColor(mContext, R.color.black)), start, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.append("：");
        start = builder.length();
        builder.append(" ").append(String.valueOf(second)).append(" ");
        builder.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.white)), start, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new BackgroundColorSpan(ContextCompat.getColor(mContext, R.color.black)), start, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return builder;
    }

    public SpannableStringBuilder getProductNumAndTotalPrice(String proNum, String totalPrice) {
        String str = mContext.getString(R.string.product_num_and_total_price);
        str = str.replace("1$", String.valueOf(proNum));
        str = str.replace("2$", String.valueOf(totalPrice));
        SpannableStringBuilder builder = new SpannableStringBuilder(str);
        int start = str.indexOf(proNum);
        builder.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.main_text_color)), start, start + proNum.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new StyleSpan(Typeface.BOLD), start, start + proNum.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        start = str.indexOf(totalPrice);
        builder.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.main_text_color)), start, start + totalPrice.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new StyleSpan(Typeface.BOLD), start, start + totalPrice.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return builder;
    }

    public SpannableStringBuilder span(String s, String name) {
        SpannableStringBuilder builder = new SpannableStringBuilder(s + name);
        int start = 0;
        ;
        builder.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.main_red)), start, s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;
    }
}
