package com.serby.employeeservice.service;

import com.serby.employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8080", value="DEPARTMENT-SERVICE") //ASTA ERA PT 1 SINGUR SRV
//@FeignClient(name="DEPARTMENT-SERVICE") //serviceID din eureka srv registry/ load balancer si gata
//asa merge doar prin api gateway. daca vrei serviciile intre ele, mai bine cel de sus
public interface ApiClient {

    //literalmente copiata din ctorler dept service
    @GetMapping("api/departments/{code}")
    public DepartmentDto getDepartmentByCode(@PathVariable("code") String departmentCode);
}
