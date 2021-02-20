package com.pradnyawant.app.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pradnyawant.app.models.Post;
import com.pradnyawant.app.repos.PostRepo;

import java.util.List;

public class PostsViewModel extends ViewModel {
    private MutableLiveData<List<Post>> posts;
    private PostRepo postRepo;

    public PostsViewModel() {
        this.postRepo = new PostRepo();
    }

    public LiveData<List<Post>> getPosts() {
        if (posts == null) {
            posts = new MutableLiveData<List<Post>>();
            posts = postRepo.getPosts();
        }
        return posts;
    }

}
