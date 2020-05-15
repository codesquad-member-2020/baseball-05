package com.codesquad.baseball05.infra.exception;

public class NotFoundUserException extends RuntimeException {
    private String errorMessage = "유저가 없습니다.";

    public NotFoundUserException() {
        super();
    }

    public NotFoundUserException(String errorMessage) {
        super(errorMessage);
    }
}
