package com.fitnessapp.userservice.controller;

import com.fitnessapp.userservice.dto.response.UserIdentityResponseDto;
import com.fitnessapp.userservice.entity.UserEntity;
import com.fitnessapp.userservice.service.UserIdentityService;
import com.fitnessapp.userservice.service.support.AuthenticateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/identities")
public class UserIdentityController {

    private final UserIdentityService userIdentityService;
    private final AuthenticateService authenticateService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserIdentityResponseDto> getAllUserIdentities(Authentication authentication) {
        UserEntity user = authenticateService.getUserByAuthenticatedIdentity(authentication);

        return userIdentityService.getAllUserIdentities(user.getPublicId());
    }
}
