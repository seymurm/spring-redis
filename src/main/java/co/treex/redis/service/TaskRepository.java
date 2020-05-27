package co.treex.redis.service;

import co.treex.redis.model.Task;
import co.treex.redis.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by seymurmanafov on 2020. 05. 25..
 */
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUser(User user);

}
