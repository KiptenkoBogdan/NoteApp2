package com.example.noteapp2.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteapp2.R;

import java.util.List;

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.MyViewHolder> {

    private IOnItemClick listener;
    private Context context;
    private List<Article> articleList;
    public ArticleListAdapter(Context context) {
        this.context = context;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ArticleListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false);

        return new MyViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleListAdapter.MyViewHolder holder, int position) {
        holder.tvTitle.setText(this.articleList.get(position).title);
        holder.tvSubtitle.setText(this.articleList.get(position).subtitle);
    }

    @Override
    public int getItemCount() {
        return  this.articleList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        TextView tvSubtitle;
        LinearLayout layout;

        public MyViewHolder(View view, IOnItemClick listener) {
            super(view);
            tvTitle = view.findViewById(R.id.tvTitle);
            tvSubtitle = view.findViewById(R.id.tvSubtitle);
            layout = view.findViewById(R.id.id_recycler_row);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION && listener != null){
                        listener.onItemClick(position);
                    }
                }
            });
        }
    }
    public interface IOnItemClick{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(IOnItemClick listener){
        this.listener = listener;
    }
}