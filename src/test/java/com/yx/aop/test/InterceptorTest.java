package com.yx.aop.test;

import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;

/**
 * Created by renyueliang on 17/2/5.
 */
public class InterceptorTest {

    Logger logger;

    public void  init(){
        logger=Logger.getLogger("");
        DailyRollingFileAppender appender=new DailyRollingFileAppender();
        appender.setFile("/Users/renyueliang/Documents/document/317hu/log/buslog.log");
        appender.setDatePattern("'.'yyyy-MM-dd");

        PatternLayout layout=new PatternLayout("%-d{yyyy-MM-dd HH:mm:ss}  %m%n");
        appender.setLayout(layout);
        appender.setAppend(true);
        appender.activateOptions();
        logger.addAppender(appender);
        logger.setLevel(Level.WARN);
    }




    public void startInvoke(JoinPoint joinPoint) {

        String methodName = String.format("%s.%s", joinPoint.getSignature().getDeclaringTypeName()
                , joinPoint.getSignature().getName() );
        System.out.println("startInvoke ----------------->[" + methodName + "]");

        logger.warn("startInvoke ----------------->[" + methodName + "]");

    }

    public void endInvoke(JoinPoint joinPoint) {
        String methodName = String.format("%s.%s", joinPoint.getSignature().getDeclaringTypeName()
                , joinPoint.getSignature().getName() );
        System.out.println("endInvoke *******************>[" + methodName + "]");

        logger.error("endInvoke *******************>[" + methodName + "]");



    }

    public void returnInvoke(JoinPoint joinPoint,Object retValue){

        String methodName = String.format("%s.%s", joinPoint.getSignature().getDeclaringTypeName()
                , joinPoint.getSignature().getName() );
        System.out.println("returnInvoke *******************>[" + methodName + "]");

        logger.warn("returnInvoke *******************>[" + methodName + "]"+JSON.toJSONString(retValue));

    }
   // @AfterThrowing(value = "execution(* *.*(..))", throwing = "e")
    public void throwingInvoke(JoinPoint joinPoint,Throwable e){
        String methodName = String.format("%s.%s", joinPoint.getSignature().getDeclaringTypeName()
                , joinPoint.getSignature().getName() );

        logger.error("throwingInvoke *******************>[" + e.getMessage() + "]");


        System.out.println("throwingInvoke *******************>[" + e.getMessage() + "]");

        System.out.println("throwingInvoke *******************>[" + methodName + "]");
    }
}
