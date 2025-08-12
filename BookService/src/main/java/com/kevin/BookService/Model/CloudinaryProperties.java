package com.kevin.BookService.Model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "cloudinary.config")
public class CloudinaryProperties {
    private String cloudName;
    private  String clientID;
    private  String clientSecret;
}
