package ru.ithub.jucr.secondtask.restcontrollerservice.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ithub.jucr.secondtask.restcontrollerservice.model.dto.CreateUserDTO;
import ru.ithub.jucr.secondtask.restcontrollerservice.model.dto.UserDTO;
import ru.ithub.jucr.secondtask.restcontrollerservice.service.UserService;

import java.util.List;

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

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers(PageRequest.of(0, 10)).getContent();
    }

    @PostMapping
    public ResponseEntity<CreateUserDTO> createUser(@Valid @RequestBody CreateUserDTO userData){
        return new ResponseEntity<CreateUserDTO>(userData, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable Long id,
                           @RequestBody UserDTO userDTO) {
        userDTO.setId(id);
        userService.updateUser(userDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PatchMapping("/{id}")
    public void patchUser(@PathVariable Long id,
                          @RequestBody UserDTO updatedUserDTO) throws Exception {
        userService.patchUser(id, updatedUserDTO);
    }

    @GetMapping("/prefix")
    public List<UserDTO> getUsersByPrefix(@RequestParam("name") String prefix) {
        return userService.getUsersByPrefix(prefix);
    }

}
