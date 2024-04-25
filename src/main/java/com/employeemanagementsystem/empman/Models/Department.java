package com.employeemanagementsystem.empman.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name="Departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int departmentId ;

    private String departmentName ;



    @OneToMany(mappedBy = "department")
    private List<Employee> employeeList =  new ArrayList<>();

    @ManyToOne
    private Company company;





}
