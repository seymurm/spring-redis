package co.treex.redis.controller;

import co.treex.redis.model.Task;
import co.treex.redis.model.User;
import co.treex.redis.service.TaskRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by seymurmanafov on 2020. 05. 25..
 */

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final RedisTemplate<String, User> redisTemplate;

    public TaskController(TaskRepository taskRepository, UserRepository userRepository, RedisTemplate<String, User> redisTemplate) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.redisTemplate = redisTemplate;
    }

    @PostMapping("/{requestId}")
    public void addTask(@PathVariable String requestId) {
        User user = (User) redisTemplate.opsForHash().get(requestId, "user");
        user.setPassword("");
        userRepository.save(user);

        Task task = new Task();
        task.setName("task1");
        task.setUser(user);
        task.setDescriptiion("taskDesc");
        taskRepository.save(task);
    }

    @GetMapping("/{requestId}")
    public List<Task> getTasks(@PathVariable String requestId) {
        User user = (User) redisTemplate.opsForHash().get(requestId, "user");
        return taskRepository.findByUser(user);
    }

    @PutMapping("/{id}")
    public void editTask(@PathVariable long id, @RequestBody Task task) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
        taskRepository.save(existingTask);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable long id) {
        Task taskToDel = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
        taskRepository.delete(taskToDel);
    }
}
