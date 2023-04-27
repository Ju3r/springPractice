package ru.ithub.jucr.modulefive.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@ToString(exclude = {"username"})

public class UserDto {
    @Positive
    private Long id;
    @NotEmpty
//    @ToString.Exclude
    private String username;
    @PastOrPresent
    private LocalDate dateOfBirth;
}