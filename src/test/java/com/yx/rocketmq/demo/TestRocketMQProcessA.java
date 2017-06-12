package com.yx.rocketmq.demo;

import com.alibaba.fastjson.JSON;
import com.yx.common.MetaStatus;
import com.yx.metaq.demo.TestMessgeDTO;
import com.yx.rocketmq.RocketMQAbstractMsgType;
import com.yx.rocketmq.RocketMQProcess;

/**
 * Created with IntelliJ IDEA.
 * User: renyueliang
 * Date: 16/10/25
 * Time: 下午5:14
 * To change this template use File | Settings | File Templates.
 */
public class TestRocketMQProcessA extends RocketMQProcess<TestMessgeDTO> {

    @Override
    public RocketMQAbstractMsgType getMsgType() {
        return new TestMsgTypeA();
    }

    @Override
    public void excute(TestMessgeDTO testMessgeDTO, MetaStatus metaStatus) {

        System.out.println(TestRocketMQProcessA.class.getSimpleName() + " has excute !:"
                + JSON.toJSONString(testMessgeDTO));


    }
}
