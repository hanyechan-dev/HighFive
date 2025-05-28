package com.jobPrize.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${file.upload-jobposting-image-dir}")
    private String jobpostingPath;

    @Value("${file.upload-advertisement-image-dir}")
    private String advertisementPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/jobposting/**")
                .addResourceLocations("file:///" + jobpostingPath);
        
        registry.addResourceHandler("/images/advertisement/**")
                .addResourceLocations("file:///" + advertisementPath);
    }
}