package com.example.interactrivemenu.service;

import com.example.interactrivemenu.config.SecurityConfig;
import com.example.interactrivemenu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private final SecurityConfig securityConfig;

    @Autowired
    public UserDetailsServiceImpl(SecurityConfig securityConfig) {
        this.securityConfig = securityConfig;
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        if ("admin".equals(username)) {
//            return User.builder()
//                    .username("admin")
//                    .password(securityConfig.passwordEncoder().encode("adminpassword"))
//                    .roles("ADMIN")
//                    .build();
//        }
//        throw new UsernameNotFoundException("User not found");
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
