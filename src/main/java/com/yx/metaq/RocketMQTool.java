package com.yx.metaq;

import com.yx.metaq.exception.MetaQException;
import org.apache.log4j.Logger;
import org.apache.rocketmq.client.ClientConfig;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.MixAll;
import org.springframework.util.StringUtils;

/**
 * Created by xiezg@317hu.com on 2017/6/12 0012.
 */
public class RocketMQTool {
    final static Logger logger = Logger.getLogger(RocketMQTool.class);
    /**
     * 注册中心地址
     */
    private String namesrvAddr;

    /**
     * 分组
     */
    private String producerGroup;

    /**
     * 生产者
     */
    private static DefaultMQProducer producer;

    public void init() {
        final ClientConfig clientConfig = new ClientConfig();
        if (StringUtils.isEmpty(clientConfig.getNamesrvAddr())) {
            clientConfig.setNamesrvAddr(this.namesrvAddr);
        }

        if (StringUtils.isEmpty(clientConfig.getNamesrvAddr())) {
            throw new MetaQException("NameSrv_Addr_CONNECT_IS_NULL", "NameSrv_Addr 地址是空!");
        }

        initDefaultMQProducer(clientConfig, producerGroup);
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

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public void setProducerGroup(String producerGroup) {
        this.producerGroup = producerGroup;
    }

    public static void setProducer(DefaultMQProducer producer) {
        RocketMQTool.producer = producer;
    }
}
