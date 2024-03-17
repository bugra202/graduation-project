package com.bugratasdemir.graduationproject.enums;

public enum UserState implements EntityEnum{
    ACTIVE("Active"),
    INVALID("Invalid"),
    PASSIVE("Passive");
    private final String text;
    UserState(String text) {
        this.text = text;
    }
    public String stringValue() {
        return this.text;
    }
    public String getStringValue() {
        return this.stringValue();
    }
}
