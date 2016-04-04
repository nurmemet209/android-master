package com.cn.fragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cn.adapter.GoodsAdapter;
import com.cn.entity.Item;
import com.cn.pppcar.R;
import com.cn.util.UIHelper;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nurmemet on 2016/4/2.
 */
public class Goods extends Fragment{
    private View mainView;

    @Bind(R.id.recycle_view)
    protected RecyclerView recyclerView;

    private GoodsAdapter adapter;

    public static Goods getInstance(){
        Goods frag=new Goods();
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      super.onCreateView(inflater, container, savedInstanceState);
        mainView=inflater.inflate(R.layout.frag_goods,null);
        ButterKnife.bind(this,mainView);
        init();
        return mainView;
    }

    private void init() {
        adapter=new GoodsAdapter(getActivity(),getList());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new CustomItemDecoration(getActivity()));
       recyclerView.setAdapter(adapter);

    }

    private ArrayList<Item> getList() {
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

        return imageUrlList;
    }

    private Item getItem(String str) {
        Item item = new Item();
        item.setImgAddress(str);
        item.setTitle("D Forged 轮毂 S11系列 规格:18*8.5J  PCD:5*120 表面处理:中银拉丝（宝马3 宝马4 君威 迈锐宝）ET值:35 中心孔:72.5");
        item.setPrice(123);
        item.setCollectNum(230565);
        return item;
    }


    class  CustomItemDecoration extends RecyclerView.ItemDecoration{

        private GradientDrawable drawable;
        int height=1;
        public CustomItemDecoration(Context context) {
            drawable=new GradientDrawable();
            drawable.setColor(context.getResources().getColor(R.color.main_bg_gray));
            height = context.getResources().getDimensionPixelSize(R.dimen.main_big_divider_height);
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            final int left = parent.getPaddingLeft();
            final int right = parent.getWidth() - parent.getPaddingRight();

            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                final int top = child.getTop()-height;
                final int bottom = top + height+ params.topMargin;
                drawable.setBounds(left, top, right, bottom);
                drawable.draw(c);
            }
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.set(0,height,0,0);
        }
    }
}
