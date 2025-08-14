package caoh29.OMS.api_gateway.routes;

import org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

@Configuration
public class Routes {
    @Bean
    public RouterFunction<ServerResponse> orderRoutes() {
        return GatewayRouterFunctions.route("order_service")
                .route(RequestPredicates.path("/api/order/**"), HandlerFunctions.http("http://order-service:3000"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker(
                        "order_service_circuit_breaker",
                        URI.create("forward:/fallback")
                        ))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> productCatalogRoutes() {
        return GatewayRouterFunctions.route("product_catalog_service")
                .route(RequestPredicates.path("/api/product-catalog/**"), HandlerFunctions.http("http://product-catalog-service:3001"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker(
                        "product_catalog_service_circuit_breaker",
                        URI.create("forward:/fallback")
                ))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> inventoryRoutes() {
        return GatewayRouterFunctions.route("inventory_service")
                .route(RequestPredicates.path("/api/inventory/**"), HandlerFunctions.http("http://inventory-service:3002"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker(
                        "inventory_service_circuit_breaker",
                        URI.create("forward:/fallback")
                ))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> fallbackRoute() {
        return GatewayRouterFunctions.route("fallback_service")
                .GET("/fallback", request -> ServerResponse
                        .status(HttpStatus.SERVICE_UNAVAILABLE)
                        .body("Service is currently unavailable. Please try again later.")
                )
                .build();
    }
}
