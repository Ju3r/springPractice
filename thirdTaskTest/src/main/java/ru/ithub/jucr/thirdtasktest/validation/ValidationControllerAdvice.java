package ru.ithub.jucr.thirdtasktest.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.ithub.jucr.secondtask.restcontrollerservice.model.dto.validation.ValidationError;
import ru.ithub.jucr.secondtask.restcontrollerservice.model.dto.validation.ValidationErrorResponse;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ValidationControllerAdvice {
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
}