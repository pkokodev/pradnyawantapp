package com.pradnyawant.app.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.pradnyawant.app.R;
import com.pradnyawant.app.adapters.PostsAdapter;
import com.pradnyawant.app.interfaces.LoadMoreListener;
import com.pradnyawant.app.models.Post;
import com.pradnyawant.app.services.ApiClient;
import com.pradnyawant.app.services.ApiInterface;
import com.pradnyawant.app.viewmodels.PostsViewModel;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements LoadMoreListener {

    private ApiInterface apiInterface;
    private List<Post> postList;
    private PostsAdapter postsAdapter;
    private RecyclerView rvPosts;
    private int pageNumber = 1;
    private PostsViewModel postsViewModel;
    private LinearProgressIndicator lpiHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        setData();
        getLiveData();
    }

    private void init() {
        postList = new ArrayList<>();
        rvPosts = findViewById(R.id.rvPosts);
        lpiHome = findViewById(R.id.lpiHome);
    }



    private void setData() {
        postsAdapter = new PostsAdapter(postList, this);
        rvPosts.setLayoutManager(new GridLayoutManager(this, 2));
        rvPosts.setAdapter(postsAdapter);
        postsViewModel = new ViewModelProvider(this).get(PostsViewModel.class);
    }

    @Override
    public void onLoadMore() {
        lpiHome.show();
        pageNumber++;
        getLiveData();
    }

    private void getLiveData(){
        postsViewModel.getPosts(pageNumber).observe(this, new Observer<List<Post>>() {
            @Override
            public void onChanged(List<Post> posts) {
                if(posts != null){
                    postList.addAll(posts);
                    postsAdapter.notifyItemRangeInserted((pageNumber - 1) * 30, postList.size());
                }
                lpiHome.hide();
            }
        });
    }
}