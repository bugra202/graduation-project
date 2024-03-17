package com.bugratasdemir.graduationprojectapi.errormessage;

import lombok.Getter;

@Getter
public enum RestaurantErrorMessage implements BaseErrorMessage {

    RESTAURANT_NOT_FOUND("Restaurant not found!");
    private final String message;

    RestaurantErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
