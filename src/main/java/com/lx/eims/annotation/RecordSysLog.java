package com.lx.eims.annotation;
import java.lang.annotation.*;
/**
 * @author: lixing
 * date: 2019-03-15
 * time: 23:14
 * description:自定义系统日志注解
 */
@Target({ElementType.METHOD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RecordSysLog {
    String value() default "";
}
