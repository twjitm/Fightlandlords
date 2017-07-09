package com.game.executor.event;

import com.game.executor.event.common.IEvent;

import java.io.Serializable;

/**
 * Created by 文江 on 2017/7/9.
 * 抽象事件
 */
public abstract class  AbstractEvent<ID extends Serializable> implements IEvent {

    private EventType eventType;        //事件类型
    private EventParam[] eventParamps;  //事件参数
    private ID id;                      //事件id

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public EventType getEventType() {
        return this.eventType;
    }

    public EventParam[] getParams() {
        return this.eventParamps;
    }

    public void setParams(EventParam... eventParams) {
        this.eventParamps = eventParams;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}
