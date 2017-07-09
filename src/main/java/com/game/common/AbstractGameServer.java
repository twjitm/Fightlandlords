package com.game.common;

import java.lang.reflect.Method;

/**
 * Created by 文江 on 2017/7/8.
 */
public class AbstractGameServer implements  GameServerInterface{

    public void init() {

    }

    public Method[] getClazz(String serverName) {
        Method[] methords=null;
        try {
            Class clzz=this.getClass().getClassLoader().loadClass(serverName);
            methords = clzz.getMethods();
        } catch (ClassNotFoundException e) {
        }
        return methords;
    }
}
