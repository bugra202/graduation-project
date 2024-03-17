package com.bugratasdemir.graduationproject.enums;

public enum TopicNameEnum {
    _USER_LOG_TOPIC_NAME("USER_LOG_START_COMMAND");

    private final String text;

    private TopicNameEnum(String text) {
        this.text = text;
    }

    public String stringValue() {
        return text;
    }

}
