package ru.ithub.jucr.secondtask.restcontrollerservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.ithub.jucr.secondtask.restcontrollerservice.model.dto.exception.CreateUserDTO;
import ru.ithub.jucr.secondtask.restcontrollerservice.model.dto.user.UserDTO;
import ru.ithub.jucr.secondtask.restcontrollerservice.exception.HttpStatusEnum;
import ru.ithub.jucr.secondtask.restcontrollerservice.exception.HttpStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private List<UserDTO> userDTOS = new ArrayList<>();
    private List<CreateUserDTO> createUserDTOS = new ArrayList<>();
    private Long nextId = 1L;

    public UserService() {
        userDTOS.add(new UserDTO(nextId++, "Alice", "alice@example.com"));
        userDTOS.add(new UserDTO(nextId++, "Bob", "bob@example.com"));
        userDTOS.add(new UserDTO(nextId++, "Charlie", "charlie@example.com"));
    }

    public UserDTO getUserById(Long userId) {
        Optional<UserDTO> user = userDTOS.stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst();

        if (user.isPresent()){
            return user.get();
        } else{
            throw new HttpStatusException(HttpStatusEnum.NOT_FOUND);
        }
    }

    public Page<UserDTO> getAllUsers(Pageable pageable) {
        Page<UserDTO> page = new PageImpl<>(userDTOS, pageable, userDTOS.size());
        if(page == null){
            throw new HttpStatusException(HttpStatusEnum.NOT_FOUND);
        }
        return page;
    }

    public HttpStatus createUser(CreateUserDTO userData){
        boolean userExists = createUserDTOS.stream().anyMatch(u -> u.getName().equals(userData.getName()));
        if (userExists) {
            throw new HttpStatusException(HttpStatusEnum.BAD_REQUEST);
        }

        CreateUserDTO user = new CreateUserDTO(userData.getName(), userData.getAge(), userData.getDateOfBirth());
        createUserDTOS.add(user);
        return HttpStatus.OK;
    }

    public HttpStatus updateUser(UserDTO userDTO) {
        Optional<UserDTO> existingUserDTO = userDTOS.stream()
                .filter(u -> u.getId().equals(userDTO.getId()))
                .findFirst();

        if (!existingUserDTO.isPresent()){
            throw new HttpStatusException(HttpStatusEnum.NOT_FOUND);
        }

        existingUserDTO.ifPresent(u -> {
            u.setName(userDTO.getName());
            u.setEmail(userDTO.getEmail());
        });

        return HttpStatus.OK;
    }

    public HttpStatus patchUser(Long id, UserDTO updatedUserDTO) throws Exception {
        UserDTO userDTO = getUserById(id);
        if (userDTO == null) {
            throw new HttpStatusException(HttpStatusEnum.NOT_FOUND);
        }
        if (updatedUserDTO.getName() != null) {
            userDTO.setName(updatedUserDTO.getName());
        }

        updateUser(userDTO);
        return HttpStatus.OK;
    }

    public HttpStatus deleteUser(Long userId) {
        boolean removed = userDTOS.removeIf(u -> u.getId().equals(userId));
        if (!removed) {
            throw new HttpStatusException(HttpStatusEnum.NOT_FOUND);
        }
        return HttpStatus.OK;
    }

    public List<UserDTO> getUsersByPrefix(String prefix) {
        List<UserDTO> matchingUsers = userDTOS.stream()
                .filter(u -> u.getName().startsWith(prefix))
                .collect(Collectors.toList());
        if (matchingUsers.isEmpty()) {
            throw new HttpStatusException(HttpStatusEnum.NOT_FOUND);
        }

        return matchingUsers;
    }
}
