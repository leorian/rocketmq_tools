package com.yx.aop.test;

import com.alibaba.fastjson.JSON;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by renyueliang on 17/2/5.
 */
public class AopCallBeforeEdvice implements MethodBeforeAdvice {


    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {


        BusLogCommon busLogCommon = method.getAnnotation(BusLogCommon.class);
        if(busLogCommon==null){
            return ;
        }

        System.out.println(JSON.toJSON(args));
        String methodName = method.getDeclaringClass().getSimpleName() + "." + method.getName();
        System.out.println("AopCallBeforeEdvice start ServiceCall@"+methodName);

    }
}
