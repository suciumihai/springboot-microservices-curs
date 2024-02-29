package com.serby.departmentservice.service;

import com.serby.departmentservice.dto.DepartmentDto;


public interface DepartmentService {

    public DepartmentDto saveDepartment(DepartmentDto departmentDto);

    DepartmentDto getDepartmentByCode(String code);
}
