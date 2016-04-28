package com.cn.pppcar;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cn.entity.Item;
import com.cn.util.UIHelper;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nurmemet on 2016/4/22.
 */
public class AuctionBid extends BaseAct {

    @Bind(R.id.bid_level_container)
    protected LinearLayout bidLevelContainer;
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

    Item item = new Item();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_auction_bid);
        ButterKnife.bind(this);
        bindData();

    }

    private void bindData() {

        item.setImgAddress("http://img.pppcar.com/image/getImage/570dd7104d5d393c32095af7/820/0/80/WaterMark");
        item.setTitle("RS高品制燃油添加剂 24瓶/一箱（重性能 增加燃油辛烷值5个点 减少引擎爆震）");
        item.setCurrentPrice(123.00F);
        item.setFixedPrice(2344.00F);
        item.setMinAucitionPrice(4455.00F);
        item.setMaxAddPrice(35435.F);
        item.setMinAddPrice(34434F);
        item.setStartPrice(345454F);


        titleImg.setImageURI(Uri.parse(item.getImgAddress()));
        title.setText(item.getTitle());
        currentPrice.setText(spanHelper.priceSpan(R.string.current_price_,item.getCurrentPrice()));
        fixedPrice.setText(spanHelper.priceSpan(R.string.fixed_price_,item.getFixedPrice()));
        addBidLevel();
        startPrice.setText(spanHelper.convertToPrice(R.string.start_price_,item.getStartPrice()));
        minDealPrice.setText(spanHelper.convertToPrice(R.string.min_deal_price_,item.getMinAucitionPrice()));
        minAddPrice.setText(spanHelper.convertToPrice(R.string.min_add_price_,item.getMinAddPrice()));
        maxAddPrice.setText(spanHelper.convertToPrice(R.string.max_add_price_,item.getMaxAddPrice()));
    }


    private void addBidLevel() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.rightMargin = getResources().getDimensionPixelOffset(R.dimen.padding_small);
        int topBottom = getResources().getDimensionPixelOffset(R.dimen.padding_smallest_);
        int leftRight=getResources().getDimensionPixelOffset(R.dimen.padding_bigger);

        for (int i = 0; i < 5; i++) {
            TextView textView = new TextView(this);
            textView.setBackground(getResources().getDrawable(R.drawable.round_rectangle_to_rectangle_red_sl));
            textView.setClickable(true);
            textView.setGravity(Gravity.CENTER);
            textView.setLayoutParams(params);
            textView.setPadding(leftRight, topBottom, leftRight, topBottom);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimension(R.dimen.text_size_large));
            textView.setTextColor(getResources().getColorStateList(R.color.main_text_color_to_white));
            textView.setEnabled(true);
            textView.setText("￥" + i * 10);

            bidLevelContainer.addView(textView);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (selectedPrice != null) {
                        selectedPrice.setSelected(false);
                    }
                    selectedPrice = (TextView) v;
                    selectedPrice.setSelected(true);

                    myBidPrice.setText(selectedPrice.getText());
                }
            });

        }
    }

    public void submitPrice(View view){
        UIHelper.showToast(this,"提交报价", Toast.LENGTH_SHORT);

    }
    public void buyWithFixedPrice(View view){
        UIHelper.showToast(this,"一口价拍下", Toast.LENGTH_SHORT);
    }
}
