package com.dyh.commonlib.entity.local;

/**
 * 作者：DongYonghui
 * 时间：2019/9/18/018
 * 邮箱：648731994@qq.com
 * 描述：EventBus的消息对象
 */
public class EventMessage<T> {
    private int code;
    private T message;

    public EventMessage(int code) {
        this(code,null);
    }

    public EventMessage(int code, T message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }
}
