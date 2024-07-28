package com.a2m.project.mapper;

import com.a2m.project.domains.CustomUser;
import com.a2m.project.domains.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.stream.Collectors;

public class CustomUserMapper {

    public static CustomUser toCustomUser(User user){
        return CustomUser.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(
                        user.getRoles().stream()
                                .map(role -> new SimpleGrantedAuthority("ROLE_"+role.getName()))
                                .collect(Collectors.toList())
                )
                .build();
    }

}
