package com.example.pope.cream.page.creamarea.program.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pope.cream.R;
import com.example.pope.cream.biz.beans.ProgramBean;

public class ActorListAdapter extends RecyclerView.Adapter<ActorListAdapter.ViewHolder> {

    private ProgramBean programBean;
    private Context context;

    public ActorListAdapter(ProgramBean programBean, Context context) {
        this.programBean = programBean;
        this.context = context;
    }

    @Override
    public ActorListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_program_actorlist, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ActorListAdapter.ViewHolder holder, int position) {
        int isMovie = programBean.getProgramType();
        Glide.with(context).load(programBean.getProgramActorImage().get(position)).into(holder.actorAvatar);
        String name = programBean.getProgramActorList().get(position);
        if (isMovie == 1) {
            name = name + "\n饰\n" + programBean.getProgramRoleList().get(position);
        }
        holder.actorName.setText(name);
    }

    @Override
    public int getItemCount() {
        return programBean.getProgramActorList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView actorAvatar;
        TextView actorName;

        public ViewHolder(View itemView) {
            super(itemView);
            actorAvatar = itemView.findViewById(R.id.imageView_actorAvatar);
            actorName = itemView.findViewById(R.id.textView_actorName);
        }
    }
}
