package com.treex.redis.controller;

import com.treex.redis.model.Task;
import com.treex.redis.model.User;
import com.treex.redis.service.UserRepository;
import com.treex.redis.service.web.TaskFacade;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.security.SecureRandom;
import java.util.List;

/**
 * Created by seymurmanafov on 2020. 05. 25..
 */

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final UserRepository userRepository;
    private final RedisTemplate<String, User> redisTemplate;
    private final TaskFacade taskFacade;

    public TaskController(UserRepository userRepository, RedisTemplate<String, User> redisTemplate, TaskFacade taskFacade) {
        this.userRepository = userRepository;
        this.redisTemplate = redisTemplate;
        this.taskFacade = taskFacade;
    }

    @GetMapping
    public List<Task> getTasks(Principal principal) {
        User user = userRepository.findByUsername(principal.getName());

        SecureRandom random = new SecureRandom();
        String requestId = String.valueOf(random.nextInt(100000));

        redisTemplate.opsForHash().put(requestId, "user", user);
        return taskFacade.getTasks(requestId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addTask(Principal principal) {
        User user = userRepository.findByUsername(principal.getName());

        SecureRandom random = new SecureRandom();
        String requestId = String.valueOf(random.nextInt(100000));

        redisTemplate.opsForHash().put(requestId, "user", user);
        taskFacade.addTasks(requestId);

    }

}
