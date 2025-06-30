package com.kevin.AuthService.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/OAuth")
public class OAuthController {

    @PostMapping("/googleAuth")
    public ResponseEntity<?> authenticateUsingGoogle(){
        return null;
    }

}
