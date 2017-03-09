package com.web.config;

import com.web.interceptor.LoginInterceptor;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/posts/*","/ip/*");
    }

    @Bean
    public EmbeddedServletContainerCustomizer errorHandle() {
        return container -> container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/404"));
    }

}
