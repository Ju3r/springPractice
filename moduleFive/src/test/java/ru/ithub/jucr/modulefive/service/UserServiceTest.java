package ru.ithub.jucr.modulefive.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ithub.jucr.modulefive.model.dto.CreateUserDto;
import ru.ithub.jucr.modulefive.model.dto.UserDto;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    private UserService service;

    @ParameterizedTest
    @MethodSource("ru.ithub.jucr.modulefive.argument.UserServiceArg#getCreateUserDtoArgs")
    void createUser_whenValidUser_thenUser(CreateUserDto createUserDto,
                                           UserDto expectedUserDto) {
        UserDto actualUserDto = service.createUser(createUserDto);

        assertEquals(expectedUserDto, actualUserDto);
    }

}