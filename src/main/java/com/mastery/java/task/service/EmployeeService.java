package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class EmployeeService {

    private static final String PERSISTING_ERROR_MESSAGE = "Failure wile persisting employee";
    private static final String NOT_FOUND_MESSAGE = "Employee with id %s not found";

    private final EmployeeDao employeeDaoImp;

    @Autowired
    public EmployeeService(EmployeeDao employeeDaoImp) {
        this.employeeDaoImp = employeeDaoImp;
    }

    @Transactional
    public Employee create(Employee employee) {
        Long id = employeeDaoImp.insert(employee);
        return employeeDaoImp.getById(id)
                .orElseThrow(() -> new BusinessException(PERSISTING_ERROR_MESSAGE, INTERNAL_SERVER_ERROR));
    }

    public Employee getOne(Long id) {
        return employeeDaoImp.getById(id)
                .orElseThrow(() -> new BusinessException(format(NOT_FOUND_MESSAGE, id), NOT_FOUND));
    }

    public void deleteOne(Long id) {
        employeeDaoImp.deleteById(id);
    }

    public List<Employee> getAll() {
        return employeeDaoImp.getAll();
    }

    @Transactional
    public Employee updateOne(Employee employee, Long id) {
        employee.setEmployeeId(id);
        employeeDaoImp.updateById(employee);
        return employeeDaoImp.getById(id)
                .orElseThrow(() -> new BusinessException(format(NOT_FOUND_MESSAGE, id), NOT_FOUND));
    }

}
