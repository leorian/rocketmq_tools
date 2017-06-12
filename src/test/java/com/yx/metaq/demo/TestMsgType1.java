package com.yx.metaq.demo;

import com.yx.metaq.MsgType;

/**
 * Created with IntelliJ IDEA.
 * User: renyueliang
 * Date: 16/10/25
 * Time: 下午7:20
 * To change this template use File | Settings | File Templates.
 */
public class TestMsgType1 extends MsgType {

    @Override
    public String getTopic() {
        return "test";
    }

    @Override
    public String getMessageType() {
        return "test-metaq-messagetype1";
    }
}
