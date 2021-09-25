package com.example.mvvmexample.util;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.jiongbull.jlog.JLog;

import java.util.concurrent.atomic.AtomicBoolean;

// 생명주기 안에서 안전하게 이벤트를 처리
public class SingleLiveEvent<T> extends MutableLiveData<T> {
    private final AtomicBoolean mPending = new AtomicBoolean(false);

    @MainThread
    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
        if (hasActiveObservers()) {
            JLog.w("여러 Observer가 조재하지만, 단 하나만 알림을 받을 수 있다.");
        }
        super.observe(owner, t -> {
            //compareAndSet?
            if (mPending.compareAndSet(true, false)) {
                JLog.d("observer 등록");
                observer.onChanged(t);
            }
        });
    }

    @MainThread
    @Override
    public void setValue(T value) {
        mPending.set(true);
        super.setValue(value);
//        JLog.d("setValue is called");
    }

    @MainThread
    public void call() {
        setValue(null);
    }
}
