package com.bugratasdemir.graduationproject.enums;

public enum GenderState implements EntityEnum{
    MALE("Male"),
    FEMALE("Female"),
    UNKNOWN("Unknown");
    private final String text;
    GenderState(String text) {
        this.text = text;
    }
    public String stringValue() {
        return this.text;
    }
    public String getStringValue() {
        return this.stringValue();
    }
}
