package ru.ithub.jucr.modulefive.provider;

import ru.ithub.jucr.modulefive.model.dto.CreateUserDto;
import ru.ithub.jucr.modulefive.model.dto.UserDto;

import java.time.LocalDate;

public class UserProvider {
    public static CreateUserDto getCreateUserDto(String userName) {
        return CreateUserDto.builder()
                .username(userName)
                .dateOfBirth(LocalDate.of(2015, 1, 1))
                .build();
    }

    public static UserDto getUserDto(String userName) {
        return UserDto.builder()
                .id(1L)
                .username(userName)
                .dateOfBirth(LocalDate.of(2015, 1, 1))
                .build();
    }
}