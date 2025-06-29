package com.kevin.AuthService.Model;

import com.kevin.AuthService.Model.EnumModel.ResponseStatus;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseModel {
    private ResponseStatus responseStatus;
    private String jwtToken;
}
