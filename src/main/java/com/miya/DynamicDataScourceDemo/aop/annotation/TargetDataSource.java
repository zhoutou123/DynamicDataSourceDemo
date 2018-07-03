package com.miya.DynamicDataScourceDemo.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.miya.DynamicDataScourceDemo.utils.enums.EnumDataSourceName;

/**
 * @description 多数据源切换用注解
 * @date 2018年4月8日 上午10:25:37
 *
 * @author zhoutuo
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TargetDataSource {

	EnumDataSourceName value() default EnumDataSourceName.DS1;
}
