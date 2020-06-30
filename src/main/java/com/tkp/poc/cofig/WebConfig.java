package com.tkp.poc.cofig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.tkp.poc.filter.UserInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Bean
	public UserInterceptor userInterceptor() {
		return new UserInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(userInterceptor());
		WebMvcConfigurer.super.addInterceptors(registry);
	}
	
}
