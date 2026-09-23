package com.fitnessapp.userservice.service.support;

import com.fitnessapp.userservice.dto.security.AuthenticatedUserDto;
import com.fitnessapp.userservice.entity.UserEntity;
import com.fitnessapp.userservice.entity.UserIdentityEntity;
import com.fitnessapp.userservice.enums.IdentityProvider;
import com.fitnessapp.userservice.exception.UserIdentityNotFoundException;
import com.fitnessapp.userservice.service.UserIdentityService;
import com.fitnessapp.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticateService {

    private final UserService userService;
    private final UserIdentityService userIdentityService;

    public UserEntity getUserByAuthenticatedIdentity(Authentication authentication) {
        return userService.findByPublicId(getAuthenticatedIdentity(authentication).map(UserIdentityEntity::getUserId)
                .orElseThrow(() -> new UserIdentityNotFoundException("User identity not found.")));
    }

    public Optional<UUID> getAuthenticatedUserPublicId(Authentication authentication) {
        return getAuthenticatedIdentity(authentication).map(UserIdentityEntity::getUserId);
    }

    public UUID requireAuthenticatedUserPublicId(Authentication authentication) {
        return getAuthenticatedUserPublicId(authentication).orElseThrow(() -> new UserIdentityNotFoundException("User identity not found."));
    }

    public Jwt getJwtTokenFromPrincipal(Authentication authentication) {
        return (Jwt) authentication.getPrincipal();
    }

    public Optional<UserIdentityEntity> getAuthenticatedIdentity(Authentication authentication) {
        AuthenticatedUserDto authenticatedUser = AuthenticatedUserDto.from(authentication);

        return userIdentityService.getAuthenticatedUserIdentityProvider(
                IdentityProvider.valueOf(authenticatedUser.provider()),
                authenticatedUser.subject());
    }
}
