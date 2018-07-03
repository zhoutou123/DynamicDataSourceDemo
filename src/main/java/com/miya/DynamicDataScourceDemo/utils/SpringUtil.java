package com.miya.DynamicDataScourceDemo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * @description 获取spring容器bean工具类
 * @date 2018年3月22日 下午5:04:23
 *
 * @author zhoutuo
 */
@Configuration
public class SpringUtil implements ApplicationContextAware {

	private static final Logger _LOG = LoggerFactory.getLogger(SpringUtil.class);

	private static ApplicationContext applicationContext;

	/**
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (SpringUtil.applicationContext == null) {
			SpringUtil.applicationContext = applicationContext;
		}
		_LOG.info("========ApplicationContext配置成功,在普通类可以通过调用SpringUtils.getAppContext()获取applicationContext对象,applicationContext={}========",
				SpringUtil.applicationContext);
	}

	/**
	 * @description 获取applicationContext
	 * @author zhoutuo
	 * @date 2018年3月22日 下午5:56:56
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * @description 通过name获取 Bean.
	 * @author zhoutuo
	 * @date 2018年3月22日 下午5:57:11
	 */
	public static Object getBean(String name) {
		System.out.println(name);
		return getApplicationContext().getBean(name);
	}

	/**
	 * @description 通过class获取Bean.
	 * @author zhoutuo
	 * @date 2018年3月22日 下午5:57:26
	 */
	public static <T> T getBean(Class<T> clazz) {
		return getApplicationContext().getBean(clazz);
	}

	/**
	 * @description 通过name,以及Clazz返回指定的Bean
	 * @author zhoutuo
	 * @date 2018年3月22日 下午5:57:33
	 */
	public static <T> T getBean(String name, Class<T> clazz) {
		return getApplicationContext().getBean(name, clazz);
	}

}
