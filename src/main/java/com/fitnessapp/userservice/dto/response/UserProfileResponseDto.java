package com.fitnessapp.userservice.dto.response;

import com.fitnessapp.userservice.entity.UserProfileEntity;
import com.fitnessapp.userservice.enums.Gender;

import java.time.LocalDate;
import java.util.UUID;

public record UserProfileResponseDto(
        UUID userId,
        LocalDate birthDate,
        String nickname,
        String firstName,
        String lastName,
        Gender gender
) {

    public static UserProfileResponseDto from(UserProfileEntity userProfile) {
        return new UserProfileResponseDto(
                userProfile.getUserId(),
                userProfile.getBirthDate(),
                userProfile.getNickname(),
                userProfile.getFirstName(),
                userProfile.getLastName(),
                userProfile.getGender()
        );
    }
}
