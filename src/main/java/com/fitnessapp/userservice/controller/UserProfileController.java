package com.fitnessapp.userservice.controller;

import com.fitnessapp.userservice.dto.response.UserProfileResponseDto;
import com.fitnessapp.userservice.entity.UserEntity;
import com.fitnessapp.userservice.service.UserProfileService;
import com.fitnessapp.userservice.service.support.AuthenticateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/profile")
public class UserProfileController {

    private final UserProfileService userProfileService;
    private final AuthenticateService authenticateService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UserProfileResponseDto getUserProfile(Authentication authentication) {
        UserEntity user = authenticateService.getUserByAuthenticatedIdentity(authentication);

        return userProfileService.getUserProfile(user.getPublicId());
    }
}
