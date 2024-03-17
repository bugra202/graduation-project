package com.bugratasdemir.graduationproject.errormessage;

import lombok.Getter;

@Getter
public enum CommentErrorMessage implements BaseErrorMessage{

    COMMENT_NOT_FOUND("Comment not found!");
    private final String message;

    CommentErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
