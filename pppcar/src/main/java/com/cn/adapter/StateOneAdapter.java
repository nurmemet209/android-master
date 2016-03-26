package com.cn.adapter;

import android.content.Context;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cn.entity.BaseEntity;
import com.cn.pppcar.R;
import com.cn.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nurmemet on 2016/3/19.
 */
public class StateOneAdapter extends RecyclerView.Adapter<StateOneAdapter.StateViewHolder>{
    private List<BaseEntity> dataList=new ArrayList<>();
    private Context context;
    private BaseEntity selectedEntity;
    private OnSelListner onSelListner;
    private RecyclerView recyclerView;

    public static interface OnSelListner{
        void OnSel(BaseEntity selectedEntity);


    }

    public StateOneAdapter(Context context,RecyclerView recyclerView,List<BaseEntity> dataList,BaseEntity selectedEntity,OnSelListner onSelListner) {
        this.context=context;
        this.dataList=dataList;
        this.selectedEntity =selectedEntity;
        this.onSelListner=onSelListner;
        this.recyclerView=recyclerView;
    }

    @Override
    public int getItemCount() {
        if(Util.isNoteEmpty(dataList)){
            return  dataList.size();
        }
        return 0;
    }

    @Override
    public StateOneAdapter.StateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.multi_stage_item,null);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,RecyclerView.LayoutParams.WRAP_CONTENT));
        StateViewHolder holder=new StateViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(StateOneAdapter.StateViewHolder holder, final int position) {
        holder.tv.setText(dataList.get(position).getName());

        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedEntity!=null){
                    int index=dataList.indexOf(selectedEntity);
                    StateOneAdapter.this.notifyItemChanged(index);
                    //StateOneAdapter.this.notifyItemRangeChanged(index,index+1);
                }
                selectedEntity=dataList.get(position);
                v.setSelected(true);
                if(onSelListner!=null){
                    onSelListner.OnSel(selectedEntity);
                }
            }
        });
        if (dataList.get(position).equals(selectedEntity)){
            holder.tv.setSelected(true);
        }else{
            holder.tv.setSelected(false);
        }
    }

    public static class StateViewHolder extends RecyclerView.ViewHolder{
        public  TextView tv;
        public StateViewHolder(View view){
            super(view);
            tv=(TextView) view.findViewById(R.id.name);
        }
    }

    public void setData(List<BaseEntity> dataList){
        this.dataList=dataList;
    }
}
