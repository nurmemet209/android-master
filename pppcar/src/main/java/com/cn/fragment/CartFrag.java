package com.cn.fragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.android.volley.Response;
import com.cn.adapter.CarFragAdapter;
import com.cn.adapter.CustomItemDecoration;
import com.cn.commans.NetUtil;
import com.cn.entity.AuctionOfferDetailResBean;
import com.cn.entity.CartResBean;
import com.cn.entity.Item;
import com.cn.pppcar.R;
import com.cn.util.UIHelper;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by nurmemet on 2015/12/19.
 */
public class CartFrag extends BaseFrag {

    @Bind(R.id.recycle_view)
    protected RecyclerView recyclerView;
    @Bind(R.id.view_flipper)
    protected ViewFlipper viewFlipper;
    private CarFragAdapter adapter;
    private CartResBean cartResBean;

    public static CartFrag getInstance(){
        CartFrag frag=new CartFrag();
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this,mainView);
        init();
        return mainView;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.frag_cart;
    }

    private void init() {
        apiHandler.getCartPage( new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (NetUtil.isSucced(response)) {
                    cartResBean = apiHandler.toObject(NetUtil.getData(response), CartResBean.class);
                    adapter=new CarFragAdapter(cartResBean.getContent(),getActivity());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.addItemDecoration(new CustomItemDecoration(getActivity(), getContext().getResources().getDimensionPixelSize(R.dimen.main_big_divider_height)));
                    recyclerView.setAdapter(adapter);

                } else {
                    showToast(NetUtil.getError(response));
                }
            }
        });


    }
    @OnClick(R.id.edit)
    protected void editCart(View view){


        TextView edit=(TextView)view;
        if (edit.getText().equals(getString(R.string.edit))){
            viewFlipper.showNext();
            edit.setText(R.string.complete);
        }
        else{
            viewFlipper.showPrevious();
            edit.setText(R.string.edit);
        }
    }


//    class  CustomItemDecoration extends RecyclerView.ItemDecoration{
//
//        private GradientDrawable drawable;
//        int height=1;
//        public CustomItemDecoration(Context context) {
//            drawable=new GradientDrawable();
//            drawable.setColor(context.getResources().getColor(R.color.main_bg_gray));
//            height = context.getResources().getDimensionPixelSize(R.dimen.main_big_divider_height);
//        }
//
//        @Override
//        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
//            final int left = parent.getPaddingLeft();
//            final int right = parent.getWidth() - parent.getPaddingRight();
//
//            final int childCount = parent.getChildCount();
//            for (int i = 0; i < childCount; i++) {
//                final View child = parent.getChildAt(i);
//                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
//                        .getLayoutParams();
//                final int top = child.getTop()-height;
//                final int bottom = top + height+ params.topMargin;
//                drawable.setBounds(left, top, right, bottom);
//                drawable.draw(c);
//            }
//        }
//
//        @Override
//        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//            outRect.set(0,height,0,0);
//        }
//    }

}
