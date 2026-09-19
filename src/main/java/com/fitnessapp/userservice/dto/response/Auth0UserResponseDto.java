package com.fitnessapp.userservice.dto.response;

import com.fitnessapp.userservice.dto.security.Auth0UserInfoDto;

public record Auth0UserResponseDto(
        String sub,
        String nickname,
        String name,
        String picture,
        String updatedAt,
        String email,
        Boolean emailVerified
) {

    public static Auth0UserResponseDto from(Auth0UserInfoDto auth0User) {
        return new Auth0UserResponseDto(
              auth0User.sub(),
              auth0User.nickname(),
              auth0User.name(),
              auth0User.picture(),
              auth0User.updatedAt(),
              auth0User.email(),
              auth0User.emailVerified()
        );
    }
}
