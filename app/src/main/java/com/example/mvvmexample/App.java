package com.example.mvvmexample;

import com.example.mvvmexample.di.DaggerAppComponent;
import com.jiongbull.jlog.JLog;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class App extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        JLog.init(this).setDebug(BuildConfig.DEBUG);
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.factory().create(this);
    }
}
