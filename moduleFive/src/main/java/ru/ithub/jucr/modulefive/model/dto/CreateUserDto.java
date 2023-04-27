package ru.ithub.jucr.modulefive.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserDto {
    @NotEmpty
    @ToString.Exclude
    private String username;
    @PastOrPresent
    private LocalDate dateOfBirth;
}