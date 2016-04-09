package com.example.nurmemet.animaitonmaster;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.renderscript.Sampler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.CycleInterpolator;
import android.widget.TextView;

import com.cn.dialog.ConfirmDialog;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.recycle_view)
    protected RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        list.add("flash");
        list.add("RubberBand");
        list.add("shakeHorizontal");
        list.add("shakeVertical");
        list.add("swing");
        adapter = new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View mainView = LayoutInflater.from(MainActivity.this).inflate(R.layout.list_item, null);
                mainView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
                TextView tv = (TextView) mainView.findViewById(R.id.item);
                RecyclerView.ViewHolder holder = new RecyclerView.ViewHolder(mainView) {
                    @Override
                    public String toString() {
                        return super.toString();
                    }
                };
                return holder;

            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
                TextView tv = (TextView) holder.itemView.findViewById(R.id.item);
                tv.setText(list.get(position));
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        AlertDialog dialog = builder.create();
                        dialog.setMessage("ljdfldsfkkdslf");
                        dialog.setTitle("alert");
                        dialog.show();

                        AnimatorSet animatorSet = new AnimatorSet();
                        animatorSet.setDuration(1000);
                        switch (position) {
                            //flash 闪烁
                            case 0:
                                animatorSet.play(ObjectAnimator.ofFloat(dialog.getWindow().getDecorView(), "alpha", 0, 1, 0, 1, 0, 1));
                                animatorSet.start();
                                break;
                            case 1:
                                animatorSet.playTogether(ObjectAnimator.ofFloat(dialog.getWindow().getDecorView(), "scaleX", 1, 1.25f, 0.75f, 1.15f, 1), ObjectAnimator.ofFloat(dialog.getWindow().getDecorView(), "scaleY", 1, 0.75f, 1.25f, 0.85f, 1));
                                animatorSet.start();
                                break;
                            case 2:
                                //shakeHorizontal
                                animatorSet.playTogether(ObjectAnimator.ofFloat(dialog, "translationX", 20));

                                animatorSet.start();
                                break;
                            case 3:
                                //shakeVertical
                                ValueAnimator animator = ObjectAnimator.ofFloat(dialog.getWindow().getDecorView(), "translationY", -10, 10);
                                animatorSet.playTogether(animator);
                                animatorSet.setInterpolator(new CycleInterpolator(5));
                                animatorSet.start();

                                break;
                            case 4://swing
                                animatorSet.playTogether(ObjectAnimator.ofFloat(dialog.getWindow().getDecorView(),"rotation", 0, 10, -10, 6, -6, 3, -3, 0));
                                animatorSet.setInterpolator(new CycleInterpolator(5));
                                animatorSet.start();
                                break;
                            case 5:
                                break;
                            case 6:
                                break;
                            case 7:
                                break;
                            case 8:
                                break;
                            case 9:
                                break;

                        }

                    }
                });
            }

            @Override
            public int getItemCount() {
                return list.size();
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setAdapter(adapter);

    }


    class DividerItemDecoration extends RecyclerView.ItemDecoration {


        public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;

        public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;
        /**
         * dp为单位
         */
        private float height = 1;

        private GradientDrawable mDivider;

        private int mOrientation;

        public DividerItemDecoration(Context context, int orientation) {
            mDivider = new GradientDrawable();
            mDivider.setColor(Color.parseColor("#FFEBEBEB"));

            setOrientation(orientation);
        }

        public void setOrientation(int orientation) {
            if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
                throw new IllegalArgumentException("invalid orientation");
            }
            mOrientation = orientation;
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {

            if (mOrientation == VERTICAL_LIST) {
                drawVertical(c, parent);
            } else {
                drawHorizontal(c, parent);
            }

        }

        public void drawVertical(Canvas c, RecyclerView parent) {
            final int left = parent.getPaddingLeft();
            final int right = parent.getWidth() - parent.getPaddingRight();

            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                final int top = child.getBottom() + params.bottomMargin;
                final int bottom = top + dip2px(MainActivity.this, height);
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }

        public void drawHorizontal(Canvas c, RecyclerView parent) {
            final int top = parent.getPaddingTop();
            final int bottom = parent.getHeight() - parent.getPaddingBottom();

            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                final int left = child.getRight() + params.rightMargin;
                final int right = left + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            if (mOrientation == VERTICAL_LIST) {
                outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
            } else {
                outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
            }
        }
    }


    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
