package com.fitnessapp.userservice.dto.response;

import com.fitnessapp.userservice.dto.security.Auth0UserInfoDto;

public record Auth0UserResponseDto(
        String picture,
        Boolean emailVerified
) {

    public static Auth0UserResponseDto from(Auth0UserInfoDto auth0User) {
        return new Auth0UserResponseDto(
              auth0User.sub(),
              auth0User.emailVerified()
        );
    }
}
