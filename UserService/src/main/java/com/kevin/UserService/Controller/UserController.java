package com.kevin.UserService.Controller;

import com.kevin.UserService.Model.UserModel;
import com.kevin.UserService.Services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServices userServices;

    @GetMapping("/getUserByEmail")
    public ResponseEntity<UserModel> getUserByEmailAddress(@RequestParam("email") String email){
        try{
            UserModel user = userServices.getUserByEmailAddress(email);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/addNewUser")
    public ResponseEntity<UserModel> addNewUser(@RequestBody UserModel user){
        try{
            UserModel newUser  = userServices.createNewUser(user);
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


}
