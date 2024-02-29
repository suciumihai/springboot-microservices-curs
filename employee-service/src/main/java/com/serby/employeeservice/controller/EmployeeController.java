package com.serby.employeeservice.controller;

import com.serby.employeeservice.dto.ApiResponseDto;
import com.serby.employeeservice.dto.EmployeeDto;
import com.serby.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {

        EmployeeDto created = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponseDto> getEmployee(@PathVariable Long id) {

        ApiResponseDto found = employeeService.getEmployeeById(id);

        return new ResponseEntity<>(found, HttpStatus.OK);
    }
}
