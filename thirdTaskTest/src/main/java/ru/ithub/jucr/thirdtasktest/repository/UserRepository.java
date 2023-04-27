package ru.ithub.jucr.thirdtasktest.repository;

import lombok.Getter;
import org.springframework.stereotype.Service;
import ru.ithub.jucr.thirdtasktest.model.dto.user.CreateUserDto;
import ru.ithub.jucr.thirdtasktest.model.dto.user.UserDto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Getter
public class UserRepository {
    private List<UserDto> userDtos = new ArrayList<>();
    private List<CreateUserDto> createUserDTOS = new ArrayList<>();
    private Long nextId = 1L;

    {
        userDtos.add(new UserDto(nextId++, "Alice", "alice@example.com"));
        userDtos.add(new UserDto(nextId++, "Bob", "bob@example.com"));
        userDtos.add(new UserDto(nextId++, "Charlie", "charlie@example.com"));

        String dateString = "2003-04-22";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = dateFormat.parse(dateString);
            createUserDTOS.add(new CreateUserDto("Alice", 25, date));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public UserRepository() {}

    public Optional<UserDto> getUserById(Long userId) {
        return userDtos.stream().filter(u -> u.getId().equals(userId)).findFirst();
    }

    public Optional<UserDto> getUserByName(String username) {
        return userDtos.stream().filter(s -> s.getName().equals(username)).findFirst();
    }
}
