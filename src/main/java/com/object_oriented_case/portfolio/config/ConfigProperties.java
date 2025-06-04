package com.object_oriented_case.portfolio.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigProperties {
    @Value("${server.address}")
    private String serverAddress;

    public String getServerAddress() {
        return serverAddress;
    }
}
