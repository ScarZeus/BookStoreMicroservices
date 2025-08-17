package com.kevin.AuthService.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kevin.AuthService.Model.AuthProvider;
import com.kevin.AuthService.Model.AuthResponseModel;
import com.kevin.AuthService.Model.EnumModel.ResponseStatus;
import com.kevin.AuthService.Model.UserModel;
import com.kevin.AuthService.Services.ApiServices.UserServices;
import com.kevin.AuthService.Services.SecurityServices.JwtService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class OAuthCustomHandler implements AuthenticationSuccessHandler {

    private final JwtService jwtService;
    private final UserServices userServices;
    private final ObjectMapper objectMapper; // To convert Java object to JSON

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        OAuth2User authUser = (OAuth2User) authentication.getPrincipal();

        String name = authUser.getAttribute("name");
        String email = authUser.getAttribute("email");

        if (email == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Email not found from OAuth2 provider");
            return;
        }

        UserModel user = new UserModel();
        user.setUserName(name);
        user.setEmailAddress(email);
        user.setUserAddress(null);
        user.setPassword(null);
        user.setAuthProvider(AuthProvider.GOOGLE);

        if (userServices.getUserByEmailAddress(user.getEmailAddress()).getBody()!=null &&
                userServices.getUserByEmailAddress(user.getEmailAddress()).getStatusCode()== HttpStatusCode.valueOf(400)) {
            userServices.addNewUser(user).getBody();
        }


        String jwtToken = jwtService.generateJwtTokenWithUserDetails(user);


        AuthResponseModel responseModel = new AuthResponseModel();
        responseModel.setResponseStatus(ResponseStatus.SUCCESS);
        responseModel.setJwtToken(jwtToken);

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);

        objectMapper.writeValue(response.getOutputStream(), responseModel);
    }
}


