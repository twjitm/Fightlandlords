package com.game.executor.common;

/**
 * Created by 文江 on 2017/7/9.
 * 更新执行器类型
 */
public enum  UpdateExecutorEnum {
     /*使用locksupport方式*/
    locksupport,
    /*使用绑定线程*/
    bindThread,
    /**使用disruptor*/
    disruptor,
    ;
}
