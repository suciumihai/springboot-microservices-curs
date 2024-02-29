package com.serby.employeeservice.service;

import com.serby.employeeservice.dto.ApiResponseDto;
import com.serby.employeeservice.dto.EmployeeDto;

public interface EmployeeService {

    public EmployeeDto saveEmployee(EmployeeDto employeeDto);

    public ApiResponseDto getEmployeeById(Long id);

    public ApiResponseDto getEmployeeByIdRestTemplate(Long id);

    public ApiResponseDto getEmployeeByIdWebClient(Long id);
}
