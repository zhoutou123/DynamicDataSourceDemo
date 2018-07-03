/**
 */
package com.miya.DynamicDataScourceDemo.advice;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;

import com.miya.utils.Result;
import com.miya.utils.exception.ApplicationException;

@ControllerAdvice
public class ControllerExceptionAdvice {

	protected Logger _LOGGER = LoggerFactory.getLogger(getClass());

	/**
	 * 系统统一抛出异常处理
	 *
	 * @param e 异常
	 * @return
	 */
	@ExceptionHandler(value = { ApplicationException.class })
	@ResponseBody
	public Result applicationExcetion(ApplicationException e, ServletWebRequest request) {
		_LOGGER.info("应用程序已知异常：[{}],请求描述[]", e.getMessage(), getDescript(request));
		return Result.failure(e.getMessage());
	}

	/**
	 * 系统未知异常统一处理
	 *
	 * @param e 异常
	 * @param request 请求
	 * @return
	 */
	@ExceptionHandler(value = { Exception.class })
	@ResponseBody
	public Result exception(Exception e, ServletWebRequest request) {
		e.printStackTrace();
		_LOGGER.info("应用程序未知异常：[{}],请求描述[]", e.getMessage(), getDescript(request));
		return Result.failure(e.getMessage());
	}

	/**
	 * 
	 * 请求缺少参数异常
	 *
	 * @param e 异常
	 * @param request 请求
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(value = MissingServletRequestParameterException.class)
	public Result MissingServletRequestParameterException(MissingServletRequestParameterException e, ServletWebRequest request) {
		_LOGGER.info("请求参数不合法：缺少参数[{}],请求描述[]", e.getParameterName(), getDescript(request));
		return Result.failure("缺少必要参数：" + e.getParameterName());
	}

	/**
	 * 获取request的描述信息
	 *
	 * @return
	 */
	protected String getDescript(ServletWebRequest request) {
		StringBuilder description = new StringBuilder("请求路径及客户端描述：");
		description.append(request.getDescription(true) + System.getProperty("line.separator"));
		description.append("请求参数描述{");
		Iterator<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasNext()) {
			String paramName = (String) parameterNames.next();
			description.append("[").append(paramName).append("=");

			String[] paramValues = request.getParameterValues(paramName);
			for (int i = 0; i < paramValues.length; i++) {
				description.append(paramValues[i]).append(",");
				if (paramValues.length > 1 && (i + 1) < paramValues.length) {
					description.append(",");
				}
			}
			description.append("]");
		}
		description.append("}");
		return description.toString();
	}
}
