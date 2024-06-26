package com.luv2code.springboot.thymeleafdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoggingAspect {
    //setup logger
    private Logger myLogger = Logger.getLogger(getClass().getName());

    //setup piointcut declaration
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.controller.*.*(..))")
    private  void forControllerPackage(){

    }
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.service.*.*(..))")
    private  void forServicePackage(){

    }
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.dao.*.*(..))")
    private  void forDaoPackage(){

    }
    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private  void  forAppFlow(){
    }

    //add before Advise
    @Before("forAppFlow()")
    private  void  before(JoinPoint thJoinPoint){
        //display the method we are calling
        String theMethod = thJoinPoint.getSignature().toShortString();
        myLogger.info("====> In @Before : calling method is: "+ theMethod);

        //desplay the arguments to the method
        //get arguments
        Object[]  args = thJoinPoint.getArgs();
        //loop throw and display args
        for (Object tempArgs : args){
            myLogger.info("====> arguments are: "+ tempArgs);
        }

    }

    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "theResult")
    public  void  afterReturning(JoinPoint thJoinPoint, Object theResult){
        //display method we aree calling
        String theMethod = thJoinPoint.getSignature().toShortString();
        myLogger.info("====> In @AfterReturning : calling method is: "+ theMethod);
        //display data returned
        myLogger.info("===> result: "+ theResult);
    }

}
