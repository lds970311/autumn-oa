package com.autumn.auth.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerMethodExecutionLoggingAspect {

    @Pointcut("execution(* com.autumn.auth.controller.*Controller.*(..))")
    private void controllerMethods() {
    }

    @AfterReturning(pointcut = "controllerMethods()", returning = "returnValue")
    public void logControllerMethodExecution(Object returnValue) {
        // 获取执行方法的全类名+方法名
        String className = returnValue.getClass().getName();
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        String fullMethodName = className + "." + methodName;
        System.out.println("Method executed: " + fullMethodName);
    }
}
