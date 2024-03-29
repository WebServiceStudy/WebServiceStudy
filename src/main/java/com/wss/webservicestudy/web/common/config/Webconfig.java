package com.wss.webservicestudy.web.common.config;

import com.wss.webservicestudy.web.common.constant.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class Webconfig implements WebMvcConfigurer {

    @Value("${image.save.path}")
    private String imageSavePath;

    @Value("${image.resource.path}")
    private String imageResourcePath;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(Constants.FRONT_URL)
                .allowCredentials(true)
                .allowedMethods("POST", "GET");
    };

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(imageResourcePath).addResourceLocations("file:///" + imageSavePath);
    }
}
