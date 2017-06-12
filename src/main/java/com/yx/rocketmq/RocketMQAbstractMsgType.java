package com.yx.rocketmq;

import com.yx.common.MsgType;

/**
 * Created by xiezg@317hu.com on 2017/6/12 0012.
 */
public abstract class RocketMQAbstractMsgType extends MsgType {
    public abstract String getTags();
}
