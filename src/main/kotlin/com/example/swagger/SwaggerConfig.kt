package com.example.swagger

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.google.common.base.Predicates;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
open class SwaggerConfig : WebMvcConfigurer {
	@Bean
	open fun api() =
		Docket(DocumentationType.SWAGGER_2).select()
			.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
			.build();

	@Override
	override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}