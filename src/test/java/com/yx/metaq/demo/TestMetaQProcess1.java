package com.yx.metaq.demo;

import com.alibaba.fastjson.JSON;
import com.yx.metaq.MetaQProcess;
import com.yx.common.MetaStatus;
import com.yx.common.MsgType;

/**
 * Created with IntelliJ IDEA.
 * User: renyueliang
 * Date: 16/10/25
 * Time: 下午7:14
 * To change this template use File | Settings | File Templates.
 */
public class TestMetaQProcess1 extends MetaQProcess<TestMessageDTO1> {

    @Override
    public MsgType getMsgType() {
        return new TestMsgType1();
    }

    @Override
    public void excute(TestMessageDTO1 testMessageDTO1, MetaStatus metaStatus) {

        System.out.println("TestMetaQProcess1  has excute !" + JSON.toJSONString(testMessageDTO1));

    }
}
