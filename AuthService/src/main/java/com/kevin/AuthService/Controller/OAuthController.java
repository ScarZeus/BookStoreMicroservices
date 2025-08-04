package com.kevin.AuthService.Controller;

import com.kevin.AuthService.Services.SecurityServices.OAuthAuthenicationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/OAuth")
@RequiredArgsConstructor
public class OAuthController {


    private final OAuthAuthenicationService authAuthenicationService;
    @GetMapping("/googleAuth")
    public ResponseEntity<?> authenticateUsingGoogle(
            @AuthenticationPrincipal OAuth2AuthenticationToken userPrincipal) {
        authAuthenicationService.validateUser(userPrincipal.getPrincipal().getAttributes());
        return ResponseEntity.ok(Map.of("message", "OAuth login successful"));
    }

    @GetMapping("/success")
    public ResponseEntity<?> authenticateUsingGoogleSucess(
            @AuthenticationPrincipal OAuth2AuthenticationToken userPrincipal) {
        authAuthenicationService.validateUser(userPrincipal.getPrincipal().getAttributes());
        return ResponseEntity.ok(Map.of("message", "OAuth login successful"));
    }



    @GetMapping("/failure")
    public ResponseEntity<Map<String,Object>> handleFailure(HttpServletRequest request) {
        String errorMessage = (String) request.getSession().getAttribute("error.message");

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(Map.of(
                        "error", "OAuth login failed",
                        "message", errorMessage != null ? errorMessage : "Unknown error"
                ));
    }

}
