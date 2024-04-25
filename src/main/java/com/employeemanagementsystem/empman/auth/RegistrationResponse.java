package com.employeemanagementsystem.empman.auth;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RegistrationResponse {

    private Integer HttpStatus;
    private String message;
}