package com.treex.redis.service.web;

import com.treex.redis.model.Task;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by seymurmanafov on 2020. 05. 26..
 */
@Component
public class TaskFacade {

    private final RestTemplate restTemplate;

    public TaskFacade(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Task> getTasks(String reqId) {
        return restTemplate.getForObject("http://localhost:8084/tasks/{requestId}", List.class, reqId);
    }

    public void addTasks(String reqId) {
        restTemplate.postForObject("http://localhost:8084/tasks/{requestId}", null, String.class, reqId);
    }
}
