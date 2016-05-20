package com.cn.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.TextView;

import com.cn.customlibrary.R;
import com.cn.util.UIHelper;

/**
 * Created by nurmemet on 2016/4/28.
 */
public class CustomTextView extends TextView {
    private Paint linePaint;
    private boolean isLined=false;
    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        linePaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setStrokeWidth(UIHelper.dip2px(getContext(),1));
        linePaint.setColor(Color.parseColor("#999999"));


    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isLined){
            int left=getPaddingLeft();
            int right=getWidth()-getPaddingRight();
            canvas.drawLine(left,getHeight()/2,right,getHeight()/2,linePaint);
        }
    }

    public void setLine(boolean isLined){
        this.isLined=isLined;
        invalidate();
    }

}
