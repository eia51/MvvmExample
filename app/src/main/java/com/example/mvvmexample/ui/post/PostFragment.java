package com.example.mvvmexample.ui.post;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mvvmexample.databinding.FragmentPostBinding;
import com.example.mvvmexample.di.AppViewModelFactory;
import com.jiongbull.jlog.JLog;

import javax.inject.Inject;

import dagger.Lazy;
import dagger.android.support.DaggerFragment;

/**
 * 게시글 화면 구현
 * 멤버 인젝션 위하여 DaggerFragment 상속
 */
public class PostFragment extends DaggerFragment {
    @Inject
    FragmentPostBinding binding;

    @Inject
    AppViewModelFactory viewModelFactory;

    @Inject
    PostAdapter adapter;

    @Inject
    LinearLayoutManager linearlayoutManager;

    @Inject
    Lazy<NavController> navController;

    PostViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JLog.i("onCreate");
        viewModel = new ViewModelProvider(this, viewModelFactory).get(PostViewModel.class);
        if (savedInstanceState == null) {
            viewModel.loadPosts();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        JLog.i("onCreateView");
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        JLog.i("onViewCreate");
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(linearlayoutManager);
        binding.setViewModel(viewModel);

        //게시글 목록 동기화를 위한 이벤트 구독
        viewModel.getLivePosts()
                .observe(getViewLifecycleOwner(), list -> adapter.setItems(list));

        //클릭이벤트 처리를 위한 구독
        viewModel.getPostClickEvent()
                .observe(getViewLifecycleOwner(), item -> {
                    JLog.i("Post Click 2");
                    navController.get()
                        .navigate(
                            PostFragmentDirections.actionPostToDetail(item.getPost())
                        );
                });
    }
}
