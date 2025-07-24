package com.kevin.AuthService.Model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "security.google")
@Getter
@Setter
public class GoogleAuthPropertiesModel {
    private String clientId;
    private String clientSecret;
}
