package com.altas.draw.log.trace;

import com.alibaba.druid.support.json.JSONUtils;
import com.altas.draw.constants.Constants;
import com.altas.draw.util.AltasUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * 日志拦截器
 */
@Component
@Aspect
@Slf4j
public class LogAspect {
    /**
     * 拦截入口
     */
    @Pointcut("@annotation(com.altas.draw.log.trace.LogTrace)")
    public void pointCut() {
    }
    /**
     * 拦截处理
     * @param point point 信息
     * @return result
     * @throws Throwable if any
     */
     @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable{
        try {
            MethodSignature joinPointObject = (MethodSignature) point.getSignature();
            Method method = joinPointObject.getMethod();
            //添加 TRACE_ID
            MDC.put(Constants.TRACE_ID, AltasUtil.getUUID());
            LogTrace logTrace =   method.getAnnotation(LogTrace.class);
            if(logTrace.input()){
                if(log.isInfoEnabled()) {
                    for(int i = 0 ;i<point.getArgs().length;i++) {
                        log.info("param["+i+"]"+JSONUtils.toJSONString(point.getArgs()[i]));
                    }
                }
            }
            Object object = point.proceed();
            if(logTrace.output()){
                if(log.isInfoEnabled()) {
                    log.info(JSONUtils.toJSONString(object));
                }
            }
            return object;
        }finally {
            //移除 TRACE_ID
               MDC.remove(Constants.TRACE_ID);
        }
    }
}
