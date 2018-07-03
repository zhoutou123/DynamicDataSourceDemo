package com.miya.DynamicDataScourceDemo.http;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpHeaders;
import okio.Buffer;
import okio.BufferedSource;

/**
 * @description 网络请求的日志拦截器
 * @date 2018年5月15日 上午10:57:11
 *
 * @author zhoutuo
 */
@Component
public class LoggingInterceptor implements Interceptor {

	private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

	private static final Charset UTF8 = Charset.forName("UTF-8");

	public enum Level {
		/** No logs. */
		NONE,
		/**
		 * Logs request and response lines.
		 *
		 * <p>
		 * Example:
		 * 
		 * <pre>
		 * {@code
		 * --> POST /greeting http/1.1 (3-byte body)
		 *
		 * <-- 200 OK (22ms, 6-byte body)
		 * }
		 * </pre>
		 */
		BASIC,
		/**
		 * Logs request and response lines and their respective headers.
		 *
		 * <p>
		 * Example:
		 * 
		 * <pre>
		 * {@code
		 * --> POST /greeting http/1.1
		 * Host: example.com
		 * Content-Type: plain/text
		 * Content-Length: 3
		 * --> END POST
		 *
		 * <-- 200 OK (22ms)
		 * Content-Type: plain/text
		 * Content-Length: 6
		 * <-- END HTTP
		 * }
		 * </pre>
		 */
		HEADERS,
		/**
		 * Logs request and response lines and their respective headers and bodies (if present).
		 *
		 * <p>
		 * Example:
		 * 
		 * <pre>
		 * {@code
		 * --> POST /greeting http/1.1
		 * Host: example.com
		 * Content-Type: plain/text
		 * Content-Length: 3
		 *
		 * Hi?
		 * --> END POST
		 *
		 * <-- 200 OK (22ms)
		 * Content-Type: plain/text
		 * Content-Length: 6
		 *
		 * Hello!
		 * <-- END HTTP
		 * }
		 * </pre>
		 */
		BODY
	}

	private volatile Level level = Level.NONE;

	public Level getLevel() {
		return level;
	}

	@Override
	public Response intercept(Chain chain) throws IOException {
		Level level = this.level;

		Request request = chain.request();
		if (level == Level.NONE) {
			return chain.proceed(request);
		}

		boolean logBody = level == Level.BODY;
		boolean logHeaders = logBody || level == Level.HEADERS;

		RequestBody requestBody = request.body();
		boolean hasRequestBody = requestBody != null;

		Connection connection = chain.connection();
		Protocol protocol = connection != null ? connection.protocol() : Protocol.HTTP_1_1;
		String requestStartMessage = "--> " + request.method() + ' ' + request.url() + ' ' + protocol;
		if (!logHeaders && hasRequestBody) {
			requestStartMessage += " (" + requestBody.contentLength() + "-byte body)";
		}
		logger.info(requestStartMessage);

		if (logHeaders) {
			if (hasRequestBody) {
				// Request body headers are only present when installed as a network interceptor. Force
				// them to be included (when available) so there values are known.
				if (requestBody.contentType() != null) {
					logger.info("Content-Type: " + requestBody.contentType());
				}
				if (requestBody.contentLength() != -1) {
					logger.info("Content-Length: " + requestBody.contentLength());
				}
			}

			Headers headers = request.headers();
			for (int i = 0, count = headers.size(); i < count; i++) {
				String name = headers.name(i);
				// Skip headers from the request body as they are explicitly logged above.
				if (!"Content-Type".equalsIgnoreCase(name) && !"Content-Length".equalsIgnoreCase(name)) {
					logger.info(name + ": " + headers.value(i));
				}
			}

			if (!logBody || !hasRequestBody) {
				logger.info("--> END " + request.method());
			} else if (bodyEncoded(request.headers())) {
				logger.info("--> END " + request.method() + " (encoded body omitted)");
			} else {
				Buffer buffer = new Buffer();
				requestBody.writeTo(buffer);

				Charset charset = UTF8;
				MediaType contentType = requestBody.contentType();
				if (contentType != null) {
					charset = contentType.charset(UTF8);
				}

				logger.info("");
				if (isPlaintext(buffer)) {
					logger.info(buffer.readString(charset));
					logger.info("--> END " + request.method() + " (" + requestBody.contentLength() + "-byte body)");
				} else {
					logger.info("--> END " + request.method() + " (binary " + requestBody.contentLength() + "-byte body omitted)");
				}
			}
		}

		long startNs = System.nanoTime();
		Response response;
		try {
			response = chain.proceed(request);
		} catch (Exception e) {
			logger.info("<-- HTTP FAILED: " + e);
			throw e;
		}
		long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);

		ResponseBody responseBody = response.body();
		long contentLength = responseBody.contentLength();
		String bodySize = contentLength != -1 ? contentLength + "-byte" : "unknown-length";
		logger.info("<-- " + response.code() + ' ' + response.message() + ' ' + response.request().url() + " (" + tookMs + "ms"
				+ (!logHeaders ? ", " + bodySize + " body" : "") + ')');

		if (logHeaders) {
			Headers headers = response.headers();
			for (int i = 0, count = headers.size(); i < count; i++) {
				logger.info(headers.name(i) + ": " + headers.value(i));
			}

			if (!logBody || !HttpHeaders.hasBody(response)) {
				logger.info("<-- END HTTP");
			} else if (bodyEncoded(response.headers())) {
				logger.info("<-- END HTTP (encoded body omitted)");
			} else {
				BufferedSource source = responseBody.source();
				source.request(Long.MAX_VALUE); // Buffer the entire body.
				Buffer buffer = source.buffer();

				Charset charset = UTF8;
				MediaType contentType = responseBody.contentType();
				if (contentType != null) {
					charset = contentType.charset(UTF8);
				}

				if (!isPlaintext(buffer)) {
					logger.info("");
					logger.info("<-- END HTTP (binary " + buffer.size() + "-byte body omitted)");
					return response;
				}

				if (contentLength != 0) {
					logger.info("");
					logger.info(buffer.clone().readString(charset));
				}

				logger.info("<-- END HTTP (" + buffer.size() + "-byte body)");
			}
		}

		return response;
	}

	/**
	 * Returns true if the body in question probably contains human readable text. Uses a small sample of code points to
	 * detect unicode control characters commonly used in binary file signatures.
	 */
	static boolean isPlaintext(Buffer buffer) {
		try {
			Buffer prefix = new Buffer();
			long byteCount = buffer.size() < 64 ? buffer.size() : 64;
			buffer.copyTo(prefix, 0, byteCount);
			for (int i = 0; i < 16; i++) {
				if (prefix.exhausted()) {
					break;
				}
				int codePoint = prefix.readUtf8CodePoint();
				if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
					return false;
				}
			}
			return true;
		} catch (EOFException e) {
			return false; // Truncated UTF-8 sequence.
		}
	}

	/** Change the level at which this interceptor logs. */
	public LoggingInterceptor setLevel(Level level) {
		if (level == null)
			throw new NullPointerException("level == null. Use Level.NONE instead.");
		this.level = level;
		return this;
	}

	private boolean bodyEncoded(Headers headers) {
		String contentEncoding = headers.get("Content-Encoding");
		return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
	}

}
