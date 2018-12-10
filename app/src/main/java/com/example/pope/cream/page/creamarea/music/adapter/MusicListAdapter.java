package com.example.pope.cream.page.creamarea.music.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pope.cream.R;
import com.example.pope.cream.biz.beans.MusicBean;

import java.util.List;

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.ViewHolder> {

    private List<MusicBean> beans;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public MusicListAdapter(List<MusicBean> beans, Context context) {
        this.beans = beans;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_music_card,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(beans.get(position).getMusicCover()).into(holder.cover);
        holder.songName.setText(beans.get(position).getMusicName());
        holder.singerName.setText(beans.get(position).getMusicSingerName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener!=null){
                    onItemClickListener.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return beans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView cover;
        TextView songName;
        TextView singerName;

        public ViewHolder(View itemView) {
            super(itemView);
            cover = itemView.findViewById(R.id.imageView_musicItem_cover);
            songName = itemView.findViewById(R.id.textView_musicItem_songName);
            singerName = itemView.findViewById(R.id.textView_musicItem_singerName);
        }
    }

    public interface OnItemClickListener{
        void onClick(Integer position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

}
