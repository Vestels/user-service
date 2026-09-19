package com.fitnessapp.userservice.dto.response;

import com.fitnessapp.userservice.entity.UserIdentityEntity;
import com.fitnessapp.userservice.enums.IdentityProvider;

import java.time.Instant;

public record UserIdentityResponseDto(
        IdentityProvider provider,
        Instant createdAt,
        Instant lastUsedAt
) {

    public static UserIdentityResponseDto from(UserIdentityEntity identity) {
        return new UserIdentityResponseDto(
                identity.getProvider(),
                identity.getCreatedAt(),
                identity.getLastUsedAt()
        );
    }
}
