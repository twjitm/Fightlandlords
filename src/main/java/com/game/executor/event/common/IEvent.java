package com.game.executor.event.common;


import com.game.executor.event.EventParam;
import com.game.executor.event.EventType;

/**
 * Created by 文江 on 2017/7/9.
 */
public interface IEvent {
    /**
     *设置事件类型
     * @param eventType
     */
    public void setEventType(EventType eventType);

    /**
     * 获取事件类型
     * @return
     */
    public EventType getEventType();

    /**
     * 获取事件参数
     * @return
     */
    public EventParam[] getParams();

    /**
     * 获取事件参数
     * @param eventParams
     */
    public void setParams(EventParam... eventParams);

    /**
     * 事件接通
     */
    public void call();
}
