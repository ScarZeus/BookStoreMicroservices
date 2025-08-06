package com.kevin.AuthService.Config;


import com.kevin.AuthService.Filterer.JwtFilterer;
import com.kevin.AuthService.Model.GoogleAuthPropertiesModel;
import com.kevin.AuthService.Services.SecurityServices.AppUserService;
import com.kevin.AuthService.handler.OAuthCustomHandler;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final OAuthCustomHandler successHandler;
    private final GoogleAuthPropertiesModel googleProps;
    private final JwtFilterer jwtAuth;
    private final AppUserService customUserDetailService;
    private final BCryptPasswordEncoder passwordEncoder;

    private List<String> clients = Arrays.asList("google");

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(customizer-> customizer.disable())
                .authorizeHttpRequests(auth->
                        auth.requestMatchers( "/api/v1/tokenAuth/login",
                                        "/api/v1/tokenAuth/signup",
                                        "/api/v1/OAuth/**"

                                )
                                .permitAll()
                                .anyRequest()
                                .authenticated())
                .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuth, UsernamePasswordAuthenticationFilter.class)
                .oauth2Login(oauth2 -> oauth2
                .successHandler(successHandler)
        )
                .build();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository(){
        List<ClientRegistration> registrations = clients.stream()
                .map(client -> getClientRegistration(client))
                .filter(registration -> registration!=null)
                .collect(Collectors.toList());
        return new InMemoryClientRegistrationRepository(registrations);
    }

    @Bean
    public ClientRegistration getClientRegistration(String client){
            if("google".equals(client)){
                return CommonOAuth2Provider
                        .GOOGLE
                        .getBuilder(client)
                        .clientId(googleProps.getClientId())
                        .clientSecret(googleProps.getClientSecret())
                        .build();
            }
        return null;
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return passwordEncoder;
    }


}
