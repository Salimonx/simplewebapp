package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    ResponseEntity<String> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok("<h1> Alive </h1>");
    }

    @GetMapping
    void getall() {

    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") Long id) {

    }

    @PutMapping
    void update() {

    }


}
