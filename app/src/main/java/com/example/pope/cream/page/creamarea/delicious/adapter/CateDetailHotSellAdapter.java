package com.example.pope.cream.page.creamarea.delicious.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pope.cream.R;
import com.example.pope.cream.biz.beans.CateBean;

public class CateDetailHotSellAdapter extends RecyclerView.Adapter<CateDetailHotSellAdapter.ViewHolder> {

    private Context context;
    private CateBean cateBean;

    public CateDetailHotSellAdapter(Context context, CateBean cateBean) {
        this.context = context;
        this.cateBean = cateBean;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_program_actorlist, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(cateBean.getCatePics().get(position)).into(holder.pic);
        holder.name.setText(cateBean.getCateNames().get(position));
    }

    @Override
    public int getItemCount() {
        return cateBean.getCatePics().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView pic;
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            pic = itemView.findViewById(R.id.imageView_actorAvatar);
            name = itemView.findViewById(R.id.textView_actorName);
        }
    }
}
