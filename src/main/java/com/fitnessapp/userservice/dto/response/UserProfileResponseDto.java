package com.fitnessapp.userservice.dto.response;

import com.fitnessapp.userservice.entity.UserProfileEntity;
import com.fitnessapp.userservice.enums.Gender;

import java.time.LocalDate;

public record UserProfileResponseDto(
        LocalDate birthDate,
        String nickname,
        String firstName,
        String lastName,
        Gender gender
) {

    public static UserProfileResponseDto from(UserProfileEntity userProfile) {
        return new UserProfileResponseDto(
                userProfile.getBirthDate(),
                userProfile.getNickname(),
                userProfile.getFirstName(),
                userProfile.getLastName(),
                userProfile.getGender()
        );
    }
}
