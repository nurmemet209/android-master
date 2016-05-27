package com.cn.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cn.commans.SpanHelper;
import com.cn.component.OnListItemWidgetClickedListener;
import com.cn.entity.ResOrder;
import com.cn.entity.ResOrderProduct;
import com.cn.pppcar.R;
import com.cn.util.Util;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * Created by nurmemet on 2016/4/5.
 */
public class OrderAdapter extends BaseListAdapter<ResOrder> {
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
    private final static int DELETE_BTN = 1;
    /**
     * 取消订单
     */
    private final static int CANCEL_BTN = 2;
    /**
     * 付款
     */
    private final static int PAY_BTN = 3;
    /**
     * 申请退款
     */
    private final static int REFUND_BTN = 4;
    /**
     * 查看物流
     */
    private final static int SEE_LOGISTIC_BTN = 5;


    private int adapterType = 1;

    private OnListItemWidgetClickedListener onListItemWidgetClickedListener;

    public OrderAdapter(ArrayList<ResOrder> list, Context mContext, int adapterType, OnListItemWidgetClickedListener onListItemWidgetClickedListener) {
        super(mContext, list);
        this.list = list;
        this.mContext = mContext;
        this.adapterType = adapterType;
        spanHelper = new SpanHelper(mContext);
        WindowManager wm = (WindowManager) mContext
                .getSystemService(Context.WINDOW_SERVICE);
        imgWidth = (wm.getDefaultDisplay().getWidth() - 5 * mContext.getResources().getDimensionPixelSize(R.dimen.padding_normal)) / 4;
        this.onListItemWidgetClickedListener = onListItemWidgetClickedListener;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_list_order, null);
        RecyclerView.ViewHolder holder = new RecyclerView.ViewHolder(view) {
        };
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        View view = holder.itemView;
        ViewGroup container = (ViewGroup) view.findViewById(R.id.container);
        int count = container.getChildCount();
        if (container.getChildCount() > 2) {
            for (int i = 0; i < container.getChildCount(); i++) {
                if (container.getChildAt(i).getId() == R.id.item_title || container.getChildAt(i).getId() == R.id.title_img) {
                    continue;
                }
                container.removeViewAt(i);
                i = i - 1;
                count = container.getChildCount();

            }
        }
        count = container.getChildCount();
        SimpleDraweeView img = (SimpleDraweeView) view.findViewById(R.id.title_img);
        TextView title = (TextView) view.findViewById(R.id.item_title);
        int productNum = 0;
        if (list.get(position).getOrderProducts().size() == 1) {
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
            img.setVisibility(View.GONE);
            title.setVisibility(View.GONE);
            for (int i = 0; i < list.get(position).getOrderProducts().size(); i++) {
                ResOrderProduct pro = list.get(position).getOrderProducts().get(i);
                if (i < 4) {
                    SimpleDraweeView proImg = new SimpleDraweeView(mContext);
                    int w = mContext.getResources().getDimensionPixelSize(R.dimen.list_item_img_height);
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(w, w);

                    proImg.setLayoutParams(params);
                    proImg.setImageURI(Uri.parse(pro.getImgs()));
                    proImg.setPadding(mContext.getResources().getDimensionPixelSize(R.dimen.padding_normal), 0, 0, 0);
                    container.addView(proImg);
                }

                productNum += pro.getProNum();
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
                break;

        }
        state.setText(stateStr);
        TextView result = (TextView) view.findViewById(R.id.sub_trade_result);
        TextView price = (TextView) view.findViewById(R.id.price);
        //price.setText();
        price.setText(spanHelper.getProductNumAndTotalPrice(String.valueOf(productNum), "￥" + String.valueOf(list.get(position).getTotalPrice())));

        setActionButton(holder,position);


    }

    @Override
    public int getItemCount() {
        if (Util.isNoteEmpty(list)) {
            return list.size();
        }
        return 0;
    }

    private void setActionButton(RecyclerView.ViewHolder holder, int position) {
        ViewGroup actionContainer = (ViewGroup) holder.itemView.findViewById(R.id.container_button);
        actionContainer.removeAllViews();
        switch (list.get(position).getState()) {
            case "payed":
                break;
            case "cancel":
                actionContainer.addView(getLabelButton("删除",holder,DELETE_BTN));
                break;
            case "paying":
                actionContainer.addView(getLabelButton("取消",holder,CANCEL_BTN));
                actionContainer.addView(getLabelButton("付款",holder,PAY_BTN));
                break;
            case "finish":
                actionContainer.addView(getLabelButton("删除",holder,DELETE_BTN));
                break;
            case "audit":
                actionContainer.addView(getLabelButton("取消",holder,CANCEL_BTN));
                break;
            case "deliver":
                break;
            case "receive":
                break;
            case "prepaying":
                actionContainer.addView(getLabelButton("付款",holder,PAY_BTN));
                break;
            case "ordered"://订货中
                break;
            case "arrive"://待付尾款
                actionContainer.addView(getLabelButton("付款",holder,PAY_BTN));
                break;
            case "prepayed"://完结
                actionContainer.addView(getLabelButton("删除",holder,DELETE_BTN));
                break;
            default:
                break;

        }


    }

    private TextView getLabelButton(String title, final RecyclerView.ViewHolder holder, final int actionType) {
        TextView action = new TextView(mContext);
        int lr = mContext.getResources().getDimensionPixelSize(R.dimen.padding_normal);
        int tb = mContext.getResources().getDimensionPixelSize(R.dimen.padding_smallest_);
        action.setPadding(lr, tb, lr, tb);
        int textSize = mContext.getResources().getDimensionPixelSize(R.dimen.text_size_small);
        action.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        action.setText(title);
        action.setBackgroundResource(R.drawable.rectangle);
        LinearLayoutCompat.LayoutParams params = new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.leftMargin = mContext.getResources().getDimensionPixelSize(R.dimen.padding_normal);
        action.setLayoutParams(params);
        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onListItemWidgetClickedListener.OnItemClicke(actionType, holder, null);

            }
        });

        return action;
    }

}
