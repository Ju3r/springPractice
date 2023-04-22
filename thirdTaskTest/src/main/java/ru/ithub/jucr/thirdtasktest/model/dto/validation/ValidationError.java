package ru.ithub.jucr.thirdtasktest.model.dto.validation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ValidationError {
    private final String fieldName;
    private final String message;

}
