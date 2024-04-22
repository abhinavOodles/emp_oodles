package com.employeemanagementsystem.empman.Service;

import com.employeemanagementsystem.empman.Dtos.DepartmentEntryDto;
import com.employeemanagementsystem.empman.Exception.DepartmentAlreadyPresent;
import com.employeemanagementsystem.empman.Exception.DepartmentDoesNotExist;
import com.employeemanagementsystem.empman.Models.Department;
import com.employeemanagementsystem.empman.Models.Employee;
import com.employeemanagementsystem.empman.Repository.DepartmentRepo;
import com.employeemanagementsystem.empman.Repository.EmployeeRepo;
import com.employeemanagementsystem.empman.Transformers.DepartmentTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DepartmentService {
    @Autowired
    DepartmentRepo departmentRepo;
    @Autowired
    EmployeeRepo employeeRepo;

    public String addDepartment(DepartmentEntryDto departmentDto) throws DepartmentAlreadyPresent, DepartmentDoesNotExist {

        Optional<Department> optionalDepartment = departmentRepo.findById(departmentDto.getDepartmentId());

        if (optionalDepartment.isPresent()) {
            throw new DepartmentAlreadyPresent("Department is Already Present");
        }

        Department department = DepartmentTransformer.convertDtoTOEntity(departmentDto);
        departmentRepo.save(department);

        return "Department Added Successfully.";
    }

    public int getNumberOfEmployee(int departmentId) {

        List<Employee> employeeList = employeeRepo.findAll();

        int number = 0;

        for (int i = 0; i < employeeList.size(); i++) {
            Employee employee = employeeList.get(i);
            if (employee.getDepartmentId() == departmentId) {
                number++;
            }
        }

        return number;
    }

    public String changeTheNameOfDepartment(int departmentId, String newName) throws DepartmentDoesNotExist{
        Optional<Department> d = departmentRepo.findById(departmentId) ;

        if (d.isEmpty()){
            throw new DepartmentDoesNotExist("Department-Id is word") ;
        }

        else{
            Department department = d.get() ;
            department.setDepartmentName(newName);
            departmentRepo.save(department) ;
            return "Changed Successfully" ;
        }

    }
}
