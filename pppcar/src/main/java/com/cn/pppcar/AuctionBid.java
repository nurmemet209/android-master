package com.cn.pppcar;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.cn.commans.NetUtil;
import com.cn.entity.AuctionOfferDetailResBean;
import com.cn.pppcar.widget.TagGroupLayout;
import com.cn.util.UIHelper;
import com.facebook.drawee.view.SimpleDraweeView;

import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nurmemet on 2016/4/22.
 */
public class AuctionBid extends BaseAct {

    @Bind(R.id.tag_group)
    protected TagGroupLayout bidLevelContainer;
    @Bind(R.id.title_img)
    protected SimpleDraweeView titleImg;
    @Bind(R.id.title)
    protected TextView title;
    @Bind(R.id.current_price)
    protected TextView currentPrice;
    @Bind(R.id.fixed_price)
    protected TextView fixedPrice;
    @Bind(R.id.my_price)
    protected TextView myBidPrice;
    private TextView selectedPrice;

    @Bind(R.id.first_price)
    protected TextView startPrice;
    @Bind(R.id.min_auction_price)
    protected TextView minDealPrice;
    @Bind(R.id.min_add_price)
    protected TextView minAddPrice;
    @Bind(R.id.max_add_price)
    protected TextView maxAddPrice;

    AuctionOfferDetailResBean item;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_auction_bid);
        ButterKnife.bind(this);
        bindData();

    }

    private void bindData() {

        apiHandler.getAuctionDetail("65", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (NetUtil.isSucced(response)) {
                    item = apiHandler.toObject(NetUtil.getData(response), AuctionOfferDetailResBean.class);

                    titleImg.setImageURI(Uri.parse(item.getImgUrl()));
                    title.setText(item.getProductName());
                    currentPrice.setText(spanHelper.priceSpan(R.string.current_price_, item.getCurrentPrice()));
                    fixedPrice.setText(spanHelper.priceSpan(R.string.fixed_price_, item.getaPrice()));
                    addBidLevel();
                    startPrice.setText(spanHelper.convertToPrice(R.string.start_price_, item.getInitPrice()));
                    minDealPrice.setText(spanHelper.convertToPrice(R.string.min_deal_price_, item.getMinimumPrice()));
                    minAddPrice.setText(spanHelper.convertToPrice(R.string.min_add_price_, item.getRangPrice()));
                    maxAddPrice.setText(spanHelper.convertToPrice(R.string.max_add_price_, item.getMaxRangPrice()));

                } else {
                    showToast(NetUtil.getError(response));
                }
            }
        }, null);


    }


    private void addBidLevel() {

        //bidLevelContainer.setOnItemChecked(this);
        bidLevelContainer.setPadding(10, 4);
        bidLevelContainer.setSelected(0);
        bidLevelContainer.setTags(item.getAddRangePrice(), new TagGroupLayout.BindProperty() {
            @Override
            public void OnBindProperty(TextView view) {
                view.setTextColor(ContextCompat.getColorStateList(AuctionBid.this, R.color.main_text_color_to_white));
                view.setBackground(ContextCompat.getDrawable(AuctionBid.this, R.drawable.circle_stroke_gray_to_fill_red_sl));
            }
        });

//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        params.rightMargin = getResources().getDimensionPixelOffset(R.dimen.padding_small);
//        int topBottom = getResources().getDimensionPixelOffset(R.dimen.padding_smallest_);
//        int leftRight=getResources().getDimensionPixelOffset(R.dimen.padding_bigger);
//
//        for (int i = 0; i < 5; i++) {
//            TextView textView = new TextView(this);
//            textView.setBackground(getResources().getDrawable(R.drawable.circle_stroke_gray_to_fill_red_sl));
//            textView.setClickable(true);
//            textView.setGravity(Gravity.CENTER);
//            textView.setLayoutParams(params);
//            textView.setPadding(leftRight, topBottom, leftRight, topBottom);
//            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimension(R.dimen.text_size_large));
//            textView.setTextColor(getResources().getColorStateList(R.color.main_text_color_to_white));
//            textView.setEnabled(true);
//            textView.setText("￥" + i * 10);
//
//            bidLevelContainer.addView(textView);
//            textView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (selectedPrice != null) {
//                        selectedPrice.setSelected(false);
//                    }
//                    selectedPrice = (TextView) v;
//                    selectedPrice.setSelected(true);
//
//                    myBidPrice.setText(selectedPrice.getText());
//                }
//            });
//
//        }
    }

    public void submitPrice(View view) {
        Long price=(Long)bidLevelContainer.getSelectedItem();
        if (price!=null){
           apiHandler.auctionBid(new Response.Listener<JSONObject>() {
               @Override
               public void onResponse(JSONObject response) {

                   if (NetUtil.isSucced(response)){
                       UIHelper.showToast(AuctionBid.this, "报价成功", Toast.LENGTH_SHORT);
                   }else{
                       showToast(NetUtil.getError(response));
                   }
               }
           },29,price);
        }


    }

    public void buyWithFixedPrice(View view) {
        Long price=(Long)bidLevelContainer.getSelectedItem();
        if (price!=null){
            apiHandler.auctionByAPrice(new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if (NetUtil.isSucced(response)){
                        UIHelper.showToast(AuctionBid.this, "报价成功", Toast.LENGTH_SHORT);
                    }else{
                        showToast(NetUtil.getError(response));
                    }
                }
            },29,price);
        }
    }
}
