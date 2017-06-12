package com.yx.rocketmq;

import com.alibaba.fastjson.JSON;
import com.yx.metaq.exception.MetaQException;
import com.yx.serializer.util.HessianSerializerTool;
import org.apache.log4j.Logger;
import org.apache.rocketmq.client.ClientConfig;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.MixAll;
import org.apache.rocketmq.common.message.Message;
import org.springframework.util.StringUtils;

/**
 * Created by xiezg@317hu.com on 2017/6/12 0012.
 */
public class RocketMQTool {
    final static Logger logger = Logger.getLogger(RocketMQTool.class);


    /**
     * 分组
     */
    private String producerGroup;
    /**
     * 注册中心地址
     */
    private static String namesrvAddr;

    /**
     * 生产者
     */
    private static DefaultMQProducer producer;

    private static ClientConfig clientConfig;

    public void init() {
        initDefaultMQProducer(getClientConfig(), producerGroup);
    }

    public static ClientConfig getClientConfig() {

        if (RocketMQTool.clientConfig == null) {
            synchronized (RocketMQTool.class) {
                if (RocketMQTool.clientConfig == null) {
                    RocketMQTool.clientConfig = new ClientConfig();
                    if (StringUtils.isEmpty(RocketMQTool.clientConfig.getNamesrvAddr())) {
                        RocketMQTool.clientConfig.setNamesrvAddr(RocketMQTool.namesrvAddr);
                    }

                    if (StringUtils.isEmpty(RocketMQTool.clientConfig.getNamesrvAddr())) {
                        throw new MetaQException("NameSrv_Addr_CONNECT_IS_NULL", "NameSrv_Addr 地址是空!");
                    }
                }
            }
        }

        return RocketMQTool.clientConfig;
    }

    public static void setClientConfig(ClientConfig clientConfig) {
        RocketMQTool.clientConfig = clientConfig;
    }

    private static void initDefaultMQProducer(ClientConfig clientConfig, String producerGroup) {
        if (RocketMQTool.producer == null) {
            synchronized (RocketMQTool.class) {
                if (RocketMQTool.producer == null) {
                    try {
                        RocketMQTool.producer = new DefaultMQProducer(StringUtils.isEmpty(producerGroup) ? MixAll
                                .DEFAULT_PRODUCER_GROUP : producerGroup);
                        RocketMQTool.producer.setNamesrvAddr(clientConfig.getNamesrvAddr());
                        RocketMQTool.producer.setClientIP(clientConfig.getClientIP());
                        RocketMQTool.producer.setInstanceName(clientConfig.getInstanceName());
                        RocketMQTool.producer.start();
                    } catch (Throwable e) {
                        logger.error(e.getMessage(), e);
                    }
                }
            }
        }
    }

    public static void setNamesrvAddr(String namesrvAddr) {
        RocketMQTool.namesrvAddr = namesrvAddr;
    }

    public void setProducerGroup(String producerGroup) {
        this.producerGroup = producerGroup;
    }

    public static void setProducer(DefaultMQProducer producer) {
        RocketMQTool.producer = producer;
    }

    public static final <T> SendResult sendMessage(String topic, String msgType, T t) {
        return sendMessage(new RocketMQMessage(new RocketMQMsgType(topic, msgType), t));

    }

    private static final SendResult sendMessage(RocketMQMessage rocketMQMessage) {
        SendResult sendResult = null;
        try {
            Message message = new Message(rocketMQMessage.getTopic(), rocketMQMessage.getMessageType(),
                    HessianSerializerTool.newParseObjectToBytes(rocketMQMessage));
            sendResult = RocketMQTool.producer.send(message);
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (sendResult != null && sendResult.getSendStatus() != null && sendResult.getSendStatus().
                    equals(SendStatus.SEND_OK)) {
                logger.warn(RocketMQTool.class.getSimpleName() + ".sendMessage success:"
                        + JSON.toJSONString(rocketMQMessage));
            } else {
                logger.error(RocketMQTool.class.getSimpleName() + ".sendMessage error:"
                        + JSON.toJSONString(rocketMQMessage));
            }
        }

        return sendResult;
    }
}
