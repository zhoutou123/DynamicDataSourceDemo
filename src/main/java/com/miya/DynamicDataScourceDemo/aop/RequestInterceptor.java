package com.miya.DynamicDataScourceDemo.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.alibaba.fastjson.JSONObject;

@Aspect
@Configuration
public class RequestInterceptor {

	protected static ThreadLocal<Long> start = new ThreadLocal<Long>();

	private static final Logger _LOGGER = LoggerFactory.getLogger(RequestInterceptor.class.getSimpleName());

	/**
	 * 切面: controller包下所有公共方法
	 */
	@Pointcut("execution(public * com.miya.DynamicDataScourceDemo.controller..*.*(..))")
	public void request() {
	}

	/**
	 * 前置通知
	 */
	@Before("request()")
	public void before() {
		start.set(System.currentTimeMillis());
	}

	/**
	 * @description 环绕通知
	 */
	@Around("request()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		Signature signature = pjp.getSignature();
		String methodName = signature.getName();
		MethodSignature methodSignature = (MethodSignature) signature;
		Method method = methodSignature.getMethod();
		// RequestAnnotation ao = method.getAnnotation(RequestAnnotation.class); //针对特定注解可做处理
		Object res = pjp.proceed();
		// 获取请求参数
		Object[] pars = pjp.getArgs();
		// 获取请求参数名称
		String[] parNames = methodSignature.getParameterNames();
		JSONObject json = new JSONObject();
		for (int i = 0; i < parNames.length; i++) {
			json.put(parNames[i], pars[i]);
		}
		_LOGGER.info("{}:{}", methodName, json.toString());
		_LOGGER.info(method.getName() + " - " + (System.currentTimeMillis() - start.get()));
		start.remove();
		return res;
	}

}
