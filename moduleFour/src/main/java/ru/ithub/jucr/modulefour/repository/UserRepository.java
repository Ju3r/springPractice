package ru.ithub.jucr.modulefour.repository;

import lombok.Getter;
import org.springframework.stereotype.Service;
import ru.ithub.jucr.modulefour.model.dto.CreateUserDto;
import ru.ithub.jucr.modulefour.model.dto.UserDto;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Getter
public class UserRepository {
    private List<UserDto> userDtos = new ArrayList<>();
    protected List<CreateUserDto> createUserDTOS = new ArrayList<>();
    private Long nextId = 1L;

    {
        userDtos.add(new UserDto(nextId++, "Alice", "alice@example.com"));
        userDtos.add(new UserDto(nextId++, "Bob", "bob@example.com"));
        userDtos.add(new UserDto(nextId++, "Charlie", "charlie@example.com"));

        LocalDateTime date = LocalDateTime.now();
        createUserDTOS.add(new CreateUserDto("Alice", 25, date));
        createUserDTOS.add(new CreateUserDto("Ben", 33, date));
        createUserDTOS.add(new CreateUserDto("George", 18, date));
    }

    public UserRepository() {}

    public Optional<UserDto> getUserById(Long userId) {
        return userDtos.stream().filter(u -> u.getId().equals(userId)).findFirst();
    }

    public Optional<UserDto> getUserByName(String username) {
        return userDtos.stream().filter(s -> s.getName().equals(username)).findFirst();
    }

    public Optional<CreateUserDto> getCreateUserByName(String username) {
        return createUserDTOS.stream().filter(s -> s.getName().equals(username)).findFirst();
    }

    public CreateUserDto createUserDto(CreateUserDto createUserDto) {
        CreateUserDto newUser = new CreateUserDto(createUserDto.getName(), createUserDto.getAge(), createUserDto.getDateOfBirth());
        createUserDTOS.add(newUser);
        return newUser;
    }
}