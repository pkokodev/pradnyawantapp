package com.pradnyawant.app.adapters;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pradnyawant.app.R;
import com.pradnyawant.app.models.Post;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsViewHolder> {
    private List<Post> posts;

    public PostsAdapter(List<Post> posts) {
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new PostsAdapter.PostsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsViewHolder holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class PostsViewHolder extends RecyclerView.ViewHolder {
        private TextView tvAuthor;
        private ImageView ivPost;
        public PostsViewHolder(@NonNull View itemView) {
            super(itemView);
            initViews();
        }

        private void initViews() {
            tvAuthor = itemView.findViewById(R.id.tvAuthor);
            ivPost = itemView.findViewById(R.id.ivPost);
        }

        public void setData(int position) {
            tvAuthor.setText(posts.get(position).getAuthor());
            Uri uri = Uri.parse(posts.get(position).getDownload_url());
            Glide.with(itemView.getContext())
                    .load(uri)
                    .into(ivPost);
        }
    }
}
