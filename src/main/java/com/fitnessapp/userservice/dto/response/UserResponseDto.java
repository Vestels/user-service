package com.fitnessapp.userservice.dto.response;

import com.fitnessapp.userservice.entity.UserEntity;
import com.fitnessapp.userservice.enums.UserStatus;

import java.time.Instant;

public record UserResponseDto(
        String email,
        UserStatus userStatus,
        Instant createdAt,
        Instant updatedAt,
        Instant lastLoginAt,
        Instant lastActivityAt,
        Instant deletionRequestAt,
        Instant scheduledDeletionAt
) {

    public static UserResponseDto from(UserEntity user) {
        return new UserResponseDto(
                user.getEmail(),
                user.getStatus(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.getLastLoginAt(),
                user.getLastActivityAt(),
                user.getDeletionRequestAt(),
                user.getScheduledDeletionAt()
        );
    }
}
