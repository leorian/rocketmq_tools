package com.yx.rocketmq;


import com.yx.common.MsgType;

/**
 * Created with IntelliJ IDEA.
 * User: renyueliang
 * Date: 16/10/25
 * Time: 下午3:23
 * To change this template use File | Settings | File Templates.
 */
public class RocketMQMessage<T> implements java.io.Serializable {

    static final long serialVersionUID = -1L;

    private RocketMQAbstractMsgType rocketMQAbstractMsgType;

    private T data;

    public RocketMQMessage() {
    }


    public RocketMQMessage(RocketMQAbstractMsgType rocketMQAbstractMsgType, T t) {
        this.data = t;
        this.rocketMQAbstractMsgType = rocketMQAbstractMsgType;
    }

    public T getData() {
        return data;
    }

    public String getMessageType() {
        return rocketMQAbstractMsgType.getMessageType();
    }

    public String getTags() {
        return rocketMQAbstractMsgType.getTags();
    }


    public String getTopic() {
        return rocketMQAbstractMsgType.getTopic();
    }
}
