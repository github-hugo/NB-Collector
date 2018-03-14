package com.nhb.NBIot.config;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class PropertiesConfig {

	@Bean
	public Properties properties() throws IOException {
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocation(new ClassPathResource("/nbiot.properties"));
		propertiesFactoryBean.afterPropertiesSet();
		return propertiesFactoryBean.getObject();
	}

	@Bean
	public SpringProperty property(Properties properties) {
		SpringProperty property = new SpringProperty();
		property.setProperties(properties);
		return property;
	}
}
