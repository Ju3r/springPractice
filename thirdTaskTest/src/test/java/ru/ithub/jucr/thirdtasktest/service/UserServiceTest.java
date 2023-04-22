package ru.ithub.jucr.thirdtasktest.service;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import ru.ithub.jucr.thirdtasktest.exception.HttpStatusException;
import ru.ithub.jucr.thirdtasktest.model.dto.CreateUserDTO;
import ru.ithub.jucr.thirdtasktest.model.dto.UserDTO;
import ru.ithub.jucr.thirdtasktest.service.UserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class UserServiceTest {
    UserService userService = new UserService();
    @Test
    public void getUserByIdReturnUser() {
        Long userId = 1L;
        UserDTO userDTO = userService.getUserById(userId);

        assertNotNull(userDTO);
        assertEquals(userId, userDTO.getId());
    }

    @Test
    public void getUserByIdReturnNotFound() {
        Long invalidUserId = 88L;
        assertThrows(HttpStatusException.class, () -> userService.getUserById(invalidUserId));
    }

    @Test
    public void createUserReturnStatusOk() throws ParseException {
        String dateString = "2024-04-22";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(dateString);

        CreateUserDTO createUserDTO = new CreateUserDTO("David", 20, date);
        HttpStatus httpStatus = userService.createUser(createUserDTO);

        assertEquals(HttpStatus.OK, httpStatus);
    }

    @Test
    public void createUserReturnBadRequest() throws ParseException {
        String dateString = "2003-04-22";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(dateString);

        CreateUserDTO existingUserDTO = new CreateUserDTO("Alice", 25, date);
        assertThrows(HttpStatusException.class, () -> userService.createUser(existingUserDTO));
    }
}
