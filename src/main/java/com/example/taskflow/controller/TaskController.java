package com.example.taskflow.controller;

import com.example.taskflow.Entity.Task;
import com.example.taskflow.dto.TaskRequest;
import com.example.taskflow.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private  final TaskService taskService;

    @GetMapping
    public ResponseEntity<?> getAllTasks(@RequestHeader("Authorization")String token){
       return ResponseEntity.ok(taskService.getAllTask(token));
    }

    @PostMapping
    public  ResponseEntity<?> createTask(@RequestHeader("Authorization") String token, @RequestBody TaskRequest taskRequest){

        return  ResponseEntity.ok(taskService.createTask(token,taskRequest));
    }
    @PutMapping("/{id}/status")

    public ResponseEntity<?> updateStatus(@RequestHeader("Authorization") String token,@PathVariable Long id,@RequestParam Task.TaskStauts status){
        return  ResponseEntity.ok(taskService.updateStatus(token,id,status));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteTask(@RequestHeader("Authorization") String token, @PathVariable Long id){
        taskService.deleteTask(token,id);
        return ResponseEntity.ok("Task delete ho gayi");

    }

}
