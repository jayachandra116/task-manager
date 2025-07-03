package com.jc.task_manager.repository;

import com.jc.task_manager.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository {

    private List<Task> tasks;

    @Autowired
    public TaskRepository() {
        tasks = new ArrayList<>();
    }


    public Task save(Task task) {
        tasks.add(task);
        return task;
    }


    public List<Task> findAll() {
        return tasks;
    }


    public Task findById(Long id) {
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                return task;
            }
        }
        return null;
    }

    public int update(Task updatedTask) {
        for (Task task : tasks) {
            if (task.getId().equals(updatedTask.getId())) {
                task.setTitle(updatedTask.getTitle());
                task.setDescription(updatedTask.getDescription());
                task.setUpdatedAt(LocalDateTime.now());
                task.setStatus(updatedTask.getStatus());
                task.setPriority(updatedTask.getPriority());
                task.setAssignedTo(updatedTask.getAssignedTo());
                task.setDueDate(updatedTask.getDueDate());
                return tasks.indexOf(task);
            }
        }
        return -1;
    }

    public boolean deleteById(Long id) {
        return tasks.remove(findById(id));
    }
}
