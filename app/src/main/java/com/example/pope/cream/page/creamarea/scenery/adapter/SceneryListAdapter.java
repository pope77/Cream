package com.example.pope.cream.page.creamarea.scenery.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pope.cream.R;
import com.example.pope.cream.biz.beans.SceneryBean;

import java.util.List;

public class SceneryListAdapter extends RecyclerView.Adapter<SceneryListAdapter.ViewHolder> {

    private List<SceneryBean> sceneryBeans;
    private Context context;
    private List<Boolean> isCollectedList;
    private OnCollectionListener onCollectionListener;
    private OnOpenDetailListener onOpenDetailListener;

    public SceneryListAdapter(List<SceneryBean> sceneryBeans, Context context, List<Boolean> isCollectedList) {
        this.sceneryBeans = sceneryBeans;
        this.context = context;
        this.isCollectedList = isCollectedList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_scenery_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SceneryBean bean = sceneryBeans.get(position);
        Glide.with(context).load(bean.getSceneryPicUrl()).into(holder.bgPic);
        holder.tvLikes.setText(String.valueOf(bean.getSceneryLikes()));
        holder.tvDislikes.setText(String.valueOf(bean.getSceneryDislikes()));
        holder.tvPrice.setText(String.valueOf(bean.getSceneryPrice()));
        if (isCollectedList.get(position)) {
            holder.tvCollection.setText("已收藏");
        } else {
            holder.tvCollection.setText("收藏");
        }
        switch (bean.getSceneryUICode()) {
            //red
            case 11:
                Glide.with(context).load(R.mipmap.ic_scenery_like_red).into(holder.icLike);
                Glide.with(context).load(R.mipmap.ic_scenery_dislike_red).into(holder.icDislike);
                if (isCollectedList.get(position)) {
                    Glide.with(context).load(R.mipmap.ic_scenery_collection_red_selected).into(holder.icCollection);
                } else {
                    Glide.with(context).load(R.mipmap.ic_scenery_collection_red).into(holder.icCollection);
                }
                Glide.with(context).load(R.mipmap.ic_scenery_price_red).into(holder.icPrice);
                holder.bg1.setImageDrawable(context.getDrawable(R.drawable.bg_scenery_card1_red));
                holder.bg2.setImageDrawable(context.getDrawable(R.drawable.bg_scenery_card2_red));
                holder.bg3.setImageDrawable(context.getDrawable(R.drawable.bg_scenery_card3_red));
                holder.tvLikes.setTextColor(context.getColor(R.color.sceneryRed));
                holder.tvDislikes.setTextColor(context.getColor(R.color.sceneryRed));
                holder.tvCollection.setTextColor(context.getColor(R.color.sceneryRed));
                holder.tvPrice.setTextColor(context.getColor(R.color.sceneryRed));
                break;
            //green
            case 22:
                Glide.with(context).load(R.mipmap.ic_scenery_like_green).into(holder.icLike);
                Glide.with(context).load(R.mipmap.ic_scenery_dislike_green).into(holder.icDislike);
                if (isCollectedList.get(position)) {
                    Glide.with(context).load(R.mipmap.ic_scenery_collection_green_selected).into(holder.icCollection);
                } else {
                    Glide.with(context).load(R.mipmap.ic_scenery_collection_green).into(holder.icCollection);
                }
                Glide.with(context).load(R.mipmap.ic_scenery_price_green).into(holder.icPrice);
                holder.bg1.setImageDrawable(context.getDrawable(R.drawable.bg_scenery_card1_green));
                holder.bg2.setImageDrawable(context.getDrawable(R.drawable.bg_scenery_card2_green));
                holder.bg3.setImageDrawable(context.getDrawable(R.drawable.bg_scenery_card3_green));
                holder.tvLikes.setTextColor(context.getColor(R.color.sceneryGreen));
                holder.tvDislikes.setTextColor(context.getColor(R.color.sceneryGreen));
                holder.tvCollection.setTextColor(context.getColor(R.color.sceneryGreen));
                holder.tvPrice.setTextColor(context.getColor(R.color.sceneryGreen));
                break;
            //yellow
            case 33:
                Glide.with(context).load(R.mipmap.ic_scenery_like_yellow).into(holder.icLike);
                Glide.with(context).load(R.mipmap.ic_scenery_dislike_yellow).into(holder.icDislike);
                if (isCollectedList.get(position)) {
                    Glide.with(context).load(R.mipmap.ic_scenery_collection_yellow_selected).into(holder.icCollection);
                } else {
                    Glide.with(context).load(R.mipmap.ic_scenery_collection_yellow).into(holder.icCollection);
                }
                Glide.with(context).load(R.mipmap.ic_scenery_price_yellow).into(holder.icPrice);
                holder.bg1.setImageDrawable(context.getDrawable(R.drawable.bg_scenery_card1_yellow));
                holder.bg2.setImageDrawable(context.getDrawable(R.drawable.bg_scenery_card2_yellow));
                holder.bg3.setImageDrawable(context.getDrawable(R.drawable.bg_scenery_card3_yellow));
                holder.tvLikes.setTextColor(context.getColor(R.color.sceneryYellow));
                holder.tvDislikes.setTextColor(context.getColor(R.color.sceneryYellow));
                holder.tvCollection.setTextColor(context.getColor(R.color.sceneryYellow));
                holder.tvPrice.setTextColor(context.getColor(R.color.sceneryYellow));
                break;
            //blue
            case 44:
                Glide.with(context).load(R.mipmap.ic_scenery_like_blue).into(holder.icLike);
                Glide.with(context).load(R.mipmap.ic_scenery_dislike_blue).into(holder.icDislike);
                if (isCollectedList.get(position)) {
                    Glide.with(context).load(R.mipmap.ic_scenery_collection_blue_selected).into(holder.icCollection);
                } else {
                    Glide.with(context).load(R.mipmap.ic_scenery_collection_blue).into(holder.icCollection);
                }
                Glide.with(context).load(R.mipmap.ic_scenery_price_blue).into(holder.icPrice);
                holder.bg1.setImageDrawable(context.getDrawable(R.drawable.bg_scenery_card1_blue));
                holder.bg2.setImageDrawable(context.getDrawable(R.drawable.bg_scenery_card2_blue));
                holder.bg3.setImageDrawable(context.getDrawable(R.drawable.bg_scenery_card3_blue));
                holder.tvLikes.setTextColor(context.getColor(R.color.sceneryBlue));
                holder.tvDislikes.setTextColor(context.getColor(R.color.sceneryBlue));
                holder.tvCollection.setTextColor(context.getColor(R.color.sceneryBlue));
                holder.tvPrice.setTextColor(context.getColor(R.color.sceneryBlue));
                break;
            //purple
            case 55:
                Glide.with(context).load(R.mipmap.ic_scenery_like_purple).into(holder.icLike);
                Glide.with(context).load(R.mipmap.ic_scenery_dislike_purple).into(holder.icDislike);
                if (isCollectedList.get(position)) {
                    Glide.with(context).load(R.mipmap.ic_scenery_collection_purple_selected).into(holder.icCollection);
                } else {
                    Glide.with(context).load(R.mipmap.ic_scenery_collection_purple).into(holder.icCollection);
                }
                Glide.with(context).load(R.mipmap.ic_scenery_price_purple).into(holder.icPrice);
                holder.bg1.setImageDrawable(context.getDrawable(R.drawable.bg_scenery_card1_purple));
                holder.bg2.setImageDrawable(context.getDrawable(R.drawable.bg_scenery_card2_purple));
                holder.bg3.setImageDrawable(context.getDrawable(R.drawable.bg_scenery_card3_purple));
                holder.tvLikes.setTextColor(context.getColor(R.color.sceneryPurple));
                holder.tvDislikes.setTextColor(context.getColor(R.color.sceneryPurple));
                holder.tvCollection.setTextColor(context.getColor(R.color.sceneryPurple));
                holder.tvPrice.setTextColor(context.getColor(R.color.sceneryPurple));
                break;
            default:
                break;
        }
        holder.sceneryName.setText(bean.getSceneryName());
        holder.sceneryAddr.setText(bean.getSceneryAddr());
        holder.sceneryAWord.setText(bean.getSceneryAWord());
        holder.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.introduce.setText(bean.getSceneryIntroduce());
                holder.guide.setText(bean.getSceneryGuide());
                holder.scrollView.setVisibility(View.VISIBLE);
                holder.changeCard(-1);
                if (onOpenDetailListener != null) {
                    onOpenDetailListener.open();
                }
            }
        });

        holder.bg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.introduce.setText(bean.getSceneryIntroduce());
                holder.guide.setText(bean.getSceneryGuide());
                holder.scrollView.setVisibility(View.VISIBLE);
                holder.changeCard(-1);
                if (onOpenDetailListener != null) {
                    onOpenDetailListener.open();
                }
            }
        });

        holder.tvCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isCollected = isCollectedList.get(position);
                if (isCollected) {
                    holder.changeCollecionUI(1, bean.getSceneryUICode(), position);
                } else {
                    holder.changeCollecionUI(-1, bean.getSceneryUICode(), position);
                }
                if (onCollectionListener != null) {
                    if (isCollected) {
                        onCollectionListener.collection(bean, false);
                    } else {
                        onCollectionListener.collection(bean, true);
                    }
                }
            }
        });

        holder.icCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isCollected = isCollectedList.get(position);
                if (isCollected) {
                    holder.changeCollecionUI(1, bean.getSceneryUICode(), position);
                } else {
                    holder.changeCollecionUI(-1, bean.getSceneryUICode(), position);
                }
                if (onCollectionListener != null) {
                    if (isCollected) {
                        onCollectionListener.collection(bean, false);
                    } else {
                        onCollectionListener.collection(bean, true);
                    }
                }
            }
        });

        holder.preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.changeCard(1);
            }
        });

        holder.bg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.changeCard(1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return sceneryBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView bgPic;
        CardView group1;
        ImageView bg1;
        ImageView bg2;
        ImageView icLike;
        ImageView icDislike;
        TextView tvLikes;
        TextView tvDislikes;
        TextView sceneryName;
        TextView sceneryAddr;
        TextView sceneryAWord;
        TextView tvCollection;
        TextView tvPrice;
        ImageView icPrice;
        ImageView icCollection;
        ImageView next;
        ConstraintLayout group2;
        TextView introduce;
        TextView guide;
        ScrollView scrollView;
        ImageView bg3;
        ImageView preview;


        public ViewHolder(View itemView) {
            super(itemView);
            bgPic = itemView.findViewById(R.id.imageView＿sceneryPic);
            group1 = itemView.findViewById(R.id.cardView_scenery_group1);
            bg1 = itemView.findViewById(R.id.imageView_sceneryCard_bg1);
            bg2 = itemView.findViewById(R.id.imageView_sceneryCard_bg2);
            icLike = itemView.findViewById(R.id.imageView_scenery_likeIcon);
            icDislike = itemView.findViewById(R.id.imageView_scenery_dislikeIcon);
            tvLikes = itemView.findViewById(R.id.textView_scenery_likes);
            tvDislikes = itemView.findViewById(R.id.textView_scenery_dislikes);
            sceneryName = itemView.findViewById(R.id.textView_sceneryName);
            sceneryAddr = itemView.findViewById(R.id.textView_sceneryAddr);
            sceneryAWord = itemView.findViewById(R.id.textView_scenery_aWord);
            tvCollection = itemView.findViewById(R.id.textView_scenery_collection);
            tvPrice = itemView.findViewById(R.id.textView_scenery_price);
            icPrice = itemView.findViewById(R.id.imageView_scenery_price);
            icCollection = itemView.findViewById(R.id.imageView_scenery_collection);
            next = itemView.findViewById(R.id.imageView_scenery_next);
            group2 = itemView.findViewById(R.id.constraintLayout_group2);
            introduce = itemView.findViewById(R.id.textView_sceneryIntroduce);
            guide = itemView.findViewById(R.id.textView_secnery_guide);
            scrollView = itemView.findViewById(R.id.scrollView_group2_container);
            bg3 = itemView.findViewById(R.id.imageView_sceneryCard_bg3);
            preview = itemView.findViewById(R.id.imageView_scenery_preview);
        }

        /**
         * 判断event坐标是否在ScrollView里
         *
         * @param x event的rowX
         * @param y event的rowY
         * @return 这个点在不在ScrollView范围内
         */
        public boolean isTouchScrollView(float x, float y) {
            int[] pos = new int[2];
            //获取屏幕上的位置
            scrollView.getLocationOnScreen(pos);
            int width = scrollView.getMeasuredWidth();
            int height = scrollView.getMeasuredHeight();
            return x >= pos[0] && x <= pos[0] + width && y >= pos[1] && y <= pos[1] + height;
        }

        void changeCard(int code) {
            switch (code) {
                case 1:
                    sceneryAWord.setVisibility(View.VISIBLE);
                    next.setVisibility(View.VISIBLE);
                    bg2.setVisibility(View.VISIBLE);
                    preview.setVisibility(View.GONE);
                    bg3.setVisibility(View.GONE);
                    scrollView.setVisibility(View.GONE);
                    break;
                case -1:
                    scrollView.setVisibility(View.VISIBLE);
                    next.setVisibility(View.GONE);
                    bg2.setVisibility(View.GONE);
                    preview.setVisibility(View.VISIBLE);
                    bg3.setVisibility(View.VISIBLE);
                    sceneryAWord.setVisibility(View.GONE);
                    break;
                default:
                    break;
            }
        }

        void changeCollecionUI(int code, int uiCode, int pos) {
            switch (code) {
                case 1:
                    tvCollection.setText("收藏");
                    switch (uiCode) {
                        case 11:
                            Glide.with(context).load(R.mipmap.ic_scenery_collection_red).into(icCollection);
                            break;
                        case 22:
                            Glide.with(context).load(R.mipmap.ic_scenery_collection_green).into(icCollection);
                            break;
                        case 33:
                            Glide.with(context).load(R.mipmap.ic_scenery_collection_yellow).into(icCollection);
                            break;
                        case 44:
                            Glide.with(context).load(R.mipmap.ic_scenery_collection_blue).into(icCollection);
                            break;
                        case 55:
                            Glide.with(context).load(R.mipmap.ic_scenery_collection_purple).into(icCollection);
                            break;
                        default:
                            break;
                    }
                    isCollectedList.set(pos, false);
                    break;
                case -1:
                    tvCollection.setText("已收藏");
                    switch (uiCode) {
                        case 11:
                            Glide.with(context).load(R.mipmap.ic_scenery_collection_red_selected).into(icCollection);
                            break;
                        case 22:
                            Glide.with(context).load(R.mipmap.ic_scenery_collection_green_selected).into(icCollection);
                            break;
                        case 33:
                            Glide.with(context).load(R.mipmap.ic_scenery_collection_yellow_selected).into(icCollection);
                            break;
                        case 44:
                            Glide.with(context).load(R.mipmap.ic_scenery_collection_blue_selected).into(icCollection);
                            break;
                        case 55:
                            Glide.with(context).load(R.mipmap.ic_scenery_collection_purple_selected).into(icCollection);
                            break;
                        default:
                            break;
                    }
                    isCollectedList.set(pos, true);
                    break;
                default:
                    break;
            }
        }

    }

    interface OnCollectionListener {
        void collection(SceneryBean bean, boolean collectThisScenery);
    }

    public void setOnCollectionListener(OnCollectionListener onCollectionListener) {
        this.onCollectionListener = onCollectionListener;
    }

    interface OnOpenDetailListener {
        void open();
    }

    public void setOnOpenDetailListener(OnOpenDetailListener onOpenDetailListener) {
        this.onOpenDetailListener = onOpenDetailListener;
    }

}
