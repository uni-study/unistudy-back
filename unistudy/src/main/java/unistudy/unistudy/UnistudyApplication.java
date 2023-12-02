package unistudy.unistudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import unistudy.unistudy.config.WebConfig;

@SpringBootApplication
@Import(WebConfig.class)
public class UnistudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnistudyApplication.class, args);
	}
//	@Bean
//	@SuppressWarnings("unused")
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/**").allowedOrigins("*").allowedHeaders("*").allowedMethods("GET", "POST", "DELETE", "PATCH", "PUT", "OPTIONS").allowCredentials(true);
////				registry.addMapping("/**").allowedOrigins("*");
//
//			}
//		};
//	}
}
