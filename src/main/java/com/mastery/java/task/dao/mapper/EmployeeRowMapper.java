package com.mastery.java.task.dao.mapper;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Employee employee = new Employee();
        employee.setEmployeeId(resultSet.getLong("employee_id"));
        employee.setFirstName(resultSet.getString("first_name"));
        employee.setLastName(resultSet.getString("last_name"));
        employee.setGender(Gender.values()[resultSet.getInt("gender")]);
        employee.setJobTitle(resultSet.getString("job_title"));
        employee.setDepartmentId(resultSet.getLong("department_id"));
        employee.setDateOfBirth(resultSet.getDate("date_of_birth"));
        return employee;
    }
}
