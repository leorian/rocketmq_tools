package com.yx.aop.test;

import java.lang.annotation.ElementType;

/**
 * Created by renyueliang on 17/2/5.
 */
@java.lang.annotation.Target({ElementType.METHOD})
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface BusLogCommon {

    String actionName() default "";

    String depLevel() default "";
}
