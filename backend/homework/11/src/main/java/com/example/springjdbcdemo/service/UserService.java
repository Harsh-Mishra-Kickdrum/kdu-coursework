package com.example.springjdbcdemo.service;

import com.example.springjdbcdemo.dao.UserDao;
import com.example.springjdbcdemo.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Saves a user to the database.
     *
     * @param userDto The user data transfer object.
     */
    public void saveUser(UserDto userDto) {
        try {
            userDao.saveUser(userDto);
            log.info("User saved successfully: {}", userDto.getUsername());
        } catch (Exception e) {
            log.error("Error saving user: {}", e.getMessage(), e);
            throw e;
        }
    }
}
