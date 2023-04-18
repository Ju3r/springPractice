package ru.ithub.jucr.secondtask.restcontrollerservice.model.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class HttpStatusEnumExceptionHandler {

    @ExceptionHandler(HttpStatusEnumException.class)
    @ResponseBody
    public ResponseEntity<HttpStatusEnumExceptionResponse> handleHttpStatusEnumException(HttpStatusEnumException ex) {
        HttpStatusEnum httpStatusEnum = ex.getHttpStatusEnum();
        HttpStatusEnumExceptionResponse response = new HttpStatusEnumExceptionResponse(httpStatusEnum);
        return new ResponseEntity<>(response, httpStatusEnum.getHttpStatus());
    }

    public static class HttpStatusEnumExceptionResponse {
        private final int status;
        private final String error;
        private final String message;

        public HttpStatusEnumExceptionResponse(HttpStatusEnum httpStatusEnum) {
            this.status = httpStatusEnum.getHttpStatus().value();
            this.error = httpStatusEnum.getHttpStatus().getReasonPhrase();
            this.message = httpStatusEnum.getMessage();
        }

        public int getStatus() {
            return status;
        }

        public String getError() {
            return error;
        }

        public String getMessage() {
            return message;
        }
    }
}
