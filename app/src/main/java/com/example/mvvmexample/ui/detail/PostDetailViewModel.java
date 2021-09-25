package com.example.mvvmexample.ui.detail;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmexample.data.CommentService;
import com.example.mvvmexample.data.UserService;
import com.example.mvvmexample.data.entity.Comment;
import com.example.mvvmexample.data.entity.Post;
import com.example.mvvmexample.util.SingleLiveEvent;
import com.jiongbull.jlog.JLog;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PostDetailViewModel extends AndroidViewModel implements
        PostDetailUserItem.EventListener,
        PostDetailComment.EventListener,
        PostDetailPostItem.EventListener {

    private final MutableLiveData<List<PostDetailItem>> liveItems = new MutableLiveData<>();
    private final UserService userService;
    private final CommentService commentService;
    private final SingleLiveEvent<Throwable> errorEvent;
    private final CompositeDisposable disposable = new CompositeDisposable();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>(true);

    private final SingleLiveEvent<Long> userClickEvent = new SingleLiveEvent<>();
    private final SingleLiveEvent<Comment> commentClickEvent = new SingleLiveEvent<>();
    private final SingleLiveEvent<Post> postClickEvent = new SingleLiveEvent<>();

    @Inject
    public PostDetailViewModel(@NonNull Application application,
                               UserService userService,
                               CommentService commentService,
                               @Named("errorEvent") SingleLiveEvent<Throwable> errorEvent) {
        super(application);
        JLog.i("PostDetailViewModel is created !");
        this.userService = userService;
        this.commentService = commentService;
        this.errorEvent = errorEvent;
    }

    public void load(Post post) {
        disposable.add(
                //api 요청 여러개를 하나로 묶어 트랜잭션 개념으로 처리한다.
                Single.zip(
                        userService.getUser(post.getUserId()),
                        Single.just(post),
                        commentService.getComments(post.getId()),
                        (user, p, comments) -> {
                            List<PostDetailItem> list = new ArrayList<>();
                            list.add(new PostDetailUserItem(user, this));
                            list.add(new PostDetailPostItem(p, this));
                            for (Comment c : comments) {
                                list.add(new PostDetailComment(c, this));
                            }
                            return list;
                        }
                )
                .retry(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(postDetailItem -> loading.postValue(false))
                .subscribe(liveItems::setValue, errorEvent::setValue));
    }

    public MutableLiveData<List<PostDetailItem>> getLiveItems() {
        return liveItems;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        JLog.d("onCleared !");
        disposable.dispose();
    }

    public MutableLiveData<Boolean> getLoading() {
        return loading;
    }

    public SingleLiveEvent<Long> getUserClickEvent() {
        return userClickEvent;
    }

    public SingleLiveEvent<Comment> getCommentClickEvent() {
        return commentClickEvent;
    }

    public SingleLiveEvent<Post> getPostClickEvent() {
        return postClickEvent;
    }

    @Override
    public void onCommentClick(Comment comment) {
        commentClickEvent.setValue(comment);
    }

    @Override
    public void onPostDetailClick(Post post) {
        postClickEvent.setValue(post);
    }

    @Override
    public void onUserClick(long userId) {
        userClickEvent.setValue(userId);
    }
}
