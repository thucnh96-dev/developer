package com.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebMvcConfigurerAdapter extends WebMvcConfigurationSupport {

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        registry.addResourceHandler(
                "/css/**",
                "/fonts/**",
                "/images/**",
                "/js/**",
                "/vendor/**",
                "/admin/**"
        )
                .addResourceLocations(
                        "classpath:/static/css/",
                        "classpath:/static/fonts/",
                        "classpath:/static/images/",
                        "classpath:/static/js/",
                        "classpath:/static/admin/"

                );
    }
    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:/home");
    }

}
