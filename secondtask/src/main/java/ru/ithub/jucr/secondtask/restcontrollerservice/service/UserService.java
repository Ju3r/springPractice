package ru.ithub.jucr.secondtask.restcontrollerservice.service;

import org.springframework.stereotype.Service;
import ru.ithub.jucr.secondtask.restcontrollerservice.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private List<User> users = new ArrayList<>();
    private Long nextId = 1L;

    public UserService() {
        // add some test users to the list
        users.add(new User(nextId++, "Alice", "alice@example.com"));
        users.add(new User(nextId++, "Bob", "bob@example.com"));
        users.add(new User(nextId++, "Charlie", "charlie@example.com"));
    }

    public User getUserById(Long userId) {
        return users.stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst()
                .orElse(null);
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User createUser(User user) {
        user.setId(nextId++);
        users.add(user);
        return user;
    }

    public void updateUser(User user) {
        User existingUser = users.stream()
                .filter(u -> u.getId().equals(user.getId()))
                .findFirst()
                .orElse(null);
        if (existingUser != null) {
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
        }
    }

    public User patchUser(Long id, User updatedUser) throws Exception {
        User user = getUserById(id);
        if (user == null) {
            throw new Exception();
        }
        if (updatedUser.getName() != null) {
            user.setName(updatedUser.getName());
        }
        if (updatedUser.getEmail() != null) {
            user.setEmail(updatedUser.getEmail());
        }

        updateUser(user);
        return user;
    }

    public void deleteUser(Long userId) {
        users.removeIf(u -> u.getId().equals(userId));
    }

}
