package ru.ithub.jucr.thirdtasktest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ithub.jucr.thirdtasktest.model.dto.CreateUserDTO;
import ru.ithub.jucr.thirdtasktest.model.dto.UserDTO;
import ru.ithub.jucr.thirdtasktest.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public ResponseEntity<CreateUserDTO> createUser(@Valid @RequestBody CreateUserDTO userData){
        return new ResponseEntity<CreateUserDTO>(userData, HttpStatus.CREATED);
    }
}