package com.example.aopexam;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

// Advice + Pointcut
@Aspect
@Component  // Bean 등록 필요
public class ServiceAspect {
    @Pointcut("execution(* com.example.aopexam.SimpleService.*(..))")
    public void pc(){}

    @Pointcut("execution(* hello())")
    public void pc2(){}

//    @Before("pc()")
//    public void before(JoinPoint joinPoint){
//        System.out.println("Before :::::::::: "+ joinPoint.getSignature().getName());
//    }
//
//    @After("pc2()")
//    public void after(){
//        System.out.println("After :::::::::::::::::");
//    }

    @Before("execution(* com.example.aopexam.SimpleService.*(..))") // pointcut 지정
    // advice
    public void before(JoinPoint joinPoint) {
        System.out.println("Before :::: " + joinPoint.getSignature().getName());    // mehtod name
    }

    @After("execution(* com.example.aopexam.SimpleService.*(..))")
    public void after() {
        System.out.println("After ::::");
    }

    @AfterReturning(pointcut = "execution(* com.example.aopexam.*.*(..))", returning = "result")
    public void afterReturningServiceMethod(JoinPoint joinPoint, Object result) {
        System.out.println("After method: " + joinPoint.getSignature().getName() + ", return value: " + result);
    }

    @Before("execution(* setName(String))")
    public void beforeName(JoinPoint joinPoint) {
        System.out.println("beforeName :::: ");
        System.out.println(joinPoint.getSignature().getName());
        Object[] args = joinPoint.getArgs();
        for(Object o: args) System.out.println(":::: 인자 --> " + o);
    }

    @AfterThrowing(value = "pc2()", throwing = "ex")
    public void afterThrowing(Throwable ex) {
        System.out.println("AfterThrowing ::::");
        System.out.println("exception value ::" + ex);
    }

    @Around("pc()")
    public String around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("Around run :::: 실제 호출된 메소드가 실행되기 전에 할 일 구현");

        String value = (String) pjp.proceed();// 실제 target 메소드 호출

        System.out.println("Around run :::: 실제 호출된 메소드가 실행된 후 할 일 구현");
        value += " aop run!";

        return value;
    }
}
