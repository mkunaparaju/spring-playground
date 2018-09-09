package com.example.springplayground.Config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "movie")
public class MovieConfig {
    private String host;
    private String apiKey;
    private String i;

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i = i;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
