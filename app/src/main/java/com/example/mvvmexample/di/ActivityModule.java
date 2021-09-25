package com.example.mvvmexample.di;

import com.example.mvvmexample.ui.MainActivity;
import com.example.mvvmexample.ui.MainModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {
    // MainActivity를 위한 서브컴포넌트 정의
    @ActivityScope
    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity mainActivity();
}
