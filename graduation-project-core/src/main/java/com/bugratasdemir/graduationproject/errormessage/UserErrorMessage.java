package com.bugratasdemir.graduationproject.errormessage;

import lombok.Getter;

@Getter
public enum UserErrorMessage implements BaseErrorMessage{

    USER_NOT_FOUND("User not found!");
    private final String message;

    UserErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
