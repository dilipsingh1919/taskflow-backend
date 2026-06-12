package com.example.taskflow.service;

import com.example.taskflow.Entity.Task;
import com.example.taskflow.Entity.User;
import com.example.taskflow.dto.TaskRequest;
import com.example.taskflow.repo.TaskRepo;
import com.example.taskflow.repo.UserRepo;
import com.example.taskflow.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private  final UserRepo userRepo;
    private  final TaskRepo taskRepo;
    private  final JwtUtil jwtUtil;


    private User getUserFromToken(String Token){
        String email= jwtUtil.extractEmail(Token.replace("Bearer",""));
        return userRepo.findByEmail(email).orElseThrow(()->new RuntimeException("user nai mila "));
    }
    public List<Task> getAllTask(String token){
        User user = getUserFromToken(token);
        return taskRepo.findByUser(user);


    }
    public Task createTask(String token, TaskRequest taskRequest){
        User user = getUserFromToken(token);
        Task task= new Task();
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setUser(user);

        return taskRepo.save(task);


    }
    public Task updateStatus(String token, Long taskId, Task.TaskStauts stauts){
        User user= getUserFromToken(token);
        Task task= taskRepo.findById(taskId).orElseThrow(()->new RuntimeException("task nai mila "));

        if (!task.getUser().getId().equals(user.getId())){
            throw  new RuntimeException("ye tumari nai hai ");
        }
        task.setStatus(stauts);
        return  taskRepo.save(task);
    }
    public  void deleteTask(String Token ,  Long taskId){
        User user = getUserFromToken(Token);
        Task task = taskRepo.findById(taskId).orElseThrow(()-> new RuntimeException("task nai mila "));
        if (!task.getUser().getId().equals(user.getId())){
            throw  new RuntimeException("Yeh tumari task nai hai ");
        }
        taskRepo.delete(task);
    }


}
