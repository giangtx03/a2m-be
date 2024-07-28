package com.a2m.project.services.impl;

import com.a2m.project.commons.RoleName;
import com.a2m.project.domains.CustomUser;
import com.a2m.project.domains.Role;
import com.a2m.project.domains.User;
import com.a2m.project.domains.UserInfo;
import com.a2m.project.dtos.requests.LoginRequest;
import com.a2m.project.dtos.requests.RegisterRequest;
import com.a2m.project.dtos.responses.LoginResponse;
import com.a2m.project.dtos.responses.RegisterResponse;
import com.a2m.project.mapper.CustomUserMapper;
import com.a2m.project.repositories.RoleRepository;
import com.a2m.project.repositories.UserInfoRepository;
import com.a2m.project.repositories.UserRepository;
import com.a2m.project.services.UserService;
import com.a2m.project.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;
    private final RoleRepository roleRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public LoginResponse login(LoginRequest loginRequest) throws Exception {

        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Thông tin đăng nhập không chính xác!!!"));

        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            throw new BadCredentialsException("Thông tin đăng nhập không chính xác!!!");
        }

        CustomUser customUser = CustomUserMapper.toCustomUser(user);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(), loginRequest.getPassword(), customUser.getAuthorities()
        );

        authenticationManager.authenticate(authenticationToken);
        String token = jwtTokenUtil.generateToken(customUser);
        return LoginResponse.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .token(token)
                .build();
    }

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) throws Exception {

        if(userRepository.existsByEmail(registerRequest.getEmail())){
            throw new InvalidParameterException("Email đã tồn tại !!!");
        }

        if(userInfoRepository.existsByPhoneNumber(registerRequest.getPhoneNumber())){
            throw new InvalidParameterException("Số điện thoại đã tồn tại !!!");
        }

        Role role = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy role"));

        UserInfo userInfo = UserInfo.builder()
                .fullName(registerRequest.getFullName())
                .address(registerRequest.getAddress())
                .phoneNumber(registerRequest.getPhoneNumber())
                .dob(registerRequest.getDob())
                .build();
        userInfoRepository.save(userInfo);

        User user = User.builder()
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .roles(List.of(role))
                .userInfo(userInfo)
                .build();

        userRepository.save(user);
        return RegisterResponse.builder()
                .email(user.getEmail())
                .fullName(user.getUserInfo().getFullName())
                .address(user.getUserInfo().getAddress())
                .phoneNumber(user.getUserInfo().getPhoneNumber())
                .dob(user.getUserInfo().getDob())
                .build();
    }
}
