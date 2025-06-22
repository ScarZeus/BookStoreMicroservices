package com.ScarZeus.recipe_backend.service.authServices;

import com.ScarZeus.recipe_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppDetailService implements UserDetailsService {
    private final UserService service;
    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
        return service.getUser(emailId);
    }
}
