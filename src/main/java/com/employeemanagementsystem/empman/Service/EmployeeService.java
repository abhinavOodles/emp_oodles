package com.employeemanagementsystem.empman.Service;

import com.employeemanagementsystem.empman.Dtos.empDto;
import com.employeemanagementsystem.empman.Models.Employee;
import com.employeemanagementsystem.empman.Repository.EmployeeRepo;
import com.employeemanagementsystem.empman.Transformers.EmployeeTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;



    public String addEmp(empDto empDto) {
      Employee emp = EmployeeTransformer.covertDtoToEntity(empDto) ;
      employeeRepo.save(emp) ;
      return "Employee added successfully" ;
    }

    public List<Employee> getListOfEmployeeInaSameDepartment(int departmentId) {

        List<Employee> employeeList = employeeRepo.findAll() ;
        List<Employee> employeeList1 = new ArrayList<>() ;

        for (int i = 0 ; i<employeeList.size() ; i++){
            Employee emp = employeeList.get(i) ;

            if (emp.getDepartmentId() == departmentId){
                employeeList1.add(emp) ;
            }

        }
        return employeeList1 ;
    }
}
