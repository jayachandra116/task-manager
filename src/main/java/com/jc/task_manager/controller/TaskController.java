package com.jc.task_manager.controller;

import com.jc.task_manager.model.Task;
import com.jc.task_manager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/task")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        System.out.println("POST /api/tasks => Creating new task");
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        System.out.println("GET /api/tasks => Fetching all tasks");
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        System.out.println("GET /api/task/{id} => Fetching a single task with id: " + id);
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @PutMapping("/task/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        System.out.println("PUT /api/task/{id} => Updating a task with id: " + id);
        Task updatedTask = taskService.updateTask(id, task);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        System.out.println("DELETE /api/task/{id} => Deleting a task with id: " + id);
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

}
