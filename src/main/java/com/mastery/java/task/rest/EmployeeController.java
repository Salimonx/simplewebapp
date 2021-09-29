package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {


    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @PostMapping
    ResponseEntity<Employee> create(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.create(employee));
    }

    @GetMapping("/{id}")
    ResponseEntity<Employee> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(employeeService.getOne(id));
    }

    @GetMapping
    ResponseEntity<List<Employee>> getall() {
        return ResponseEntity.ok(employeeService.getAll());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        employeeService.deleteOne(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping
    void update() {

    }


}
