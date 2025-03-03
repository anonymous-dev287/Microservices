package com.finflow.apigateway.config;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // Auth Service
                .route("auth-service", r -> r.path("/api/auth/**")
                        .uri("lb://auth-service"))  // ✅ Use Eureka Load Balancer

                // Account Service
                .route("account-service", r -> r.path("/api/accounts/**")
                        .uri("lb://account-service"))  // ✅ No hardcoded ports

                // Transaction Service
                .route("transaction-service", r -> r.path("/api/transactions/**")
                        .uri("lb://transaction-service"))

                // Loan Service
                .route("loan-service", r -> r.path("/api/loans/**")
                        .uri("lb://loan-service"))

                // Notification Service
                .route("notification-service", r -> r.path("/api/notifications/**")
                        .uri("lb://notification-service"))

                .build();
    }
}
