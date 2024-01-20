package org.example;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class LogAspect {

    @Around("execution(* org.example.BusinessService.methodA(..))")
    public Object logMethodA(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = "methodA";
        Logging.getmsg().info("INFO: Executing method " + methodName);

        // Proceed with the original method execution
        Object result = joinPoint.proceed();

        Logging.getmsg().info("INFO: Exiting method " + methodName);

        return result;
    }

    @Around("execution(* org.example.BusinessService.methodB(..))")
    public Object logMethodB(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = "methodB";
        Logging.getmsg().debug("DEBUG: Executing method " + methodName);

        // Proceed with the original method execution
        Object result = joinPoint.proceed();

        Logging.getmsg().info("INFO: Exiting method " + methodName);

        return result;
    }
}
