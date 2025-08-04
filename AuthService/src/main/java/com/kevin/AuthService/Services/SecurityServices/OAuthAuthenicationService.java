package com.kevin.AuthService.Services.SecurityServices;

import com.kevin.AuthService.Model.AuthResponseModel;
import com.kevin.AuthService.Services.ApiServices.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class OAuthAuthenicationService {

    private final JwtService jwt;
    private final UserServices userService;

    public AuthResponseModel validateUser(Map<String,Object> userCredentials){
        System.out.println(userCredentials.toString());
        return null;
    }


}
