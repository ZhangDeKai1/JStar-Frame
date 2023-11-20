package com.zdk.log;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * 日志切面，提供简单的方法入参出参，方法耗时的记录
 */
@Aspect
@Slf4j
@Component
@ConditionalOnProperty(name={"log.aspect.enable"},havingValue = "true",matchIfMissing = true)
public class LogAspect {

    //定义切点
    @Pointcut("execution(* com.zdk.*.controller.*Controller.*(..))||execution(* com.zdk.*.service.*Service.*(..))")
    public void pointCut(){
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        //1、获取请求参数
        Object[] reqArgs=pjp.getArgs();
        String req=new Gson().toJson(reqArgs);
        //2、获取方法
        MethodSignature methodSignature=(MethodSignature) pjp.getSignature();
        String methodName = methodSignature.getDeclaringType().getName()+"."+methodSignature.getName();
        log.info("method:{},req:{}",methodName,req);
        long startTime=System.currentTimeMillis();
        //切面需要抛出异常,不能吞掉
        Object responseObj=pjp.proceed();
        String resp=new Gson().toJson(responseObj);
        long endTime=System.currentTimeMillis();
        log.info("method:{},resp:{}",methodName,resp);
        log.info("method:{},costTime:{}",methodName,resp);
        return responseObj;
    }

}
