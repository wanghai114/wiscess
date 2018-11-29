package com.wiscess.filter.autoconfig;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * HttpMothod配置
 * 增加Filter，用于判断提交http的方式，只允许GET和POST
 * @author wh
 */
@ConditionalOnWebApplication
public class HttpMethodAutoConfiguration {
	
	@Bean
	public FilterRegistrationBean<HttpMethodFilter> httpMethodFilterRegistration() {
	    FilterRegistrationBean<HttpMethodFilter> registration = new FilterRegistrationBean<HttpMethodFilter>();
	    registration.setFilter(new HttpMethodFilter());
	    registration.addUrlPatterns("/*");
	    registration.setName("httpMethodFilter");
	    registration.setOrder(3);
	    return registration;
	} 

	private final class HttpMethodFilter extends OncePerRequestFilter{
		private String errorPage="/error";
		private Pattern allowedMethods=Pattern.compile("^(HEAD|TRACE|OPTIONS|PUT|DELETE|PATCH)$");
		
		@Override
		protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
				throws ServletException, IOException {
			if(!allowedMethods.matcher(request.getMethod()).matches()){
				//request方法，如果是GET或POST
				filterChain.doFilter(request, response);
				return;
			}
			//不允许访问
			response.sendRedirect(request.getContextPath() + errorPage);	
		}
	}
}
