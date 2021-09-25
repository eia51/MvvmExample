package com.example.mvvmexample.data;

import com.example.mvvmexample.data.entity.Post;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface PostService {
    @GET("/posts")
    Single<List<Post>> getPost();
}
