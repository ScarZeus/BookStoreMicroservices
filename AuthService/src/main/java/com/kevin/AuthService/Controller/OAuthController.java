package com.kevin.AuthService.Controller;

import com.kevin.AuthService.Model.UserModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/OAuth")
public class OAuthController {

    @PostMapping("/googleAuth")
    public ResponseEntity<?> authenticateUsingGoogle(@AuthenticationPrincipal UserModel user){
        return null;
    }

}
