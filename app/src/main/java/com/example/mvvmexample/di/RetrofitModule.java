package com.example.mvvmexample.di;

import com.example.mvvmexample.data.CommentService;
import com.example.mvvmexample.data.PostService;
import com.example.mvvmexample.data.UserService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {
    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Reusable
    PostService providePostService(Retrofit retrofit) {
        return retrofit.create(PostService.class);
    }

    @Provides
    @Reusable
    CommentService provideCommentService(Retrofit retrofit) {
        return retrofit.create(CommentService.class);
    }

    @Provides
    @Reusable
    UserService provideUserService(Retrofit retrofit) {
        return retrofit.create(UserService.class);
    }
}
