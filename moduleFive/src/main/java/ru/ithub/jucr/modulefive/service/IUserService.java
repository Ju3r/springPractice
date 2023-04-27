package ru.ithub.jucr.modulefive.service;

import ru.ithub.jucr.modulefive.model.dto.CreateUserDto;
import ru.ithub.jucr.modulefive.model.dto.UserDto;

public interface IUserService {
    UserDto createUser(CreateUserDto createUserDto);
}