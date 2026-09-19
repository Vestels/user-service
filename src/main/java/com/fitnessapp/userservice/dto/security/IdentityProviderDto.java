package com.fitnessapp.userservice.dto.security;

import com.fitnessapp.userservice.entity.UserIdentityEntity;
import com.fitnessapp.userservice.enums.IdentityProvider;

import java.time.Instant;
import java.util.UUID;

public record IdentityProviderDto(
        UUID userId,
        IdentityProvider provider,
        String subject,
        Instant createdAt,
        Instant lastUsedAt
) {

    public static IdentityProviderDto from(UserIdentityEntity identity) {
        return new IdentityProviderDto(
                identity.getUserId(),
                identity.getProvider(),
                identity.getSubject(),
                identity.getCreatedAt(),
                identity.getLastUsedAt()
        );
    }
}
