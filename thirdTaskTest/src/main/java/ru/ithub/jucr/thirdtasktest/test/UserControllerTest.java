package ru.ithub.jucr.thirdtasktest.test;

import ru.ithub.jucr.thirdtasktest.controller.UserController;
import ru.ithub.jucr.thirdtasktest.service.UserService;


public class UserControllerTest {
    UserController userController = new UserController(new UserService());
}
