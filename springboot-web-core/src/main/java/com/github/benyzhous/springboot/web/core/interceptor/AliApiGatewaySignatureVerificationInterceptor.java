
package com.github.benyzhous.springboot.web.core.interceptor;

import java.io.BufferedReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * ProjectName: springboot-web-core <br/>
 * ClassName: AliApiGatewaySignatureVerificationInterceptor <br/>
 * Date: 2017年3月6日 上午1:34:25 <br/>
 * 
 * @author benyzhous@gmail.com
 */
public class AliApiGatewaySignatureVerificationInterceptor implements HandlerInterceptor {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 在请求处理之前进行调用（Controller方法调用之前）
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println(">>>AliApiGatewaySignatureVerificationInterceptor>>>>>>>在请求处理之前进行调用（Controller方法调用之前）");
		try {
			if (handler instanceof HandlerMethod) {
				HandlerMethod handlerMethod = (HandlerMethod) handler;
				String uri = request.getRequestURI();
				String method = request.getMethod();
				System.out.println("请求方式为=" + method);

				if ("POST".equals(method)) {
					StringBuffer jb = new StringBuffer();
					BufferedReader reader = request.getReader();
					String line = "";
					while ((line = reader.readLine()) != null)
						jb.append(line);
					String jbstring = jb.toString();
					System.err.println();
				} else {
					Map params = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
				}
			}
		} catch (Exception e) {
			logger.error("用户操作日志记录异常", e);
		}

		String uri = request.getRequestURI();
		String httpMethod = request.getMethod();

		Map<String, String> headers = new HashMap<String, String>();
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			logger.info("header : {} = {}", headerName, request.getHeader(headerName));
			headers.put(headerName, request.getHeader(headerName));
		}

		Map<String, Object> paramsMap = new HashMap<String, Object>();

		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String parameterName = parameterNames.nextElement();
			logger.info("parameter : {} = {}", parameterName, request.getParameter(parameterName));
			headers.put(parameterName, request.getHeader(parameterName));
		}

		// Sign.serviceSign(uri, httpMethod, headers, paramsMap,
		// inputStreamBytes);

		return true;// 只有返回true才会继续向下执行，返回false取消当前请求
	}

	/**
	 * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		System.out.println(">>>AliApiGatewaySignatureVerificationInterceptor>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");
	}

	/**
	 * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		System.out.println(">>>AliApiGatewaySignatureVerificationInterceptor>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");

	}

}
