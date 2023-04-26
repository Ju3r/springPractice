package ru.ithub.jucr.thirdtasktest.controller;

import ru.ithub.jucr.thirdtasktest.repository.UserRepository;
import ru.ithub.jucr.thirdtasktest.service.UserService;


public class UserControllerTest {
    UserController userController = new UserController(new UserService(new UserRepository()));
}
