package ru.ithub.jucr.modulefour.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ithub.jucr.modulefour.exception.HttpStatusEnum;
import ru.ithub.jucr.modulefour.exception.HttpStatusException;
import ru.ithub.jucr.modulefour.model.dto.UserDto;
import ru.ithub.jucr.modulefour.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @Test
    public void getUserByName_whenUserExists_thenUser() {
        String name = "Katya";
        UserDto expectedUser = UserDto.builder()
                .id(1L)
                .name("Katya")
                .email("test@mail.com")
                .build();

        when(userRepository.getUserByName(eq(name)))
                .thenReturn(Optional.of(expectedUser));

        UserDto actualUser = userService.getByName(name);
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void getUserByName_whenUserNotExists_thenException() {
        String name = "test";

        HttpStatusEnum expectedExceptionStatusCode = HttpStatusEnum.NOT_FOUND;

        when(userRepository.getUserByName(name))
                .thenReturn(Optional.empty());

        HttpStatusException actualApplicationException = assertThrows(HttpStatusException.class, () -> userService.get(name));
        assertEquals(expectedExceptionStatusCode, actualApplicationException.getHttpStatusEnum());
    }

    @Test
    public void getUserById_whenUserNotExists_thenException() {
        Long invalidUserId = userRepository.getNextId();
        assertThrows(HttpStatusException.class, () -> userService.getUserById(invalidUserId));
    }
}
