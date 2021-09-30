package com.mastery.java.task.helper;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;

import java.util.Date;
import java.util.Optional;

public class EmployeeHelper {

    public final static Long EMPLOYEE_ID = 1L;
    public final static Date EMPLOYEE_DATE = new Date();
    public final static Gender EMPLOYEE_GENDER = Gender.MALE;
    public final static Long EMPLOYEE_DEPARTMENT = 1L;
    public final static String EMPLOYEE_TITLE = "Junior Java Developer";
    public final static String EMPLOYEE_FIRST_NAME = "Paul";
    public final static String EMPLOYEE_LAST_NAME = "Salimon";

    public static Employee getEmployee() {
        return new Employee() {{
            setEmployeeId(EMPLOYEE_ID);
            setDateOfBirth(EMPLOYEE_DATE);
            setGender(EMPLOYEE_GENDER);
            setDepartmentId(EMPLOYEE_DEPARTMENT);
            setJobTitle(EMPLOYEE_TITLE);
            setFirstName(EMPLOYEE_FIRST_NAME);
            setLastName(EMPLOYEE_LAST_NAME);
        }};
    }

    public static Optional<Employee> getOptionalEmployee() {
        return Optional.of(getEmployee());
    }

}
