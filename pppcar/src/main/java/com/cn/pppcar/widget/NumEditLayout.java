package com.cn.pppcar.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.Shape;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cn.pppcar.R;

/**
 * 推荐尺寸 100*40 以dp为单位
 * Created by nurmemet on 2016/5/11.
 */
public class NumEditLayout extends LinearLayout {

    private int mBorderColor = Color.BLACK;
    /**
     * 以px为单位
     */
    private int mBorderWidth;

    public NumEditLayout(Context context) {
        super(context);
    }

    public NumEditLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumEditLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mBorderWidth = (int) dp2px(getContext(), 1);
        this.setDividerDrawable(getCustomDividerDrawable());
        this.setShowDividers(SHOW_DIVIDER_MIDDLE);
        this.setBackground(getBackgroundDrawable());
        LinearLayout.LayoutParams paramsPlus = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        paramsPlus.weight = 1;
        paramsPlus.gravity = Gravity.CENTER_VERTICAL;
        TextView plus = new TextView(getContext());
        plus.setGravity(Gravity.CENTER);
        plus.setText("+");
        this.addView(plus, paramsPlus);

        LinearLayout.LayoutParams paramsEditText = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        paramsEditText.weight = 2;
        paramsEditText.gravity = Gravity.CENTER_VERTICAL;
        final EditText editText = new EditText(getContext());
        editText.setGravity(Gravity.CENTER);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText.setCursorVisible(false);
        editText.setText("0");
        this.addView(editText, paramsEditText);

        LinearLayout.LayoutParams paramsMinus = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        paramsMinus.weight = 1;
        paramsMinus.gravity = Gravity.CENTER_VERTICAL;
        final TextView minus = new TextView(getContext());
        minus.setGravity(Gravity.CENTER);
        minus.setText("-");
        this.addView(minus, paramsMinus);
        minus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String numStr = editText.getText().toString();
                if ("0".equals(numStr)) {
                    return;
                }
                int num = Integer.valueOf(numStr);
                num--;
                editText.setText(num + "");
            }
        });
        plus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String numStr = editText.getText().toString();
                int num = Integer.valueOf(numStr);
                num++;
                editText.setText(num + "");
            }
        });

    }

    private Drawable getBackgroundDrawable() {
        RectShape shape = new RectShape();
        ShapeDrawable drawable = new ShapeDrawable(shape);
        drawable.getPaint().setStrokeWidth(mBorderWidth);
        drawable.getPaint().setStyle(Paint.Style.STROKE);
        drawable.getPaint().setColor(mBorderColor);
        drawable.getPaint().setAntiAlias(true);
        return drawable;
    }

    private Drawable getCustomDividerDrawable() {
        RectShape shape = new RectShape();

        ShapeDrawable drawable = new ShapeDrawable(shape);
        drawable.setIntrinsicHeight(mBorderWidth);
        drawable.setIntrinsicWidth(mBorderWidth);
        drawable.getPaint().setStyle(Paint.Style.FILL);
        drawable.getPaint().setColor(mBorderColor);
        drawable.getPaint().setAntiAlias(true);
        return drawable;
    }

    public float dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }
}
