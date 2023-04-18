package ru.ithub.jucr.secondtask.restcontrollerservice.model.exception;

public class HttpStatusEnumException extends RuntimeException {
    private final HttpStatusEnum httpStatusEnum;

    public HttpStatusEnumException(HttpStatusEnum httpStatusEnum) {
        super(httpStatusEnum.getMessage());
        this.httpStatusEnum = httpStatusEnum;
    }

    public HttpStatusEnum getHttpStatusEnum() {
        return httpStatusEnum;
    }
}
