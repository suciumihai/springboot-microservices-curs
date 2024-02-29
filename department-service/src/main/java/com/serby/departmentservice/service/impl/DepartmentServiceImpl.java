package com.serby.departmentservice.service.impl;

import com.serby.departmentservice.dto.DepartmentDto;
import com.serby.departmentservice.entity.Department;
import com.serby.departmentservice.mapper.DepartmentMapper;
import com.serby.departmentservice.repository.DepartmentRepository;
import com.serby.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;
    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Department department = DepartmentMapper.mapDepartmentDtoToEntity(departmentDto);
        return DepartmentMapper.mapDepartmenEntityToDto(departmentRepository.save(department));
    }

    @Override
    public DepartmentDto getDepartmentByCode(String code) {
        Department department = departmentRepository.findDepartmentByDepartmentCode(code);
        return DepartmentMapper.mapDepartmenEntityToDto(department);
    }
}
