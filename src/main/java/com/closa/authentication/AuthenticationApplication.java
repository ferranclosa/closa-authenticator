package com.closa.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.closa.global.security.service",
        "com.closa.global.status.util",
        "com.closa.authentication.*",
        "com.closa.global.security.config",
        "com.closa.global.functions",
        "com.closa.global.throwables.handlers",
        "com.closa.global.trace.*"})

@EnableJpaRepositories(basePackages = {
        "com.closa.global.status.dao",
        "com.closa.authentication.dao",
        "com.closa.global.trace.dao"})
@EntityScan(basePackages = {
        "com.closa.global.status.model",
        "com.closa.authentication.model",
        "com.closa.global.trace.model"})
@EnableSwagger2
public class AuthenticationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationApplication.class, args);
    }

}
