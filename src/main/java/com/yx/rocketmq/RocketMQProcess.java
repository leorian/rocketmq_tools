package com.yx.rocketmq;


import com.yx.common.MetaStatus;
import com.yx.common.MsgType;

/**
 * Created with IntelliJ IDEA.
 * User: renyueliang
 * Date: 16/10/25
 * Time: 下午3:26
 * To change this template use File | Settings | File Templates.
 */
public abstract class RocketMQProcess<T> {

    public abstract RocketMQAbstractMsgType getMsgType();


    public abstract void excute(T t,MetaStatus metaStatus);


}
