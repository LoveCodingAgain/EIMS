package com.lx.eims.aspect;
import com.google.gson.Gson;
import com.lx.eims.annotation.RecordSysLog;
import com.lx.eims.entity.system.SysLog;
import com.lx.eims.service.SysLogService;
import com.lx.eims.util.HttpContextUtils;
import com.lx.eims.util.IPUtils;
import com.lx.eims.util.ShiroUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
/**
 * @author: lixing
 * date: 2019-03-15
 * time: 23:17
 * description:日志注解切点
 */
@Aspect
@Component
public class SysLogAspect {

    @Autowired
    private SysLogService sysLogService;
    /**
     * 定义切点,这里配置自定义注解
     */
    @Pointcut("@annotation(com.lx.eims.annotation.RecordSysLog)")
    public void logPointCut(){

    }
    /**
     * 环绕通知
     */
    @Around("logPointCut()")
    public Object sysAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 开始时间
        Instant start=Instant.now();
        // 处理目标方法
        Object result=joinPoint.proceed();
        // 结束时间
        Instant end=Instant.now();
        // 执行时间毫秒
        long time= Duration.between(start, end).toMillis();
        // 保存系统日志方法
        saveSysLog(joinPoint,time);
        return result;
    }

    /**
     * 保存系统日志
     * @param joinPoint
     * @param time
     */
    private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取注解方法
        Method method = signature.getMethod();
        SysLog sysLog=new SysLog();
        // 获取方法上的注解
        RecordSysLog recordSysLog = method.getAnnotation(RecordSysLog.class);
        if(recordSysLog!=null){
            sysLog.setOperation(recordSysLog.value());
        }
        // 获取请求的类名和方法名
        String className=joinPoint.getTarget().getClass().getName();
        String methodName=signature.getMethod().getName();
        sysLog.setMethod(className+"."+methodName+"()");
        // 获取请求的参数
        Object[] objs=joinPoint.getArgs();

        try {
            String params=new Gson().toJson(objs[0]);
            sysLog.setParams(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 获取Http请求
        HttpServletRequest request= HttpContextUtils.getHttpServletRequest();
        // 设置请求IP地址
        sysLog.setIp(IPUtils.getIpAddr(request));
        // 获取执行请求用户名
        String username=((SysLog) ShiroUtils.getSubject().getPrincipal()).getUsername();
        sysLog.setUsername(username);
        // 设置请求执行时间
        sysLog.setTime(time);
        // 设置创建时间
        sysLog.setCreateDate(new Date());
        // 保存日志
        sysLogService.save(sysLog);
    }

}
