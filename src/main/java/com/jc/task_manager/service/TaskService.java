package com.jc.task_manager.service;

import com.jc.task_manager.model.Task;
import com.jc.task_manager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void addTask(Task task) {
        taskRepository.save(task);
        System.out.println("Task added");
    }

    public void removeTask(Long id) {
        taskRepository.deleteById(id);
        System.out.println("Task removed");
    }

    public Task getTask(Long id) {
        Task task = taskRepository.findById(id);
        System.out.println("Task retrieved");
        return task;
    }

    public List<Task> getFinishedTasks() {
        System.out.println("Retrieved finished tasks");
        return taskRepository.findAll().stream().filter(task -> task.getStatus().equals("Completed")).toList();
    }

    public List<Task> getUnfinishedTasks() {
        System.out.println("Retrieved unfinished tasks");
        return taskRepository.findAll().stream().filter(task -> task.getStatus().equals("Not Completed")).toList();
    }

    public void markAsDone(Long id) {
        Task task = taskRepository.findById(id);
        if (task == null) {
            return;
        }
        taskRepository.markAsDone(id);
        System.out.println("Marked as done");
    }

    public void markAsUnDone(Long id) {
        Task task = taskRepository.findById(id);
        if (task == null) {
            return;
        }
        task.setStatus("Not Completed");
        taskRepository.save(task);
        System.out.println("Marked as un-done");
    }

    public boolean isDone(Long id) {
        Task task = taskRepository.findById(id);
        if (task == null) {
            return false;
        }
        return task.getStatus().equals("Completed");
    }


}
