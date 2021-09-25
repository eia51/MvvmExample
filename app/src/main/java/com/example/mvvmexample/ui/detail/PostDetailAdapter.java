package com.example.mvvmexample.ui.detail;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmexample.BR;
import com.example.mvvmexample.R;
import com.example.mvvmexample.util.ViewBindingHolder;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class PostDetailAdapter extends RecyclerView.Adapter<ViewBindingHolder> {

    private final List<PostDetailItem> items = new ArrayList<>();

    @Inject
    public PostDetailAdapter() {
    }

    @Override
    public int getItemViewType(int position) {
        switch (PostDetailItem.Type.values()[items.get(position).getType().ordinal()]) {
            case COMMENT:
                return R.layout.rv_detail_comment;
            case POST:
                return R.layout.rv_detail_post;
            case USER:
                return R.layout.rv_detail_user;
            default:
                throw new IllegalArgumentException();
        }
    }

    @NonNull
    @Override
    public ViewBindingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewBindingHolder(parent.getContext(), viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewBindingHolder holder, int position) {
        holder.getBinding().setVariable(BR.item, items.get(position));
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<PostDetailItem> items) {
        this.items.clear();
        this.items.addAll(items);
        this.notifyDataSetChanged();
    }
}