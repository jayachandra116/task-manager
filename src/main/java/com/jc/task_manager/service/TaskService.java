package com.jc.task_manager.service;

import com.jc.task_manager.model.Task;
import com.jc.task_manager.model.TaskStatus;
import com.jc.task_manager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    private static Long lastId = 101L;

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    public Task createTask(Task task) {
        task.setId(lastId++);
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        task.setStatus(TaskStatus.TODO);
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }


    public Task getTaskById(Long id) {
        return taskRepository.findById(id);
    }


    public Task updateTask(Long id, Task task) {
        task.setUpdatedAt(LocalDateTime.now());
        int updatedIndex = taskRepository.update(task);
        System.out.println("Updated task at index: " + updatedIndex);
        return getTaskById(task.getId());
    }


    public void deleteTask(Long id) {
        boolean deleted = taskRepository.deleteById(id);
        System.out.println("Deleted : " + deleted);
    }
}
