package ru.ithub.jucr.modulefive.controller;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ithub.jucr.modulefive.model.dto.CreateUserDto;
import ru.ithub.jucr.modulefive.model.dto.UserDto;
import ru.ithub.jucr.modulefive.service.UserService;

@Log4j2
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public UserDto createUser(@Valid @RequestBody CreateUserDto createUserDto) {
        log.info("Get request for create user: {}", createUserDto);

        UserDto userDto = service.createUser(createUserDto);

        log.info("Return response for create user: {}", userDto);

        return userDto;
    }
}
