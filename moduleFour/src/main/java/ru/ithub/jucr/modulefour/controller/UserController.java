package ru.ithub.jucr.modulefour.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.ithub.jucr.modulefour.model.dto.CreateUserDto;
import ru.ithub.jucr.modulefour.model.dto.UserDto;
import ru.ithub.jucr.modulefour.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public CreateUserDto createUser(@Valid @RequestBody CreateUserDto userData){
        return new CreateUserDto(userData, HttpStatus.CREATED);
    }
}
