package ru.ithub.jucr.secondtask.restcontrollerservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.ithub.jucr.secondtask.restcontrollerservice.model.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private List<UserDTO> userDTOS = new ArrayList<>();
    private Long nextId = 1L;

    public UserService() {
        // add some test users to the list
        userDTOS.add(new UserDTO(nextId++, "Alice", "alice@example.com"));
        userDTOS.add(new UserDTO(nextId++, "Bob", "bob@example.com"));
        userDTOS.add(new UserDTO(nextId++, "Charlie", "charlie@example.com"));
    }

    public UserDTO getUserById(Long userId) {
        Optional<UserDTO> user = userDTOS.stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst();
        return user.isPresent() ? user.get() : null;
    }

    public Page<UserDTO> getAllUsers(Pageable pageable) {
        Page<UserDTO> page = new PageImpl<>(userDTOS, pageable, userDTOS.size());
        return page;
    }

    public UserDTO createUser(UserDTO userDTO) {
        userDTO.setId(nextId++);
        userDTOS.add(userDTO);
        return userDTO;
    }

    public void updateUser(UserDTO userDTO) {
        Optional<UserDTO> existingUserDTO = userDTOS.stream()
                .filter(u -> u.getId().equals(userDTO.getId()))
                .findFirst();
        existingUserDTO.ifPresent(u -> {
            u.setName(userDTO.getName());
            u.setEmail(userDTO.getEmail());
        });
    }

    public HttpStatus patchUser(Long id, UserDTO updatedUserDTO) throws Exception {
        UserDTO userDTO = getUserById(id);
        if (userDTO == null) {
            return HttpStatus.NOT_FOUND;
        }
        if (updatedUserDTO.getName() != null) {
            userDTO.setName(updatedUserDTO.getName());
        }

        updateUser(userDTO);
        return HttpStatus.OK;
    }

    public HttpStatus deleteUser(Long userId) {
        boolean removed = userDTOS.removeIf(u -> u.getId().equals(userId));
        return removed ? HttpStatus.OK : HttpStatus.NOT_FOUND;
    }

    public List<UserDTO> getUsersByPrefix(String prefix) {
        List<UserDTO> matchingUsers = userDTOS.stream()
                .filter(u -> u.getName().startsWith(prefix))
                .collect(Collectors.toList());
        if (matchingUsers.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No users found with the given prefix");
        }

        return matchingUsers;
    }
}
