package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final List<Task> tasks = new ArrayList<>();

    @GetMapping
    public List<Task> getAllTasks() {
        return tasks;
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        // Implement logic to find and return a task by ID
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        // Implement logic to create a new task
        task.setId(tasks.size() + 1L);
        tasks.add(task);
        return task;
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        // Implement logic to update an existing task
        Task existingTask = tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (existingTask != null) {
            existingTask.setName(updatedTask.getName());
            existingTask.setCompleted(updatedTask.isCompleted());
        }

        return existingTask;
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        // Implement logic to delete a task by ID
        tasks.removeIf(task -> task.getId().equals(id));
    }
}
