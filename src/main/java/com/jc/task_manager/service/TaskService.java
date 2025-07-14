package com.jc.task_manager.service;

import com.jc.task_manager.dto.TaskRequest;
import com.jc.task_manager.dto.TaskResponse;
import com.jc.task_manager.exception.TaskNotFoundException;
import com.jc.task_manager.model.Task;
import com.jc.task_manager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    private Task mapToTask(TaskRequest request) {
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setPriority(request.getPriority());
        task.setDueDate(request.getDueDate());
        return task;
    }

    private TaskResponse mapToResponse(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .priority(task.getPriority())
                .status(task.getStatus())
                .dueDate(task.getDueDate())
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .build();
    }


    public TaskResponse createTask(TaskRequest taskRequest) {
        Task task = mapToTask(taskRequest);
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        taskRepository.save(task);
        return mapToResponse(task);
    }


    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll().stream().map(this::mapToResponse).toList();
    }


    public TaskResponse getTaskById(Long id) {
        Task task = taskRepository.findById(id);
        if (task == null) {
            throw new TaskNotFoundException(id);
//            return null;
        }
        return mapToResponse(task);
    }


    public TaskResponse updateTask(Long id, TaskRequest taskRequest) {
        Task existing = taskRepository.findById(id);
        if (existing == null) {
            throw new TaskNotFoundException(id);
        }
        existing.setTitle(taskRequest.getTitle());
        existing.setDescription(taskRequest.getDescription());
        existing.setPriority(taskRequest.getPriority());
        existing.setStatus(taskRequest.getStatus());
        if (taskRequest.getDueDate() != null) {
            existing.setDueDate(taskRequest.getDueDate());
        }
        existing.setUpdatedAt(LocalDateTime.now());
        taskRepository.update(id, existing);
        return mapToResponse(existing);
    }


    public void deleteTask(Long id) {
        taskRepository.delete(id);
    }
}
