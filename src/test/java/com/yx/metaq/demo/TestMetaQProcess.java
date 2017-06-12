package com.yx.metaq.demo;

import com.alibaba.fastjson.JSON;
import com.yx.metaq.MetaQProcess;
import com.yx.common.MetaStatus;
import com.yx.common.MsgType;

/**
 * Created with IntelliJ IDEA.
 * User: renyueliang
 * Date: 16/10/25
 * Time: 下午5:14
 * To change this template use File | Settings | File Templates.
 */
public class TestMetaQProcess extends MetaQProcess<TestMessgeDTO> {

    @Override
    public MsgType getMsgType() {
        return new TestMsgType();
    }

    @Override
    public void excute(TestMessgeDTO testMessgeDTO, MetaStatus metaStatus) {

        System.out.println("TestMetaQProcess has excute !:"+JSON.toJSONString(testMessgeDTO));



    }
}
