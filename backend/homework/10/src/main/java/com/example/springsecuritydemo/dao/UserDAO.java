package com.example.springsecuritydemo.dao;

import com.example.springsecuritydemo.dto.UserDto;
import com.example.springsecuritydemo.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserDAO {
    List<User> findAll();
    Optional<User> findByUsername(String username);
    User save(User user);
}
