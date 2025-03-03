//
package com.finflow.commonlib;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class CommonlibApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonlibApplication.class, args);
    }
}
