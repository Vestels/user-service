package com.fitnessapp.userservice.exception.global;

import com.fitnessapp.userservice.dto.api.ApiErrorDto;
import com.fitnessapp.userservice.exception.AccountLinkRequiredException;
import com.fitnessapp.userservice.exception.UserNotFoundException;
import com.fitnessapp.userservice.exception.UserPreferencesNotFoundException;
import com.fitnessapp.userservice.exception.UserProfileNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiErrorDto> handleIllegalArgument(IllegalArgumentException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiErrorDto(
                        "BAD_REQUEST",
                        exception.getMessage()
                ));
    }

    @ExceptionHandler(AccountLinkRequiredException.class)
    public ResponseEntity<ApiErrorDto> handleAccountLinkRequired(AccountLinkRequiredException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ApiErrorDto(
                        "ACCOUNT_LINK_REQUIRED",
                        exception.getMessage()
                ));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiErrorDto> handleUserNotFound(UserNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiErrorDto(
                        "USER_NOT_FOUND",
                        exception.getMessage()
                ));
    }

    @ExceptionHandler(UserProfileNotFoundException.class)
    public ResponseEntity<ApiErrorDto> handleUserProfileNotFound(UserProfileNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiErrorDto(
                        "USER_PROFILE_NOT_FOUND",
                        exception.getMessage()
                ));
    }

    @ExceptionHandler(UserPreferencesNotFoundException.class)
    public ResponseEntity<ApiErrorDto> handleUserPreferencesNotFound(UserPreferencesNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiErrorDto(
                        "USER_PREFERENCES_NOT_FOUND",
                        exception.getMessage()
                ));
    }
}
