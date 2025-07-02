package com.jc.task_manager;

import com.jc.task_manager.controller.TaskController;
import com.jc.task_manager.model.Task;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TaskManagerApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(TaskManagerApplication.class, args);

        TaskController taskController = context.getBean(TaskController.class);

        taskController.createTasks();

        for (Task task : taskController.getTasks()) {
            System.out.println(task);
        }

        taskController.markTasksAsDone();

        for (Task task : taskController.getTasks()) {
            System.out.println(task);
        }

    }

}
