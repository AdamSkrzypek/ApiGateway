package org.example.apigateway.routing;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.rewritePath;
import static org.springframework.cloud.gateway.server.mvc.filter.LoadBalancerFilterFunctions.lb;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
import static org.springframework.cloud.gateway.server.mvc.predicate.GatewayRequestPredicates.method;
import static org.springframework.cloud.gateway.server.mvc.predicate.GatewayRequestPredicates.path;

@Configuration
@EnableConfigurationProperties(ConfigurationApiProperties.class)
public class RoutingConfig {

    @Bean
    public RouterFunction<ServerResponse> routeLocator(ConfigurationApiProperties properties) {
        String serviceId = properties.getBaseUrl().getHost();

        RouterFunction<ServerResponse> routes = properties.getEndpoints().entrySet().stream()
                .map(entry -> {
                    String name = entry.getKey();
                    ConfigurationApiProperties.Endpoint endpoint = entry.getValue();
                    return route(name)
                            .route(path(endpoint.getPath()).and(method(endpoint.getMethod())), http())
                            .filter(lb(serviceId))
                            .build();
                })
                .reduce(RouterFunction::and)
                .orElseThrow();

        RouterFunction<ServerResponse> v2Passthrough = route("order-service-v2-passthrough")
                .route(path("/v2/**"), http())
                .filter(rewritePath("/v2/(?<segment>.*)", "/v1/${segment}"))
                .filter(lb(serviceId))
                .build();

        return routes.and(v2Passthrough);
    }

}
