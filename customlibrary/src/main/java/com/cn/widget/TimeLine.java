package com.cn.widget;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.cn.customlibrary.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nurmemet on 2016/1/26.
 */
public class TimeLine extends View {

    /**
     * 每一个事件之间的距离，以dp为单位
     */
    private final static int DEFAULT_TIME_SPACE = 30;
    /**
     * 事件描述跟icon距离,以dp为单位
     */
    private final static int DEFAULT_ICON_SPACE = 30;
    private Integer mTextSize;
    private Integer mTextColor;
    private Integer mLineColor;
    private Integer mEndTextColor;
    private Integer mStartTextColor;
    private Integer mStartBallColor;
    private Integer mOtherBallColor;
    private TextPaint mTextPaint;
    private TextPaint mEndItemTextPaint;
    private Paint mLinePaint;
    /**
     * 物流开始节点图标
     */
    private GradientDrawable firstDrawabel;
    /**
     * 物流结束节点图标
     */
    private Drawable lastDrawable;
    /**
     * 其他中间节点图标
     */
    private GradientDrawable otherDrawable;

    private boolean eventRefresh = false;
    /**
     * 节点之间线条的宽度，以dp为单位
     */
    private final static float LINE_WIDTH = 2.5f;

    private List<EventItem> mTimeDescription = new ArrayList<>();
    private ArrayList<String> eventList;

    public TimeLine(Context context) {
        super(context);
        init();
    }

    public TimeLine(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimeLine(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TimeLine, defStyleAttr, 0);
        mTextColor = a.getColor(R.styleable.TimeLine_textColor, Color.BLACK);
        mTextSize = (int) a.getDimension(R.styleable.TimeLine_textSize, sp2px(15));
        mLineColor=a.getColor(R.styleable.TimeLine_lineColor,Color.GRAY);
        mEndTextColor=a.getColor(R.styleable.TimeLine_endItemTextColor,Color.BLACK);
        mStartBallColor=a.getColor(R.styleable.TimeLine_startBallColor,Color.RED);
        mOtherBallColor=a.getColor(R.styleable.TimeLine_otherBallColor,Color.GREEN);
        //对绘图进行优化
        setWillNotDraw(false);
        init();
        a.recycle();
    }

    private void init() {
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextSize(mTextSize);
        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setColor(mLineColor);

        mEndItemTextPaint= new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mEndItemTextPaint.setColor(mEndTextColor);
        mEndItemTextPaint.setTextSize(mTextSize);

        firstDrawabel =  (GradientDrawable) getResources().getDrawable(R.drawable.first_point);
        firstDrawabel.setColor(mStartBallColor);
        otherDrawable = (GradientDrawable) getResources().getDrawable(R.drawable.other_point);
        otherDrawable.setColor(mOtherBallColor);
        lastDrawable = getResources().getDrawable(R.drawable.last_point);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mTimeDescription.size() > 1) {
            canvas.save();
            int x = getIconSpacePx() / 2;
            canvas.translate(x, 0);

            int left, top, right, bottom;
            left = -dip2px(LINE_WIDTH) / 2;
            right = dip2px(LINE_WIDTH) / 2;
            for (int i = 0; i < mTimeDescription.size() - 1; i++) {
                top = mTimeDescription.get(i).getCenterY() + getIconSpacePx() / 2;
                bottom = mTimeDescription.get(i + 1).getCenterY() - getIconSpacePx() / 2;
                canvas.drawRect(left, top, right, bottom, mLinePaint);
            }
            canvas.restore();
        }
        for (int i = 0; i < mTimeDescription.size(); i++) {
            mTimeDescription.get(i).draw(canvas);
        }


    }

    /**
     * px为单位
     *
     * @return
     */

    private int getIconSpacePx() {
        int x = dip2px(DEFAULT_ICON_SPACE);
        return x;
    }

    public int sp2px(float spValue) {
        final float fontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    public void addEvent(ArrayList<String> eventList) {
        if (eventList != null && !eventList.isEmpty()) {
            this.eventList = eventList;
            requestLayout();
        }
    }

    private int measureContent(int width) {
        int y = 0;
        width = width - dip2px(DEFAULT_ICON_SPACE);
        for (int i = 0; i < eventList.size(); i++) {
            EventItem eItem;
            if (i == 0) {
                eItem = new EventItem(eventList.get(i), 0, y, width);
            } else if (i == eventList.size() - 1) {
                eItem = new EventItem(eventList.get(i), 2, y, width);
            } else {
                eItem = new EventItem(eventList.get(i), 1, y, width);
            }
            mTimeDescription.add(eItem);
            y = eItem.getBottom();
        }
        return y;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        if (eventList != null && !eventList.isEmpty()) {
            int width = 0;
            width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
            int contentHeight = measureContent(width);
            int height = contentHeight + getPaddingBottom() + getPaddingTop();
            setMeasuredDimension(width, height);
        } else {
            super.measure(widthMeasureSpec, heightMeasureSpec);
        }


    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    private class EventItem {
        public EventItem(String description, int itemPsn, int y, int width) {
            this.description = description;
            mItemPsn = itemPsn;
            init(y, width);
        }

        private int top;
        private int bottom;
        private StaticLayout mStaticLayout;
        private String description;
        private int mItemPsn = 0;//0表示第一个事件，1表示其他，2表示最后一个


        private void init(int y, int width) {
            if (mItemPsn==0){
                mStaticLayout = new StaticLayout(description, mEndItemTextPaint, width, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0, false);

            }else{
                mStaticLayout = new StaticLayout(description, mTextPaint, width, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0, false);
            }

            top = y;
            int timeSpce = dip2px(DEFAULT_TIME_SPACE);
            int height = mStaticLayout.getHeight();
            bottom = y + height + timeSpce;
        }

        public int getY4EventText() {
            int y = top + dip2px(DEFAULT_TIME_SPACE) / 2;
            return y;
        }

        public int getBottom() {
            return bottom;
        }

        public int getHeight() {
            return bottom - top;
        }


        public int getCenterY() {
            int cY = top + (bottom - top) / 2;
            return cY;
        }

        public void draw(Canvas c) {
            if (!mTimeDescription.isEmpty()) {
                c.save();
                int x = dip2px(DEFAULT_ICON_SPACE) / 2;
                int y = (bottom - top) / 2+top;
                c.translate(x, y);
                if (mItemPsn == 0) {
                    int width = lastDrawable.getIntrinsicWidth();
                    int height = lastDrawable.getIntrinsicHeight();
                    lastDrawable.setBounds(-width / 2, -height / 2, width / 2, height / 2);
                    lastDrawable.draw(c);
                } else if (mItemPsn == 1) {
                    int width = otherDrawable.getIntrinsicWidth();
                    int height = otherDrawable.getIntrinsicHeight();
                    otherDrawable.setBounds(-width / 2, -height / 2, width / 2, height / 2);
                    otherDrawable.draw(c);
                }//最后一个
                else if (mItemPsn == 2) {
                    int width = firstDrawabel.getIntrinsicWidth();
                    int height = firstDrawabel.getIntrinsicHeight();
                    firstDrawabel.setBounds(-width / 2, -height / 2, width / 2, height / 2);
                    firstDrawabel.draw(c);
                }
                c.restore();
                c.save();
                x = dip2px(DEFAULT_ICON_SPACE);
                y = getY4EventText();
                c.translate(x, y);

                mStaticLayout.draw(c);
                c.restore();

            }

        }

    }
}
