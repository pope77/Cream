package com.example.pope.cream.page.creamarea.program.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pope.cream.R;
import com.example.pope.cream.biz.beans.ProgramBean;

import java.util.List;

/**
 * @author popeg
 */
public class ProgramListAdapter extends RecyclerView.Adapter<ProgramListAdapter.ViewHolder> {

    private Context context;
    private List<ProgramBean> programBeans;
    private OnCardClickListener onCardClickListener;
    private OnPlayListener onPlayListener;

    public ProgramListAdapter(Context context, List<ProgramBean> programBeans) {
        this.context = context;
        this.programBeans = programBeans;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_program_recommend, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ProgramBean bean = programBeans.get(position);
        String name = bean.getProgramName();
        String time = bean.getProgramTime();
        String aWord = bean.getProgramAWord();
        String score = String.valueOf(bean.getProgramScore());
        holder.programName.setText(name);
        holder.programTime.setText(time);
        holder.programAWord.setText(aWord);
        holder.programScore.setText(score);
        Glide.with(context).load(bean.getProgramPosterUrl()).placeholder(R.mipmap.bg_program_poster).into(holder.programPoster);
        holder.groupCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onCardClickListener != null) {
                    onCardClickListener.onClick(bean);
                }
            }
        });
        holder.groupPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onPlayListener != null) {
                    onPlayListener.onClick(bean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return programBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView programPoster;
        TextView programName;
        TextView programTime;
        TextView programAWord;
        TextView programScore;
        ConstraintLayout groupPlay;
        ConstraintLayout groupCard;

        public ViewHolder(View itemView) {
            super(itemView);
            groupCard = itemView.findViewById(R.id.group_program_card);
            groupPlay = itemView.findViewById(R.id.group_program_play);
            programPoster = itemView.findViewById(R.id.imageView_program_poster);
            programName = itemView.findViewById(R.id.textView_program_name);
            programTime = itemView.findViewById(R.id.textView_program_time);
            programAWord = itemView.findViewById(R.id.textView_program_aWord);
            programScore = itemView.findViewById(R.id.textView_program_score);
        }
    }

    public interface OnCardClickListener {
        void onClick(ProgramBean programBean);
    }

    public void setCardOnClickListener(OnCardClickListener onCardClickListener) {
        this.onCardClickListener = onCardClickListener;
    }

    public interface OnPlayListener {
        void onClick(ProgramBean programBean);
    }

    public void setOnPlayListener(OnPlayListener onPlayListener) {
        this.onPlayListener = onPlayListener;
    }

}
