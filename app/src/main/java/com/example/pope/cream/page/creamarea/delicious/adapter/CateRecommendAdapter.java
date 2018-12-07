package com.example.pope.cream.page.creamarea.delicious.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pope.cream.R;
import com.example.pope.cream.biz.beans.CateBean;

import java.util.List;

public class CateRecommendAdapter extends RecyclerView.Adapter<CateRecommendAdapter.ViewHolder> {

    private OnItemClickListener onItemClickListener;
    private List<CateBean> cateBeans;
    private Context context;

    public CateRecommendAdapter(List<CateBean> cateBeans, Context context) {
        this.cateBeans = cateBeans;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_catelist,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        CateBean cateBean = cateBeans.get(position);
        Glide.with(context).load(cateBean.getCateCardPic()).placeholder(R.mipmap.bg_cateitem).into(holder.bigPic);
        int a = 0;
        double score = cateBean.getCateScore();
        if (score == 5.0){
            a = R.mipmap.ic_score_five;
        }else if (score >= 4.0){
            a = R.mipmap.ic_score_four;
        }else if (score >= 3.0){
            a = R.mipmap.ic_score_three;
        }else if(score >= 2.0){
            a = R.mipmap.ic_score_two;
        }else if (score >= 1.0){
            a = R.mipmap.ic_score_one;
        }
        if (a == 0){
            Toast.makeText(context,"error评分小于1",Toast.LENGTH_SHORT).show();
        }
        Glide.with(context).load(a).into(holder.scoreImage);
        holder.name.setText(cateBean.getCateName());
        holder.scoreTxt.setText(score+"分");
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
        return cateBeans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView bigPic;
        TextView name;
        ImageView scoreImage;
        TextView scoreTxt;

        ViewHolder(View itemView) {
            super(itemView);
            bigPic = itemView.findViewById(R.id.imageView_cateItem_bigPic);
            name = itemView.findViewById(R.id.textView_cateName);
            scoreImage = itemView.findViewById(R.id.imageView_cateScore);
            scoreTxt = itemView.findViewById(R.id.textView_cateScore);
        }
    }

    public interface OnItemClickListener{
        void onClick(Integer position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

}
