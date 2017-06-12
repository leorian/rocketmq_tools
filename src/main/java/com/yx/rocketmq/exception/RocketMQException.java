package com.yx.rocketmq.exception;

/**
 * Created by xiezg@317hu.com on 2017/6/12 0012.
 */
public class RocketMQException extends Exception {
    private static final long serialVersionUID = 1L;

    private String errorCode;

    private String msg;


    public RocketMQException(Throwable e) {
        super(e);
    }


    public RocketMQException(String errorCode, String msg) {
        super(errorCode);
        this.errorCode = errorCode;
        this.msg = msg;
    }

    public RocketMQException(String errorCode, String msg, Throwable e) {
        super(e);
        this.errorCode = errorCode;
        this.msg = msg;
    }
}
