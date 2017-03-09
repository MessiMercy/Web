package com.web;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAutoConfiguration
@EnableAsync
public class SpringWebDevelopmentApplication extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(SpringWebDevelopmentApplication.class, args);
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.setPort(8081);
    }
}
