package com.yx.rocketmq;

import com.yx.common.MsgType;
import com.yx.metaq.exception.MetaQException;
import com.yx.serializer.util.StringUtil;
import org.springframework.util.StringUtils;

/**
 * Created by renyueliang on 17/1/9.
 */
public class RocketMQMsgType extends RocketMQAbstractMsgType {

    private String topic;
    private String tags;
    private String messageType;


    public RocketMQMsgType(String topic, String tags, String messageType) {

        if (StringUtil.isEmpty(topic) || StringUtils.isEmpty(tags) || StringUtil.isEmpty(messageType)) {
            throw new MetaQException("TOPIC_IS_NULL OR TAGS_IS_NULL OR MESSAGE_TYPE_IS_NULL", "");
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
    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Override
    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}
