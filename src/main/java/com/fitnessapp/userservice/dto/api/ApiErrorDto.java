package com.fitnessapp.userservice.dto.api;

public record ApiErrorDto(
        String code,
        String message
) {
}
