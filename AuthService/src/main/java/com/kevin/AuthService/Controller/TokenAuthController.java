package com.kevin.AuthService.Controller;


import com.kevin.AuthService.Model.AuthRequestModel;
import com.kevin.AuthService.Model.AuthResponseModel;
import com.kevin.AuthService.Model.UserModel;
import com.kevin.AuthService.Services.SecurityServices.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController("/api/v1/tokenAuth")
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

    @PostMapping("/signup")
    ResponseEntity<AuthResponseModel> signUp(@RequestBody UserModel user){
        try{
            AuthResponseModel response = authenticationService.authenticateNewUser(user);
            return ResponseEntity.ok(response);
        }
        catch (Exception e){
            AuthResponseModel response = authenticationService.authenticateNewUser(user);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/validateToken")
    ResponseEntity<Map<String,Object>> validateToken(@RequestHeader("Authorization") String authHeader){
        return null;
    }
}
