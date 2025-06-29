package com.kevin.AuthService.Services.ApiServices;

import com.kevin.AuthService.Model.UserModel;
import com.kevin.AuthService.Repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServices {

    private final UserRepo userRepo;

    public UserModel getUserByEmailAddress(String emailAddress){
        UserModel  user = Optional.ofNullable(userRepo.findByEmailAddress(emailAddress))
                .get()
                .orElseThrow(() -> new RuntimeException("User not found of the Given email Address : "+emailAddress));
        return user;
    }


}
