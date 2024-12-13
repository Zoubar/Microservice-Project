# Spring Cloud Gateway Configuration Guide 

## Overview

This project demonstrates three primary approaches to configuring Spring Cloud Gateway, providing flexibility in routing and service management.

## Configuration Types

### 1. Programmatic RouteLocator Configuration

#### Definition
Programmatically define routes using Java-based `@Bean` configuration, offering maximum customization and complex routing logic.

#### Example
```java
@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("customer_route", r -> r.path("/api/customers/**")
                .uri("http://localhost:9001"))
            .route("inventory_route", r -> r.path("/api/products/**")
                .uri("http://localhost:9002"))
            .build();
    }
}
```

#### Use Cases
- Advanced routing scenarios
- Dynamic route generation
- Complex predicate and filter configurations

### 2. Declarative YAML/Properties Configuration

#### Definition
Configure routes declaratively using `application.yml` or `application.properties`, ideal for simple to moderate routing needs.

#### Example (application.yml)
```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: customer_route
          uri: lb://CUSTOMER-SERVICE
          predicates:
            - Path=/api/customers/**
        - id: inventory_route
          uri: lb://INVENTORY-SERVICE
          predicates:
            - Path=/api/products/**
```

#### Advantages
- Clean, readable configuration
- Easy to modify without code changes
- Support for load balancing
- Quick service mapping

### 3. Spring Cloud Global Configuration

#### Definition
Configure overall Spring Cloud and gateway behaviors, including service discovery and global settings.

#### Example (application.properties)
```properties
# Route Configurations
spring.cloud.gateway.routes[0].id=customer-route
spring.cloud.gateway.routes[0].uri=lb://CUSTOMER-SERVICE
spring.cloud.gateway.routes[0].predicates[0]=Path=/api/customers/**

spring.cloud.gateway.routes[1].id=inventory-route
spring.cloud.gateway.routes[1].uri=lb://INVENTORY-SERVICE
spring.cloud.gateway.routes[1].predicates[0]=Path=/api/products/**

# Additional Global Configurations
spring.cloud.gateway.globalcors.corsConfigurations[/**].allowedOrigins=*
spring.cloud.gateway.globalcors.corsConfigurations[/**].allowedMethods=GET,POST,PUT,DELETE
```

#### Key Features
- Global route configurations
- Cross-Origin Resource Sharing (CORS) settings
- Service discovery integration
- Centralized gateway behavior management

## Best Practices

1. **Choose the Right Approach**
   - Use programmatic configuration for complex, dynamic routing
   - Prefer YAML/properties for straightforward, static routes
   - Leverage global configurations for cross-cutting concerns

2. **Load Balancing**
   - Use `lb://` prefix for service discovery and load balancing
   - Ensure service registry (e.g., Eureka) is properly configured

3. **Security and Filters**
   - Add global or route-specific filters
   - Implement authentication and authorization at the gateway level

##  Dependencies

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-gateway</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>
</dependencies>
```

##  Getting Started

1. Configure service discovery
2. Define routes using preferred method
3. Add necessary filters and predicates
4. Start the gateway service

