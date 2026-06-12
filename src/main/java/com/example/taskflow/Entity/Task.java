package com.example.taskflow.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private  String title;

    private String description;
    @Enumerated(value = EnumType.STRING)
    private  TaskStauts  status= TaskStauts.TODO;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public  User user;

    public enum TaskStauts{
        TODO, IN_PROGRESS, DONE
    }

}
