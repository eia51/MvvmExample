package com.example.mvvmexample.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

@Qualifier //의존성을 주입할 대상 객체임을 표시하는 한정자
@Retention(RetentionPolicy.RUNTIME)
/*
    어노테이션의 수명을 어디까지 가져갈것인지?
    - SOURCE : 그냥 주석
    - CLASS : 컴파일시점까지 유효
    - RUNTIME : 런타임상태에도 어노테이션 유효
 */
public @interface ActivityContext {
//interface 선언 앞에 @를 붙여 '@interface' 라고 타입을 선언했는데,
//이는 커스텀 어노테이션을 선언한 것이다.
}
