package com.lx.eims.exception;
import com.lx.eims.util.Message;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lixing
 * date: 2019-03-13
 * time: 10:56
 * description:全局异常处理,返回JSON格式数据
 */
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
    /**
     * 日志
     */
    private Logger logger=LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(GlobalException.class)
    public Message handGlobalException(GlobalException e){
        Message message=new Message();
        message.put("code", e.getCode());
        message.put("msg", e.getMsg());
        return message;
    }

    /**
     * 处理重复记录异常
     * @param e
     * @return
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public Message handleDuplicateKeyException(DuplicateKeyException e){
        logger.error(e.getMessage(), e);
        return Message.error("数据库中已存在该记录!无法添加,请检查!");
    }

    /**
     * 权限异常
     */
    @ExceptionHandler(AuthorizationException.class)
    public Message handleAuthorizationException(AuthorizationException e){
        logger.error(e.getMessage(), e);
        return Message.error("没有权限，请联系管理员授权!");
    }

    /**
     * 处理不是上面几类的异常.
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Message hangException(Exception e){
        logger.error(e.getMessage(), e);
        return Message.error();
    }

}
