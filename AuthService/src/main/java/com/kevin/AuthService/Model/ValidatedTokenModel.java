package com.kevin.AuthService.Model;


import com.kevin.AuthService.Model.EnumModel.ResponseStatus;
import lombok.*;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class ValidatedTokenModel {
    private ResponseStatus response;
}
