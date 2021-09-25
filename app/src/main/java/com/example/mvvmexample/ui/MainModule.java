package com.example.mvvmexample.ui;

import android.content.Context;

import androidx.databinding.DataBindingUtil;

import com.example.mvvmexample.R;
import com.example.mvvmexample.databinding.ActivityMainBinding;
import com.example.mvvmexample.di.ActivityContext;
import com.example.mvvmexample.di.ActivityScope;
import com.example.mvvmexample.di.FragmentScope;
import com.example.mvvmexample.ui.detail.PostDetailFragment;
import com.example.mvvmexample.ui.detail.PostDetailModule;
import com.example.mvvmexample.ui.post.PostFragment;
import com.example.mvvmexample.ui.post.PostModule;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainModule {
    @Provides
    @ActivityScope
    static ActivityMainBinding provideBinding(MainActivity activity) {
        return DataBindingUtil.setContentView(activity, R.layout.activity_main);
    }

    @Provides
    @ActivityContext
    static Context provideContext(MainActivity activity) {
        return activity;
    }

    @FragmentScope
    @ContributesAndroidInjector(modules = PostModule.class)
    abstract PostFragment getPostFragment();

    @FragmentScope
    @ContributesAndroidInjector(modules = PostDetailModule.class)
    abstract PostDetailFragment getPostDetailFragment();
}
