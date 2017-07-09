package com.game.executor.event;

import java.io.Serializable;

/**
 * Created by 文江 on 2017/7/9.
 * 单次循环事件
 */
public class EventLoopEvent <ID extends Serializable> extends AbstractEvent<ID>{
    //用于线程分片的shardingId
    private Long shardingId;
    public EventLoopEvent(EventType eventType, ID eventId, long shardingId, EventParam... parms){
        setEventType(eventType);
        setParams(parms);
        setId(eventId);
        this.shardingId = shardingId;
    }

    public Long getShardingId() {
        return shardingId;
    }

    public void setShardingId(Long shardingId) {
        this.shardingId = shardingId;
    }


    @Override
    public void call() {

    }
}
