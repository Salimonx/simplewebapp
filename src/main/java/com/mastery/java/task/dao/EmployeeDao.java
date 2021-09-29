package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDao {

    private final static String INSERT_SQL = "INSERT INTO public.employee (first_name, last_name, department_id, job_title, gender, date_of_birth) VALUES (?, ?, ?, ?, ?, ?) RETURNING employee_id";
    private final static String SELECT_ONE_SQL = "SELECT * FROM public.employee where employee_id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
        return jdbcTemplate.queryForObject(SELECT_ONE_SQL, (rs, rowNum) -> {
            Employee employee = new Employee();
            employee.setEmployeeId(rs.getLong("employee_id"));
            employee.setFirstName(rs.getString("first_name"));
            employee.setLastName(rs.getString("last_name"));
            employee.setGender(Gender.values()[rs.getInt("gender")]);
            rs.getObject("", Gender.class);
            employee.setJobTitle(rs.getString("job_title"));
            employee.setDepartmentId(rs.getLong("department_id"));
            employee.setDateOfBirth(rs.getDate("date_of_birth"));
            return employee;
        }, id);
    }

}
