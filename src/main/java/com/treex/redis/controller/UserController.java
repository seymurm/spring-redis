package com.treex.redis.controller;

import com.treex.redis.model.User;
import com.treex.redis.model.dto.UserDTO;
import com.treex.redis.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by seymurmanafov on 2020. 04. 18..
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody UserDTO dto) {
        User user = new User(dto);
        userRepository.save(user);
    }

}
