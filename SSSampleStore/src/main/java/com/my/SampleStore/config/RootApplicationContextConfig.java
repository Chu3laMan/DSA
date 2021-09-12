package com.my.SampleStore.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.my.SampleStore.DAO.ProductRepository;
import com.my.SampleStore.DAO.ProductRepositoryImpl;

@Configuration
@ComponentScan("com.my.SampleStore")
public class RootApplicationContextConfig {
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/sampleStore");
		dataSource.setUsername("root");
		dataSource.setPassword("aze102030.");
		return dataSource;
	}
	
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource());
		return jdbcTemplate;
	}
	
	

	@Bean
	public ProductRepository productRepository() {
		ProductRepositoryImpl productRepository = new ProductRepositoryImpl();
		productRepository.setJdbcTemplate(jdbcTemplate()); //the injection has happened here
		return productRepository;
	}
	
	
	

}
