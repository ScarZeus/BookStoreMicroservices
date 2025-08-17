package com.kevin.OrderService.Services;

import com.kevin.OrderService.Model.UserModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "UserServices",url = "http://localhost:5020/api/v1/user")
public interface UserServices {
    @GetMapping("/getUserByEmail")
    public ResponseEntity<UserModel> getUserByEmailAddress(@RequestParam("email") String email);

    @PostMapping("/addNewUser")
    public ResponseEntity<UserModel> addNewUser(@RequestBody UserModel user);

}
