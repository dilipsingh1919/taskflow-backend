package com.example.taskflow.repo;

import com.example.taskflow.Entity.Task;
import com.example.taskflow.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task,Long> {

    List<Task>findByUser(User user);
}
