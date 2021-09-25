package com.example.mvvmexample.ui.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mvvmexample.databinding.FragmentDetailBinding;
import com.example.mvvmexample.di.AppViewModelFactory;
import com.example.mvvmexample.ui.MainActivity;
import com.jiongbull.jlog.JLog;

import javax.inject.Inject;

import dagger.Lazy;
import dagger.android.support.DaggerFragment;

public class PostDetailFragment extends DaggerFragment {

    @Inject
    FragmentDetailBinding binding;
    @Inject
    PostDetailAdapter adapter;
    @Inject
    LinearLayoutManager linearLayoutManager;
    @Inject
    AppViewModelFactory viewModelFactory;
    @Inject
    Lazy<NavController> navController;

    PostDetailViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this, viewModelFactory).get(PostDetailViewModel.class);
        if (savedInstanceState == null) {
            PostDetailFragmentArgs args = PostDetailFragmentArgs.fromBundle(getArguments());
            viewModel.load(args.getPost());
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        binding.setViewModel(viewModel);
        viewModel.getLiveItems().observe(getViewLifecycleOwner(), items -> adapter.setItems(items));
        viewModel.getUserClickEvent().observe(getViewLifecycleOwner(), userId -> {
            JLog.w("User Detail Click -> " + userId);
//            navController.get().navigate(
//                    PostDetailFragmentDirections.actionPostDetailFragmentToUserFragment(userId)
//            );
        });
        viewModel.getPostClickEvent().observe(getViewLifecycleOwner(), post -> {
            ((MainActivity)getActivity()).showToast("Detail-Post Click : " + post.getId());
        });
        viewModel.getCommentClickEvent().observe(getViewLifecycleOwner(), comment -> {
            ((MainActivity)getActivity()).showToast("Detail-Comment Click : " + comment.getId());
        });
    }
}
