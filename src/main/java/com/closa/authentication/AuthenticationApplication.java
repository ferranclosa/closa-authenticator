package com.closa.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.closa.global.security.*",
        "com.closa.global.status.*",
        "com.closa.authentication.*",
        "com.closa.global.trace.*",
        "com.closa.global.functions"})

@EnableJpaRepositories(basePackages = {
        "com.closa.global.status.dao",
        "com.closa.authentication.dao",
        "com.closa.global.trace.dao"})
@EntityScan(basePackages = {
        "com.closa.global.status.model",
        "com.closa.authentication.model",
        "com.closa.global.trace.model"})
@EnableSwagger2
@EnableEurekaClient
public class AuthenticationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationApplication.class, args);
    }

}
