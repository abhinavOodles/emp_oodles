package com.employeemanagementsystem.empman.Service;

import com.employeemanagementsystem.empman.Dtos.empDto;
import com.employeemanagementsystem.empman.Enums.Gender;
import com.employeemanagementsystem.empman.Exception.EmployeeDoesNotExist;
import com.employeemanagementsystem.empman.Models.Employee;
import com.employeemanagementsystem.empman.Repository.EmployeeRepo;
import com.employeemanagementsystem.empman.Transformers.EmployeeTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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


    public String changeName(int employeeId , String name) throws EmployeeDoesNotExist {
        Optional<Employee> optionalEmployee = employeeRepo.findById(employeeId) ;

        if (optionalEmployee.isEmpty()){
            throw new EmployeeDoesNotExist("The given Id of Employee is not correct") ;
        }

         else {
            Employee employee = optionalEmployee.get();
            employee.setEmployeeName(name);

            employeeRepo.save(employee);

            return "Name of the employee with employee Id" + employeeId +"successfully changed" ;
        }
    }

    public String deleteEmployee(int empId) throws EmployeeDoesNotExist{

        Optional<Employee> employee = employeeRepo.findById(empId) ;
        if (employee.isEmpty()){
            throw new EmployeeDoesNotExist("Employee with empID "+empId+" does not exist") ;
        }
        else{
            employeeRepo.deleteById(empId);
            return "Employee with empID " + empId + "removed successfully" ;
        }

    }

    public String deleteAllEmployee() {
         employeeRepo.deleteAll();
         return "All Employee Deleted Successfully" ;
    }

    public List<Employee> getListOfEmpAccToGender(Gender gender) {

        List<Employee> employeeList = employeeRepo.findAll();
        List<Employee> list = new ArrayList<>();

        for (int i = 0 ; i<employeeList.size() ; i++){
            Employee employee = employeeList.get(i);
            if(employee.getGender()==gender){
                list.add(employee);
            }
        }
        return list ;
    }

    public List<Employee> getAllEmployee() {
        return employeeRepo.findAll();
    }
}
