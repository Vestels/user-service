package com.fitnessapp.userservice.service.support;

import com.fitnessapp.userservice.dto.security.AuthenticatedUserDto;
import com.fitnessapp.userservice.entity.UserIdentityEntity;
import com.fitnessapp.userservice.entity.UserEntity;
import com.fitnessapp.userservice.enums.IdentityProvider;
import com.fitnessapp.userservice.service.UserIdentityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LoginTrackingService {

    private final UserIdentityService userIdentityService;
    private final AuthenticateService authenticateService;

    @Transactional
    public void recordLoginIfNecessary(Authentication authentication) {

        AuthenticatedUserDto authenticatedUser = AuthenticatedUserDto.from(authentication);
        IdentityProvider provider = IdentityProvider.valueOf(authenticatedUser.provider());
        UserIdentityEntity identity = userIdentityService.getAuthenticatedUserIdentityProvider(provider, authenticatedUser.subject());

        if (identity == null) return;

        Jwt jwt = (Jwt) authentication.getPrincipal();
        UserEntity user = authenticateService.getUserByAuthenticatedIdentity(authentication);
        Instant loginTime = Objects.requireNonNull(jwt).getIssuedAt();

        if (loginTime == null) return;

        if (user.getLastLoginAt() == null || loginTime.isAfter(user.getLastLoginAt())) {
            user.updateLastLogin();
            identity.updateLastUsedAt();
        }
    }
}