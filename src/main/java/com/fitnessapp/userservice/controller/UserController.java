package com.fitnessapp.userservice.controller;

import com.fitnessapp.userservice.dto.response.Auth0UserResponseDto;
import com.fitnessapp.userservice.dto.response.UserResponseDto;
import com.fitnessapp.userservice.service.UserService;
import com.fitnessapp.userservice.service.support.Auth0UserInfoService;
import com.fitnessapp.userservice.service.support.UserProvisioningService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final Auth0UserInfoService auth0UserInfoService;
    private final UserProvisioningService userProvisioningService;

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto getOrProvisionUser(Authentication authentication) {
        return userProvisioningService.getOrProvisionUser(authentication);
    }

    @GetMapping("/info")
    @ResponseStatus(HttpStatus.OK)
    public Auth0UserResponseDto getUserInfoData(Authentication authentication) {
        return auth0UserInfoService.getUserInfoData(authentication);
    }
}
