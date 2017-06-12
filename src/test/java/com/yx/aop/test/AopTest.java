package com.yx.aop.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by renyueliang on 17/2/5.
 */
public class AopTest {

  private static  ApplicationContext factory1;
    public static void loadSpring(){

    factory1 = new ClassPathXmlApplicationContext("applicationContext-aop.xml");
    }


    public static void main(String[] args){

        loadSpring();

        ServiceTestOneInter serviceTestOne =(ServiceTestOneInter)   factory1.getBean("serviceTestOne");
        ServiceTestTwoInter  serviceTestTwo =(ServiceTestTwoInter)   factory1.getBean("serviceTestTwo");
        ServiceTestThreeInter serviceTestThree=(ServiceTestThreeInter)   factory1.getBean("serviceTestThree");

        BaseDO baseDO = new BaseDO();
        baseDO.setName("renyl");

        serviceTestOne.getObject(baseDO);
        serviceTestOne.getObject(baseDO);

        serviceTestTwo.print("renyl haha !");

        serviceTestThree.getStr("renyl tc");

    }



}
