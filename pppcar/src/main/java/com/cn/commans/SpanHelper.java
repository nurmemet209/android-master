package com.cn.commans;

import android.content.Context;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;

import com.cn.pppcar.R;
import com.cn.util.Util;

/**
 * Created by nurmemet on 2016/4/23.
 */
public class SpanHelper {

    private Context mContext;

    public SpanHelper(Context mContext) {
        this.mContext = mContext;
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
}
