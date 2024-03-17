package com.bugratasdemir.graduationproject.enums;
public enum RateState implements EntityEnum{
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5");
    private final String text;
    RateState(String text) {
        this.text = text;
    }
    public String stringValue() {
        return this.text;
    }
    public String getStringValue() {
        return this.stringValue();
    }

}