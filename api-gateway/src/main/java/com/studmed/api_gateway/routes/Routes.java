package com.studmed.api_gateway.routes;

import com.studmed.api_gateway.fallback.handler.LoadBalancerHandler;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class Routes {

    private final LoadBalancerHandler loadBalancerHandler;
    public Routes(LoadBalancerHandler loadBalancerHandler) {
        this.loadBalancerHandler = loadBalancerHandler;
    }

    private RouterFunction<ServerResponse> loadBalancedTravelBoxRoute(
            String routeId,
            String path,
            String fallbackUrl
    ) {
        String basePath = "/api/v1/";
        return GatewayRouterFunctions.route(routeId)
                .route(RequestPredicates.path(basePath + path),
                        request -> loadBalancerHandler.handler( routeId, fallbackUrl, request))
                .build();
    }

    private RouterFunction<ServerResponse> generalTravelBoxRoute(
            String routeId, String path, String url
    ) {
        String basePath = "/api/v1/";
        return GatewayRouterFunctions.route(routeId)
                .route(RequestPredicates.path(basePath + path)
                        , HandlerFunctions.http(url))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> evaluationServiceRoute() {
        return loadBalancedTravelBoxRoute(
                "evaluation",
                "evaluations",
                "/evaluationFallback"
        );
    }

    @Bean
    public RouterFunction<ServerResponse> notificationServiceRoute() {
        return loadBalancedTravelBoxRoute(
                "notification",
                "notifications",
                "/notificationFallback"
        );
    }

    @Bean
    public RouterFunction<ServerResponse> userServiceRoute() {
        return loadBalancedTravelBoxRoute(
                "user",
                "users",
                "/userFallback"
        );
    }

    @Bean
    public RouterFunction<ServerResponse> attendanceServiceRoute() {
        return loadBalancedTravelBoxRoute(
                "attendance",
                "attendances",
                "/attendanceFallback"
        );
    }

    @Bean
    public RouterFunction<ServerResponse> soporteServiceRoute() {
        return loadBalancedTravelBoxRoute(
                "soporte",
                "soportes",
                "/soporteFallback"
        );
    }
}
