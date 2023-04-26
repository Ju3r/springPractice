package ru.ithub.jucr.secondtask.restcontrollerservice.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class CreateUserDTO {
    @NotBlank(message = "Please enter valid name")
    @Size(min=3, message = "Name should be atleast 3 characters")
    @Size(max=20, message = "Name should not be greater than 20 characters")
    private String name;

    @NotNull(message = "Please enter valid age")
    @Min(value = 1, message = "The age can be at least 1 year")
    @Max(value = 150, message = "The age can be a maximum of 150 year")
    private int age;

    @FutureOrPresent(message = "The date of birth must be greater than now")
    @NotNull(message = "Please enter valid date of birth")
    private Date dateOfBirth;

    public CreateUserDTO(CreateUserDTO userData) {
    }
}
