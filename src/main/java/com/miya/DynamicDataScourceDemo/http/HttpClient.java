package com.miya.DynamicDataScourceDemo.http;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.miya.DynamicDataScourceDemo.http.LoggingInterceptor.Level;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @description http链接
 * @date 2018年5月15日 下午2:58:44
 *
 * @author zhoutuo
 */
@Component
public class HttpClient {

	private static final Logger LOG = LoggerFactory.getLogger(HttpClient.class);

	@Autowired
	private LoggingInterceptor loggingInterceptor;

	public OkHttpClient build() {
		loggingInterceptor.setLevel(Level.BODY);// 日志级别，Body级别打印的信息最全面
		OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(10000L, TimeUnit.MILLISECONDS)
				.readTimeout(10000L, TimeUnit.MILLISECONDS).addInterceptor(loggingInterceptor).build();
		return okHttpClient;
	}

	public JSONObject sendHttp(OkHttpClient okHttpClient, Request request) {
		String res = null;
		try {
			Response response = okHttpClient.newCall(request).execute();
			if (response.isSuccessful()) {
				res = response.body().string();
			} else {
				LOG.error("请求失败:{},url:{}", response.body().string(), request.url().toString());
			}
		} catch (IOException e) {
			LOG.error("请求异常:{},url:{}", e.getMessage(), request.url().toString());
		}
		return JSON.parseObject(res);
	}
}
