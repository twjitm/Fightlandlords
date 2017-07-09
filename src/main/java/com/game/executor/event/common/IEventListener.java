package com.game.executor.event.common;


import com.game.executor.event.EventType;

/**
 * 事件监听器
 *
 */
public interface IEventListener {
    /**
     * 注册事件
     * @param eventType
     */
    public void register(EventType eventType);

    /**
     *限制事件类型
     * @param eventType
     * @return
     */
    public boolean containEventType(EventType eventType);

    /**
     * 解除事件
     * @param event
     */
    public void fireEvent(IEvent event);
}
