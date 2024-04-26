package com.employeemanagementsystem.empman.Repository;

import com.employeemanagementsystem.empman.Models.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepo extends JpaRepository<Service,Integer> {
}
