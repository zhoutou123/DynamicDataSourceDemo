package com.miya.DynamicDataScourceDemo;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @description tomcat启动类
 * @date 2018年3月9日 上午11:41:03
 * @author lmx
 */
public class TomcatStartInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SimpleArchetypeApplication.class);
	}
}
