package com.example.mvvmexample.ui.post;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmexample.data.PostService;
import com.example.mvvmexample.util.SingleLiveEvent;
import com.jiongbull.jlog.JLog;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PostViewModel extends AndroidViewModel implements PostItem.EventListener {

    @NonNull
    private final PostService postService;

    @NonNull
    private final SingleLiveEvent<Throwable> errorEvent;

    @NonNull
    private final SingleLiveEvent<PostItem> postClickEvent = new SingleLiveEvent<>();

    private final MutableLiveData<List<PostItem>> livePosts = new MutableLiveData<>();

    private final CompositeDisposable disposable = new CompositeDisposable();

    private final MutableLiveData<Boolean> loading = new MutableLiveData<>(true);

    @Inject
    public PostViewModel(Application application,
                         PostService postService,
                         @Named("errorEvent") SingleLiveEvent<Throwable> errorEvent) {
        super(application);
        JLog.d("PostViewModel Created");
        this.postService = postService;
        this.errorEvent = errorEvent;
    }

    public void loadPosts() {
        disposable.add(postService.getPost()
                .flatMapObservable(Observable::fromIterable)
                .map(item -> new PostItem(item, this))
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(item -> loading.postValue(false))
                .subscribe(livePosts::setValue, errorEvent::setValue)
        );
    }

    public MutableLiveData<List<PostItem>> getLivePosts() {
        return livePosts;
    }

    /*
        뷰모델은 생명주기를 알고 동작.
        뷰모델이 파괴될 때, RxJava의 Disposal과 같은 리소스를 해제하자.
     */

    @Override
    protected void onCleared() {
        super.onCleared();
        JLog.d("onCleared!");
        disposable.dispose();
    }

    public MutableLiveData<Boolean> getLoading() {
        return loading;
    }

    @Override
    public void onPostClick(PostItem item) {
        //fragment 로 값을 전달하도록 singleEvent의 값을 변경한다
        JLog.w("PostClick 1");
        postClickEvent.setValue(item);
    }

    public SingleLiveEvent<PostItem> getPostClickEvent() {
        return postClickEvent;
    }
}
