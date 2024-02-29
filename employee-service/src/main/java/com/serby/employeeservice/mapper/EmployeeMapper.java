package com.serby.employeeservice.mapper;


import com.serby.employeeservice.dto.EmployeeDto;
import com.serby.employeeservice.entity.Employee;

public class EmployeeMapper {

    public static Employee mapEmployeeDtoToEntity(EmployeeDto employee) {
        return Employee.builder()
                .email(employee.getEmail())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .id(employee.getId())
                .departmentCode(employee.getDepartmentCode())
                .build();
    }

    public static EmployeeDto mapEmployeeEntityToDto(Employee employee) {
        return EmployeeDto.builder()
                .email(employee.getEmail())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .id(employee.getId())
                .departmentCode(employee.getDepartmentCode())
                .build();
    }
}
