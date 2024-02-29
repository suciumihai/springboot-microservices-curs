package com.serby.employeeservice.service.impl;

import com.serby.employeeservice.dto.ApiResponseDto;
import com.serby.employeeservice.dto.DepartmentDto;
import com.serby.employeeservice.dto.EmployeeDto;
import com.serby.employeeservice.entity.Employee;
import com.serby.employeeservice.mapper.EmployeeMapper;
import com.serby.employeeservice.repository.EmployeeRepository;
import com.serby.employeeservice.service.ApiClient;
import com.serby.employeeservice.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    private RestTemplate restTemplate;

    private WebClient webClient;

    private ApiClient feignClient;

    private static final Logger mylogger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee savedEmployee = employeeRepository.save(EmployeeMapper.mapEmployeeDtoToEntity(employeeDto));
        return EmployeeMapper.mapEmployeeEntityToDto(savedEmployee);
    }

    //deprecated
    @Override
    public ApiResponseDto getEmployeeByIdRestTemplate(Long id) {

        Employee employee = employeeRepository.findById(id).get();
        ResponseEntity<DepartmentDto> responseEntity = restTemplate
                .getForEntity("http://localhost:8080/api/departments/" + employee.getDepartmentCode(), DepartmentDto.class);
        DepartmentDto departmentDto = responseEntity.getBody();

        return ApiResponseDto.builder()
                .departmentDto(departmentDto)
                .employeeDto(EmployeeMapper.mapEmployeeEntityToDto(employee))
                .build();
    }

    @Override
    public ApiResponseDto getEmployeeByIdWebClient(Long id) {

        Employee employee = employeeRepository.findById(id).get();

        DepartmentDto departmentDto = webClient.get()
                .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();

        return ApiResponseDto.builder()
                .departmentDto(departmentDto)
                .employeeDto(EmployeeMapper.mapEmployeeEntityToDto(employee))
                .build();
    }

    @Override
    //@CircuitBreaker(name="${spring.application.name}", fallbackMethod = "getEmployeeByIdWithDefaultDepartment")
    //circuti breaker era doar pt default method. retyr e ma jemker
    @Retry(name="${spring.application.name}", fallbackMethod = "getEmployeeByIdWithDefaultDepartment")
    public ApiResponseDto getEmployeeById(Long id) {

        mylogger.info("inside getEmpById method");
        Employee employee = employeeRepository.findById(id).get();

        DepartmentDto departmentDto = feignClient.getDepartmentByCode(employee.getDepartmentCode());

        return ApiResponseDto.builder()
                .departmentDto(departmentDto)
                .employeeDto(EmployeeMapper.mapEmployeeEntityToDto(employee))
                .build();
    }

    public ApiResponseDto getEmployeeByIdWithDefaultDepartment(Long id, Exception exception) {

        mylogger.info("inside default method");
        Employee employee = employeeRepository.findById(id).get();

        DepartmentDto departmentDto = DepartmentDto.builder()
                .departmentCode("default")
                .departmentDescription("default")
                .departmentName("default")
                .build();

        return ApiResponseDto.builder()
                .departmentDto(departmentDto)
                .employeeDto(EmployeeMapper.mapEmployeeEntityToDto(employee))
                .build();
    }
}
