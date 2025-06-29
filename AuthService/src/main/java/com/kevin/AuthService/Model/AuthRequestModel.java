package com.kevin.AuthService.Model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequestModel {
    private String emailAddress;
    private String password;
}
