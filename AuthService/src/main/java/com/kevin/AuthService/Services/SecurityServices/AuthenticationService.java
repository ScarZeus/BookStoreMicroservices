package com.kevin.AuthService.Services.SecurityServices;


import com.kevin.AuthService.Model.AuthRequestModel;
import com.kevin.AuthService.Model.AuthResponseModel;
import com.kevin.AuthService.Model.EnumModel.ResponseStatus;
import com.kevin.AuthService.Model.UserModel;
import com.kevin.AuthService.Services.ApiServices.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserServices userService;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;


    public AuthResponseModel authenticate(AuthRequestModel request) {
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmailAddress(),
                            request.getPassword()
                    )
            );

            UserModel user = userService.getUserByEmailAddress(request.getEmailAddress());
            String jwtToken = jwtService.generateJwtTokenWithUserDetails(user);
            return AuthResponseModel.builder()
                    .responseStatus(ResponseStatus.SUCCESS)
                    .jwtToken(jwtToken)
                    .build();
        }
        catch (Exception e){
            return AuthResponseModel
                    .builder()
                    .responseStatus(ResponseStatus.FAILED)
                    .jwtToken("Not Available")
                    .build();
        }
    }
}

