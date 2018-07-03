package com.miya.DynamicDataScourceDemo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.miya.DynamicDataScourceDemo.aop.annotation.TargetDataSource;
import com.miya.DynamicDataScourceDemo.config.DynamicDataSourceContextHolder;
import com.miya.DynamicDataScourceDemo.utils.enums.EnumDataSourceName;

/**
 * @description 动态切换数据源
 * @date 2018年4月8日 上午9:52:50
 *
 * @author zhoutuo
 */
@Aspect
@Order(1) // 保证该AOP在@Transactional之前执行
@Component
public class DynamicDataSourceAspect {

	private static Logger log = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

	/*
	 * @Before("@annotation(ds)") 的意思是：
	 *
	 * @Before：在方法执行之前进行执行：
	 * 
	 * @annotation(targetDataSource)： 会拦截注解targetDataSource的方法，否则不拦截;
	 */
	@Before("@annotation(targetDataSource)")
	public void changeDataSource(JoinPoint point, TargetDataSource targetDataSource) throws Throwable {
		// 获取当前的指定的数据源;
		EnumDataSourceName dsId = targetDataSource.value();
		log.info("Change DataSource : {}", dsId);
		DynamicDataSourceContextHolder.setDataSourceType(dsId);
	}

	@After("@annotation(targetDataSource)")
	public void restoreDataSource(JoinPoint point, TargetDataSource targetDataSource) {
		log.info("Revert DataSource : {} > {}", targetDataSource.value(), point.getSignature());
		// 方法执行完毕之后，销毁当前数据源信息，进行垃圾回收。
		DynamicDataSourceContextHolder.clearDataSourceType();
	}

}