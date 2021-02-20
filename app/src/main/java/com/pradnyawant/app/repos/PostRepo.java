package com.pradnyawant.app.repos;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.pradnyawant.app.models.Post;
import com.pradnyawant.app.services.ApiClient;
import com.pradnyawant.app.services.ApiInterface;

import java.util.List;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostRepo {
    private ApiInterface apiInterface;

    public MutableLiveData<List<Post>> getPosts() {
        apiInterface = ApiClient.getInstnace().create(ApiInterface.class);

        MutableLiveData<List<Post>> mutableLiveData = new MutableLiveData<>();

        Call<List<Post>> postsCall = apiInterface.getPosts();
        postsCall.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(response.isSuccessful() && response.body() != null) {
                    mutableLiveData.setValue(response.body());
                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.getMessage());
            }
        });
        return mutableLiveData;
    }
}
