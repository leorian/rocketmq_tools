package com.yx.metaq.demo;

import com.yx.common.MsgType;

/**
 * Created with IntelliJ IDEA.
 * User: renyueliang
 * Date: 16/10/25
 * Time: 下午5:16
 * To change this template use File | Settings | File Templates.
 */
public class TestMsgType extends MsgType {

    @Override
    public String getTopic() {
        return "test";
    }

    @Override
    public String getMessageType() {
        return "test-metaq-messagetype";
    }


}
