package com.kevin.AuthService.Services.SecurityServices;


import com.kevin.AuthService.Services.ApiServices.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserService implements UserDetailsService {

    private final UserServices userDbService;
    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        return userDbService.getUserByEmailAddress(emailAddress);
    }


}

