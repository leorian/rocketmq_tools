package com.yx.metaq;

import com.yx.metaq.exception.MetaQException;
import com.yx.serializer.util.StringUtil;

/**
 * Created by renyueliang on 17/1/9.
 */
public class MetaQMsgType extends MsgType {

    private String topic;
    private String messageType;


    public MetaQMsgType( String topic,String messageType){

        if(StringUtil.isEmpty(topic) || StringUtil.isEmpty(messageType)){
            throw new MetaQException("TOPIC_IS_NULL OR MESSAGE_TYPE_IS_NULL","");
        }

        setTopic(topic);
        setMessageType(messageType);
    }

    @Override
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}
