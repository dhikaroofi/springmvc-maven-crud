package com.collega.springmvc.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.XmlViewResolver;

import com.collega.springmvc.dao.*;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.view.XmlViewResolver;


@Configuration
@ComponentScan(basePackages="com.collega.springmvc")
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter{

	@Bean
	public ViewResolver getViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	

	@Bean
	public DataSource getDataSource() {
		   try {
				DriverManagerDataSource dataSource = new DriverManagerDataSource();
				dataSource.setDriverClassName("com.mysql.jdbc.Driver");
				dataSource.setUrl("jdbc:mysql://localhost:3306/collega");
				dataSource.setUsername("root");
				dataSource.setPassword("admin@321");
				return dataSource;
		   } catch (Exception e) {
	            System.err.println("Failed to load JDBC driver.");
	        	return null;
	        }
	
	}
	
	@Bean
	public CustomerDAOInterface getCustomerDAO() {
		return new CustomerDAO(getDataSource());
	}
	
	@Bean
	public KotaDAOInterface getKotaDAO() {
		return new KotaDAO(getDataSource());
	}
	
	
	


	
}
