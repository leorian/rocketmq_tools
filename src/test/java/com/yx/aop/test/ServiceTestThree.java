package com.yx.aop.test;

/**
 * Created by renyueliang on 17/2/5.
 */
public class ServiceTestThree implements ServiceTestThreeInter {
    @BusLogCommon(actionName = "242",depLevel = "33")
    public void print(String userId){
        System.out.println("userId:"+userId);
    }

    @BusLogCommon(actionName = "242",depLevel = "33")
    public String getStr(String userId){

        System.out.println("userId:"+userId);
        return "dd_"+userId;
    }

    @BusLogCommon(actionName = "242",depLevel = "33")
    public ResultTestDO getObject(BaseDO baseDO){

        ResultTestDO resultTestDO = new ResultTestDO();
        resultTestDO.setErrorCode("code");
        resultTestDO.setModule(baseDO);
        resultTestDO.setSuccess(true);


        System.out.println(" getObject doExcute ！ ");
        return resultTestDO;


    }

    @BusLogCommon(actionName = "242",depLevel = "33")
    public ResultTestDO getNullResult(){
        ResultTestDO resultTestDO = new ResultTestDO();
        resultTestDO.setErrorCode("code");
        resultTestDO.setSuccess(false);
        return resultTestDO;
    }
}
