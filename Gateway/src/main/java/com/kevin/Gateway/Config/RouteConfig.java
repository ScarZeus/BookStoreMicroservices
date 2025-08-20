package com.kevin.Gateway.Config;

import org. springframework. web. servlet. function. ServerResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;

@Configuration
public class RouteConfig {

    @Bean
    public RouterFunction<ServerResponse> userRouter(){
        
    }


}
