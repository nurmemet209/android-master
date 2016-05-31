package com.cn.fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cn.commans.SpanHelper;
import com.cn.entity.Item;
import com.cn.net.ApiHandler;
import com.cn.pppcar.R;
import com.cn.pppcar.widget.ProgressDlg;
import com.cn.util.StringBuilderEx;
import com.cn.util.UIHelper;

import java.util.ArrayList;
import java.util.logging.Handler;

/**
 * Created by nurmemet on 2016/4/25.
 */
public abstract class BaseFrag extends Fragment {

    protected View mainView;
    protected StringBuilderEx builderEx;
    protected SpanHelper spanHelper;
    ApiHandler apiHandler;
    protected boolean isFirstShow = true;
    private ProgressDlg mProgressDlg;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mainView = inflater.inflate(getLayoutResId(), null);
        builderEx = new StringBuilderEx();
        spanHelper = new SpanHelper(getActivity());
        apiHandler = ApiHandler.getInstance(getActivity());
        return mainView;
    }

    protected abstract int getLayoutResId();


    protected ArrayList<Item> getList() {
        ArrayList<Item> imageUrlList = new ArrayList<>();
        imageUrlList
                .add(getItem("http://b.hiphotos.baidu.com/image/pic/item/d01373f082025aaf95bdf7e4f8edab64034f1a15.jpg"));
        imageUrlList
                .add(getItem("http://g.hiphotos.baidu.com/image/pic/item/6159252dd42a2834da6660c459b5c9ea14cebf39.jpg"));
        imageUrlList
                .add(getItem("http://d.hiphotos.baidu.com/image/pic/item/adaf2edda3cc7cd976427f6c3901213fb80e911c.jpg"));
        imageUrlList
                .add(getItem("http://g.hiphotos.baidu.com/image/pic/item/b3119313b07eca80131de3e6932397dda1448393.jpg"));
        imageUrlList
                .add(getItem("http://b.hiphotos.baidu.com/image/pic/item/d01373f082025aaf95bdf7e4f8edab64034f1a15.jpg"));
        imageUrlList
                .add(getItem("http://b.hiphotos.baidu.com/image/pic/item/d01373f082025aaf95bdf7e4f8edab64034f1a15.jpg"));
        imageUrlList
                .add(getItem("http://b.hiphotos.baidu.com/image/pic/item/d01373f082025aaf95bdf7e4f8edab64034f1a15.jpg"));
        imageUrlList
                .add(getItem("http://b.hiphotos.baidu.com/image/pic/item/d01373f082025aaf95bdf7e4f8edab64034f1a15.jpg"));
        imageUrlList
                .add(getItem("http://b.hiphotos.baidu.com/image/pic/item/d01373f082025aaf95bdf7e4f8edab64034f1a15.jpg"));
        imageUrlList
                .add(getItem("http://b.hiphotos.baidu.com/image/pic/item/d01373f082025aaf95bdf7e4f8edab64034f1a15.jpg"));
        imageUrlList
                .add(getItem("http://b.hiphotos.baidu.com/image/pic/item/d01373f082025aaf95bdf7e4f8edab64034f1a15.jpg"));
        imageUrlList
                .add(getItem("http://b.hiphotos.baidu.com/image/pic/item/d01373f082025aaf95bdf7e4f8edab64034f1a15.jpg"));
        imageUrlList
                .add(getItem("http://b.hiphotos.baidu.com/image/pic/item/d01373f082025aaf95bdf7e4f8edab64034f1a15.jpg"));
        imageUrlList
                .add(getItem("http://b.hiphotos.baidu.com/image/pic/item/d01373f082025aaf95bdf7e4f8edab64034f1a15.jpg"));
        imageUrlList
                .add(getItem("http://b.hiphotos.baidu.com/image/pic/item/d01373f082025aaf95bdf7e4f8edab64034f1a15.jpg"));
        imageUrlList
                .add(getItem("http://b.hiphotos.baidu.com/image/pic/item/d01373f082025aaf95bdf7e4f8edab64034f1a15.jpg"));
        imageUrlList
                .add(getItem("http://b.hiphotos.baidu.com/image/pic/item/d01373f082025aaf95bdf7e4f8edab64034f1a15.jpg"));
        imageUrlList
                .add(getItem("http://b.hiphotos.baidu.com/image/pic/item/d01373f082025aaf95bdf7e4f8edab64034f1a15.jpg"));

        return imageUrlList;
    }

    protected Item getItem(String str) {
        Item item = new Item();
        item.setImg(str);
        item.setName("D Forged 轮毂 S11系列 规格:18*8.5J  PCD:5*120 表面处理:中银拉丝（宝马3 宝马4 君威 迈锐宝）ET值:35 中心孔:72.5");
        item.setPrice(123);
        item.setCollectNum(230565);
        return item;
    }


    protected void showToast(final String msg) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            UIHelper.showToast(getActivity(), msg, Toast.LENGTH_SHORT);

        } else {
            new android.os.Handler().post(new Runnable() {
                @Override
                public void run() {
                    UIHelper.showToast(getActivity(), msg, Toast.LENGTH_SHORT);
                }
            });
        }

    }


    protected void showWithAnimation(final View view) {
        if (view != null) {
            view.setVisibility(View.VISIBLE);
            view.setAlpha(0);
            view.animate().alpha(1f).setInterpolator(new LinearInterpolator()).setDuration(600).start();

//            ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
//            animator.setDuration(700);
//            animator.setInterpolator(new LinearInterpolator());
//
//            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                @Override
//                public void onAnimationUpdate(ValueAnimator animation) {
//                    float value = (float) animation.getAnimatedValue();
//                    view.setAlpha(value);
//                }
//            });
//            animator.start();
        }

    }

    /**
     * 数据加载完成
     */
    protected void dataLoaded(final boolean isSucceed) {
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                if (isFirstShow) {
                    isFirstShow = false;
                    View contentView = mainView.findViewById(R.id.content);
                    if (isSucceed) {
                        mainView.findViewById(R.id.loading_view).setVisibility(View.GONE);

                        showWithAnimation(contentView);

                    } else {

                        TextView msg = (TextView) mainView.findViewById(R.id.message);
                        msg.setText("网络错误，请检查你的网络");
                        ProgressBar progressBar = (ProgressBar) mainView.findViewById(R.id.progress_bar);
                        progressBar.setVisibility(View.GONE);

                    }
                }
            }
        }, 2000);

    }


    protected void showProgressDlg() {
        if (mProgressDlg == null) {
            mProgressDlg = new ProgressDlg();
        }
        mProgressDlg.show(getChildFragmentManager(), "progressDlg_");
    }

    protected void dismissProgressDlg() {
        if (mProgressDlg != null) {
            mProgressDlg.dismiss();
        }
    }
}
