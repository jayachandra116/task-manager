package com.jc.task_manager.repository;

import com.jc.task_manager.model.Task;
import com.jc.task_manager.model.TaskPriority;
import com.jc.task_manager.model.TaskStatus;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class TaskRepository {

    private final JdbcTemplate jdbcTemplate;

    public TaskRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(Task task) {
        return jdbcTemplate.update("""
                INSERT INTO tasks (title,description,status,priority, due_date, created_at, updated_at)
                VALUES (?,?,?,?,?,?,?)
                """, task.getTitle(), task.getDescription(), task.getStatus().name(), task.getPriority().name(), task.getDueDate(), task.getCreatedAt(), task.getUpdatedAt());
    }


    public List<Task> findAll() {
        return jdbcTemplate.query("SELECT * FROM tasks", (rs, rowNum) -> {
            Task task = new Task();
            task.setId(rs.getLong("id"));
            task.setTitle(rs.getString("title"));
            task.setDescription(rs.getString("description"));
            task.setStatus(TaskStatus.valueOf(rs.getString("status")));
            task.setPriority(TaskPriority.valueOf(rs.getString("priority")));

            Date dueDate = rs.getDate("due_date");
            if (dueDate == null) {
                task.setDueDate(null);
            } else {
                task.setDueDate(dueDate.toLocalDate());
            }

            task.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            task.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
            return task;
        });
    }


    public Task findById(Long id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM tasks WHERE id=?", (rs, rowNum) -> {
                Task task = new Task();
                task.setId(rs.getLong("id"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));
                task.setStatus(TaskStatus.valueOf(rs.getString("status")));
                task.setPriority(TaskPriority.valueOf(rs.getString("priority")));
                Date dueDate = rs.getDate("due_date");
                if (dueDate == null) {
                    task.setDueDate(null);
                } else {
                    task.setDueDate(dueDate.toLocalDate());
                }
                task.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                task.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
                return task;
            }, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public int update(Long id, Task task) {
        return jdbcTemplate.update("""
                UPDATE tasks
                SET title=?, description=?, status=?, priority=?,due_date=?, updated_at=?
                WHERE id=?
                """, task.getTitle(), task.getDescription(), task.getStatus().name(), task.getPriority().name(), task.getDueDate(), task.getUpdatedAt(), id);
    }

    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM tasks WHERE id=?", id);
    }
}
