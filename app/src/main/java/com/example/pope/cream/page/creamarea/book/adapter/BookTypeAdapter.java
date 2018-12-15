package com.example.pope.cream.page.creamarea.book.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.pope.cream.R;

import java.util.List;

public class BookTypeAdapter extends RecyclerView.Adapter<BookTypeAdapter.ViewHolder> {

    private List<Integer> pics;
    private OnItemClickListener onItemClickListener;

    public BookTypeAdapter(List<Integer> pics) {
        this.pics = pics;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_booktype, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.pic.setImageResource(pics.get(position));
        holder.pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(pics.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return pics.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView pic;

        public ViewHolder(View itemView) {
            super(itemView);
            pic = itemView.findViewById(R.id.imageView_bookType_item);
        }
    }

    public interface OnItemClickListener {
        void onClick(Integer picId);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
