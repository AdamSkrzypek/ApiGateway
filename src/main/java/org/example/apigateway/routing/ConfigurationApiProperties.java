package org.example.apigateway.routing;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpMethod;

@ConfigurationProperties(prefix = "order-service")
@Getter
@Setter
public class ConfigurationApiProperties {

    private URI baseUrl;

    private Map<String, Endpoint> endpoints = new LinkedHashMap<>();

    @Getter
    @Setter
    public static class Endpoint {

        private String path;

        private HttpMethod method;

    }

}