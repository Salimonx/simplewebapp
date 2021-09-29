package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.mapper.CustomRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeDao {

    private final static String INSERT_SQL = "INSERT INTO public.employee (first_name, last_name, department_id, job_title, gender, date_of_birth) VALUES (?, ?, ?, ?, ?, ?) RETURNING employee_id";
    private final static String SELECT_ONE_SQL = "SELECT * FROM public.employee where employee_id = ?";
    private final static String DELETE_ONE_SQL = "DELETE FROM public.employee where employee_id = ?";
    private final static String SELECT_ALL_SQL = "SELECT * FROM public.employee";


    private final JdbcTemplate jdbcTemplate;

    public EmployeeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long insert(Employee employee) {
        return jdbcTemplate.queryForObject(INSERT_SQL, Long.class,
                employee.getFirstName(),
                employee.getLastName(),
                employee.getDepartmentId(),
                employee.getJobTitle(),
                employee.getGender().ordinal(),
                employee.getDateOfBirth()
        );
    }

    public Employee getById(Long id) {
        return jdbcTemplate.queryForObject(SELECT_ONE_SQL, new CustomRowMapper(), id);
    }

    public void deleteById(Long id) {
        jdbcTemplate.update(DELETE_ONE_SQL, id);
    }

    public List<Employee> getAll() {
        return jdbcTemplate.query(SELECT_ALL_SQL, new CustomRowMapper());
    }

    public Employee updateById(){
        return new Employee();
    }



}
