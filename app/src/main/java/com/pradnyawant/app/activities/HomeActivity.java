package com.pradnyawant.app.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import com.pradnyawant.app.R;
import com.pradnyawant.app.adapters.PostsAdapter;
import com.pradnyawant.app.models.Post;
import com.pradnyawant.app.services.ApiClient;
import com.pradnyawant.app.services.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private ApiInterface apiInterface;
    private List<Post> postList;
    private PostsAdapter postsAdapter;
    private RecyclerView rvPosts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        getPosts();
    }

    private void init() {
        postList = new ArrayList<>();
        rvPosts = findViewById(R.id.rvPosts);
    }

    private void getPosts() {
        apiInterface = ApiClient.getInstnace().create(ApiInterface.class);
        Call<List<Post>> postsCall = apiInterface.getPosts();
        postsCall.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(response.body() != null) {
                    postList.addAll(response.body());
                    setData();
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.getMessage());
            }
        });
    }

    private void setData() {
        postsAdapter = new PostsAdapter(postList);
        rvPosts.setLayoutManager(new GridLayoutManager(this, 2));
        rvPosts.setAdapter(postsAdapter);
    }

}