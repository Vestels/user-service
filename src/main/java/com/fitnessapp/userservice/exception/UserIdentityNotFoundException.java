package com.fitnessapp.userservice.exception;

public class UserIdentityNotFoundException extends RuntimeException {
    public UserIdentityNotFoundException(String message) {
        super(message);
    }
}
