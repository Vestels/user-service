package com.fitnessapp.userservice.controller;

import com.fitnessapp.userservice.dto.response.UserResponseDto;
import com.fitnessapp.userservice.dto.security.Auth0UserResponseDto;
import com.fitnessapp.userservice.service.UserService;
import com.fitnessapp.userservice.service.support.Auth0UserInfoService;
import com.fitnessapp.userservice.service.support.AuthenticateService;
import com.fitnessapp.userservice.service.support.UserProvisioningService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/me")
public class UserController {

    private final UserService userService;
    private final AuthenticateService authenticateService;
    private final Auth0UserInfoService auth0UserInfoService;
    private final UserProvisioningService userProvisioningService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto getOrProvisionUser(Authentication authentication) {
        return userProvisioningService.getOrProvisionUser(authentication);
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    public void setUserScheduledDeletion(Authentication authentication) {
        userService.setUserScheduledDeletion(authenticateService.requireAuthenticatedUserPublicId(authentication));
    }

    @PostMapping("/deletion/cancel")
    public void clearUserScheduledDeletion(Authentication authentication) {
        userService.clearUserScheduledDeletion(authenticateService.requireAuthenticatedUserPublicId(authentication));
    }

    @GetMapping("/info")
    @ResponseStatus(HttpStatus.OK)
    public Auth0UserResponseDto getUserInfoData(Authentication authentication) {
        return auth0UserInfoService.getUserInfoData(authentication);
    }
}
