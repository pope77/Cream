package com.example.pope.cream.page.creamarea.book.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pope.cream.R;
import com.example.pope.cream.biz.beans.BookBean;

import java.util.List;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.ViewHolder> {

    private List<BookBean> bookBeans;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onClick(BookBean bookBean);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public BookListAdapter(List<BookBean> bookBeans, Context context) {
        this.bookBeans = bookBeans;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BookBean bean = bookBeans.get(position);
        Glide.with(context).load(bean.getBookCoverUrl()).into(holder.bookPic);
        holder.bookName.setText(bean.getBookName());
        holder.bookAuthor.setText(bean.getBookAuthor());
        holder.itemView.setOnClickListener(view -> {
            if (onItemClickListener != null) {
                onItemClickListener.onClick(bean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView bookPic;
        TextView bookName;
        TextView bookAuthor;

        public ViewHolder(View itemView) {
            super(itemView);
            bookPic = itemView.findViewById(R.id.imageView_bookListItem_bookPic);
            bookName = itemView.findViewById(R.id.textView_bookListItem_bookName);
            bookAuthor = itemView.findViewById(R.id.textView_bookListItem_bookAuthor);
        }
    }
}
