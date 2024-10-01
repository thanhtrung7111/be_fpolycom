package configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Áp dụng cho tất cả các endpoint
                .allowedOrigins("http://localhost:5173") // Cho phép nguồn này
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Các phương thức HTTP cho phép
                .allowedHeaders("*"); // Cho phép tất cả các header

    }
}
