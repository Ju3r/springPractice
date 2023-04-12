package ru.ithub.jucr.secondtask.restcontrollerservice.controller;

import org.springframework.web.bind.annotation.*;
import ru.ithub.jucr.secondtask.restcontrollerservice.model.User;
import ru.ithub.jucr.secondtask.restcontrollerservice.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PatchMapping("/{id}")
    public void patchUser(@PathVariable Long id, @RequestBody User updatedUser) throws Exception {
        userService.patchUser(id, updatedUser);
    }

}
