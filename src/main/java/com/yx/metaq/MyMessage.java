package com.yx.metaq;


/**
 * Created with IntelliJ IDEA.
 * User: renyueliang
 * Date: 16/10/25
 * Time: 下午3:23
 * To change this template use File | Settings | File Templates.
 */
public class MyMessage<T> implements java.io.Serializable {

    static final long serialVersionUID = -1L;

    private MsgType msgType;

    private T data;

    public MyMessage() {
    }


    public MyMessage(MsgType msgType, T  t) {
        this.data=t;
        this.msgType=msgType;
    }

    public T getData() {
        return data;
    }

    public String getMessageType() {
        return msgType.getMessageType();
    }



    public String getTopic() {
        return msgType.getTopic();
    }
}
