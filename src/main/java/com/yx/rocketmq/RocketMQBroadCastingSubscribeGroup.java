package com.yx.rocketmq;

import com.yx.serializer.util.HessianSerializerTool;
import org.apache.log4j.Logger;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by xiezg@317hu.com on 2017/6/12 0012.
 */
public class RocketMQBroadCastingSubscribeGroup extends RocketMQAbstractSubscribeGroup {
    final static Logger logger = Logger.getLogger(RocketMQBroadCastingSubscribeGroup.class);


    private List<RocketSubscribe> rocketSubscribeList;

    public void init() {


        if (rocketSubscribeList == null || rocketSubscribeList.size() == 0) {
            return;
        }

        for (final RocketSubscribe subscribe : rocketSubscribeList) {
            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(subscribe.getGroupId());
            consumer.setMessageModel(MessageModel.BROADCASTING);
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
            consumer.setNamesrvAddr(RocketMQTool.getClientConfig().getNamesrvAddr());
            consumer.setClientIP(RocketMQTool.getClientConfig().getClientIP());
            consumer.setInstanceName(RocketMQTool.getClientConfig().getInstanceName());

            try {
                consumer.subscribe(subscribe.getTopic(), "*");
                consumer.registerMessageListener(new MessageListenerConcurrently() {

                    @Override
                    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                                    ConsumeConcurrentlyContext context) {
                        return RocketMQBroadCastingSubscribeGroup.super.consumeMessage(subscribe, msgs, context);
                    }
                });

                consumer.start();

                System.out.printf("Consumer Started.%n");
            } catch (Throwable e) {
                logger.error(e.getMessage(), e);

            }
        }
    }

    public void setRocketSubscribeList(List<RocketSubscribe> rocketSubscribeList) {
        this.rocketSubscribeList = rocketSubscribeList;
    }
}
