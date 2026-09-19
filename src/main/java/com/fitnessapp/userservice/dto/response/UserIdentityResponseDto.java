package com.fitnessapp.userservice.dto.response;

import com.fitnessapp.userservice.entity.UserIdentityEntity;
import com.fitnessapp.userservice.enums.IdentityProvider;

import java.time.Instant;
import java.util.UUID;

public record UserIdentityResponseDto(
        UUID userId,
        IdentityProvider provider,
        String subject,
        Instant createdAt,
        Instant lastUsedAt
) {

    public static UserIdentityResponseDto from(UserIdentityEntity identity) {
        return new UserIdentityResponseDto(
                identity.getUserId(),
                identity.getProvider(),
                identity.getSubject(),
                identity.getCreatedAt(),
                identity.getLastUsedAt()
        );
    }
}
