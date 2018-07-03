package com.miya.DynamicDataScourceDemo.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.miya.DynamicDataScourceDemo.utils.enums.EnumDataSourceName;

/**
 * @description 多数据源配置类
 * @date 2018年4月8日 下午3:01:28
 *
 * @author zhoutuo
 */
@Configuration
public class DataSourceConfig {

	private static Logger log = LoggerFactory.getLogger(DataSourceConfig.class);

	@ConfigurationProperties(prefix = "spring.datasource")
	@Bean(name = "ds1")
	public DataSource dataSource1() {
		log.info("数据源1生成");
		return DataSourceBuilder.create().build();
	}

	@ConfigurationProperties(prefix = "spring.datasource.ds2")
	@Bean(name = "ds2")
	public DataSource dataSource2() {
		log.info("数据源2生成");
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "dynamicDataSource")
	@Primary // 优先使用，多数据源
	public DataSource dataSource() {
		DynamicDataSource dynamicDataSource = new DynamicDataSource();
		DataSource master = dataSource1();
		DataSource lida = dataSource2();
		// 设置默认数据源
		dynamicDataSource.setDefaultTargetDataSource(master);
		// 配置多数据源
		Map<Object, Object> map = new HashMap<>();
		map.put(EnumDataSourceName.DS1, master); // key需要跟ThreadLocal中的值对应
		map.put(EnumDataSourceName.DS2, lida);
		dynamicDataSource.setTargetDataSources(map);
		log.info("动态数据源生成成功!");
		return dynamicDataSource;
	}

}
