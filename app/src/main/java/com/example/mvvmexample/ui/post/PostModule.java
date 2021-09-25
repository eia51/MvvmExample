package com.example.mvvmexample.ui.post;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmexample.databinding.FragmentPostBinding;
import com.example.mvvmexample.di.ApplicationContext;
import com.example.mvvmexample.di.FragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public class PostModule {
    @Provides
    @FragmentScope
    FragmentPostBinding provideBinding(@ApplicationContext Context context) {
        return FragmentPostBinding.inflate(LayoutInflater.from(context), null, false);
    }

    @Provides
    @FragmentScope
    LinearLayoutManager providerLinearLayoutManager(@ApplicationContext Context context) {
        return new LinearLayoutManager(context) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        };
    }

    @Provides
    @FragmentScope
    NavController provideNavController(PostFragment fragment) {
        return NavHostFragment.findNavController(fragment);
    }
}
