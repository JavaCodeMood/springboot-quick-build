
package com.github.benyzhous.springboot.web.core.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.benyzhous.springboot.quick.commons.servlet.Params;
import com.github.benyzhous.springboot.web.core.gateway.sign.backend.Sign;

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
public class GatewaySignatureVerificationFilter implements Filter {
	Logger logger = LoggerFactory.getLogger(GatewaySignatureVerificationFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("init IndexFilter");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		ServletRequest requestWrapper = servletRequest;
		
		String uri = request.getRequestURI();
		String httpMethod = request.getMethod();
		Map<String,String> headers = Params.getHeadersInfo(request);
		Map<String,Object> paramsMap = Params.requestToMapObject(request);
		
		String gatewaySign = headers.get("x-ca-proxy-signature");
		logger.info(" -->> API网关签名：{}", gatewaySign);
		if(StringUtils.isEmpty(gatewaySign)){
			outInvalidSignature(response);
			return;
		}
	        
		// request body bytes
		byte[] inputStreamBytes = new byte[]{};
		
		if(HttpMethod.POST.equalsIgnoreCase(httpMethod)) {
			// 防止流读取一次后就没有了, 所以需要将流继续写出去
			requestWrapper = new BodyReaderHttpServletRequestWrapper(request);
			String body = HttpHelper.getBodyString(requestWrapper);
			inputStreamBytes = body.getBytes();
		}
		
		try {
			String serviceSign = Sign.serviceSign(uri, httpMethod, headers, paramsMap, inputStreamBytes);
			// gateway 签名认证
			if(!gatewaySign.equals(serviceSign)){
				// 后端对 API 网关的签名字符串校验后，如果校验失败，
				// 建议返回 errorcode = 403；errormessage = “InvalidSignature”
				outInvalidSignature(response);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("ApiGateway验证签名错误:{}",e.getMessage());
			outInternalError(response);
			return;
		}
		chain.doFilter(requestWrapper, response);
	}

	@Override
	public void destroy() {

	}
	/**
	 * 服务器错误，按照网关建议返回
	 */
	void outInternalError(HttpServletResponse response) {
		try {
			response.setContentType("application/json");  
			response.getWriter().write("{\"errorcode\":500,\"errormessage\":\"InternalError\"}");
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * 签名无效，按照网关建议返回
	 */
	void outInvalidSignature(HttpServletResponse response) {
		try {
			response.setContentType("application/json");  
			response.getWriter().write("{\"errorcode\":403,\"errormessage\":\"InvalidSignature\"}");  
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
