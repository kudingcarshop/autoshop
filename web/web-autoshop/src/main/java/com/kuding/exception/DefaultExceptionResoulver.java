package com.kuding.exception;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import com.kuding.commons.http.Result;

public class DefaultExceptionResoulver extends ExceptionHandlerExceptionResolver {

	private Logger logger = Logger.getLogger(DefaultExceptionResoulver.class);
	
	private static final boolean IS_ERROR_IN_REQUEST_PAGE = false;

	@Override
	protected ModelAndView doResolveHandlerMethodException(HttpServletRequest req, HttpServletResponse resp,
			HandlerMethod handlerMethod, Exception ex) {
		if (handlerMethod == null) {
			return null;
		}
		Method method = handlerMethod.getMethod();
		if (method == null) {
			return null;
		}

		//发生异常时跳转的页面，若未指定则生成默认
		ModelAndView mv = super.doResolveHandlerMethodException(req, resp, handlerMethod, ex);
		logger.info("system error:" + ex.getMessage());
		if (mv == null) {
			logger.info("the default ModelAndView is null");
			mv = new ModelAndView();
			if(!IS_ERROR_IN_REQUEST_PAGE) {
				mv.setViewName("pub/errors/errors");
			}
		}
		
		//处理json返回view的异常处理
		ResponseBody responseBodyAnn = AnnotationUtils.findAnnotation(method, ResponseBody.class);
		if (responseBodyAnn != null) {
			logger.info("business error");
			try {
				Result result = new Result();
				result.setFlag(Result.FLAG_FAIL);
				result.setMessage(ex.getMessage());
				handleResponseError(result, req, resp);
				return mv;
			} catch (Exception e) {
				return null;
			}
		}
		//将异常信息放入errors中,此时会返回请求的默认页面
		mv.getModelMap().addAttribute("errors", ex);
		return mv;
	}


	@SuppressWarnings({ "unchecked", "rawtypes", "resource" })
	private ModelAndView handleResponseError(Result result, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpInputMessage inputMessage = new ServletServerHttpRequest(request);
		List<MediaType> acceptedMediaTypes = inputMessage.getHeaders().getAccept();
		if (acceptedMediaTypes.isEmpty()) {
			acceptedMediaTypes = Collections.singletonList(MediaType.ALL);
		}
		MediaType.sortByQualityValue(acceptedMediaTypes);
		HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
		Class<?> returnValueType = result.getClass();
		List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();
		if (messageConverters != null) {
			for (MediaType acceptedMediaType : acceptedMediaTypes) {
				for (HttpMessageConverter messageConverter : messageConverters) {
					if (messageConverter.canWrite(returnValueType, acceptedMediaType)) {
						messageConverter.write(result, acceptedMediaType, outputMessage);
						return new ModelAndView();
					}
				}
			}
		}
		return null;
	}
	
}
