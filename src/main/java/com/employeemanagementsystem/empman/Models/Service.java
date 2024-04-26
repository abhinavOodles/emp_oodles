package com.employeemanagementsystem.empman.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "Services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int serviceId ;

    private int  companyId;

    private String service  ;

    private int payRateAsPerServices ;


    @ManyToMany
    private  List<Company> company ;
}
