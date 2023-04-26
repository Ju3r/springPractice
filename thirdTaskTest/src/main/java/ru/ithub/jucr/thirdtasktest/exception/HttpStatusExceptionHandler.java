package ru.ithub.jucr.thirdtasktest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.ithub.jucr.thirdtasktest.model.dto.validation.ValidationError;
import ru.ithub.jucr.thirdtasktest.model.dto.validation.ValidationErrorResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class HttpStatusExceptionHandler {
    @ExceptionHandler(HttpStatusException.class)
    @ResponseBody
    public ResponseEntity<HttpStatusEnumExceptionResponse> handleHttpStatusException(HttpStatusException ex) {
        HttpStatusEnum httpStatusEnum = ex.getHttpStatusEnum();
        HttpStatusEnumExceptionResponse response = new HttpStatusEnumExceptionResponse(httpStatusEnum);
        return new ResponseEntity<>(response, httpStatusEnum.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        final List<ValidationError> validationErrors = e.getBindingResult().getFieldErrors().stream()
                .map(error -> new ValidationError(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ValidationErrorResponse(validationErrors);
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
