package com.example.pope.cream.page.hot.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pope.cream.R;
import com.example.pope.cream.biz.beans.HotBean;

import java.util.List;

public class HotListAdapter extends RecyclerView.Adapter<HotListAdapter.ViewHolder> {

    private List<HotBean> hotBeans;
    private List<String> titles;
    private OnItemClickListener onItemClickListener;

    public HotListAdapter(List<HotBean> hotBeans, List<String> titles) {
        this.hotBeans = hotBeans;
        this.titles = titles;
    }

    public interface OnItemClickListener{

        void click(String id,String type);

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hot_list,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.type.setText(hotBeans.get(position).getHotType());
        holder.name.setText(titles.get(position));
        holder.no.setText(String.valueOf(position+1));
        holder.itemView.setOnClickListener(view -> {
            if (onItemClickListener!=null){
                onItemClickListener.click(hotBeans.get(position).getHotObjId(),hotBeans.get(position).getHotType());
            }
        });
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView type;
        TextView name;
        TextView no;
        public ViewHolder(View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.textView_hotList_type);
            name = itemView.findViewById(R.id.textView_hotList_name);
            no = itemView.findViewById(R.id.textView_hotList_no);
        }
    }
}
