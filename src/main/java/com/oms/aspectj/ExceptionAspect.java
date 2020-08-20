package com.oms.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.oms.exception.OrderManagementBusinessException;
import com.oms.exception.OrderNotFoundException;

@Aspect
@Component 
public class ExceptionAspect {
 
    @Around("execution(* com.oms.service.OrderService.*(..))")
    public Object exceptionCheckAroundAllMethods(ProceedingJoinPoint joinPoint) throws Throwable 
	{
		Object retVal;
		System.out.println("****ExceptionAspect.aroundServiceMethods() : " + joinPoint.getSignature().getName()
				+ ": Before Method Execution");
		try {
			retVal = joinPoint.proceed();
		} catch (OrderNotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new OrderManagementBusinessException(e);
		}
		System.out.println("****ExceptionAspect.aroundServiceMethods() : " + joinPoint.getSignature().getName()
				+ ": After Method Execution");
		return retVal;
	}
}