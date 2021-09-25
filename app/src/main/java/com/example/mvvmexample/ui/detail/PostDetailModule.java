package com.example.mvvmexample.ui.detail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmexample.databinding.FragmentDetailBinding;
import com.example.mvvmexample.di.ApplicationContext;
import com.example.mvvmexample.di.FragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public class PostDetailModule {

    @Provides
    @FragmentScope
    public FragmentDetailBinding provideFragmentDetailBinding(@ApplicationContext Context context) {
        return FragmentDetailBinding.inflate(LayoutInflater.from(context), null, false);
    }

    @Provides
    @FragmentScope
    public LinearLayoutManager provideLinearLayoutManager(@ApplicationContext Context context) {
        return new LinearLayoutManager(context) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return new RecyclerView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );
            }
        };
    }

    @Provides
    @FragmentScope
    public NavController provideNavController(PostDetailFragment fragment) {
        return NavHostFragment.findNavController(fragment);
    }
}
