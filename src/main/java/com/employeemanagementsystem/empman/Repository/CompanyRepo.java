package com.employeemanagementsystem.empman.Repository;

import com.employeemanagementsystem.empman.Models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepo extends JpaRepository<Company,Integer> {
}
