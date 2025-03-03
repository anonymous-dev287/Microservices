
package com.finflow.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.finflow.auth", "com.finflow.commonlib.security"})
//@ComponentScan(basePackages = { "com.finflow.commonlib", "com.finflow.auth"})
public class AuthserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthserviceApplication.class, args);
    }
}
