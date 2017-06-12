package com.yx.aop.test;

/**
 * Created by renyueliang on 17/2/5.
 */
public interface ServiceTestThreeInter {


    public void print(String userId);

    public String getStr(String userId);


    public ResultTestDO getObject(BaseDO baseDO);


    public ResultTestDO getNullResult();
}
