package com.jc.task_manager.dto;

import com.jc.task_manager.model.TaskPriority;
import com.jc.task_manager.model.TaskStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskRequest {
    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private LocalDate dueDate;
}
