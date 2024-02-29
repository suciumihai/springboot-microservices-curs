package com.serby.employeeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DepartmentDto {

    private Long id;

    private String departmentName;

    private String departmentDescription;

    private String departmentCode;
}
