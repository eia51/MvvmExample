package com.example.mvvmexample.ui.detail;

import com.example.mvvmexample.data.entity.Post;

public class PostDetailPostItem extends PostDetailItem {
    private Post post;
    private EventListener eventListener;

    public PostDetailPostItem(Post post, EventListener eventListener) {
        this.post = post;
        this.eventListener = eventListener;
    }

    public String getTitle() {
        return post.getTitle();
    }

    public String getBody() {
        return post.getBody();
    }

    public long getPostId() {
        return post.getId();
    }

    @Override
    public Type getType() {
        return Type.POST;
    }

    public EventListener getEventListener() {
        return eventListener;
    }

    public Post getPost() {
        return post;
    }

    public interface EventListener {
        void onPostDetailClick(Post post);
    }
}
