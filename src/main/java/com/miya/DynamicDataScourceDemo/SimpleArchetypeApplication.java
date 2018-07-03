package com.miya.DynamicDataScourceDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
// @SpringBootApplication
@EnableTransactionManagement(order = 2) // 设置事务执行顺序(需要在切换数据源之后，否则只走默认库)
public class SimpleArchetypeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleArchetypeApplication.class, args);
	}
}
