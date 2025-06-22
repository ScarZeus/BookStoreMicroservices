package com.ScarZeus.recipe_backend.service.authServices;


import com.ScarZeus.recipe_backend.model.UserModel;
import com.ScarZeus.recipe_backend.model.authModels.AuthRequestModel;
import com.ScarZeus.recipe_backend.model.authModels.AuthResponseModel;
import com.ScarZeus.recipe_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {


    private final UserService userService;
    private final JWTServices jwtServices;
    private final AuthenticationManager authManager;

    public AuthResponseModel authenticate(AuthRequestModel request){
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        UserModel user =  userService.getUser(request.getEmail());
        String jwtToken = jwtServices.getJwtToken(user);
        return AuthResponseModel
                .builder()
                .jwtToken(jwtToken)
                .build();
    }
}
