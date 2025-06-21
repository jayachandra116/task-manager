package com.jc.task_manager;

import com.jc.task_manager.model.Task;
import com.jc.task_manager.repository.TaskRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class TaskManagerApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(TaskManagerApplication.class, args);
        Task task = context.getBean(Task.class);
        task.setTitle("First task");
        task.setDescription("First task description");
        task.setStatus("Not completed");

//       Creating a new task
        System.out.println(createNewTask(context, task));

//        Searching by Id
        Long sampleTaskId = 1L;
        System.out.println(getTask(context, sampleTaskId));

//        Fetch multipleTasks
        List<Task> tasks = getAllTasks(context);
        System.out.println(tasks);

//        Update a task
        Task updatedTask = context.getBean(Task.class);
        updatedTask.setTitle("Updated task");
        updatedTask.setDescription("updated task description");
        updatedTask.setStatus("Completed");
        updatedTask.setId(2L);
        int updatedCount = updateTask(context, updatedTask);
        System.out.println(updatedCount);

//        Delete a task
        int deletedCount = deleteTask(context, 3L);
        System.out.println(deletedCount);

    }


    private static int createNewTask(ApplicationContext context, Task task) {
        TaskRepository taskRepository = context.getBean(TaskRepository.class);
        return taskRepository.save(task);
    }

    private static Task getTask(ApplicationContext context, Long id) {
        TaskRepository taskRepository = context.getBean(TaskRepository.class);
        return taskRepository.findById(id);
    }

    private static List<Task> getAllTasks(ApplicationContext context) {
        TaskRepository taskRepository = context.getBean(TaskRepository.class);
        return taskRepository.findAll();
    }

    private static int updateTask(ApplicationContext context, Task task) {
        TaskRepository taskRepository = context.getBean(TaskRepository.class);
        return taskRepository.update(task);
    }

    private static int deleteTask(ApplicationContext context, Long id) {
        TaskRepository taskRepository = context.getBean(TaskRepository.class);
        return taskRepository.deleteById(id);
    }

}
