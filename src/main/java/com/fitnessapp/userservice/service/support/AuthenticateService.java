package com.fitnessapp.userservice.service.support;

import com.fitnessapp.userservice.dto.security.AuthenticatedUserDto;
import com.fitnessapp.userservice.entity.UserEntity;
import com.fitnessapp.userservice.entity.UserIdentityEntity;
import com.fitnessapp.userservice.enums.IdentityProvider;
import com.fitnessapp.userservice.exception.UserNotFoundException;
import com.fitnessapp.userservice.service.UserIdentityService;
import com.fitnessapp.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticateService {

    private final UserService userService;
    private final UserIdentityService userIdentityService;

    public UserEntity getUserByAuthenticatedIdentity(Authentication authentication) {

        AuthenticatedUserDto authenticatedUser = AuthenticatedUserDto.from(authentication);
        UserIdentityEntity identity = userIdentityService.getAuthenticatedUserIdentityProvider(
                IdentityProvider.valueOf(authenticatedUser.provider()), authenticatedUser.subject());

        if (identity == null) return null;

        return userService.findByPublicId(identity.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found."));
    }

    public UUID getAuthenticatedUserPublicId(Authentication authentication) {

        AuthenticatedUserDto authenticatedUser = AuthenticatedUserDto.from(authentication);

        UserIdentityEntity identity = userIdentityService.getAuthenticatedUserIdentityProvider(
                IdentityProvider.valueOf(authenticatedUser.provider()), authenticatedUser.subject()
        );

        if (identity == null) return null;

        return identity.getUserId();
    }

    public Jwt getJwtTokenFromPrincipal(Authentication authentication) {
        return (Jwt) authentication.getPrincipal();
    }
}
