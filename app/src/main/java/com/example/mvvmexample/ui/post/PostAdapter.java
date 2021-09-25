package com.example.mvvmexample.ui.post;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmexample.BR;
import com.example.mvvmexample.R;
import com.example.mvvmexample.util.ViewBindingHolder;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class PostAdapter extends RecyclerView.Adapter<ViewBindingHolder> {

    //뷰홀더용 리스트
    private final List<PostItem> items = new ArrayList<>();

    @Inject
    public PostAdapter() {
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.rv_post;
    }

    @NonNull
    @Override
    public ViewBindingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewBindingHolder(parent.getContext(), viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewBindingHolder holder, int position) {
        holder.getBinding().setVariable(BR.item, items.get(position));
        holder.getBinding().executePendingBindings(); //변수가 변경됐을 때 레이아웃에 즉시 적용되도록 하는 코드
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<PostItem> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }
}
