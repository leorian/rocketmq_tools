package com.yx.rocketmq.demo;

import com.alibaba.fastjson.JSON;
import com.yx.common.MetaStatus;
import com.yx.common.MsgType;
import com.yx.metaq.MetaQProcess;
import com.yx.metaq.demo.TestMessageDTO1;
import com.yx.rocketmq.RocketMQAbstractMsgType;
import com.yx.rocketmq.RocketMQProcess;

/**
 * Created with IntelliJ IDEA.
 * User: renyueliang
 * Date: 16/10/25
 * Time: 下午7:14
 * To change this template use File | Settings | File Templates.
 */
public class TestRocketMQProcessB extends RocketMQProcess<TestMessageDTO1> {

    @Override
    public RocketMQAbstractMsgType getMsgType() {
        return new TestMsgTypeB();
    }

    @Override
    public void excute(TestMessageDTO1 testMessageDTO1, MetaStatus metaStatus) {

        System.out.println(TestRocketMQProcessB.class.getSimpleName() + "has excute !"
                + JSON.toJSONString(testMessageDTO1));

    }
}
