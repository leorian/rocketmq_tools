package com.yx.metaq;


/**
 * Created with IntelliJ IDEA.
 * User: renyueliang
 * Date: 16/10/25
 * Time: 下午3:26
 * To change this template use File | Settings | File Templates.
 */
public abstract class MetaQProcess<T> {

    public abstract MsgType getMsgType();


    public abstract void excute(T t,MetaStatus metaStatus);


}
