package com.serby.departmentservice.mapper;

import com.serby.departmentservice.dto.DepartmentDto;
import com.serby.departmentservice.entity.Department;

public class DepartmentMapper {

    public static Department mapDepartmentDtoToEntity(DepartmentDto departmentDto) {
        return Department.builder()
                .departmentDescription(departmentDto.getDepartmentDescription())
                .departmentCode(departmentDto.getDepartmentCode())
                .departmentName(departmentDto.getDepartmentName())
                .id(departmentDto.getId())
                .build();
    }

    public static DepartmentDto mapDepartmenEntityToDto(Department department) {
        return DepartmentDto.builder()
                .departmentDescription(department.getDepartmentDescription())
                .departmentCode(department.getDepartmentCode())
                .departmentName(department.getDepartmentName())
                .id(department.getId())
                .build();
    }
}
