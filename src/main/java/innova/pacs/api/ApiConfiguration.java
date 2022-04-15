package innova.pacs.api;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class ApiConfiguration implements WebMvcConfigurer{
	String methods[] = {"GET", "POST", "PUT", "DELETE", "PATCH"};
	private String HOST = "http://192.168.3.115:4200";

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		System.out.println("::::: ApiConfiguration :::::");
        registry.addMapping("/**")
        .allowedOrigins(HOST)
        .allowedMethods(methods);
		
	}
}
