package ru.ithub.jucr.thirdtasktest.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.ithub.jucr.thirdtasktest.exception.HttpStatusEnum;
import ru.ithub.jucr.thirdtasktest.exception.HttpStatusException;
import ru.ithub.jucr.thirdtasktest.model.dto.CreateUserDTO;
import ru.ithub.jucr.thirdtasktest.model.dto.UserDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private List<UserDTO> userDTOS = new ArrayList<>();
    private List<CreateUserDTO> createUserDTOS = new ArrayList<>();
    private Long nextId = 1L;

    public UserService() {
        userDTOS.add(new UserDTO(nextId++, "Alice", "alice@example.com"));
        userDTOS.add(new UserDTO(nextId++, "Bob", "bob@example.com"));
        userDTOS.add(new UserDTO(nextId++, "Charlie", "charlie@example.com"));
        String dateString = "2003-04-22";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = dateFormat.parse(dateString);
            createUserDTOS.add(new CreateUserDTO("Alice", 25, date));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

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

    public HttpStatus createUser(CreateUserDTO userData){
        boolean userExists = createUserDTOS.stream().anyMatch(u -> u.getName().equals(userData.getName()));
        if (userExists) {
            throw new HttpStatusException(HttpStatusEnum.BAD_REQUEST);
        }

        CreateUserDTO user = new CreateUserDTO(userData.getName(), userData.getAge(), userData.getDateOfBirth());
        createUserDTOS.add(user);
        return HttpStatus.OK;
    }
}
