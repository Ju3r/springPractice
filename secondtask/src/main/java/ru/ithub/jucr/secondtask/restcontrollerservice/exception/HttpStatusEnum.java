package ru.ithub.jucr.secondtask.restcontrollerservice.exception;

import org.springframework.http.HttpStatus;

public enum HttpStatusEnum {
    OK(HttpStatus.OK, "*** OK ***"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "*** Bad Request ***"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "*** Unauthorized ***"),
    FORBIDDEN(HttpStatus.FORBIDDEN, "*** Forbidden ***"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "*** Not Found ***"),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "*** Method Not Allowed ***"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "*** Internal Server Error ***");

    private final HttpStatus httpStatus;
    private final String message;

    HttpStatusEnum(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}