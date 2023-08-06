package com.example.zadanie25;

import org.springframework.stereotype.Controller;

@Controller
public class TaskController {
    private TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


}
