package com.example.mvvmexample.data;

import com.example.mvvmexample.data.entity.Comment;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CommentService {
    //https://jsonplaceholder.typicode.com/users/1/comments?postId=1
    /*
        아래와 같이 설정했을 때, 위와 같은 url이 만들어진다.
        이는 baseUrl에서 기본 host가 설정되어있고,
        GET/POST 어노테이션에서 api의 path를 설정했고,
        파라메터의 Query 어노테이션에서 들어온 파라메터를 query key로 파싱하도록 하여 위와 같은 url이 빌드 된다.
     */
    @GET("/comments")
    Single<List<Comment>> getComments(@Query("postId") long id);

    //Single 설명 참조 (RxJava)
    //Single은 1개의 데이터만 발행하는 Observable의 특수한 형태이다. (서버 api에서 많이 사용)
    //https://taeiim.tistory.com/entry/RxJava2-2-Observable-Single-Maybe-%EB%9C%A8%EA%B1%B0%EC%9A%B4%EC%B0%A8%EA%B0%80%EC%9A%B4-Observable-%ED%8C%A9%ED%86%A0%EB%A6%AC%ED%95%A8%EC%88%98
}
