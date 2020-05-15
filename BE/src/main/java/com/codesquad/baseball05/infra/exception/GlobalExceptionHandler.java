package com.codesquad.baseball05.infra.exception;

import com.codesquad.baseball05.ui.ResponseBodyWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundUserException.class)
    public ResponseEntity<ResponseBodyWrapper> handleNotFoundUserException(NotFoundUserException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ResponseBodyWrapper.failed(e.getMessage()));
    }
}
