
package com.github.benyzhous.apigateway.conf;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.github.benyzhous.springboot.web.core.filter.GatewaySignatureVerificationFilter;

/**
 * ProjectName: springboot-apigateway-sample <br/>
 * ClassName: MyWebAppConfigurer <br/>
 * Date: 2017年3月6日 上午2:53:28 <br/>
 * 
 * @author chababa
 * @version
 * @since JDK 1.7
 * @see
 */
@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {

	@Bean
    public FilterRegistrationBean indexFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new GatewaySignatureVerificationFilter());
        registration.addUrlPatterns("/*");
        return registration;
    }

}