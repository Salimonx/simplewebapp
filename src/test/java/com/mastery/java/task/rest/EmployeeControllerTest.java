package com.mastery.java.task.rest;

import com.mastery.java.task.helper.EmployeeHelper;
import com.mastery.java.task.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import static com.mastery.java.task.helper.EmployeeHelper.EMPLOYEE_ID;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.util.Collections;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService service;

    @Test
    public void testGetAll_EmptyCollection () throws Exception {
        Mockito.when(service.getAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/employee"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json("[]"));
    }
    @Test
    public void testGetOne_EmployeeSuccess() throws Exception {
        Mockito.when(service.getOne(EMPLOYEE_ID)).thenReturn(EmployeeHelper.getEmployee());

        mockMvc.perform(get("/employee/" + EMPLOYEE_ID))
                .andDo(print())
                .andExpect(jsonPath("$.employeeId").value(EMPLOYEE_ID));
    }
}
