package com.game.executor.event;

/**
 * Created by 文江 on 2017/7/9.
 */
public class EventParam<T> {
    private  T t;

    public EventParam(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
