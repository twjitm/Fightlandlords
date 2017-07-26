package com.game.scanner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by 文江 on 2017/7/26.
 * 执行器
 */
public class ServerActuator {
    private Object target;
    private Method method;

    public Object invoke(Object object) {
        try {
            method.invoke(target, object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ServerActuator valueOf(Object target, Method method) {
        ServerActuator serverActuator = new ServerActuator();
        serverActuator.setMethod(method);
        serverActuator.setTarget(target);
        return serverActuator;
    }
    

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
