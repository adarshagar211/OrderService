package com.fda.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.fda.exception.FoodDeliveryServiceException;
import com.fda.exception.OrderNotFoundException;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Aspect
@Component
public class FoodDeliveryLogAndExceptionAspect {

	@Around("execution(* com.fda.service.OrderService.*(..))")
	public Object exceptionCheckAroundAllMethods(ProceedingJoinPoint joinPoint) throws Throwable {
		Object retVal;
		log.info("*****Calling method : " + joinPoint.getSignature().getName());
		try {
			retVal = joinPoint.proceed();
		} catch (OrderNotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new FoodDeliveryServiceException(e);
		}
		log.info("*****Exiting method : " + joinPoint.getSignature().getName());
		return retVal;
	}
}