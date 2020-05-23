package com.springmvc.configuration;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
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
@PropertySource(value = {"classpath:db.properties"})
@EnableTransactionManagement
//coponentScan quet cac class co annotation tao bean

public class SpringConfiguration extends WebMvcConfigurerAdapter{
	// file nayf = file applicationcontextConfig+ webmvcConfig
	//
	@Autowired
	Environment environment;
	@Bean 
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer () {
		//de doc cac file properties
		return new PropertySourcesPlaceholderConfigurer();
	}
	//
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
		dataSource.setDriverClassName(environment.getProperty("driver"));
		dataSource.setUrl( environment.getProperty("url"));
		dataSource.setUsername( environment.getProperty("usernam"));// neu de username nó sẽ mặc định là giá trị= TranHuy. tên máy
		dataSource.setPassword ( environment.getProperty("password"));
		return dataSource;
	}
	@Bean
	public LocalSessionFactoryBean sessionFactoryBean() {
		LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
		bean.setDataSource(dataSource());
		bean.setPackagesToScan("com.springmvc.entity");// package: ddeer chua class de dung mapping voi cac bang trogn CSDL
		
		
		Properties hibernateProperties = new Properties();
		// tao cau lenh sql chuan voi tung he quan tri CSDL
		hibernateProperties.put("hibernate.dialect",environment.getProperty("hibernate.dialect")); // value= hibernate.dialect for mysql
		hibernateProperties.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));// ddee hien thi cau lenh sql tren console
		bean.setHibernateProperties(hibernateProperties);
		return bean;
	}
	@Bean(name="transactionManager")
	@Autowired
	// autuwrite dde tu dong gan bean vao bien sesstionFactory
	public HibernateTransactionManager hibernateTransactionManager(SessionFactory sessionFactory){
		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
		hibernateTransactionManager.setSessionFactory(sessionFactory);
		return hibernateTransactionManager;
		
	}
	
//	@Bean
//	public JdbcTemplate jdbcTemplate() {
//		return new JdbcTemplate(dataSource());
//	}
//	@Bean(name="transactionmanager")
//	// vi khi su dung ta su dung nhieu transaction nen them name ddeex quanr lis
//	public DataSourceTransactionManager dataSourceTransactionManager() {
//		return new DataSourceTransactionManager(dataSource());
//	}
	
}
