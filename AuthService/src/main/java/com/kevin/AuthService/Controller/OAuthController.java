package com.kevin.AuthService.Controller;

import com.kevin.AuthService.Services.SecurityServices.OAuthAuthenicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/OAuth")
@RequiredArgsConstructor
public class OAuthController {

    private final OAuthAuthenicationService oAuthAuthenicationService;

    @PostMapping("/googleAuth")
    public ResponseEntity<?> authenticateUsingGoogle(@AuthenticationPrincipal
                                                         OAuth2AuthenticationToken userPrincipal){
        try{
            if(userPrincipal !=null){

            }
        }

    }

}
