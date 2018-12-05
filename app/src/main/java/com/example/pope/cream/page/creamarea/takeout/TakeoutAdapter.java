package com.example.pope.cream.page.creamarea.takeout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.pope.cream.R;
import com.example.pope.cream.biz.beans.TakeOutBean;

import java.util.List;

public class TakeoutAdapter extends RecyclerView.Adapter<TakeoutAdapter.ViewHolder> {

    private List<TakeOutBean> takeOutBeans;
    private Context context;

    public TakeoutAdapter(List<TakeOutBean> takeOutBeans, Context context) {
        this.takeOutBeans = takeOutBeans;
        this.context = context;
    }

    @Override
    public TakeoutAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_takeout_card,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(TakeoutAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(takeOutBeans.get(position).getTakeoutPicUrl()).placeholder(R.mipmap.bg_spin_card).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return takeOutBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView_takeout_card);
        }
    }
}
