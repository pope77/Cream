package com.example.pope.cream.page.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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

    public interface OnItemClickListener {
        void onClick(String id);
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
        Glide.with(context).load(coverUrls.get(position)).into(holder.itemPic);
        holder.itemTitle.setText(titles.get(position));
        holder.itemView.setOnClickListener(view -> {
            if (onItemClickListener != null) {
                onItemClickListener.onClick(idList.get(position));
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
            //不同类型 图片显示的裁剪方式不同
            switch (type) {
                case "书籍":
                    itemPic.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    break;
                case "电影":
                    itemPic.setScaleType(ImageView.ScaleType.CENTER);
                    break;
                case "综艺":
                    itemPic.setScaleType(ImageView.ScaleType.CENTER);
                    break;
                default:
                    break;
            }
        }
    }
}
