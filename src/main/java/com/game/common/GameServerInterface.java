package com.game.common;

import java.lang.reflect.Method;

/**
 * Created by 文江 on 2017/7/8.
 */
public interface GameServerInterface {
    /**
     * 初始化服务
     */
    public  void init();

    /**
     * 获取服务
     * @return
     */

     public Method[] getClazz(String  serverName);
}
