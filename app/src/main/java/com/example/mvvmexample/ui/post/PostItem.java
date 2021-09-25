package com.example.mvvmexample.ui.post;

import androidx.annotation.NonNull;

import com.example.mvvmexample.data.entity.Post;

public class PostItem {
    private final Post post;
    private final EventListener eventListener;

    public PostItem(@NonNull Post post, EventListener eventListener) {
        this.post = post;
        this.eventListener = eventListener;
    }

    public Post getPost() {
        return post;
    }

    public String getTitle() {
        return post.getTitle();
    }

    public long getId() {
        return post.getId();
    }

    public EventListener getEventListener() {
        return eventListener;
    }

    public interface EventListener {
        void onPostClick(PostItem item);
    }

    @Override
    public String toString() {
        return "PostItem{" +
                "post=" + post +
                '}';
    }
}
