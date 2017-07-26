package com.game.scanner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 文江 on 2017/7/26.
 * 执行器管理者
 */
public class ServerActuatorManager {
    private static Map<Short, Map<Short, ServerActuator>> serverActuatorManagerMap = new HashMap<Short, Map<Short, ServerActuator>>();


    public static void add(short modele, short comId, ServerActuator serverActuator) {
        HashMap<Short, ServerActuator> map = new HashMap<Short, ServerActuator>();
        map.put(comId, serverActuator);
        serverActuatorManagerMap.put(modele, map);
    }

    public static void delete(short modele, short comId) {

    }

    public static ServerActuator getServerActuator(short modelId, short cmdId) {
        Map<Short, ServerActuator> map = serverActuatorManagerMap.get(modelId);
        if (map != null) {
            return map.get(cmdId);
        }
        return null;
    }
}
