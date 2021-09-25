package com.example.mvvmexample.ui.detail;

import com.example.mvvmexample.data.entity.Comment;

public class PostDetailComment extends PostDetailItem {

    private Comment comment;
    private EventListener eventListener;

    public PostDetailComment(Comment comment, EventListener eventListener) {
        this.comment = comment;
        this.eventListener = eventListener;
    }

    public String getName() {
        return comment.getName();
    }

    public String getBody() {
        return comment.getBody();
    }

    @Override
    public Type getType() {
        return Type.COMMENT;
    }

    public EventListener getEventListener() {
        return eventListener;
    }

    public Comment getComment() {
        return comment;
    }

    public interface EventListener {
        void onCommentClick(Comment comment);
    }
}
