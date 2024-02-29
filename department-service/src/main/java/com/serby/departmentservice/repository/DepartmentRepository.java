package com.serby.departmentservice.repository;

import com.serby.departmentservice.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findDepartmentByDepartmentCode(String departmentCode);
}
