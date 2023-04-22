package ru.ithub.jucr.thirdtasktest.model.dto.validation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ValidationErrorResponse {
    private final List<ValidationError> validationErrors;
}