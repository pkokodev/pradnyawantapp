package com.pradnyawant.app.services;

import com.pradnyawant.app.models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("list")
    Call<List<Post>> getPosts();
    @GET("list")
    Call<List<Post>> getPosts(@Query("page") int pageNumber);
}
