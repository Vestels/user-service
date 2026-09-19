package com.fitnessapp.userservice.controller;

import com.fitnessapp.userservice.dto.response.UserPreferencesResponseDto;
import com.fitnessapp.userservice.entity.UserEntity;
import com.fitnessapp.userservice.service.UserPreferencesService;
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
@RequestMapping("/users/preferences")
public class UserPreferencesController {

    private final UserPreferencesService userPreferencesService;
    private final AuthenticateService authenticateService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UserPreferencesResponseDto getUserPreferences(Authentication authentication) {
        UserEntity user = authenticateService.getUserByAuthenticatedIdentity(authentication);

        return userPreferencesService.getUserPreferences(user.getPublicId());
    }
}
