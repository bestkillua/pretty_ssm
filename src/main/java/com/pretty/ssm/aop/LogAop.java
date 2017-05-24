package com.pretty.ssm.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 使用aop功能来记录操作
 * 具体功能根据需要进行完善
 * @author lushaoqing 
 */
@Component
@Aspect
public class LogAop {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Pointcut("execution(* com.pretty.ssm.web.*.*(..))")
	public void aopMethod(){
		
	}
	
	@Around("aopMethod()")
	public Object record(ProceedingJoinPoint joinPoint) throws Throwable{
		if(LOGGER.isInfoEnabled()){
			LOGGER.info("before " + joinPoint);
		}
		return joinPoint.proceed();
	}
	
}
