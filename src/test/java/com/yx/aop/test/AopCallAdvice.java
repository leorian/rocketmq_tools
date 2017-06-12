package com.yx.aop.test;

import com.alibaba.fastjson.JSON;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Created by renyueliang on 17/2/5.
 */
public class AopCallAdvice implements MethodInterceptor {



    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {

        BusLogCommon busLogCommon = methodInvocation.getMethod().getAnnotation(BusLogCommon.class);
        Object result = null;
        if(busLogCommon==null){

            try {

                result = methodInvocation.proceed();


            }  catch (Throwable e) {
                throw e;
            }


            return result;
        }

        String methodName = methodInvocation.getMethod().getDeclaringClass().getSimpleName() + "." + methodInvocation.getMethod().getName();



        System.out.println(JSON.toJSON(methodInvocation.getArguments()));

        System.out.println(" AopCallAdvice start ServiceCall@"+methodName);

        Throwable failed=null;
        try {

            result = methodInvocation.proceed();

            System.out.println("AopCallAdvice:result->"+JSON.toJSON(result));

        }  catch (Throwable e) {
            failed = e ;
            throw e;
        } finally {

            System.out.println(" AopCallAdvice end ServiceCall@"+methodName);
        }


        return result;
    }
}
