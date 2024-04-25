package com.employeemanagementsystem.empman.Service;

import com.employeemanagementsystem.empman.Dtos.DepartmentEntryDto;
import com.employeemanagementsystem.empman.Exception.CompanyNotFound;
import com.employeemanagementsystem.empman.Exception.DepartmentAlreadyPresent;
import com.employeemanagementsystem.empman.Exception.DepartmentDoesNotExist;
import com.employeemanagementsystem.empman.Exception.EmployeeDoesNotExist;
import com.employeemanagementsystem.empman.Models.Department;
import com.employeemanagementsystem.empman.Models.Employee;
import com.employeemanagementsystem.empman.Repository.DepartmentRepo;
import com.employeemanagementsystem.empman.Repository.EmployeeRepo;
import com.employeemanagementsystem.empman.Transformers.DepartmentTransformer;
import org.apache.kafka.common.quota.ClientQuotaAlteration;
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

    public String addDepartment(DepartmentEntryDto departmentDto) throws DepartmentAlreadyPresent {

        Optional<Department> optionalDepartment = (departmentRepo.findByName(departmentDto.getDepartmentName()));

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

        System.out.print(number);

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

    public List<Department> listOfDepartments() {

        return departmentRepo.findAll() ;
    }

    public void deleteDepartments(int departmentId) throws DepartmentDoesNotExist {
        Optional<Department> optionalDepartment = departmentRepo.findById(departmentId) ;
        if(optionalDepartment.isEmpty()){
            throw new DepartmentDoesNotExist("Wrong Department-Id");
        }else{
            Department department = optionalDepartment.get() ;
            departmentRepo.delete(department);
        }

    }

    public void deleteAllDepartments() {
        departmentRepo.deleteAll();
    }

    public String changeTheDepartment(int employeeId, int departmentId, String departmentName) throws EmployeeDoesNotExist , DepartmentDoesNotExist {
        Optional<Employee> optionalEmployee = employeeRepo.findById(employeeId);
        Optional<Department> optionalDepartment = departmentRepo.findById(departmentId);

        if (optionalDepartment.isEmpty()){
            throw new DepartmentDoesNotExist("Department-Id is Wrong");
        }

        if (optionalEmployee.isEmpty()){
            throw new EmployeeDoesNotExist("Wrong Employee-Id");
        }
        else {
            Employee employee = optionalEmployee.get();
            employee.setDepartmentId(departmentId);
            employee.setDepartmentName(departmentName);

            employeeRepo.save(employee);
            return"Department Of Employee with "+employeeId+" Changed Successfully" ;
        }
    }

    public String setTheSalaryOfEmployee(int employeeId , int salary) throws EmployeeDoesNotExist{
        Optional<Employee> optionalEmployee = employeeRepo.findById(employeeId);

        if(optionalEmployee.isEmpty()){
            throw new EmployeeDoesNotExist("Employee-Id Is Not Correct") ;
        }
        else{
            Employee employee =optionalEmployee.get() ;
            employee.setSalary(salary);
            employeeRepo.save(employee);
            return "Salary Of Employee with Id: "+employeeId+" Saved Successfully"  ;
        }
    }
}
