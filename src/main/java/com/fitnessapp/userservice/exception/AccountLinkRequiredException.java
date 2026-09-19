package com.fitnessapp.userservice.exception;

public class AccountLinkRequiredException extends RuntimeException {
    public AccountLinkRequiredException(String message) {
        super(message);
    }
}
