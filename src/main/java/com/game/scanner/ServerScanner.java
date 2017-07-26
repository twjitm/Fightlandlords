package com.game.scanner;

import com.game.annotion.HandlerModel;
import com.game.net.CmdBase;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Method;

/**
 * Created by 文江 on 2017/7/26.
 * 服务扫描器
 */
public class ServerScanner implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<? extends Object> clzz = bean.getClass();
        if (null == clzz) {
            return null;
        }
        HandlerModel handlerModel = clzz.getAnnotation(HandlerModel.class);
        if (handlerModel != null) {
            Method[] method = handlerModel.getClass().getMethods();
            for (int i = 0; i < method.length; i++) {
                CmdBase cmdBase = method[i].getAnnotation(CmdBase.class);
                short cmdid = cmdBase.cmdId();

                ServerActuator serverActuator = new ServerActuator();
                serverActuator.valueOf(bean, method[i]);
            }

        }
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        return null;
    }
}
