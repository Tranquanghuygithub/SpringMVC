package com.springmvc.configuration;
import javax.sql.DataSource;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.springmvc")
//coponentScan quet cac class co annotation tao bean

public class SpringConfiguration extends WebMvcConfigurerAdapter{
	// file nayf = file applicationcontextConfig+ webmvcConfig
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
		
	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resouce/**").addResourceLocations("/resouces/");
		// /resouce/** dduwongf dan tren trinh duyet: ten co the bat ki
		///resouces duong dan tren server
	}
	//tao bean
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource bundleMessageSource= new ReloadableResourceBundleMessageSource();
		bundleMessageSource.setBasename("classpath:messages");// classpath tror dden cai src/main/resource
		bundleMessageSource.setDefaultEncoding("utf-8");// ho tro tieng viwr
		return bundleMessageSource;
	}
//	@Bean // tao bean Uservalidator tu class user validator
//	public UserValidator userValidator() {
//		return new UserValidator();
//	}
	@Bean(name="multipartResolver")
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver commonsMultipartResolver= new CommonsMultipartResolver();
		commonsMultipartResolver.setMaxUploadSize(-1);// -1 ko gioi han/ con ko ghi dung luong dang byte
		return commonsMultipartResolver;
	}
	@Bean
	public  DataSource dataSource() {
		DriverManagerDataSource dataSource= new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/banhang");
		dataSource.setUsername("root");
		dataSource.setPassword("huy123456789");
		return dataSource;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}
}
