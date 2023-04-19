package com.example.project.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Around("execution(* com.example.project.controller..*(..)) || execution(* com.example.project.service..*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info(String.format("Method %s from %s %s will be executed. Time: %s",
                joinPoint.getSignature().getName(),
                getMethodLayer(joinPoint.getTarget().getClass().getCanonicalName()),
                formatClassName(joinPoint.getTarget().getClass().getCanonicalName()),
                LocalDateTime.now()));

        Object result = joinPoint.proceed();

        log.info(String.format("Method %s from %s %s finished the execution. Time: %s",
                joinPoint.getSignature().getName(),
                getMethodLayer(joinPoint.getTarget().getClass().getCanonicalName()),
                formatClassName(joinPoint.getTarget().getClass().getCanonicalName()),
                LocalDateTime.now()));

        return result;
    }

    private String formatClassName(String className) {
        var stringDivided = className.split("\\.");
        return stringDivided[stringDivided.length - 1];
    }

    private String getMethodLayer(String className) {
        var stringDivided = className.split("\\.");
        return stringDivided[3];
    }

}
