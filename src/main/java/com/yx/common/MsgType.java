package com.yx.common;

/**
 * Created with IntelliJ IDEA.
 * User: renyueliang
 * Date: 16/10/25
 * Time: 下午4:12
 * To change this template use File | Settings | File Templates.
 */
public abstract class MsgType implements java.io.Serializable {

    public abstract String getTopic();


    public abstract String getMessageType();

}
