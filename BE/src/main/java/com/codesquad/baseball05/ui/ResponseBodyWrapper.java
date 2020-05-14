package com.codesquad.baseball05.ui;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseBodyWrapper {
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
    private Object status;
    private String errorMessage;

    private ResponseBodyWrapper(Object status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static ResponseBodyWrapper ok() {
        return new ResponseBodyWrapper( null, null);
    }

    public static ResponseBodyWrapper ok(Object data) {
        return new ResponseBodyWrapper( data, null);
    }

    public static ResponseBodyWrapper failed(String errorMessage) {
        return new ResponseBodyWrapper( null, errorMessage);
    }
}
