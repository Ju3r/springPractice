package ru.ithub.jucr.thirdtasktest.exception;

public class HttpStatusException extends RuntimeException {
    private final HttpStatusEnum httpStatusEnum;

    public HttpStatusException(HttpStatusEnum httpStatusEnum) {
        super(httpStatusEnum.getMessage());
        this.httpStatusEnum = httpStatusEnum;
    }

    public HttpStatusEnum getHttpStatusEnum() {
        return httpStatusEnum;
    }
}
