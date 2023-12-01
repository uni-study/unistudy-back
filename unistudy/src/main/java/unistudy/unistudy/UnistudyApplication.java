package unistudy.unistudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class UnistudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnistudyApplication.class, args);
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedHeaders("*").allowedMethods("GET", "POST", "DELETE", "PATCH", "PUT", "OPTIONS").allowCredentials(true);
//				registry.addMapping("/**").allowedOrigins("*");

			}
		};
	}
}
