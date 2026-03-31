package dev.java10x.cadastroapi.Tasks.Controller;

import org.springframework.web.bind.annotation.*;

//Localhost8080/tasks

@RestController
@RequestMapping("/task")
public class TaskController {

    @PostMapping("/create")
    public String createTask() {
        return "Task created successfully";
    }

    @GetMapping("/all")
    public String showAllTasks() { return "Show all tasks"; }

    @PutMapping("/update")
    public String updateTask() { return "Task updated successfully"; }

    @DeleteMapping("/delete")
    public String deleteTask() { return "Task deleted successfully"; }



}