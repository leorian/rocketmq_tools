package com.yx.rocketmq.demo;

import com.yx.rocketmq.RocketMQAbstractMsgType;

/**
 * Created with IntelliJ IDEA.
 * User: renyueliang
 * Date: 16/10/25
 * Time: 下午7:20
 * To change this template use File | Settings | File Templates.
 */
public class TestMsgTypeB extends RocketMQAbstractMsgType {

    @Override
    public String getTopic() {
        return "lion_test";
    }

    @Override
    public String getMessageType() {
        return "test-metaq-messagetype1";
    }

    @Override
    public String getTags() {
        return "B";
    }
}
