package com.fitnessapp.userservice.controller;

import com.fitnessapp.userservice.dto.request.UpdateUserPreferencesRequestDto;
import com.fitnessapp.userservice.dto.response.UserPreferencesResponseDto;
import com.fitnessapp.userservice.entity.UserEntity;
import com.fitnessapp.userservice.exception.BadRequestException;
import com.fitnessapp.userservice.service.UserPreferencesService;
import com.fitnessapp.userservice.service.support.AuthenticateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateUserPreferences(@Valid @RequestBody UpdateUserPreferencesRequestDto request, Authentication authentication) {
        if (request.isEmpty()) throw new BadRequestException("At least one field must be provided.");

        userPreferencesService.updateUserPreferences(
                request,
                authenticateService.requireAuthenticatedUserPublicId(authentication)
        );
    }
}
