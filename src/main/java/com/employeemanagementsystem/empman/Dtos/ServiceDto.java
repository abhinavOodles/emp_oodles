package com.employeemanagementsystem.empman.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceDto {

    private int companyId ;
    private String service ;
    private int payRateAsPerServices ;
}
