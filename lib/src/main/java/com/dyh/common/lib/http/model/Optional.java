package com.dyh.common.lib.http.model;

import java.util.NoSuchElementException;

import io.reactivex.annotations.Nullable;

/**
 * <p>描述：为了使Rxjava2 onNext 返回null,使用了此包装类，进行过渡</p>
 * 作者： zhouyou<br>
 * 日期： 2018/7/6 14:33 <br>
 * 版本： v1.0<br>
 */
public class Optional<M> {
    private final M optional; // 接收到的返回结果

    public Optional( M optional) {
        this.optional = optional;
    }

    // 判断返回结果是否为null
    public boolean isEmpty() {
        return this.optional == null;
    }

    // 获取不能为null的返回结果，如果为null，直接抛异常，经过二次封装之后，这个异常最终可以在走向RxJava的onError()
    public M get() {
        if (optional == null) {
            throw new NoSuchElementException("No value present");
        }
        return optional;
    }

    // 获取可以为null的返回结果
    public M getIncludeNull() {
        return optional;
    }
}
