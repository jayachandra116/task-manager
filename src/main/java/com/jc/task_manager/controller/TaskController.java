package com.jc.task_manager.controller;

import com.jc.task_manager.model.Task;
import com.jc.task_manager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TaskController {

    @Autowired
    TaskService taskService;
    List<Task> tasks = new ArrayList<>(List.of(
            new Task(8L, "Learn Java", "Core Java", "Not Completed"),
            new Task(9L, "Learn Java Spring Core", "Spring", "Not Completed")
    ));

    public void createTasks() {
        for (Task task : tasks) {
            taskService.addTask(task);
        }
    }

    public void markTasksAsDone() {
        for (Task task : tasks) {
            taskService.markAsDone(task.getId());
        }
    }

    public List<Task> getTasks() {
        Task task1 = taskService.getTask(1L);
        Task task2 = taskService.getTask(2L);
        return new ArrayList<>(List.of(task1, task2));
    }


}
