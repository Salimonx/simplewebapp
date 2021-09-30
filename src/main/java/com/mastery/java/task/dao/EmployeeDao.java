package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDao {

    Long insert(Employee employee);

    Optional<Employee> getById(Long id);

    void deleteById(Long id);

    List<Employee> getAll();

    void updateById(Employee employee);

}
