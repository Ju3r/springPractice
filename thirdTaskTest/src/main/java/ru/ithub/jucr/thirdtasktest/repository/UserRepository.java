package ru.ithub.jucr.thirdtasktest.repository;

import lombok.Getter;
import org.springframework.stereotype.Repository;
import ru.ithub.jucr.thirdtasktest.model.dto.user.CreateUserDto;
import ru.ithub.jucr.thirdtasktest.model.dto.user.UserDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@Getter
public class UserRepository {
    private List<UserDTO> userDTOS = new ArrayList<>();
    private List<CreateUserDto> createUserDTOS = new ArrayList<>();
    private Long nextId = 1L;
    public UserRepository() {
        userDTOS.add(new UserDTO(nextId++, "Alice", "alice@example.com"));
        userDTOS.add(new UserDTO(nextId++, "Bob", "bob@example.com"));
        userDTOS.add(new UserDTO(nextId++, "Charlie", "charlie@example.com"));

        String dateString = "2003-04-22";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = dateFormat.parse(dateString);
            createUserDTOS.add(new CreateUserDto("Alice", 25, date));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<UserDTO> getUserById(Long userId) {
        return userDTOS.stream().filter(u -> u.getId().equals(userId)).findFirst();
    }

    public Optional<UserDTO> getUserByName(String username) {
        return userDTOS.stream().filter(s -> s.getName().equals(username)).findFirst();
    }
}
