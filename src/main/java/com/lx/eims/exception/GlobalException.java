package com.lx.eims.exception;
import lombok.Getter;
import lombok.Setter;
/**
 * author: lixing
 * date: 2019-03-13
 * time: 12:01
 * description:自定义异常
 */
@Getter
@Setter
public class GlobalException extends RuntimeException{
    private String msg;
    private int code = 500;

    public GlobalException(String msg){
        super(msg);
        this.msg=msg;
    }

    public GlobalException(String msg, Throwable e){
        super(msg, e);
        this.msg = msg;
    }

    public GlobalException(String msg, int code, Throwable e){
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }
}
