package ru.ithub.jucr.thirdtasktest.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.ithub.jucr.thirdtasktest.exception.HttpStatusEnum;
import ru.ithub.jucr.thirdtasktest.exception.HttpStatusException;
import ru.ithub.jucr.thirdtasktest.model.dto.user.CreateUserDto;
import ru.ithub.jucr.thirdtasktest.repository.UserRepository;
import ru.ithub.jucr.thirdtasktest.model.dto.user.UserDto;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserDto getByName(String username) {
        return userRepository.getUserByName(username)
                .orElseThrow(() -> new HttpStatusException(HttpStatusEnum.NOT_FOUND));
    }

    public UserDto getUserById(Long userId) {
        Optional<UserDto> user = userRepository.getUserDtos().stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst();

        if (user.isPresent()){
            return user.get();
        } else{
            throw new HttpStatusException(HttpStatusEnum.NOT_FOUND);
        }
    }

    public HttpStatus createUser(CreateUserDto userData){
        boolean userExists = userRepository.getCreateUserDTOS().stream().anyMatch(u -> u.getName().equals(userData.getName()));
        if (userExists) {
            throw new HttpStatusException(HttpStatusEnum.BAD_REQUEST);
        }

        CreateUserDto user = new CreateUserDto(userData.getName(), userData.getAge(), userData.getDateOfBirth());
        userRepository.getCreateUserDTOS().add(user);
        return HttpStatus.OK;
    }
}
