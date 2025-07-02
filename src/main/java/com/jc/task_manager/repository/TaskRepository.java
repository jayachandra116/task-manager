package com.jc.task_manager.repository;

import com.jc.task_manager.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TaskRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Task task) {
        String sql = "INSERT INTO tasks (title,description,status) values (?,?,?)";
        int rows = jdbcTemplate.update(sql, task.getTitle(), task.getDescription(), task.getStatus());
        System.out.println("Affected rows: " + rows);
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
        int rows = jdbcTemplate.update(sql, task.getTitle(), task.getDescription(), task.getStatus(), task.getId());
        System.out.println("Affected rows: " + rows);
        return rows;
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM tasks WHERE id=?";
        int rows = jdbcTemplate.update(sql, id);
        System.out.println("Affected rows: " + rows);
        return rows;
    }

    public void markAsDone(Long id) {
        String sql = "UPDATE tasks SET status='Completed' WHERE id=?";
        int rows = jdbcTemplate.update(sql, id);
        System.out.println("Affected rows: " + rows);
    }

    public void markAsUnDone(Long id) {
        String sql = "UPDATE tasks SET status='Not Completed' WHERE id=?";
        int rows = jdbcTemplate.update(sql, id);
        System.out.println("Affected rows: " + rows);
    }

}
