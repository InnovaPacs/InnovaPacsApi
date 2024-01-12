package innova.pacs.api;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApiConfiguration implements WebMvcConfigurer{
	String methods[] = {"GET", "POST", "PUT", "DELETE", "PATCH"};
		private String HOST = "*";

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		System.out.println("::::: ApiConfiguration :::::");
        registry.addMapping("/**")
        .allowedOrigins(HOST)
        .allowedMethods(methods);
		
	}
}
