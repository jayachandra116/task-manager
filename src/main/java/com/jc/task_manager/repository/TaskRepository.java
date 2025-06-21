package com.jc.task_manager.repository;

import com.jc.task_manager.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(Task task) {
        String sql = "INSERT INTO tasks (title,description,status) values (?,?,?)";
        return jdbcTemplate.update(sql, task.getTitle(), task.getDescription(), task.getStatus());
    }

    public Task findById(Long id) {
        String sql = "SELECT * FROM tasks WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Task.class), id);
    }

    public List<Task> findAll() {
        String sql = "SELECT * FROM tasks";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Task.class));
    }

    public int update(Task task) {
        String sql = "UPDATE tasks SET title=?, description=?,status=? WHERE id=?";
        return jdbcTemplate.update(sql, task.getTitle(), task.getDescription(), task.getStatus(), task.getId());
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM tasks WHERE id=?";
        return jdbcTemplate.update(sql, id);
    }

}
