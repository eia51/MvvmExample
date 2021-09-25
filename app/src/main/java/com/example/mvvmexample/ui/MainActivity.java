package com.example.mvvmexample.ui;

import android.os.Bundle;
import android.widget.Toast;

import com.example.mvvmexample.databinding.ActivityMainBinding;
import com.example.mvvmexample.util.SingleLiveEvent;
import com.jiongbull.jlog.JLog;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.Lazy;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    @Inject
    Lazy<ActivityMainBinding> binding;

    @Inject
    @Named("errorEvent")
    SingleLiveEvent<Throwable> errorEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.get().setLifecycleOwner(this);

        errorEvent.observe(this, this::showErrorToast);
        JLog.i("onCreate");
    }

    private void showErrorToast(Throwable throwable) {
        throwable.printStackTrace();
        showToast(throwable.getMessage());
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}