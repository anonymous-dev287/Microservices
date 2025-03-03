
package com.finflow.transactionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.finflow.commonlib.security", "com.finflow.transactionservice"})
public class TransactionserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TransactionserviceApplication.class, args);
    }
}
