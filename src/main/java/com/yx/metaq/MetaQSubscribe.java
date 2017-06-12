package com.yx.metaq;


import com.yx.common.MetaStatus;
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
public class MetaQSubscribe {

 final static   Logger logger = Logger.getLogger(MetaQSubscribe.class);

    private String groupId;

    private String topic;

    private List<MetaQProcess> metaQProcessList;


    public void init() {

        if (StringUtil.isBlank(groupId)
                ||
                StringUtil.isBlank(topic)) {
            throw new MetaQException("SUBSCRIBE_GROUP_OR_TOPIC_IS_NULL", "订阅者groupId 或者topic是空!");
        }


    }

    public void excute(MyMessage myMessage) {


        for (MetaQProcess metaQProcess : metaQProcessList) {
            if (metaQProcess.getMsgType().getMessageType().equals(myMessage.getMessageType())) {

                MetaStatus metaStatus = new MetaStatus();

                metaQProcess.excute(myMessage.getData(), metaStatus);

                if (!metaStatus.isSuccess()) {
                    throw new MetaQException("EXCUTE_PROCESS_ERROR", "处理metaQ消息失败");
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

    public void setMetaQProcessList(List<MetaQProcess> metaQProcessList) {
        this.metaQProcessList = metaQProcessList;
    }
}
