package com.jc.task_manager.controller;

import com.jc.task_manager.dto.TaskRequest;
import com.jc.task_manager.dto.TaskResponse;
import com.jc.task_manager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskRequest taskRequest) {
        System.out.println("POST /api/tasks => Creating new task");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(taskService.createTask(taskRequest));
    }

    @GetMapping
    public List<TaskResponse> getAllTasks() {
        System.out.println("GET /api/tasks => Fetching all tasks");
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public TaskResponse getTaskById(@PathVariable Long id) {
        System.out.println("GET /api/task/{id} => Fetching a single task with id: " + id);
        return taskService.getTaskById(id);
    }

    @PutMapping("/{id}")
    public TaskResponse updateTask(@PathVariable Long id,@Valid @RequestBody TaskRequest taskRequest) {
        System.out.println("PUT /api/task/{id} => Updating a task with id: " + id);
        return taskService.updateTask(id, taskRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        System.out.println("DELETE /api/task/{id} => Deleting a task with id: " + id);
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

}
