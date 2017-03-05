
package com.github.benyzhous.springboot.web.core.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.connector.Response;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ProjectName: springboot-web-core <br/>
 * ClassName: demo <br/>
 * Date: 2017年3月6日 上午3:20:50 <br/>
 * 
 * @author chababa
 * @version
 * @since JDK 1.7
 * @see
 */
// @WebFilter(urlPatterns = "/*", filterName =
// "gatewaySignatureVerificationFilter")
public class GatewaySignatureVerificationFilter implements Filter {
	Logger logger = LoggerFactory.getLogger(GatewaySignatureVerificationFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("init IndexFilter");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		if (HttpMethod.POST.equalsIgnoreCase(httpServletRequest.getMethod())) {
			// 防止流读取一次后就没有了, 所以需要将流继续写出去
			ServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(httpServletRequest);
			String body = HttpHelper.getBodyString(requestWrapper);
			if (StringUtils.isBlank(body)) {
				logger.error("非法请求, 没有APP_KEY, APP_SECRET");
				return;
			}

			chain.doFilter(requestWrapper, response);
		} else {
			chain.doFilter(request, response);
		}

	}

	@Override
	public void destroy() {

	}
}
