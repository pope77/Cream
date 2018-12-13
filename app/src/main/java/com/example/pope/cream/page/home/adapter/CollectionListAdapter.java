package com.example.pope.cream.page.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pope.cream.R;

import java.util.List;

public class CollectionListAdapter extends RecyclerView.Adapter<CollectionListAdapter.ViewHolder> {

    private String type;
    private List<String> idList;
    private List<String> coverUrls;
    private List<String> titles;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public CollectionListAdapter(String type, List<String> idList, List<String> coverUrls, List<String> titles, Context context) {
        this.type = type;
        this.idList = idList;
        this.coverUrls = coverUrls;
        this.titles = titles;
        this.context = context;
    }

    interface OnItemClickListener {
        void onClick(String type, List<String> idList);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_collectionlist, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.i("error7","运行到了");
        Glide.with(context).load(coverUrls.get(position)).into(holder.itemPic);
        holder.itemTitle.setText(titles.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(type,idList);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemPic;
        TextView itemTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            itemPic = itemView.findViewById(R.id.imageView_collectionItemCover);
            itemTitle = itemView.findViewById(R.id.textView_collectionTitle);
        }
    }
}
