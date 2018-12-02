package com.example.pope.cream.page.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pope.cream.R;
import com.example.pope.cream.page.home.CreamFragment;

import java.util.List;

/**
 * 首页Cream地带RecyclerView适配器
 * @author popeg
 */
public class CreamAreaAdapter extends RecyclerView.Adapter<CreamAreaAdapter.ViewHolder> {

    private List<String> titleList;
    private List<Integer> picList;
    private Context context;
    private OnItemClickListener onItemClickListener = null;

    public CreamAreaAdapter(List<String> titleList, List<Integer> picList, Context context) {
        this.titleList = titleList;
        this.picList = picList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cream_area,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        String title = titleList.get(position);
        int path = picList.get(position);
        holder.title.setText(title);
        Glide.with(context).load(path).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener!=null){
                    onItemClickListener.onClick(holder.title.getText().toString());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return titleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView_creamArea_item);
            title = itemView.findViewById(R.id.textView_creamArea_item);
        }
    }

    public interface OnItemClickListener{
        void onClick(String title);
    }

    public void setItemOnClickListener(OnItemClickListener onClickListener){
        this.onItemClickListener = onClickListener;
    }

}
