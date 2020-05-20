package com.springmvc.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	// Static Resource Config
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		// Css resource.
		registry.addResourceHandler("/resource/**").addResourceLocations("/resources/");
		// /resouce/** dduwongf dan tren trinh duyet: ten co the bat ki
		///resouces duong dan tren server

	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
}
