package ru.ithub.jucr.thirdtasktest.service;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import ru.ithub.jucr.thirdtasktest.exception.HttpStatusException;
import ru.ithub.jucr.thirdtasktest.model.dto.user.CreateUserDto;
import ru.ithub.jucr.thirdtasktest.repository.UserRepository;
import ru.ithub.jucr.thirdtasktest.model.dto.user.UserDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.eq;

@SpringBootTest
public class UserServiceTest {
    @MockBean
    private UserRepository userRepository = new UserRepository();
    private UserService userService = new UserService(userRepository);

//    @Test
//    public void getUserById_whenUserExists_thenUser() {
//        Long id = 1L;
//
//        UserDTO expectedUser = UserDTO.builder()
//                .id(1L)
//                .name("test")
//                .email("test@mail.com")
//                .build();
//
//        when(userRepository.getUserById(id))
//                .thenReturn(Optional.of(expectedUser));
//
//        UserDTO actualUser = userService.getUserById(id);
//
//        assertEquals(expectedUser, actualUser);
//    }

    @Test
    public void getUserByName_whenUserExists_thenUser() {
        String name = "Katya";
        UserDTO expectedUser = UserDTO.builder()
                .id(1L)
                .name("Katya")
                .email("test@mail.com")
                .build();

        when(userRepository.getUserByName(eq(name)))
                .thenReturn(Optional.of(expectedUser));

        UserDTO actualUser = userService.getByName(name);
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void getUserById_whenUserNotExists_thanException() {
        Long invalidUserId = userRepository.getNextId();

        assertThrows(HttpStatusException.class, () -> userService.getUserById(invalidUserId));
    }


    //ВОЗВРАЩАТЬ НЕ СТАТУС, А ПОЛЬЗОВАТЕЛЯ И МЫ ПРОВЕРЯЕМ ЕГО НАЛИЧИЕ В РЕПОЗИТОРИИ
    @Test
    public void createUser_thenReturnStatusOk() throws ParseException {
        String dateString = "2024-04-22";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(dateString);
        CreateUserDto createUserDTO = new CreateUserDto("David", 20, date);

        HttpStatus httpStatus = userService.createUser(createUserDTO);

        assertEquals(HttpStatus.OK, httpStatus);
    }

    @Test
    public void createUser_whenBadRequest_whenException() throws ParseException {
        String dateString = "2003-04-22";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(dateString);
        CreateUserDto existingUserDTO = new CreateUserDto("Alice", 25, date);

        assertThrows(HttpStatusException.class, () -> userService.createUser(existingUserDTO));
    }
}
