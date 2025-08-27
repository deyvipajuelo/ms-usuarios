package com.pragma.powerup.infrastructure.exceptionhandler;

public enum ExceptionResponse {
    USER_NOT_FOUND("No User was found with that number"),
    USER_ALREADY_EXISTS("There is already a user with that document"),
    ROLE_NOT_FOUND("No role was found"),
    NO_DATA_FOUND("No data found for the requested petition"),
    USER_UNDER_AGE("The user must be at least 18 years old");

    private String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}