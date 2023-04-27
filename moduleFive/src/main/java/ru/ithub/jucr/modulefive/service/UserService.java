package ru.ithub.jucr.modulefive.service;

import org.springframework.stereotype.Service;
import ru.ithub.jucr.modulefive.model.dto.CreateUserDto;
import ru.ithub.jucr.modulefive.model.dto.UserDto;

@Service
public class UserService implements IUserService {
    @Override
    public UserDto createUser(CreateUserDto createUserDto) {
        UserDto userDto;

        if (createUserDto.getUsername().equals("test")) {
            userDto = UserDto.builder()
                    .id(1L)
                    .username(createUserDto.getUsername().concat("user"))
                    .dateOfBirth(createUserDto.getDateOfBirth())
                    .build();
        } else {
            userDto = UserDto.builder()
                    .id(1L)
                    .username(createUserDto.getUsername())
                    .dateOfBirth(createUserDto.getDateOfBirth())
                    .build();
        }

        return userDto;
    }
}