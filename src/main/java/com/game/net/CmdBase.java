package com.game.net;

import java.lang.annotation.*;

/**
 * Created by 文江 on 2017/7/26.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CmdBase {
    public short cmdId() default 0;

}
