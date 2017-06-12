package com.yx.rocketmq.demo;

import com.yx.metaq.demo.TestMessageDTO1;
import com.yx.metaq.demo.TestMessgeDTO;
import com.yx.rocketmq.RocketMQMessage;
import com.yx.rocketmq.RocketMQTool;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: renyueliang
 * Date: 16/10/25
 * Time: 下午5:52
 * To change this template use File | Settings | File Templates.
 */
public class RocketMQTest {


//    private static final ServiceFactory factory = ServiceFactory
//            .getInstanceWithPath("/Users/renyueliang/Documents/server/taobao-tomcat-7.0.59/deploy");
//

    public void loadSpring() {

        final ApplicationContext factory1 = new ClassPathXmlApplicationContext("applicationContext-basic-rocketmq.xml");
    }


    @Test
    public void metaQTest() throws Throwable {

        String topic = "lion_test";
        final ApplicationContext factory1 = new ClassPathXmlApplicationContext("applicationContext-basic-rocketmq.xml");


        TestMessgeDTO testMessgeDTO = new TestMessgeDTO();
        testMessgeDTO.setName("你好哈哈12");
        testMessgeDTO.setAge(1);
        testMessgeDTO.setAddress("sfsgs是根深蒂固紫地瓜13q412");


        TestMessageDTO1 testMessageDTO1 = new TestMessageDTO1();
        testMessageDTO1.setName("三国杀你好哈哈12");
        testMessageDTO1.setAge(10);
        testMessageDTO1.setAddress("三国杀公司sfsgs是根深蒂固紫地瓜13q412");


        TestMessgeDTO testMessageDTO2 = new TestMessgeDTO();
        testMessageDTO2.setName("三国杀你好哈哈124444444444");
        testMessageDTO2.setAge(40);
        testMessageDTO2.setAddress("三国杀公司sfsgs是根深蒂固紫地瓜13q4125555555");

        TestMessageDTO1 testMessageDTO3 = new TestMessageDTO1();
        testMessageDTO3.setName("人月亮三国杀你好哈哈12");
        testMessageDTO3.setAge(33);
        testMessageDTO3.setAddress("人月亮人月亮人月亮三国杀公司sfsgs是根深蒂固紫地瓜13q412");

//        MyMessage myMessage = new MyMessage(new TestMsgType(), testMessgeDTO);
//        MyMessage myMessage1 = new MyMessage(new TestMsgType1(), testMessageDTO1);
//        MyMessage myMessage2 = new MyMessage(new TestMsgType(), testMessgeDTO);
//        MyMessage myMessage3 = new MyMessage(new TestMsgType1(), testMessageDTO1);

        RocketMQMessage rocketMQMessage = new RocketMQMessage(new TestMsgTypeA(), testMessgeDTO);
        RocketMQMessage rocketMQMessage1 = new RocketMQMessage(new TestMsgTypeB(), testMessageDTO1);
        RocketMQMessage rocketMQMessage2 = new RocketMQMessage(new TestMsgTypeA(), testMessgeDTO);
        RocketMQMessage rocketMQMessage3 = new RocketMQMessage(new TestMsgTypeB(), testMessageDTO1);


        RocketMQTool.sendMessage(topic, "A", "test-metaq-messagetype", testMessageDTO2);
        RocketMQTool.sendMessage(rocketMQMessage);
        RocketMQTool.sendMessage(rocketMQMessage1);
        RocketMQTool.sendMessage(rocketMQMessage2);
        RocketMQTool.sendMessage(rocketMQMessage3);
        RocketMQTool.sendMessage(topic, "B", "test-metaq-messagetype1", testMessageDTO3);

        Thread.sleep(100000l);

    }

}
