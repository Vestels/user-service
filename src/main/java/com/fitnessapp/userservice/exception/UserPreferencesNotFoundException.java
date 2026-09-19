package com.fitnessapp.userservice.exception;

public class UserPreferencesNotFoundException extends RuntimeException {
    public UserPreferencesNotFoundException(String message) {
        super(message);
    }
}
