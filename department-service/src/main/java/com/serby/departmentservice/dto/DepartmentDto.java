package com.serby.departmentservice.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
