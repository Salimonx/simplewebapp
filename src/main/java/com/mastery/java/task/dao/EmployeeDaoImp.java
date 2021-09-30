package com.mastery.java.task.dao;

import com.mastery.java.task.dao.mapper.EmployeeRowMapper;
import com.mastery.java.task.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EmployeeDaoImp implements EmployeeDao {

    private final static String INSERT_SQL = "INSERT INTO public.employee (first_name, last_name, department_id, job_title, gender, date_of_birth) VALUES (?, ?, ?, ?, ?, ?) RETURNING employee_id";
    private final static String SELECT_ONE_SQL = "SELECT * FROM public.employee where employee_id = ?";
    private final static String DELETE_ONE_SQL = "DELETE FROM public.employee where employee_id = ?";
    private final static String SELECT_ALL_SQL = "SELECT * FROM public.employee ORDER BY employee_id";
    private final static String UPDATE_ONE_SQL = "UPDATE public.employee SET first_name = ?, last_name = ?, department_id = ?, job_title = ?, gender = ?, date_of_birth = ? where employee_id = ?";


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeDaoImp(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public Long insert(Employee employee) {
        return jdbcTemplate.queryForObject(INSERT_SQL, Long.class,
                employee.getFirstName(),
                employee.getLastName(),
                employee.getDepartmentId(),
                employee.getJobTitle(),
                employee.getGender().ordinal(),
                employee.getDateOfBirth());
    }

    public Optional<Employee> getById(Long id) {
        List<Employee> query = jdbcTemplate.query(SELECT_ONE_SQL, new EmployeeRowMapper(), id);
        return Optional.ofNullable(query.isEmpty() ? null : query.get(0));
    }

    public void deleteById(Long id) {
        jdbcTemplate.update(DELETE_ONE_SQL, id);
    }

    public List<Employee> getAll() {
        return jdbcTemplate.query(SELECT_ALL_SQL, new EmployeeRowMapper());
    }

    public void updateById(Employee employee) {
        jdbcTemplate.update(UPDATE_ONE_SQL,
                employee.getFirstName(),
                employee.getLastName(),
                employee.getDepartmentId(),
                employee.getJobTitle(),
                employee.getGender().ordinal(),
                employee.getDateOfBirth(),
                employee.getEmployeeId());
    }
}
