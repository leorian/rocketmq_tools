package com.yx.metaq;

import com.alibaba.fastjson.JSON;
import com.taobao.metamorphosis.Message;
import com.taobao.metamorphosis.client.MessageSessionFactory;
import com.taobao.metamorphosis.client.MetaClientConfig;
import com.taobao.metamorphosis.client.MetaMessageSessionFactory;
import com.taobao.metamorphosis.client.producer.MessageProducer;
import com.taobao.metamorphosis.client.producer.SendResult;
import com.taobao.metamorphosis.exception.MetaClientException;
import com.taobao.metamorphosis.utils.ZkUtils;
import com.yx.metaq.exception.MetaQException;
import com.yx.serializer.util.HessianSerializerTool;
import com.yx.serializer.util.StringUtil;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 * User: renyueliang
 * Date: 16/10/25
 * Time: 下午2:48
 * To change this template use File | Settings | File Templates.
 */
public class MetaQTool {

    final static Logger logger = Logger.getLogger(MetaQTool.class);


    private String zkConnect;

    private static Map<String, Boolean> publishStore = new HashMap<>();

    public void init() {

        final MetaClientConfig metaClientConfig = new MetaClientConfig();
        final ZkUtils.ZKConfig zkConfig = new ZkUtils.ZKConfig();

        if (StringUtil.isBlank(zkConnect)) {
            throw new MetaQException("ZK_CONNECT_IS_NULL", "zookeeper 地址是空!");
        }

        zkConfig.zkConnect = zkConnect;
        zkConfig.zkRoot = "/meta";

        metaClientConfig.setZkConfig(zkConfig);

        initMessageSessionFactory(metaClientConfig);
        initMessageProducer();
    }

    public void setZkConnect(String zkConnect) {
        this.zkConnect = zkConnect;
    }


    private static MessageSessionFactory messageSessionFactory = null;
    private static MessageProducer messageProducer = null;


    private static void initMessageSessionFactory(MetaClientConfig metaClientConfig) {
        if (messageSessionFactory == null) {
            synchronized (MetaQTool.class) {

                if (messageSessionFactory == null) {
                    try {
                        messageSessionFactory = new MetaMessageSessionFactory(metaClientConfig);
                    } catch (MetaClientException e) {

                        logger.error(e.getMessage(),e);
                        //e.printStackTrace();
                    }
                }

            }
        }
    }

    public static MessageSessionFactory getMessageSessionFactory() {
        return messageSessionFactory;

    }

    private static void initMessageProducer() {
        if (messageProducer == null) {
            synchronized (MetaQTool.class) {

                if (messageProducer == null) {
                    try {
                        messageProducer = getMessageSessionFactory().createProducer();
                    } catch (Throwable e) {
                        logger.error(e.getMessage(),e);
                        //e.printStackTrace();
                    }
                }

            }
        }
    }


    public static MessageProducer getMessageProducer() {
        if (messageProducer == null) {
            synchronized (MetaQTool.class) {

                if (messageProducer == null) {
                    try {
                        messageProducer = getMessageSessionFactory().createProducer();
                    } catch (Throwable e) {
                        logger.error(e.getMessage(),e);
                       // e.printStackTrace();
                    }
                }

            }
        }

        return messageProducer;
    }

    private static void publish(String topic){

       Boolean b
        = publishStore.get(topic);

        if(b!=null && b){
           return;
        }

        synchronized ("abcdef$"){

             b = publishStore.get(topic);

             if(b==null){
                synchronized ("aabcdefg$"){
                    messageProducer.publish(topic);
                    publishStore.put(topic,true);
                }
             }

        }


    }

    public static final  <T> SendResult sendMessage(String topic,String msgType, T t){

      return  sendMessage(new MyMessage(new MetaQMsgType(topic,msgType),t));

    }

    public static final SendResult sendMessage(MyMessage myMessage) {

        SendResult sendResult = null;

        try {

            publish(myMessage.getTopic());


            sendResult = messageProducer.sendMessage(
                    new Message(myMessage.getTopic(), HessianSerializerTool.newParseObjectToBytes(myMessage)));



        } catch (Throwable e) {
            logger.error(e.getMessage(),e);

        } finally {

            if(sendResult!=null && sendResult.isSuccess()){
                logger.warn("MetaQTool.sendMessage success:"+ JSON.toJSONString(myMessage));
            }else{
                logger.error("MetaQTool.sendMessage error:"+ JSON.toJSONString(myMessage));
            }



        }

       return sendResult;

    }

}
