package com.yx.aop.test;

import com.alibaba.fastjson.JSON;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by renyueliang on 17/2/5.
 */
public class AopCallAfterEdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        BusLogCommon busLogCommon = method.getAnnotation(BusLogCommon.class);
        if(busLogCommon==null){
            return ;
        }

        String methodName = method.getDeclaringClass().getSimpleName() + "." + method.getName();

        System.out.println("AopCallAfterEdvice:"+JSON.toJSON(returnValue));
        System.out.println("AopCallAfterEdvice end ServiceCall@"+methodName);


    }
}
