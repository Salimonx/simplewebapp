package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.exception.BusinessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.mastery.java.task.helper.EmployeeHelper.EMPLOYEE_ID;
import static com.mastery.java.task.helper.EmployeeHelper.getOptionalEmployee;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    EmployeeDao employeeDao;
    @InjectMocks
    EmployeeService employeeService;

    @Test
    public void testGetAll_EmptyList() {
        Mockito.when(employeeDao.getAll()).thenReturn(Collections.emptyList());
        List<Employee> all = employeeService.getAll();
        assertThat(all).isEmpty();
    }

    @Test
    public void testGetOne_EmployeeSuccess() {
        Mockito.when(employeeDao.getById(EMPLOYEE_ID)).thenReturn(getOptionalEmployee());
        Employee employee = employeeService.getOne(EMPLOYEE_ID);
        assertThat(employee).isNotNull();
        assertThat(employee.getEmployeeId()).isEqualTo(EMPLOYEE_ID);
    }

    @Test
    public void testGetOne_EmployeeNotFound() {
        Mockito.when(employeeDao.getById(EMPLOYEE_ID)).thenReturn(Optional.empty());
        BusinessException businessException = assertThrows(BusinessException.class, () -> employeeService.getOne(EMPLOYEE_ID));
        assertThat(businessException.getCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
