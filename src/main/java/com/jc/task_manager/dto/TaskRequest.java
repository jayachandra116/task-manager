package com.jc.task_manager.dto;

import com.jc.task_manager.model.TaskPriority;
import com.jc.task_manager.model.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskRequest {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    private TaskStatus status;

    @NotNull(message = "Priority is required")
    private TaskPriority priority;
    
    private LocalDate dueDate;
}
