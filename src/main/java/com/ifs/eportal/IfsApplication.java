package com.ifs.eportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.ifs.eportal.common.Utils;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = "com.ifs.eportal")
public class IfsApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(IfsApplication.class);
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	public static void main(String[] args) {
		String t = System.getenv("PRINT_STACK_TRACE");
		Utils.printStackTrace = t != null && "Y".equals(t);

		t = System.getenv("WRITE_LOG");
		Utils.writeLog = t != null && "Y".equals(t);

		SpringApplication.run(IfsApplication.class, args);
	}
}