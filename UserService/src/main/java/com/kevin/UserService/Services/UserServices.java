package com.kevin.UserService.Services;

import com.kevin.UserService.Model.UserModel;
import com.kevin.UserService.Repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServices {

    private final BCryptPasswordEncoder encoder;
    private final UserRepo userRepo;

    public UserModel getUserByEmailAddress(String emailAddress){
        UserModel  user = Optional.ofNullable(userRepo.findByEmailAddress(emailAddress))
                .get()
                .orElseThrow(() -> new RuntimeException("User not found of the Given email Address : "+emailAddress));
        return user;
    }

    public UserModel getUserByUserId(Long userId){
        UserModel user = Optional.ofNullable(userRepo.findById(userId))
                .get().orElseThrow(() -> new RuntimeException("User not found of the Given User ID : "+userId));
        return user;
    }

    public UserModel createNewUser(UserModel user){
        try{
            if(userRepo.existsByEmailAddress(user.getEmailAddress())){
                throw new RuntimeException("User Already Exist");
            }
            if(user.getPassword() !=null){
                user.setPassword(encoder.encode(user.getPassword()));
            }
            return userRepo.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Cannot Create a new User "+ user.toString()+" : " + e);
        }
    }

    public boolean doesUserExistsByEmail(String email){
        try {
            if(userRepo.existsByEmailAddress(email)){
                return true;
            }
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
