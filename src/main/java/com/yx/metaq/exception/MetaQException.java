package com.yx.metaq.exception;

/**
 * Created with IntelliJ IDEA.
 * User: renyueliang
 * Date: 16/10/25
 * Time: 下午3:15
 * To change this template use File | Settings | File Templates.
 */
public class MetaQException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String errorCode;

    private String msg;


      public MetaQException(Throwable e){
          super(e);
      }


    public MetaQException(String errorCode,String msg){
        super(errorCode);
        this.errorCode=errorCode;
        this.msg=msg;
    }

    public MetaQException(String errorCode,String msg,Throwable e){
        super(e);
        this.errorCode=errorCode;
        this.msg=msg;
    }

}
