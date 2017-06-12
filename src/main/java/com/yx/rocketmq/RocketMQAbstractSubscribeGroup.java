package com.yx.rocketmq;

import com.yx.serializer.util.HessianSerializerTool;
import org.apache.log4j.Logger;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by xiezg@317hu.com on 2017/6/12 0012.
 */
public class RocketMQAbstractSubscribeGroup {
    private static final Logger logger = Logger.getLogger(RocketMQAbstractSubscribeGroup.class);

    ConsumeConcurrentlyStatus consumeMessage(RocketSubscribe subscribe, List<MessageExt> msgs,
                                             ConsumeConcurrentlyContext context) {
        System.out.printf(Thread.currentThread().getName() + " Receive New Messages: " + msgs + "%n");
        if (CollectionUtils.isEmpty(msgs)) {
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }

        if (subscribe.getTimestamp() != null) {
            if (msgs.get(0).getStoreTimestamp() < subscribe.getTimestamp()) {
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        }

        RocketMQMessage rocketMQMessage = null;
        try {
            rocketMQMessage = HessianSerializerTool.newParseBytesToObject(msgs.get(0).getBody());
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
        }

        if (rocketMQMessage == null) {
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }

        subscribe.execute(rocketMQMessage);

        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
