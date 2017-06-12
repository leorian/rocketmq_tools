package com.yx.metaq;

import com.taobao.metamorphosis.Message;
import com.taobao.metamorphosis.client.MessageSessionFactory;
import com.taobao.metamorphosis.client.consumer.ConsumerConfig;
import com.taobao.metamorphosis.client.consumer.MessageConsumer;
import com.taobao.metamorphosis.client.consumer.MessageListener;
import com.taobao.metamorphosis.exception.MetaClientException;
import com.yx.serializer.util.HessianSerializerTool;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * Created with IntelliJ IDEA.
 * User: renyueliang
 * Date: 16/10/25
 * Time: 下午4:27
 * To change this template use File | Settings | File Templates.
 */
public class MetaQSubsribeGroup {
    final static Logger logger = Logger.getLogger(MetaQSubsribeGroup.class);


    private List<MetaQSubscribe> metaQSubscribes;

    public void init() {

        final MessageSessionFactory sessionFactory = MetaQTool.getMessageSessionFactory();

        if (metaQSubscribes == null || metaQSubscribes.size() == 0) {
            return;
        }

        for (final MetaQSubscribe subscribe : metaQSubscribes) {

            ConsumerConfig consumerConfig = new ConsumerConfig(subscribe.getGroupId());
            // 默认最大获取延迟为5秒，这里设置成100毫秒，请根据实际应用要求做设置。
            consumerConfig.setMaxDelayFetchTimeInMills(100);

            final MessageConsumer consumer = sessionFactory.createConsumer(consumerConfig);

            try {

                consumer.subscribe(subscribe.getTopic(), 1024 * 1024, new MessageListener() {

                    @Override
                    public void recieveMessages(final Message message) {

                        try {
                            MyMessage myMessage = null;

                            try {
                                myMessage = HessianSerializerTool.newParseBytesToObject(message.getData());
                            } catch (Throwable e) {

                            }
                            if (myMessage == null) {
                                return;
                            }

                            subscribe.excute(myMessage);
                        } catch (Throwable e) {
                            message.setRollbackOnly();
                            logger.error(e.getMessage(), e);
                        }
                    }


                    @Override
                    public Executor getExecutor() {
                        return null;
                    }
                });

                consumer.completeSubscribe();

            } catch (MetaClientException e) {
                logger.error(e.getMessage(), e);

            } catch (Throwable e) {
                logger.error(e.getMessage(), e);
            }


        }


    }


    public void setMetaQSubscribes(List<MetaQSubscribe> metaQSubscribes) {
        this.metaQSubscribes = metaQSubscribes;
    }
}
