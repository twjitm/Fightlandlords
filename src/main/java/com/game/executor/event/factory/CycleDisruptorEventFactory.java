package com.game.executor.event.factory;


import com.game.executor.event.CycleEvent;
import com.lmax.disruptor.EventFactory;

/**
 * Created by 文江 on 2017/7/9.
 */
public class CycleDisruptorEventFactory implements EventFactory<CycleEvent> {
    @Override
    public CycleEvent newInstance() {
        return null;
    }
}
