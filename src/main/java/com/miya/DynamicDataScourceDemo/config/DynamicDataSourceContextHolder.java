package com.miya.DynamicDataScourceDemo.config;

import com.miya.DynamicDataScourceDemo.utils.enums.EnumDataSourceName;

/**
 * @description 多数据源配置
 * @date 2018年4月8日 上午9:50:28
 *
 * @author zhoutuo
 */
public class DynamicDataSourceContextHolder {

	/*
	 * 当使用ThreadLocal维护变量时，ThreadLocal为每个使用该变量的线程提供独立的变量副本， 所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
	 */
	private static final ThreadLocal<EnumDataSourceName> contextHolder = new ThreadLocal<EnumDataSourceName>();

	/**
	 * 使用setDataSourceType设置当前的
	 * 
	 * @param dataSourceType
	 */
	public static void setDataSourceType(EnumDataSourceName dataSourceType) {
		contextHolder.set(dataSourceType);
	}

	public static EnumDataSourceName getDataSourceType() {
		return contextHolder.get();
	}

	public static void clearDataSourceType() {
		contextHolder.remove();
	}

}
