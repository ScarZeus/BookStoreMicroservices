package com.kevin.AuthService.Controller;


import com.kevin.AuthService.Model.AuthRequestModel;
import com.kevin.AuthService.Model.AuthResponseModel;
import com.kevin.AuthService.Model.UserModel;
import com.kevin.AuthService.Services.SecurityServices.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/tokenAuth")
@RequiredArgsConstructor
public class TokenAuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    ResponseEntity<AuthResponseModel> loginAuth(@RequestBody AuthRequestModel request){
        try{
            AuthResponseModel response = authenticationService.authenticate(request);
            return ResponseEntity.ok(response);
        }
        catch (Exception e){
            AuthResponseModel response = authenticationService.authenticate(request);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping
    ResponseEntity<AuthResponseModel> signUp(@RequestBody UserModel user){
        try{
            AuthResponseModel response = authenticationService.authenticateNewUser(user);
        }
    }
}
