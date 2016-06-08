package com.cn.adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cn.commans.ActivitySwitcher;
import com.cn.commans.SpanHelper;
import com.cn.component.OnListItemWidgetClickedListener;
import com.cn.entity.ResOrder;
import com.cn.entity.ResOrderProduct;
import com.cn.pppcar.R;
import com.cn.util.MyLogger;
import com.cn.util.Util;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * Created by nurmemet on 2016/4/5.
 */
public class OrderAdapter extends BaseLoadMoreAdapter<RecyclerView.ViewHolder, ResOrder> {
    //全部订单
    public final static int ALLORDER = 1;
    //待审核
    public final static int WAIT_EXAMINATE = 2;
    //待付款
    public final static int WAIT_PAY = 3;
    //待发货
    public final static int WAIT_DELIVER = 4;
    //待收货
    public final static int WAIT_RECEIVE = 5;
    //预订单
    public final static int PRE_ORDER = 6;
    private SpanHelper spanHelper;
    private int imgWidth;


    /**
     * 删除订单
     */
    public final static int DELETE_BTN = 1;
    /**
     * 取消订单
     */
    public final static int CANCEL_BTN = 2;
    /**
     * 付款
     */
    public final static int PAY_BTN = 3;
    /**
     * 申请退款
     */
    public final static int REFUND_BTN = 4;
    /**
     * 查看物流
     */
    public final static int SEE_LOGISTIC_BTN = 5;


    private int adapterType = 1;

    private OnListItemWidgetClickedListener onListItemWidgetClickedListener;

    public OrderAdapter(ArrayList<ResOrder> list, Context mContext, int adapterType, OnListItemWidgetClickedListener onListItemWidgetClickedListener) {
        super(mContext, list);
        this.list = list;
        this.mContext = mContext;
        this.adapterType = adapterType;
        spanHelper = new SpanHelper(mContext);
        this.onListItemWidgetClickedListener = onListItemWidgetClickedListener;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateItemHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_list_order, null);
        view.setClickable(true);
        RecyclerView.ViewHolder holder = new RecyclerView.ViewHolder(view) {
        };
        return holder;
    }

    @Override
    protected void onBindItemHolder(final RecyclerView.ViewHolder holder, int position) {
        View view = holder.itemView;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();
                ActivitySwitcher.toOrderDetail((Activity) mContext, list.get(pos).getId());
            }
        });
        int productNum = 0;
        if (list.get(position).getOrderProducts().size() == 1) {
            view.findViewById(R.id.image_container).setVisibility(View.GONE);
            view.findViewById(R.id.img_title_container).setVisibility(View.VISIBLE);

            SimpleDraweeView img = (SimpleDraweeView) view.findViewById(R.id.title_img);
            TextView title = (TextView) view.findViewById(R.id.item_title);
            ResOrderProduct pro = list.get(position).getOrderProducts().get(0);
            img.setImageURI(Uri.parse(pro.getImgs()));
            img.setVisibility(View.VISIBLE);
            if (adapterType == PRE_ORDER) {
                title.setText(spanHelper.span("[预订]", pro.getName()));
            } else {
                title.setText(pro.getName());
            }


            title.setVisibility(View.VISIBLE);
            productNum = pro.getProNum();

        } else if (list.get(position).getOrderProducts().size() > 1) {
            view.findViewById(R.id.image_container).setVisibility(View.VISIBLE);
            view.findViewById(R.id.img_title_container).setVisibility(View.GONE);
            ViewGroup imgGroup = (ViewGroup) view.findViewById(R.id.image_container);
            for (int i = 0; i < imgGroup.getChildCount(); i++) {
                SimpleDraweeView img = (SimpleDraweeView) imgGroup.getChildAt(i);
                if (i >= list.get(position).getOrderProducts().size()) {
                    break;
                }
                ResOrderProduct product = list.get(position).getOrderProducts().get(i);
                img.setImageURI(Uri.parse(product.getImgs()));
                productNum += product.getProNum();
            }
        }
        TextView state = (TextView) view.findViewById(R.id.tarde_result);
        String stateStr = "unknown";
        switch (list.get(position).getState()) {
            case "payed":
                stateStr = "已付款";
                break;
            case "cancel":
                stateStr = "交易关闭";
                break;
            case "paying":
                stateStr = "等待买家付款";
                break;
            case "finish":
                stateStr = "交易成功";
                break;
            case "audit":
                stateStr = "待审核";
                break;
            case "deliver":
                stateStr = "卖家已发货";
                break;
            case "receive":
                stateStr = "已收货";
                break;
            case "prepaying":
                stateStr = "待付首款";
                break;
            case "ordered":
                stateStr = "订货中";
                break;
            case "arrive":
                stateStr = "待付尾款";
                break;
            case "prepayed":
                stateStr = "完结";
                break;
            default:
                MyLogger.showError(list.get(position).getState());
                break;

        }
        state.setText(stateStr);
        //保留
        TextView result = (TextView) view.findViewById(R.id.sub_trade_result);
        TextView price = (TextView) view.findViewById(R.id.price);
        price.setText(spanHelper.getProductNumAndTotalPrice(String.valueOf(productNum), "￥" + String.valueOf(list.get(position).getTotalPrice())));
        setActionButton(holder, position);
    }


    @Override
    protected RecyclerView.ViewHolder getLoadingMoreViewHolder(View loadingMoreView) {
        RecyclerView.ViewHolder holder = new RecyclerView.ViewHolder(loadingMoreView) {
        };
        return holder;
    }

    private void setActionButton(RecyclerView.ViewHolder holder, int position) {
        ViewGroup actionContainer = (ViewGroup) holder.itemView.findViewById(R.id.container_button);
        actionContainer.removeAllViews();

        switch (list.get(position).getState()) {
//            case "payed":
//                break;
            case "cancel":
                addLabelButton(actionContainer, "删除", holder, DELETE_BTN);
                actionContainer.setVisibility(View.VISIBLE);
                break;
            case "paying":
                addLabelButton(actionContainer, "取消", holder, CANCEL_BTN);
                addLabelButton(actionContainer, "付款", holder, PAY_BTN);
                actionContainer.setVisibility(View.VISIBLE);
                break;
            case "finish":
                addLabelButton(actionContainer, "删除", holder, DELETE_BTN);
                actionContainer.setVisibility(View.VISIBLE);
                break;
            case "audit":
                addLabelButton(actionContainer, "取消", holder, CANCEL_BTN);
                actionContainer.setVisibility(View.VISIBLE);
                break;
//            case "deliver":
//                break;
//            case "receive":
//                break;
            case "prepaying":
                addLabelButton(actionContainer, "付款", holder, PAY_BTN);
                actionContainer.setVisibility(View.VISIBLE);
                break;
//            case "ordered"://订货中
//                break;
            case "arrive"://待付尾款
                addLabelButton(actionContainer, "付款", holder, PAY_BTN);
                actionContainer.setVisibility(View.VISIBLE);
                break;
            case "prepayed"://完结
                addLabelButton(actionContainer, "删除", holder, DELETE_BTN);
                actionContainer.setVisibility(View.VISIBLE);
                break;
            default:
                actionContainer.setVisibility(View.GONE);
                break;
        }


    }

    private void addLabelButton(ViewGroup container, String title, final RecyclerView.ViewHolder holder, final int actionType) {
        TextView action = new TextView(mContext);
        int lr = mContext.getResources().getDimensionPixelSize(R.dimen.padding_big);
        int tb = mContext.getResources().getDimensionPixelSize(R.dimen.padding_smallest);
        action.setPadding(lr, tb, lr, tb);
        int textSize = mContext.getResources().getDimensionPixelSize(R.dimen.text_size_small);
        action.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        action.setText(title);
        action.setClickable(true);
        if (actionType == PAY_BTN) {
            action.setTextColor(ContextCompat.getColor(mContext, R.color.main_red));
            action.setBackgroundResource(R.drawable.round_rect_stroke_main_red);
        } else {
            action.setBackgroundResource(R.drawable.round_rect_stroke_main_text_color);
        }

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.leftMargin = mContext.getResources().getDimensionPixelSize(R.dimen.padding_normal);
        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onListItemWidgetClickedListener.OnItemClicke(actionType, holder, null);

            }
        });
        container.addView(action, params);
        // action.setLayoutParams(params);
        return;
    }

}
