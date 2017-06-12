package com.yx.rocketmq.demo;

import com.yx.common.MsgType;
import com.yx.rocketmq.RocketMQAbstractMsgType;

/**
 * Created with IntelliJ IDEA.
 * User: renyueliang
 * Date: 16/10/25
 * Time: 下午5:16
 * To change this template use File | Settings | File Templates.
 */
public class TestMsgTypeA extends RocketMQAbstractMsgType {

    @Override
    public String getTopic() {
        return "lion_test";
    }

    @Override
    public String getMessageType() {
        return "test-metaq-messagetype";
    }


    @Override
    public String getTags() {
        return "A";
    }
}
