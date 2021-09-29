package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeDao employeeDao;

    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public Employee create(Employee employee) {
        Long id = employeeDao.insert(employee);
        return employeeDao.getById(id);
    }

    public Employee getOne(Long id){
        return employeeDao.getById(id);
    }

    public void deleteOne(Long id){
        employeeDao.deleteById(id);
    }

    public List<Employee> getAll(){
        return employeeDao.getAll();
    }


}
