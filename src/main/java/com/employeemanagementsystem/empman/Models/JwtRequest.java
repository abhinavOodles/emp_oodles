package com.employeemanagementsystem.empman.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class JwtRequest {

    private String  username ;
    private String password ;
}
