package com.yx.rocketmq;


import com.yx.common.MetaStatus;
import com.yx.metaq.MetaQProcess;
import com.yx.metaq.MyMessage;
import com.yx.metaq.exception.MetaQException;
import com.yx.serializer.util.StringUtil;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: renyueliang
 * Date: 16/10/25
 * Time: 下午4:03
 * To change this template use File | Settings | File Templates.
 */
public class RocketSubscribe {

    final static Logger logger = Logger.getLogger(RocketSubscribe.class);

    private String groupId;

    private String topic;

    private Long timestamp;

    private List<RocketMQProcess> rocketMQProcessList;


    public void init() {

        if (StringUtil.isBlank(groupId)
                ||
                StringUtil.isBlank(topic)) {
            throw new MetaQException("SUBSCRIBE_GROUP_OR_TOPIC_IS_NULL", "订阅者groupId 或者topic是空!");
        }


    }

    public void execute(RocketMQMessage rocketMQMessage) {


        for (RocketMQProcess rocketMQProcess : rocketMQProcessList) {
            if (rocketMQProcess.getMsgType().getMessageType().equals(rocketMQMessage.getMessageType())) {

                MetaStatus metaStatus = new MetaStatus();

                rocketMQProcess.excute(rocketMQMessage.getData(), metaStatus);

                if (!metaStatus.isSuccess()) {
                    throw new MetaQException("EXCUTE_PROCESS_ERROR", "处理RocketMQ消息失败");
                }

                break;

            }
        }


    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public void setRocketMQProcessList(List<RocketMQProcess> rocketMQProcessList) {
        this.rocketMQProcessList = rocketMQProcessList;
    }
}
